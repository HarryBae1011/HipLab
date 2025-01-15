package hip_pop.community.controller;

import hip_pop.community.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostForm {

    @NotEmpty
    private String title;

    private String content;
}
