package likelion.togethermarket.domain.market.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class WishMarketReqDto {
    private List<Integer> market_id;
}
