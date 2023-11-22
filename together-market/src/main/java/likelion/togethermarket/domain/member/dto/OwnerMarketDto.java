package likelion.togethermarket.domain.member.dto;

import likelion.togethermarket.domain.product.entity.Product;
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
    private List<Product> product_categories;
}
