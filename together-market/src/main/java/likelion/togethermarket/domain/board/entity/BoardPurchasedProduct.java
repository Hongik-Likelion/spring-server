package likelion.togethermarket.domain.board.entity;

import jakarta.persistence.*;
import likelion.togethermarket.domain.product.entity.Product;
import likelion.togethermarket.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPurchasedProduct extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchased_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public BoardPurchasedProduct(Board board, Product product) {
        this.board = board;
        this.product = product;
    }
}
