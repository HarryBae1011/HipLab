package hip_pop.community.service;

import hip_pop.community.domain.Member;
import hip_pop.community.domain.Post;
import hip_pop.community.repository.CommentRepository;
import hip_pop.community.repository.MemberRepository;
import hip_pop.community.repository.PostRepository;
import hip_pop.community.repository.PostSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Long join(//Long memberId,
                      String title, String content) {
        //Member member = memberRepository.findOne(memberId);

        Post post = Post.createPost(title, content);
        //post.setPostMember(member);

        postRepository.save(post);

        return post.getId();
    }

    // 포스트 저장
    @Transactional
    public void savePost(Post post) {
        postRepository.save(post);
    }

    //포스트 삭제
    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    /**
     * 전체 포스트 찾기
     */
    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    /**
     * 단일 포스트 찾기
     */
    public Post findSinglePost(Long postId) {
        return postRepository.findOne(postId);
    }
}
