package hip_pop.community.repository;

import hip_pop.community.domain.mapping.PostLikes;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikesRepository {

    private final EntityManager em;

    public void save(PostLikes postLikes) {
        em.persist(postLikes);
    }

    public void delete(PostLikes postLikes) {
        em.remove(postLikes);
    }

    public PostLikes findById(Long postLikeId) {
        return em.find(PostLikes.class, postLikeId);
    }
}
