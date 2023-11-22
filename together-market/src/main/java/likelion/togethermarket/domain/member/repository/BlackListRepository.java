package likelion.togethermarket.domain.member.repository;

import likelion.togethermarket.domain.member.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
}
