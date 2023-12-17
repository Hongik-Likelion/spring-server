package likelion.togethermarket.domain.shop.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
public class ShopDetailResDto {
    private String shop_name;
    private String shop_address;
    private String selling_products;
    private String opening_time;
    private String closing_time;
    private String opening_frequency;

    @Nullable
    private Float average_rating;

    @Builder
    public ShopDetailResDto(String shop_name, String shop_address, String selling_products,
                            String opening_time, String closing_time, String opening_frequency, Float average_rating) {
        this.shop_name = shop_name;
        this.shop_address = shop_address;
        this.selling_products = selling_products;
        this.opening_time = opening_time;
        this.closing_time = closing_time;
        this.opening_frequency = opening_frequency;
        this.average_rating = average_rating;
    }
}
