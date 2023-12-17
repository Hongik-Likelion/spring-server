package likelion.togethermarket.domain.board.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import likelion.togethermarket.domain.market.entity.Market;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.shop.entity.Shop;
import likelion.togethermarket.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Nullable
    @Column(name = "rating")
    private Integer rating;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardPhoto> photos;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardPurchasedProduct> purchasedProducts;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Like> likes;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Report> reports;

    // 사장용
    public void modify(String marketName, String shopName, String content){
        this.marketName = marketName;
        this.shopName = shopName;
        this.content = content;
    }

    // 고객용
    public void modify(String marketName, String shopName, String content, int rating){
        this.marketName = marketName;
        this.shopName = shopName;
        this.content = content;
        this.rating = rating;
    }


    @Builder
    public Board(Member member, Market market, Shop shop, String marketName, String shopName, String content, int rating) {
        this.member = member;
        this.market = market;
        this.shop = shop;
        this.marketName = marketName;
        this.shopName = shopName;
        this.content = content;
        this.rating = rating;
    }
}
