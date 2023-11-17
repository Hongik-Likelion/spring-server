package likelion.togethermarket.domain.member.service;

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
@Transactional(readOnly = true)
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

}
