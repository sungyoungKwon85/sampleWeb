package com.kkwonsy.sample.web.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kkwonsy.sample.web.service.PostsService;
import com.kkwonsy.sample.web.web.dto.PostsResponseDto;
import com.kkwonsy.sample.web.web.dto.PostsSaveRequestDto;
import com.kkwonsy.sample.web.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;


    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable("id") Long id) {
        return postsService.findById(id);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable("id") Long id) {
        postsService.delete(id);
        return id;
    }

}
