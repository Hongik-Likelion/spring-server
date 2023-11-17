package likelion.togethermarket.domain.member.repository;


import likelion.togethermarket.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
