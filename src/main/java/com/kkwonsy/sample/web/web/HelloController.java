package com.kkwonsy.sample.web.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kkwonsy.sample.web.web.dto.HelloResponseDto;


// JSON으로 반환하게 해줌
// 예전에는 @ResponseBody를 일일이 붙여주곤 했는데 한방에..
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

}
