package likelion.togethermarket.domain.board.repository;

import likelion.togethermarket.domain.board.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
