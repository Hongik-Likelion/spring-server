package likelion.togethermarket.domain.board.repository;

import likelion.togethermarket.domain.board.entity.BoardPurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardPurchasedProductRepository extends JpaRepository<BoardPurchasedProduct, Long> {
}
