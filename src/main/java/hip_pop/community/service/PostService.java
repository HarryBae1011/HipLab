package hip_pop.community.service;

import hip_pop.community.domain.Member;
import hip_pop.community.domain.Post;
import hip_pop.community.repository.CommentRepository;
import hip_pop.community.repository.MemberRepository;
import hip_pop.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 포스트 등록
     */
    @Transactional
    public Long join(Long memberId, String title, String content) {
        Member member = memberRepository.findOne(memberId);

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setPostMember(member);

        postRepository.save(post);

        return post.getId();
    }

}
