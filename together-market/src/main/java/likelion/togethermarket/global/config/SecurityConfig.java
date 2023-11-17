package likelion.togethermarket.global.config;

import likelion.togethermarket.global.jwt.JwtAccessDeniedHandler;
import likelion.togethermarket.global.jwt.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtSecurityConfig jwtSecurityConfig;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable() // CSRF 비활성화
                // JWT니까 세션 사용 안함
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // httpBasic, formLogin도 사용 안함
                .httpBasic().disable()
                .formLogin().disable()

                //exception handler 설정
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);

        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll();

        httpSecurity.apply(jwtSecurityConfig);

        return httpSecurity.build();
    }
    /* 스프링 시큐리티 6.1 부터 deprecated 된 표현들이 많음, 이후 수정해야 할듯 */

}
