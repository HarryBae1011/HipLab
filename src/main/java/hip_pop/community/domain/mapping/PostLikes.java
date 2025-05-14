package hip_pop.community.domain.mapping;

import hip_pop.community.domain.Member;
import hip_pop.community.domain.Post;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PostLikes {

    @Id @GeneratedValue
    @Column(name = "post_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
