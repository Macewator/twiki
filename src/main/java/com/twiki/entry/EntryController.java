package com.twiki.entry;

import com.twiki.entry.post.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EntryController {

    private PostService postService;

    public EntryController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("entries/post")
    public String publishPost(@RequestParam String accountName, @RequestParam String postContent, Authentication auth){
        postService.publishPost(postContent, auth.getName());
        String redirectUrl = "/accounts/"+ accountName +"/wall";
        return "redirect:" + redirectUrl;
    }

    @PostMapping("entries/post/{id}/comment")
    public String publishComment(@PathVariable Long id, @RequestParam String accountName,
                                 @RequestParam String commentContent, Authentication auth){
        postService.publishComment(id, commentContent, auth.getName());
        String redirectUrl = "/accounts/"+ accountName +"/wall";
        return "redirect:" + redirectUrl;
    }
}
