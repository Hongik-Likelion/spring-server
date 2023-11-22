package likelion.togethermarket.domain.shop.entity;

import jakarta.persistence.*;
import likelion.togethermarket.domain.market.entity.Market;
import likelion.togethermarket.domain.member.entity.Member;
import likelion.togethermarket.domain.product.entity.SellingProducts;
import likelion.togethermarket.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "shop_name", nullable = false)
    private String shopName;

    @Column(name = "shop_address")
    private String shopAddress;

    @Column(name = "selling_products")
    private String sellingProducts;

    @Column(name = "opening_time")
    private String openingTime;

    @Column(name = "closing_time")
    private String closingTime;

    @Column(name = "opening_frequency")
    private String openingFrequency; // e.g. 매주, 매달, 격주 ...

    @Column(name = "rating")
    @ColumnDefault("0")
    private Float rating;

    @OneToMany(mappedBy = "shop")
    private List<SellingProducts> products;

    public void modifyShopInfo(String newShopName, String newOpening, String newClosing){
        this.shopName = newShopName;
        this.openingTime = newOpening;
        this.closingTime = newClosing;
    }

    public void updateRating(float newRating){
        this.rating = newRating;
    }

    @Builder
    public Shop(Market market, Member member, String shopName, String shopAddress, String sellingProducts, String openingTime, String closingTime, String openingFrequency) {
        this.market = market;
        this.member = member;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.sellingProducts = sellingProducts;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.openingFrequency = openingFrequency;
    }
}
