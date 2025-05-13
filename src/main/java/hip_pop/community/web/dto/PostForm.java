package hip_pop.community.web.dto;

import hip_pop.community.domain.enums.PostCategory;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostForm {

    @NotEmpty
    private String title;

    private String content;

    private PostCategory category;
}
