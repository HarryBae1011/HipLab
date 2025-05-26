package hip_pop.community.service;

import hip_pop.community.domain.Member;
import hip_pop.community.domain.Post;
import hip_pop.community.domain.mapping.PostLikes;
import hip_pop.community.repository.PostLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostLikeService {

    private final PostLikesRepository postLikesRepository;

    /**
     * 좋아요 누르기
     */
    public void toggleLike(Post post, Member member) {
        //좋아요 버튼을 누른 적이 없으면 좋아요 누르기
        if (!isAlreadyLiked(post.getId(), member.getId())) {
            PostLikes postLike = PostLikes.createPostLike(post, member);
            postLikesRepository.save(postLike);
        }
        //이미 좋아요 버튼을 누른 상태에서 다시 누를시 좋아요 취소
        else {
            postLikesRepository.deleteByPostIdAndMemberId(post.getId(), member.getId());
        }
    }

    //좋아요 개수 세기
    public Integer countLikes(Long postId) {
        List<PostLikes> postLikes = postLikesRepository.findAllByPostId(postId);
        return postLikes.size();
    }

    //좋아요 중복 방지
    private boolean isAlreadyLiked(Long postId, Long memberId) {
        return postLikesRepository.existsByPostIdAndMemberId(postId, memberId);
    }

}
