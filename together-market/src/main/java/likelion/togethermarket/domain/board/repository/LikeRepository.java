package likelion.togethermarket.domain.board.repository;

import likelion.togethermarket.domain.board.entity.Board;
import likelion.togethermarket.domain.board.entity.Like;
import likelion.togethermarket.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByBoard(Board board);

    Optional<Like> findByMemberAndBoard(Member member, Board board);

    boolean existsByBoardAndMember(Board board, Member member);

    long countByBoard(Board board);
}
