package hip_pop.community.service;

import hip_pop.community.domain.Member;
import hip_pop.community.domain.Post;
import hip_pop.community.domain.mapping.PostLikes;
import hip_pop.community.repository.PostLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikesRepository postLikesRepository;

    /**
     * 좋아요 누르기
     */
    public void pressLike(Post post, Member member) {
        PostLikes postLike = PostLikes.createPostLike(post, member);
        postLikesRepository.save(postLike);
    }


    /**
     * 좋아요 취소하기
     */
    /*public void deleteLike(Post post, Member member) {
        postLikesRepository.findById()
    }*/
}
