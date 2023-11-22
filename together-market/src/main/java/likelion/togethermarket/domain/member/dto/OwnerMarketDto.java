package likelion.togethermarket.domain.member.dto;

import likelion.togethermarket.domain.product.entity.Product;
import likelion.togethermarket.domain.product.entity.SellingProducts;
import likelion.togethermarket.domain.shop.entity.Shop;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OwnerMarketDto {
    private int market_id;
    private String shop_name;
    private String shop_address;
    private String selling_products;
    private String opening_time;
    private String closing_time;
    private String opening_frequency;
    private List<Integer> product_categories;

    public OwnerMarketDto(Shop shop) {
        this.market_id = Math.toIntExact(shop.getMarket().getId());
        this.shop_name = shop.getShopName();
        this.shop_address = shop.getShopAddress();
        this.selling_products = shop.getSellingProducts();
        this.opening_time = shop.getOpeningTime();
        this.closing_time = shop.getClosingTime();
        this.opening_frequency = shop.getOpeningFrequency();
        this.product_categories = (List<Integer>) shop.getProducts().stream()
                .map(SellingProducts::getId).toList().stream().mapToInt(Long::intValue);
    }
}
