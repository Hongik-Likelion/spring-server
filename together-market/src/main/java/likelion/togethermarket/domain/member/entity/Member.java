package likelion.togethermarket.domain.member.entity;

import jakarta.persistence.*;
import likelion.togethermarket.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "memberRole")
    private MemberRole memberRole;

    @Column(nullable = false)
    private String nickName;

    @Column(name = "profile")
    private String profile;

    @Column(name = "introduction")
    private String introduction;

}
