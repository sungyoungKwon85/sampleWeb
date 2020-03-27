package com.kkwonsy.sample.service.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // final이 선언된 모든 필드를 포함한 생성자를 자동으로 만들어줌
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
