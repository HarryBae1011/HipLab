/*
package hip_pop.community.config;

import hip_pop.community.domain.Member;
import hip_pop.community.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public SecurityMemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Trying to load user: {}", username);
        Member findMember = memberRepository.findOneByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        log.info("User found: {}", findMember.getName());
        return new SecurityMemberDetails(findMember);
    }
}

 */