package likelion.togethermarket.domain.member.controller;

import likelion.togethermarket.domain.member.dto.request.CustomerModifyReqDto;
import likelion.togethermarket.domain.member.dto.request.OwnerModifyReqDto;
import likelion.togethermarket.domain.member.service.MemberService;
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
@RequestMapping("user")
public class MemberController {
    private final MemberService memberService;

    //info ,modify, block

    @GetMapping("/info")
    public ResponseEntity<?> getMyInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        Long memberId = customUserDetails.getMember().getId();
        return memberService.getMemberInfo(memberId);
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyCustomerMyInfo(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody CustomerModifyReqDto modifyRequestDto
    ){
        Long memberId = customUserDetails.getMember().getId();
        return memberService.modifyCustomerInfo(memberId, modifyRequestDto);
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyOwnerMyInfo(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody OwnerModifyReqDto modifyRequestDto
    ){
        Long memberId = customUserDetails.getMember().getId();
        return memberService.modifyOwnerInfo(memberId, modifyRequestDto);
    }
}
