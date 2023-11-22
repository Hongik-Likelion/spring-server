package likelion.togethermarket.domain.board.repository;

import likelion.togethermarket.domain.board.entity.Board;
import likelion.togethermarket.domain.board.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    long countByBoard(Board board);
}
