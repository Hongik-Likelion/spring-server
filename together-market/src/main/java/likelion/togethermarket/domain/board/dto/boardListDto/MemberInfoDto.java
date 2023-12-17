package likelion.togethermarket.domain.board.dto.boardListDto;

import likelion.togethermarket.domain.board.entity.Board;
import likelion.togethermarket.domain.member.entity.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberInfoDto {
    private int user_id;
    private boolean is_owner;
    private String nickname;
    private String profile;

    @Builder
    public MemberInfoDto(Board board) {
        this.user_id = board.getMember().getId().intValue();
        this.is_owner = board.getMember().getMemberRole().equals(MemberRole.OWNER);
        this.nickname = board.getMember().getNickName();
        this.profile = board.getMember().getProfile();
    }

    public boolean getIs_owner(){
        return is_owner;
    }
}
