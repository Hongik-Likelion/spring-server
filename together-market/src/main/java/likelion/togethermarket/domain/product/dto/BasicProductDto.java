package likelion.togethermarket.domain.product.dto;

import likelion.togethermarket.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BasicProductDto {
    private int product_id;
    private String product_type;

    @Builder
    public BasicProductDto(int product_id, String product_type) {
        this.product_id = product_id;
        this.product_type = product_type;
    }

    public BasicProductDto(Product product) {
        this.product_id = Math.toIntExact(product.getId());
        this.product_type = product.getProductType();
    }
}
