package likelion.togethermarket.domain.shop.dto.response;

import likelion.togethermarket.domain.shop.entity.Shop;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SimpleShopResDto {
    private int shop_id;
    private String shop_name;

    @Builder
    public SimpleShopResDto(Shop shop) {
        this.shop_id = shop.getId().intValue();
        this.shop_name = shop.getShopName();
    }
}
