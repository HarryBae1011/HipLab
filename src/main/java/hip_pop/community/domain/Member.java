package hip_pop.community.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long Id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @OneToMany(mappedBy = "postMember")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "commentMember")
    private List<Comment> comments = new ArrayList<>();
}
