package likelion.togethermarket.domain.member.service;

import likelion.togethermarket.domain.market.dto.BasicMarketDto;
import likelion.togethermarket.domain.market.entity.Market;
import likelion.togethermarket.domain.market.entity.WishMarket;
import likelion.togethermarket.domain.market.repository.MarketRepository;
import likelion.togethermarket.domain.market.repository.WishMarketRepository;
import likelion.togethermarket.domain.member.dto.CustomerInfoResDto;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.member.entity.MemberRole;
import likelion.togethermarket.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final WishMarketRepository wishMarketRepository;
    private final MarketRepository marketRepository;

    public ResponseEntity<?> getMemberInfo(Long memberId) {

        // 멤버를 받은 memberId 로 찾음
        Member member = memberRepository.findById(memberId).orElseThrow();


        if (member.getMemberRole() == MemberRole.CUSTOMER){ // 찾은 멤버가 소비자일때
            List<WishMarket> allByMemberId = wishMarketRepository.findAllByMember_Id(member.getId());
            List<Market> markets = allByMemberId.stream().map(WishMarket::getMarket).toList();
            List<BasicMarketDto> marketDtos = markets.stream().map(BasicMarketDto::new).toList();

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

//        } else if (member.getMemberRole() == MemberRole.OWNER) { // 가게 사장일 때
//            List<>


        }
    }


}
