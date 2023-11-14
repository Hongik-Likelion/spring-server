package likelion.togethermarket.domain.market.entity;

import jakarta.persistence.*;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WishMarket extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "wish_market_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;
}
