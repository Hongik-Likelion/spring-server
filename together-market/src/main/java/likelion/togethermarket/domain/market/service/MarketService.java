package likelion.togethermarket.domain.market.service;

import likelion.togethermarket.domain.market.dto.AllMarketDto;
import likelion.togethermarket.domain.market.dto.BasicMarketDto;
import likelion.togethermarket.domain.market.entity.Market;
import likelion.togethermarket.domain.market.repository.MarketRepository;
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

    public ResponseEntity<?> getMarkets() {
        List<Market> markets = marketRepository.findAll();
        List<BasicMarketDto> marketDtos = markets.stream().map(BasicMarketDto::new).toList();

        return new ResponseEntity<List<BasicMarketDto>>(marketDtos, HttpStatusCode.valueOf(200));
    }

}
