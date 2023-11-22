package likelion.togethermarket.domain.shop.controller;

import likelion.togethermarket.domain.shop.dto.ShopRegisterDto;
import likelion.togethermarket.domain.shop.service.ShopService;
import likelion.togethermarket.global.jwt.CustomUserDetails;
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

}
