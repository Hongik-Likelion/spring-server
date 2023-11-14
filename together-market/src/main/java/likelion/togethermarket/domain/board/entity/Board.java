package likelion.togethermarket.domain.board.entity;

import jakarta.persistence.*;
import likelion.togethermarket.domain.market.entity.Market;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.shop.entity.Shop;
import likelion.togethermarket.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    // 외래키 통해서 찾아 쓸 수도 있지만 쓸일이 많으므로 그냥 속성에 포함시킴
    @Column(name = "market_name", nullable = false)
    private String marketName;
    @Column(name = "shop_name", nullable = false)
    private String shopName;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private int rating;


}
