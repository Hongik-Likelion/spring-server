package likelion.togethermarket.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class SignupDto {
    private String email;
    private String nickname;
    private String profile;
    private boolean is_owner; // python 에서 하던 프로젝트라 변수명이 흠...
}
