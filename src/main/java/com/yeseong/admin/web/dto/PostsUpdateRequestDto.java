package com.yeseong.admin.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
