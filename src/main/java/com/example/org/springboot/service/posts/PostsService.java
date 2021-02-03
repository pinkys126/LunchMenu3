package com.example.org.springboot.service.posts;

import com.example.org.springboot.domain.posts.Posts;
import com.example.org.springboot.domain.posts.PostsRepository;
import com.example.org.springboot.web.dto.PostsListResponseDto;
import com.example.org.springboot.web.dto.PostsResponseDto;
import com.example.org.springboot.web.dto.PostsSaveRequestDto;
import com.example.org.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 RequiredArgsConstructor 어노테이션이 대신 생성
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    // readOnly = true 는 트랜젝션 범위는 유지하되 조회기능만 남겨두어 조회속도가 개선됨.
    // 등록, 수정, 삭제 기능이 없는 서비스메소드에 사용해야 함.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        // map(posts -> new PostsListResponseDto(posts)) 와 동일한 문법
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);
    }
}
