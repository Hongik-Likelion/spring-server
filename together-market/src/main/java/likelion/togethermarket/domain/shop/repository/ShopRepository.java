package likelion.togethermarket.domain.shop.repository;

import likelion.togethermarket.domain.market.entity.Market;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findByMember(Member member);

    List<Shop> findAllByMarket(Market market);

}
