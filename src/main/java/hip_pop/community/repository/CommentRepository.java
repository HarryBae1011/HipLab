package hip_pop.community.repository;

import hip_pop.community.domain.Comment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public void deleteById(Long commentId) {
        Comment findComment = em.find(Comment.class, commentId);
        if (findComment != null) {
            em.remove(findComment);
        }
    }

    public Comment findOne(Long id) {
        return em.find(Comment.class, id);
    }
}
