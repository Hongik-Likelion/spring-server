package likelion.togethermarket.domain.member.controller;

import likelion.togethermarket.domain.member.service.MemberService;
import likelion.togethermarket.global.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.attribute.UserPrincipal;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class MemberController {
    private final MemberService memberService;

    //info ,modify, block

    public ResponseEntity<?> getMyInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        Long memberId = customUserDetails.getMember().getId();
        return memberService.getMemberInfo(memberId);
    }
}
