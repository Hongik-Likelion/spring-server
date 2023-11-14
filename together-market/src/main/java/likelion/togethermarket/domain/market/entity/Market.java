package likelion.togethermarket.domain.market.entity;

import jakarta.persistence.*;
import likelion.togethermarket.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Market extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Long id;

    @Column(name = "market_name")
    private String marketName;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "postal_address")
    private String postalAddress;

    @Column(name = "has_toilet")
    private boolean hasToilet;

    @Column(name = "has_parking")
    private boolean hasParking;
}
