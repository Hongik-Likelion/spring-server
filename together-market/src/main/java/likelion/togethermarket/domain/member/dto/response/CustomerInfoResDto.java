package likelion.togethermarket.domain.member.dto.response;

import likelion.togethermarket.domain.market.dto.BasicMarketDto;
import likelion.togethermarket.domain.market.entity.Market;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CustomerInfoResDto {
    private int id;
    private String email;
    private String nickname;
    private String introduction;
    private String profile;
    private boolean is_owner;

    private List<BasicMarketDto> favourite_markets;

    @Builder
    public CustomerInfoResDto(int id, String email, String nickname, String introduction, String profile, boolean is_owner, List<BasicMarketDto> favourite_markets) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.introduction = introduction;
        this.profile = profile;
        this.is_owner = is_owner;
        this.favourite_markets = favourite_markets;
    }
}
