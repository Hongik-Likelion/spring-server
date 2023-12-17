package likelion.togethermarket.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyResDto {
    private String email;
    private String nickname;
    private String profile;
    private String introduction;
    private boolean is_owner;

    @Builder
    public ModifyResDto(String email, String nickname, String profile, String introduction, boolean is_owner) {
        this.email = email;
        this.nickname = nickname;
        this.profile = profile;
        this.introduction = introduction;
        this.is_owner = is_owner;
    }

    public boolean getIs_owner(){
        return is_owner;
    }
}
