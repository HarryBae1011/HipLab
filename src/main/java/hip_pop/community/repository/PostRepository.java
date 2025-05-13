package hip_pop.community.repository;

import hip_pop.community.domain.Post;
import hip_pop.community.domain.enums.PostCategory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public void deleteById(Long postId) {
        Post findPost = em.find(Post.class, postId);
        if (findPost != null) {
            em.remove(findPost);
        }
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .setMaxResults(1000)
                .getResultList();
    }

    public List<Post> findByCategory(PostCategory category) {
        return em.createQuery("select p from Post p where p.category = :category", Post.class)
                .setParameter("category", category)
                .setMaxResults(1000)
                .getResultList();
    }

}
