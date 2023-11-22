package likelion.togethermarket.domain.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerModifyReqDto {
    private String shop_name;
    private String introduction;
    private String opening_time;
    private String closing_time;
}
