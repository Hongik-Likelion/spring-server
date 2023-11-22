package likelion.togethermarket.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {
    private String email;

    @Builder
    public LoginDto(String email) {
        this.email = email;
    }
}
