package com.kkwonsy.sample.web.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kkwonsy.sample.web.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// @Setter를 두면 무분별하게 수정이 되기 때문에 안하는게 나음
// 수정이 필요하면 목적/의도가 명확한 메서드를 만드는게 좋음
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 주민번호등 유니크 키를 PK로 하는 경우
    // FK를 맺을 때 다른 테이블에서 복합키 전부를 갖고 있거나 중간 테이블을 더 둬야 하는 상황이 발생할 수 있음
    // 인덱스에 안좋은 영향
    // 유니크한 조건이 만약 변경 된다면? PK를 다 바꿔야함
    // 걍 유니크 키로 별도로 두고 Long을 쓰는게 나음

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
