package hip_pop.community.repository;

import hip_pop.community.domain.Member;
import hip_pop.community.domain.Post;
import hip_pop.community.domain.enums.PostCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    //@Rollback(false)
    void findByCategory() {
        //given
        Member member = new Member();
        member.setName("userA");
        Post post = new Post();
        post.setPostMember(member);
        post.setCategory(PostCategory.REVIEW);

        //when
        postRepository.save(post);
        Post findPost = postRepository.findOne(post.getId());

        //then
        assertThat(post).isEqualTo(findPost);
        System.out.println(PostCategory.REVIEW);
    }
}