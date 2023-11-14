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

    @Column(name = "marketName")
    private String marketName;

    @Column(name = "streetAddress")
    private String streetAddress;

    @Column(name = "postalAddress")
    private String postalAddress;

    @Column(name = "hasToilet")
    private boolean hasToilet;

    @Column(name = "hasParking")
    private boolean hasParking;
}
