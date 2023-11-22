package likelion.togethermarket.domain.shop.repository;

import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.shop.entity.Shop;
import likelion.togethermarket.domain.shop.entity.WishShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishShopRepository extends JpaRepository<WishShop, Long> {
    Optional<WishShop> findByMemberAndShop(Member member, Shop shop);
}
