package com.kkwonsy.sample.web.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kkwonsy.sample.web.config.auth.dto.SessionUser;
import com.kkwonsy.sample.web.domain.user.User;
import com.kkwonsy.sample.web.service.PostsService;
import com.kkwonsy.sample.web.web.dto.PostsResponseDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable("id") Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        return "posts-update";
    }

}
