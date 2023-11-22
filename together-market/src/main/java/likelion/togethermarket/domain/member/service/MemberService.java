package likelion.togethermarket.domain.member.service;

import likelion.togethermarket.domain.market.dto.BasicMarketDto;
import likelion.togethermarket.domain.market.entity.Market;
import likelion.togethermarket.domain.market.entity.WishMarket;
import likelion.togethermarket.domain.market.repository.MarketRepository;
import likelion.togethermarket.domain.market.repository.WishMarketRepository;
import likelion.togethermarket.domain.member.dto.request.CustomerModifyReqDto;
import likelion.togethermarket.domain.member.dto.request.OwnerModifyReqDto;
import likelion.togethermarket.domain.member.dto.response.*;
import likelion.togethermarket.domain.member.entity.BlackList;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.member.entity.MemberRole;
import likelion.togethermarket.domain.member.repository.BlackListRepository;
import likelion.togethermarket.domain.member.repository.MemberRepository;
import likelion.togethermarket.domain.shop.entity.Shop;
import likelion.togethermarket.domain.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final WishMarketRepository wishMarketRepository;
    private final MarketRepository marketRepository;
    private final ShopRepository shopRepository;
    private final BlackListRepository blackListRepository;

    public ResponseEntity<?> getMemberInfo(Long memberId) {

        // 멤버를 받은 memberId 로 찾음
        Member member = memberRepository.findById(memberId).orElseThrow();


        if (member.getMemberRole() == MemberRole.CUSTOMER){ // 찾은 멤버가 소비자일때
            List<WishMarket> allByMemberId = wishMarketRepository.findAllByMember_Id(member.getId());
            List<Market> markets = allByMemberId.stream().map(WishMarket::getMarket).toList();
            List<BasicMarketDto> marketDtos = markets.stream()
                    .map(BasicMarketDto::new).toList();

            CustomerInfoResDto resDto = CustomerInfoResDto.builder()
                    .id(memberId.intValue())
                    .email(member.getEmail())
                    .profile(member.getProfile())
                    .introduction(member.getIntroduction())
                    .nickname(member.getNickName())
                    .is_owner(false)
                    .favourite_markets(marketDtos)
                    .build();

            return new ResponseEntity<CustomerInfoResDto>(resDto, HttpStatusCode.valueOf(200));

        } else if (member.getMemberRole() == MemberRole.OWNER) { // 가게 사장일 때
            Shop shop = shopRepository.findByMember(member).orElseThrow();  // 사장이 운영하는 가게 찾음
            OwnerMarketDto ownerMarketDto = new OwnerMarketDto(shop);

            OwnerInfoResDto ownerInfoResDto = OwnerInfoResDto.builder()
                    .id(memberId.intValue())
                    .email(member.getEmail())
                    .profile(member.getProfile())
                    .nickname(member.getNickName())
                    .is_owner(true)
                    .introduction(member.getIntroduction())
                    .market(ownerMarketDto)
                    .build();

            return new ResponseEntity<OwnerInfoResDto>(ownerInfoResDto, HttpStatusCode.valueOf(200));
        }

        return new ResponseEntity<>(HttpStatusCode.valueOf(500));
    }

    @Transactional
    public ResponseEntity<?> modifyCustomerInfo(Long memberId, CustomerModifyReqDto modifyRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        wishMarketRepository.deleteAllByMember(member);  // 선호 가게 수정 (전부 없애고, 다시 다 만들어줌)
        for (Integer i : modifyRequestDto.getFavourite_markets()) {
            Market market = marketRepository.findById(Long.valueOf(i)).orElseThrow();
            WishMarket wish = WishMarket.builder().market(market).member(member).build();
            wishMarketRepository.save(wish);
        }
        member.modifyNickname(modifyRequestDto.getNickname());
        member.modifyIntro(modifyRequestDto.getIntroduction());

        ModifyResDto modifyResDto = ModifyResDto.builder()
                .nickname(member.getNickName())
                .email(member.getEmail())
                .introduction(member.getIntroduction())
                .profile(member.getProfile())
                .is_owner(false).build();

        return new ResponseEntity<ModifyResDto>(modifyResDto, HttpStatusCode.valueOf(200));
    }

    @Transactional
    public ResponseEntity<?> modifyOwnerInfo(Long memberId, OwnerModifyReqDto modifyRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        Shop shop = shopRepository.findByMember(member).orElseThrow();
        // 사장의 닉네임, 소개 + 가게의 이름, open, close 시간 수정
        member.modifyNickname(modifyRequestDto.getShop_name());
        member.modifyIntro(modifyRequestDto.getIntroduction());
        shop.modifyShopInfo(modifyRequestDto.getShop_name(),
                modifyRequestDto.getOpening_time(),
                modifyRequestDto.getClosing_time());

        ModifyResDto modifyResDto = ModifyResDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickName())
                .profile(member.getProfile())
                .introduction(member.getIntroduction())
                .is_owner(true).build();

        return new ResponseEntity<ModifyResDto>(modifyResDto, HttpStatusCode.valueOf(200));
    }

    @Transactional
    public ResponseEntity<?> blockUser(Long memberId, Long blockMemberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Member blockingMember = memberRepository.findById(blockMemberId).orElseThrow();

        BlackList blackList = BlackList.builder().member(member).blockedUserId(blockMemberId).build();
        blackListRepository.save(blackList);

        BlackResDto resDto = BlackResDto.builder().id(Math.toIntExact(memberId))
                .email(member.getEmail())
                .nickname(member.getNickName())
                .profile(member.getProfile())
                .introduction(member.getIntroduction())
                .is_owner(member.getMemberRole() != MemberRole.CUSTOMER).build();

        return new ResponseEntity<BlackResDto>(resDto, HttpStatusCode.valueOf(200));
    }
}
