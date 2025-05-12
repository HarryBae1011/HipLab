package hip_pop.community.service;

import hip_pop.community.domain.Member;
import hip_pop.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public Member getMember(String memberName) throws Exception {
        Optional<Member> findMember = memberRepository.findOneByName(memberName);
        if (findMember.isPresent()) {
            return findMember.get();
        } else {
            throw new Exception("Member not found");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //중복 회원 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
