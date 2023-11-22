package likelion.togethermarket.domain.member.controller;

import likelion.togethermarket.domain.member.dto.request.LoginDto;
import likelion.togethermarket.domain.member.dto.request.ReissueDto;
import likelion.togethermarket.domain.member.dto.request.SignupDto;
import likelion.togethermarket.domain.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class AuthController {

    private final AuthService authService;

    //login, reissue

    //signup
    @PostMapping("")
    public ResponseEntity<?> signup(@RequestBody SignupDto signupDto){
        return authService.signupMember(signupDto);
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        return authService.loginMember(loginDto);
    }

    // reissue
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody ReissueDto reissueDto){
        return authService.reissueToken(reissueDto);
    }

}
