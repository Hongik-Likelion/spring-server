package likelion.togethermarket.domain.market.repository;

import likelion.togethermarket.domain.market.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Long> {
}
