package com.kkwonsy.sample.web.domain.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// Entity와 밀접한 관계이므로 같은 위치에 두었다
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // @Query 예제를 위해 만들어봄
    @Query("SELECT p FROM Posts p order by p.id DESC")
    List<Posts> findAllDesc();
}
