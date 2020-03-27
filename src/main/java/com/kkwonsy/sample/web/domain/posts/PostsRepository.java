package com.kkwonsy.sample.web.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Entity와 밀접한 관계이므로 같은 위치에 두었다
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
