package likelion.togethermarket.domain.market.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AllMarketDto {
    private int market_id;
    private String market_name;
    private String market_address;

    public AllMarketDto(int market_id, String market_name, String market_address) {
        this.market_id = market_id;
        this.market_name = market_name;
        this.market_address = market_address;
    }
}
