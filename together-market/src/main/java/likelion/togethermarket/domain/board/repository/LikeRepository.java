package likelion.togethermarket.domain.board.repository;

import likelion.togethermarket.domain.board.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
