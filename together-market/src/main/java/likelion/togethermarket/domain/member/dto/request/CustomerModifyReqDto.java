package likelion.togethermarket.domain.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CustomerModifyReqDto {
    private String nickname;
    private String introduction;
    private List<Integer> favourite_markets;
}
