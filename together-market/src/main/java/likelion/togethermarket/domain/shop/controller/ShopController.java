package likelion.togethermarket.domain.shop.controller;

import likelion.togethermarket.domain.shop.dto.ShopRegisterDto;
import likelion.togethermarket.domain.shop.service.ShopService;
import likelion.togethermarket.global.jwt.CustomUserDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("shop")
public class ShopController {
    private final ShopService shopService;


    // 새로운 가게 등록
    @PostMapping("")
    public ResponseEntity<?> registerNewShop(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody ShopRegisterDto reqDto
            ){
        Long memberId = customUserDetails.getMember().getId();
        return shopService.registerShop(memberId, reqDto);
    }

    // 가게 즐겨찾기
    @PatchMapping("/{shop_id}/favourite")
    public ResponseEntity<?> registerNewWishShop(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("shop_id") Long shopId
    ){
        Long memberId = customUserDetails.getMember().getId();
        return shopService.registerWishShop(memberId, shopId);
    }

    // 가게 즐겨찾기 삭제
    @PatchMapping("/{shop_id}/de-favourite")
    public ResponseEntity<?> deleteWishShop(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("shop_id") Long shopId
    ){
        Long memberId = customUserDetails.getMember().getId();
        return shopService.deleteWishShop(memberId, shopId);
    }

    // 시장에 존재하는 모든 가게 조회
    @GetMapping("")
    public ResponseEntity<?> searchAllShopInMarket(
            @RequestParam("market_id") Long market_id
    ){
        return shopService.showAllShop(market_id);
    }

    // 상점 조회
    @GetMapping("/{shop_id}")
    public ResponseEntity<?> searchShopDetail(
            @PathVariable("shop_id") Long shopId
    ){
        return shopService.searchDetail(shopId);
    }

    // 나의 관심가게 조회
    @GetMapping("")
    public ResponseEntity<?> showMyFavouriteShops(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        Long memberId = customUserDetails.getMember().getId();
        return shopService.searchWishShop(memberId);
    }

}
