package likelion.togethermarket.domain.product.repository;

import likelion.togethermarket.domain.product.entity.SellingProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellingProductsRepository extends JpaRepository<SellingProducts, Long> {
}
