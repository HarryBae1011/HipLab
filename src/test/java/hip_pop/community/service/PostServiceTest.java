package hip_pop.community.service;

import hip_pop.community.domain.Member;
import hip_pop.community.domain.Post;
import hip_pop.community.repository.MemberRepository;
import hip_pop.community.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired PostRepository postRepository;
    @Autowired PostService postService;

    @Test
    @Rollback(value = false)
    public void 게시물_포스팅() throws Exception {
        //given
        Member member = new Member();
        member.setName("member1");
        member.setPassword("00");

        Long savedMember = memberService.join(member);

        //when
        //postService.join(member, "제목", "컨텐츠");


        //then

    }
}