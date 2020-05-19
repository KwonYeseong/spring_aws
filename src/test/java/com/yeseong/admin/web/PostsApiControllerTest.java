package com.yeseong.admin.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeseong.admin.domain.posts.PostsRepository;
import com.yeseong.admin.domain.posts.Posts;
import com.yeseong.admin.web.dto.PostsSaveRequestDto;
import com.yeseong.admin.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//WebMvcTest는 JPA 기능이 작동하지 않는다. 그래서 SpringBootTest를 사용
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

        @LocalServerPort // 실행중인 port번호를 가져옴
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        @Autowired
        private PostsRepository postsRepository;

        @Autowired
        private WebApplicationContext context;

        private MockMvc mvc;

        @Before // 매번 테스트가 시작되기 전에 MockMvc 인스턴스를 생성
        public void setup() {
            mvc = MockMvcBuilders
                    .webAppContextSetup(context)
                    .apply(springSecurity())
                    .build();
        }

        @After
        public void tearDown() throws Exception {
            postsRepository.deleteAll();
        }

        @Test
        @WithMockUser(roles = "USER") // 인증된 가짜 사용자를 만들어서 사용
        public void posts_registration() throws Exception {
            String title = "title";
            String content = "content";

            PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                                                                .title(title)
                                                                .content(content)
                                                                .author("author")
                                                                .build();

            String url = "http://localhost:" + port + "/api/v1/posts";

//            ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//
//            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//            assertThat(responseEntity.getBody()).isGreaterThan(0L);

            mvc.perform(post(url) // 생성된 MockMvc를 통해 API를 테스트한다.
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(new ObjectMapper().writeValueAsString(requestDto)))
                    .andExpect(status().isOk());

            List<Posts> all = postsRepository.findAll();
            assertThat(all.get(0).getTitle()).isEqualTo(title);
            assertThat(all.get(0).getContent()).isEqualTo(content);
            assertThat(all.get(0).getAuthor()).isEqualTo("author");
        }

        @Test
        @WithMockUser(roles = "USER")
        public void posts_update() throws Exception {
            Posts savedPosts = postsRepository
                                    .save(Posts.builder()
                                            .title("title")
                                            .content("content")
                                            .author("author")
                                            .build());

            Long updateId = savedPosts.getId();
            String expectedTitle = "title2";
            String expectedContent = "content2";

            PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                                                                    .title(expectedTitle)
                                                                    .content(expectedContent)
                                                                    .build();

            String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

//            HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
//            ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//
//            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//            assertThat(responseEntity.getBody()).isGreaterThan(0L);

            mvc.perform(put(url)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(new ObjectMapper().writeValueAsString(requestDto)))
                    .andExpect(status().isOk());

            List<Posts> all = postsRepository.findAll();
            assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
            assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
        }

}
