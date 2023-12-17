package likelion.togethermarket.global.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenProvider {

    private final String key;
    private final MemberRepository memberRepository;

    private Long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30L; //30분
    private Long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7L; //7일

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, MemberRepository memberRepository) {
        this.key = secretKey;
        this.memberRepository = memberRepository;
    }


    /*멤버 정보를 이용해 토큰을 생성함
    * 액세스 토큰은 이메일, id 가지고 있고
    * 리프레쉬 토큰은 만료기한만 설정
    * */
    public JwtTokenDto generateToken(Member member){
        Date date = new Date();

        String accessToken = JWT.create()
                .withSubject(member.getEmail())
                .withClaim("id", member.getId())
                .withExpiresAt(new Date(date.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
                .sign(Algorithm.HMAC256(key));

        String refreshToken = JWT.create()
                .withClaim("id", member.getId())
                .withExpiresAt(new Date(date.getTime() + REFRESH_TOKEN_EXPIRE_TIME))
                .sign(Algorithm.HMAC256(key));

        return JwtTokenDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    //Authentication 생성, accessToken 이용함
    public Authentication getAuthentication(String token){
        Long memberId = JWT.require(Algorithm.HMAC256(key)).build().verify(token).getClaim("id").asLong();

        Member member = memberRepository.findById(memberId).orElseThrow();// 추후 적절한 커스텀 에러 삽입
        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        return new UsernamePasswordAuthenticationToken(customUserDetails, "", customUserDetails.getAuthorities());
    }

    // verify가 제대로 되면 true, 아니라면 JWTVerificationException 오류를 던짐
    // 오류를 더 세분화해야함
    public boolean validateToken(String token){
        try {
            JWT.require(Algorithm.HMAC256(key)).build().verify(token);
            return true;
        } catch (JWTVerificationException e){
            throw e;
        }
    }

    public Long getMemberId(String token){
        return JWT.require(Algorithm.HMAC256(key)).build().verify(token).getClaim("id").asLong();
    }
}
