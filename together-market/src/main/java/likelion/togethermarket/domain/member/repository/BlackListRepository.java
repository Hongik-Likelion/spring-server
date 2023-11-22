package likelion.togethermarket.domain.member.repository;

import likelion.togethermarket.domain.member.entity.BlackList;
import likelion.togethermarket.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    List<BlackList> findAllByMember(Member member);
}
