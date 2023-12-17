package likelion.togethermarket.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CustomerModifyReqDto {
    private String nickname;
    private List<Integer> favourite_markets;
    private String introduction;

    @Builder
    public CustomerModifyReqDto(ModifyReq modifyReq) {
        this.nickname = (String) modifyReq.getData().get("nickname");
        this.favourite_markets = (List<Integer>) modifyReq.getData().get("favourite_markets");
        this.introduction = (String) modifyReq.getData().get("introduction");
    }
}
