package likelion.togethermarket.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReissueDto {
    private String refreshToken;

    @Builder
    public ReissueDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
