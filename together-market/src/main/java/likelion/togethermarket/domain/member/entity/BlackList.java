package likelion.togethermarket.domain.member.entity;

import jakarta.persistence.*;
import likelion.togethermarket.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlackList extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "black_list_id")
    private Long blackListId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Long blockedUserId;

    @Builder
    public BlackList(Long blackListId, Member member, Long blockedUserId) {
        this.member = member;
        this.blockedUserId = blockedUserId;
    }
}
