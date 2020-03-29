package com.kkwonsy.sample.web.web.dto;

import java.time.LocalDateTime;

import com.kkwonsy.sample.web.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime lastModifiedDate;

    public PostsListResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.author = posts.getAuthor();
        this.lastModifiedDate = posts.getLastModifiedDate();
    }
}
