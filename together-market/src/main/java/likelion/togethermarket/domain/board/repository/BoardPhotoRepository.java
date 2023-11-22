package likelion.togethermarket.domain.board.repository;

import likelion.togethermarket.domain.board.entity.Board;
import likelion.togethermarket.domain.board.entity.BoardPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardPhotoRepository extends JpaRepository<BoardPhoto, Long> {

    void deleteAllByBoard(Board board);

    List<BoardPhoto> findAllByBoard(Board board);
}
