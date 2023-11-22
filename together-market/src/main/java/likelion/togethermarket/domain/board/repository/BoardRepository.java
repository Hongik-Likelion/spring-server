package likelion.togethermarket.domain.board.repository;

import likelion.togethermarket.domain.board.entity.Board;
import likelion.togethermarket.domain.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByShop(Shop shop);
}
