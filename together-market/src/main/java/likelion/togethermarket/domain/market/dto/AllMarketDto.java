package likelion.togethermarket.domain.market.dto;

import likelion.togethermarket.domain.market.entity.Market;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AllMarketDto {
    private int market_id;
    private String market_name;
    private String market_address;

    public AllMarketDto(Market market) {
        this.market_id = market.getId().intValue();
        this.market_name = market.getMarketName();
        this.market_address = market.getStreetAddress();
    }
}
