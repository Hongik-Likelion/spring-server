package likelion.togethermarket.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class CustomerModifyReqDto {
    private String nickname;
    private List<Integer> favourite_markets;
    private String introduction;

    @Builder
    public CustomerModifyReqDto(Map<String, Object> modifyReq) {
        this.nickname = (String) modifyReq.get("nickname");
        this.favourite_markets = (List<Integer>) modifyReq.get("favourite_markets");
        this.introduction = (String) modifyReq.get("introduction");
    }
}
