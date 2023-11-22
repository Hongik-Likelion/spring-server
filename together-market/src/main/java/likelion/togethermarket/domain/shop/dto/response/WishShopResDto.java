package likelion.togethermarket.domain.shop.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishShopResDto {
    private int shop_id;
    private String message;

    @Builder
    public WishShopResDto(int shop_id, String message) {
        this.shop_id = shop_id;
        this.message = message;
    }
}
