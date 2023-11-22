package likelion.togethermarket.domain.market.repository;

import likelion.togethermarket.domain.market.entity.WishMarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishMarketRepository extends JpaRepository<WishMarket, Long> {
    List<WishMarket> findAllByMember_Id(Long memberId);
}
