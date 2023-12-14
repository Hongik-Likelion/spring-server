package likelion.togethermarket.domain.board.dto;

import likelion.togethermarket.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUserDto {
    private String nickname;

    @Builder
    public ReviewUserDto(Member member) {
        this.nickname = member.getNickName();
    }
}
