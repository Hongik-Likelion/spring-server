package likelion.togethermarket.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerModifyReqDto {
    private String shop_name;
    private String introduction;
    private String opening_time;
    private String closing_time;

    @Builder
    public OwnerModifyReqDto(ModifyReq modifyReq) {
        this.shop_name = (String) modifyReq.getData().get("shop_name");
        this.introduction = (String) modifyReq.getData().get("introduction");
        this.opening_time = (String) modifyReq.getData().get("opening_time");
        this.closing_time = (String) modifyReq.getData().get("closing_time");
    }
}
