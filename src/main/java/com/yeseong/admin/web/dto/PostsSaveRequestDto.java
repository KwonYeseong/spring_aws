package com.yeseong.admin.web.dto;

import com.yeseong.admin.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/* Entity클래스를 Request/Response클래스로 사용해서는 안된다. */
@Getter
@NoArgsConstructor // 기본생성자를 생성해줌
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
