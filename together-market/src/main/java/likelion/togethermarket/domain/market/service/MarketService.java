package likelion.togethermarket.domain.market.service;

import likelion.togethermarket.domain.market.dto.AllMarketDto;
import likelion.togethermarket.domain.market.dto.BasicMarketDto;
import likelion.togethermarket.domain.market.dto.WishMarketReqDto;
import likelion.togethermarket.domain.market.entity.Market;
import likelion.togethermarket.domain.market.entity.WishMarket;
import likelion.togethermarket.domain.market.repository.MarketRepository;
import likelion.togethermarket.domain.market.repository.WishMarketRepository;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepository marketRepository;
    private final MemberRepository memberRepository;
    private final WishMarketRepository wishMarketRepository;

    public ResponseEntity<?> getMarkets() {
        List<Market> markets = marketRepository.findAll();
        List<BasicMarketDto> marketDtos = markets.stream().map(BasicMarketDto::new).toList();

        return new ResponseEntity<List<BasicMarketDto>>(marketDtos, HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<?> registerWishMarket(Long memberId, WishMarketReqDto reqDto) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        for (Integer market_id : reqDto.getMarket_id()){
            Market market = marketRepository.findById(Long.valueOf(market_id)).orElseThrow();
            wishMarketRepository.save(WishMarket.builder().market(market).member(member).build());
        }

        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }
}
