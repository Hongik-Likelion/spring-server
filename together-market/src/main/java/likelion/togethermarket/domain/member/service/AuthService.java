package likelion.togethermarket.domain.member.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import likelion.togethermarket.domain.member.dto.LoginDto;
import likelion.togethermarket.domain.member.dto.ReissueDto;
import likelion.togethermarket.domain.member.dto.SignupDto;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.member.entity.MemberRole;
import likelion.togethermarket.domain.member.repository.MemberRepository;
import likelion.togethermarket.global.jwt.JwtTokenDto;
import likelion.togethermarket.global.jwt.JwtTokenProvider;
import likelion.togethermarket.global.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider tokenProvider;
    private final RedisService redisService;

    public ResponseEntity<?> signupMember(SignupDto signupDto) {
        Optional<Member> byEmail = memberRepository.findByEmail(signupDto.getEmail());
        if (byEmail.isPresent()){
            System.out.println("이미 가입된 사용자입니다.");
            return ResponseEntity.badRequest().build();
        }

        Member member = Member.builder()
                .email(signupDto.getEmail())
                .profile(signupDto.getProfile())
                .nickName(signupDto.getNickname())
                .memberRole((signupDto.is_owner()) ? MemberRole.OWNER : MemberRole.CUSTOMER)
                .build();

        memberRepository.save(member);
        JwtTokenDto jwtTokenDto = tokenProvider.generateToken(member);

        // refreshToken 을 redis를 이용해 저장
        redisService.setValue(jwtTokenDto.getRefreshToken(), member.getId().toString(), 1000 * 60 * 60 * 24 * 7L);
        return new ResponseEntity<JwtTokenDto>(jwtTokenDto, HttpStatus.CREATED);
    }


    public ResponseEntity<?> loginMember(LoginDto loginDto) {
        Member member = memberRepository.findByEmail(loginDto.getEmail()).orElseThrow();

        JwtTokenDto jwtTokenDto = tokenProvider.generateToken(member);

        // refreshToken 을 redis를 이용해 저장
        redisService.setValue(jwtTokenDto.getRefreshToken(), member.getId().toString(), 1000 * 60 * 60 * 24 * 7L);
        return new ResponseEntity<JwtTokenDto>(jwtTokenDto, HttpStatus.ACCEPTED);
    }


    public ResponseEntity<?> reissueToken(ReissueDto reissueDto) {
        String refreshToken = reissueDto.getRefreshToken();

        // refreshToken 이 Deprecated거나 유효하지 않으면 badRequest 반환
        if (redisService.getValue(refreshToken) == "Deprecated" || !tokenProvider.validateToken(refreshToken)){
            return ResponseEntity.badRequest().build();
        }

        Long memberId = Long.valueOf(redisService.getValue(refreshToken));
        Member member = memberRepository.findById(memberId).orElseThrow();

        // 원래 있던 refreshToken 은 Deprecated로 설정, 새로 발급된 refreshToken도 저장
        JwtTokenDto jwtTokenDto = tokenProvider.generateToken(member);
        redisService.setValue(refreshToken, "Deprecated", 1000 * 60 * 60 * 24 * 7L);
        redisService.setValue(jwtTokenDto.getRefreshToken(), memberId.toString(), 1000 * 60 * 60 * 24 * 7L);

        return new ResponseEntity<JwtTokenDto>(jwtTokenDto, HttpStatus.ACCEPTED);
    }
}
