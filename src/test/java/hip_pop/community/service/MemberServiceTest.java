package hip_pop.community.service;

import hip_pop.community.domain.Member;
import hip_pop.community.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("member1");
        member.setPassword("00");

        //when
        Long savedMember = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedMember));
    }

    @Test
    public void 중복회원() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("m1");
        member1.setPassword("00");

        Member member2 = new Member();
        member2.setName("m1");
        member2.setPassword("00");

        //when
        memberService.join(member1);

        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }

        //then
        fail();
    }
}