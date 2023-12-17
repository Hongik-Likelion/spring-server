package likelion.togethermarket.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class OwnerModifyReqDto {
    private String shop_name;
    private String introduction;
    private String opening_time;
    private String closing_time;

    @Builder
    public OwnerModifyReqDto(Map<String, Object> modifyReq) {
        this.shop_name = (String) modifyReq.get("shop_name");
        this.introduction = (String) modifyReq.get("introduction");
        this.opening_time = (String) modifyReq.get("opening_time");
        this.closing_time = (String) modifyReq.get("closing_time");
    }
}
