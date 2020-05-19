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
/* JPA의 어노테이션
* 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭함
* */
@Entity // DB table과 linking될 클래스임을 명시, DB table과 같은 구조, Entity클래스에서는 절대 @Setter메소드를 사용하지 않는다.
public class Posts extends BaseTimeEntity {

    @Id // 해당 table의 Primary key
    // PK의 생성규칙
    // GenerationType.IDENTITY == auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(length = 500, nullable = false)  //table의 column을 나타냄 문자열의 기본값은 varchar(255)임
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
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
