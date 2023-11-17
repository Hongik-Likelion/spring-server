package likelion.togethermarket.global.jwt;

import likelion.togethermarket.domain.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/* UserDetails를 implement해 세부정보 커스터마이즈
*  Param 에 member를 넣어서 생성 */
public class CustomUserDetails implements UserDetails {
    private Member member;

    public CustomUserDetails(Member member){this.member = member;}

    public Member getMember(){return member;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(member.getMemberRole().toString()));
        //memberRole 이용해 권한 등록
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
        // password는 쓸일 없을듯
    }

    @Override
    public String getUsername() {
        return member.getEmail();
        // email을 subject로 쓸거임
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
