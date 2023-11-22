package likelion.togethermarket.domain.market.controller;

import likelion.togethermarket.domain.market.dto.WishMarketReqDto;
import likelion.togethermarket.domain.market.service.MarketService;
import likelion.togethermarket.global.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/favourite")
    public ResponseEntity<?> registerFavouriteMarkets(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody WishMarketReqDto reqDto
    ){
        Long memberId = customUserDetails.getMember().getId();
        return marketService.registerWishMarket(memberId, reqDto);
    }
}
