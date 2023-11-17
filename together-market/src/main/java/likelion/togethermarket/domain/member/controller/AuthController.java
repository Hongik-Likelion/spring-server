package likelion.togethermarket.domain.member.controller;

import likelion.togethermarket.domain.member.dto.SignupDto;
import likelion.togethermarket.domain.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class AuthController {

    private final AuthService authService;

    //login, reissue

    //signup
    public ResponseEntity<?> signup(@RequestBody SignupDto signupDto){
        return authService.signupMember(signupDto);
    }

    //login
    public ResponseEntity<?> login(@RequestParam String email){
        return authService.loginMember(email);
    }

}
