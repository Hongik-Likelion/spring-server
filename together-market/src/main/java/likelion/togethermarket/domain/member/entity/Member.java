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
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role")
    private MemberRole memberRole;

    @Column(nullable = false)
    private String nickName;

    @Column(name = "profile")
    private String profile;

    @Column(name = "introduction")
    private String introduction;

    @Builder
    public Member(Long id, String email, MemberRole memberRole, String nickName, String profile, String introduction) {
        this.id = id;
        this.email = email;
        this.memberRole = memberRole;
        this.nickName = nickName;
        this.profile = profile;
        this.introduction = introduction;
    }

    // nickname 수정 메서드
    public void modifyNickname(String newNickname){
        this.nickName = newNickname;
    }

    // introduction 수정 메서드
    public void modifyIntro(String newIntro){
        this.introduction = newIntro;
    }
}
