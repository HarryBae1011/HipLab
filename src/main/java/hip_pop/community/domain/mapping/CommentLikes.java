package hip_pop.community.domain.mapping;

import hip_pop.community.domain.Comment;
import hip_pop.community.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class CommentLikes {

    @Id @GeneratedValue
    @Column(name = "comment_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
