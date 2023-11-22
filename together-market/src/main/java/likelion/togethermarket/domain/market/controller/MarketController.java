package likelion.togethermarket.domain.market.controller;

import likelion.togethermarket.domain.market.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("markets")
public class MarketController {
    private final MarketService marketService;

    @GetMapping("")
    public ResponseEntity<?> getAllMarkets(){
        return marketService.getMarkets();
    }
}
