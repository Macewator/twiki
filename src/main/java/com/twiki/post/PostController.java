package com.twiki.post;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public String publishPost(@RequestParam String accountName, @RequestParam String postContent, Authentication auth){
        postService.publishPost(postContent, auth.getName());
        String redirectUrl = "/accounts/"+ accountName +"/wall";
        return "redirect:" + redirectUrl;
    }
}
