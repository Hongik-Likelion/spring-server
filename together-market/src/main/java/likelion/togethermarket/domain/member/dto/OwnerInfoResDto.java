package likelion.togethermarket.domain.member.dto;

import likelion.togethermarket.domain.market.entity.Market;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerInfoResDto {
    private int id;
    private String email;
    private String nickname;
    private String introduction;
    private String profile;
    private boolean is_owner;

    private OwnerMarketDto market;

    @Builder
    public OwnerInfoResDto(int id, String email, String nickname, String introduction, String profile, boolean is_owner, OwnerMarketDto market) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.introduction = introduction;
        this.profile = profile;
        this.is_owner = is_owner;
        this.market = market;
    }
}
