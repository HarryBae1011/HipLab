package hip_pop.community.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;
}
