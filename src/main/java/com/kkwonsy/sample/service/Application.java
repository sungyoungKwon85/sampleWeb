package com.kkwonsy.sample.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 얘로 인해 Embedded WAS(tomcat)이 실행됨
        SpringApplication.run(Application.class, args);

        // Spring boot는 Embedded WAS 사용을 권장함
        // 언제 어디서나 같은 환경에서 스프링부트를 배포할 수 있기 때문
        // (외장 WAS는 종류, 설정, 버을 일치시켜야만 함)
        // (성능상 이슈는 거의 없는 것 같음. 상용화 한 서비스가 많음)
    }

}
