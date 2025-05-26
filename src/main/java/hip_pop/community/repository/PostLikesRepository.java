package hip_pop.community.repository;

import hip_pop.community.domain.mapping.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    List<PostLikes> findAllByPostId(Long postId);
}
