package hip_pop.community.domain.enums;

import lombok.Getter;

@Getter
public enum PostCategory {
    FREE("자유"),
    REVIEW("리뷰"),
    MUSIC("음악"),
    ARTIST("아티스트"),
    NEWS("뉴스"),
    CONCERT_INFO("공연 정보");

    private final String label;

    PostCategory(String label) {
        this.label = label;
    }
}
