package com.kkwonsy.sample.web.domain.posts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void read_posts() throws Exception {
        // given
        String title = "test post1";
        String content = "test content1";
        String author = "park@naver.com";
        Posts post1 = getPosts(title, content, author);
        postsRepository.save(post1);
        // when
        List<Posts> postsList = postsRepository.findAll();
        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo(author);
    }

    private Posts getPosts(String title, String content, String author) {
        return Posts.builder()
            .title(title)
            .content(content)
            .author(author)
            .build();
    }

    private Posts getPosts() {
        return Posts.builder()
            .title("title")
            .content("content")
            .author("author")
            .build();
    }

    @Test
    public void BaseTimeEntity_save() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.of(LocalDate.of(2020, 3, 27), LocalTime.MIN);
        postsRepository.save(getPosts());

        // when
        List<Posts> all = postsRepository.findAll();

        // then
        Posts posts = all.get(0);
        LocalDateTime createdDate = posts.getCreatedDate();
        LocalDateTime lastModifiedDate = posts.getLastModifiedDate();
        System.out.println("createdDate: " + createdDate);
        System.out.println("lastModifiedDate: " + lastModifiedDate);
        assertThat(createdDate).isAfter(now);
        assertThat(lastModifiedDate).isAfter(now);
    }
}