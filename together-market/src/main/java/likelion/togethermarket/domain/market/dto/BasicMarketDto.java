package likelion.togethermarket.domain.market.dto;

import likelion.togethermarket.domain.market.entity.Market;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BasicMarketDto {
    private int market_id;
    private String market_name;

    @Builder
    public BasicMarketDto(int market_id, String market_name) {
        this.market_id = market_id;
        this.market_name = market_name;
    }

    public BasicMarketDto(Market market) {
        this.market_id = Math.toIntExact(market.getId());
        this.market_name = market.getMarketName();
    }
}
