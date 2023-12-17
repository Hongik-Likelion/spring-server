package likelion.togethermarket.domain.member.controller;

import likelion.togethermarket.domain.member.dto.request.CustomerModifyReqDto;
import likelion.togethermarket.domain.member.dto.request.ModifyReq;
import likelion.togethermarket.domain.member.dto.request.OwnerModifyReqDto;
import likelion.togethermarket.domain.member.service.MemberService;
import likelion.togethermarket.global.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/modify")
    public ResponseEntity<?> modifyMyInfo(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody ModifyReq modifyRequestDto
    ){
        Long memberId = customUserDetails.getMember().getId();
        return memberService.modifyInfo(memberId, modifyRequestDto);
    }

    @PatchMapping("/{user_id}/block")
    public ResponseEntity<?> blockOtherUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("user_id") Long blockMemberId
    ){
        Long memberId = customUserDetails.getMember().getId();
        return memberService.blockUser(memberId, blockMemberId);
    }

}
