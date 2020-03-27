package com.kkwonsy.sample.web.service;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkwonsy.sample.web.domain.posts.Posts;
import com.kkwonsy.sample.web.domain.posts.PostsRepository;
import com.kkwonsy.sample.web.web.dto.PostsResponseDto;
import com.kkwonsy.sample.web.web.dto.PostsSaveRequestDto;
import com.kkwonsy.sample.web.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;


    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public PostsResponseDto findById(Long id) {
        return postsRepository.findById(id)
            .map(PostsResponseDto::new)
            .orElseThrow(() -> new IllegalArgumentException("No result of posts, id= " + id));
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No result of posts, id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getAuthor());

        return id;
    }
}
