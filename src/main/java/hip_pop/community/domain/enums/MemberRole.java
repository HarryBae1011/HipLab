package hip_pop.community.domain.enums;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    MemberRole(String value) {
        this.value = value;
    }

    private final String value;
}
