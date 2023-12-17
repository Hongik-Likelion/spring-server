package likelion.togethermarket.domain.shop.dto.response;

import likelion.togethermarket.domain.shop.entity.Shop;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
public class FavouriteShopResDto {
    private String market_name;
    private String shop_name;
    private String shop_address;
    private String opening_time;
    private String closing_time;
    private String opening_frequency;

    @Nullable
    private Float average_rating;
    private boolean is_liked;

    public FavouriteShopResDto(Shop shop) {
        this.market_name = shop.getMarket().getMarketName();
        this.shop_name = shop.getShopName();
        this.shop_address = shop.getShopAddress();
        this.opening_time = shop.getOpeningTime();
        this.closing_time = shop.getClosingTime();
        this.opening_frequency = shop.getOpeningFrequency();
        this.average_rating = shop.getRating();
        this.is_liked = true;
    }
}
