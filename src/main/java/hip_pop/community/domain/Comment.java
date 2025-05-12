package hip_pop.community.domain;

import hip_pop.community.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Comment extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long Id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member commentMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    public static Comment createComment(Post post, Member member, String content) {

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setCommentMember(member);
        comment.setContent(content);

        return comment;
    }
}
