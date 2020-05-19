package com.yeseong.admin.service.posts;

import com.yeseong.admin.domain.posts.Posts;
import com.yeseong.admin.domain.posts.PostsRepository;
import com.yeseong.admin.web.dto.PostsListResponseDto;
import com.yeseong.admin.web.dto.PostsResponseDto;
import com.yeseong.admin.web.dto.PostsSaveRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
// service layer, business logic을 가진 클래스에 사용한다.
// Repository를 통해 DB에서 데이터를 가져온 후 Controller에게 전달해 주는 클래스임을 명시
@Service 
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional // 해당 클래스에 transaction기능이 적용된 프록시 객체가 생성된다.
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsSaveRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream() // postsRepository에서 넘어온 Posts의 stream을
                .map(PostsListResponseDto::new)       // map을 통해 List로 변환
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        postsRepository.delete(posts);
    }

}
