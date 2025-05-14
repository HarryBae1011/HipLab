package hip_pop.community.domain;

import hip_pop.community.domain.common.BaseEntity;
import hip_pop.community.domain.enums.PostCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long Id;

    @Enumerated(EnumType.STRING)
    private PostCategory category;
    private String title;
    private String content;

    private Integer likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member postMember;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public static Post createPost(PostCategory category, String title, String content, Member member) {
        Post post = new Post();
        post.setCategory(category);
        post.setTitle(title);
        post.setContent(content);
        post.setPostMember(member);

        return post;
    }
}
