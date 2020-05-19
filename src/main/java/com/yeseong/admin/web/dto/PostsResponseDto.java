package com.yeseong.admin.web.dto;

import com.yeseong.admin.domain.posts.Posts;
import lombok.Getter;

// entity를 받지만 모든 정보를 가져올 필요가 없으므로 Entity -> DTO로

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
