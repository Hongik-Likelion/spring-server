package likelion.togethermarket.domain.shop.controller;

import likelion.togethermarket.domain.shop.dto.ShopRegisterDto;
import likelion.togethermarket.domain.shop.service.ShopService;
import likelion.togethermarket.global.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("shop")
public class ShopController {
    private final ShopService shopService;

    @PostMapping("")
    public ResponseEntity<?> registerNewShop(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody ShopRegisterDto reqDto
            ){
        Long memberId = customUserDetails.getMember().getId();
        return shopService.registerShop(memberId, reqDto);
    }
}
