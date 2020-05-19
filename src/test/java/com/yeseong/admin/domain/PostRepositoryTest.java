package com.yeseong.admin.domain;

import com.yeseong.admin.domain.posts.Posts;
import com.yeseong.admin.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
1.JUnit에 내장된 실행자 외 다른 실행자를 실행시킴, 여기서는 SpringRunner
2.springBootTest와 JUnit 사이에 연결자 역할을 한다.
 */
@RunWith(SpringRunner.class)

/*
1.spring framework에서 전체적으로 플로우가 제대로 동작하는지 검증
2.모든 Bean을 로드하기때문에 가장 유사한 테스트
3.H2 DB를 자동으로 실행해준다.
*/
@SpringBootTest
public class PostRepositoryTest {

    @Autowired // 의존성 자동주입
    PostsRepository postrepository;

    @After // 단위 테스트가 끝날떄마다 수행되는 메소드를 지정
    public void cleanup() {
        postrepository.deleteAll();
    }

    @Test
    public void posts_load() {
        String title = "test post";
        String content = "test content";

        //save == 상황에 따라 insert or update
        postrepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("yebali")
        .build());

        //find == select
        //findAll == select * from <table>
        List<Posts> postsList = postrepository.findAll();

        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_reg() {
        LocalDateTime now = LocalDateTime.of(2020,04,16,0,0,0);
        
        //save할 때 자동으로 CreatedDate, ModifiedDate가 저장됨
        postrepository.save(Posts.builder().title("title").content("content").author("author").build());

        List<Posts> all = postrepository.findAll();

        Posts posts = all.get(0);

        System.out.println(">>>>>>> Create Date=" + posts.getCreadtedDate()
                            + ", modefiledDate=" + posts.getModifiedDate());

        assertThat(posts.getCreadtedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
