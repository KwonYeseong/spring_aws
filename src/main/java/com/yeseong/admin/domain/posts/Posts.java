package com.yeseong.admin.domain.posts;

import com.yeseong.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // DB table과 linking될 클래스임을 명시
public class Posts extends BaseTimeEntity {

    @Id // 해당 table의 Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙
    private Long id;

    @Column(length = 500, nullable = false)  //table의 column을 나타냄 문자열의 기본값은 varchar(255)임
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
