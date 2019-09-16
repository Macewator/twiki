package com.twiki.comment;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("comments/post/{id}")
    public String publishComment(@PathVariable Long id, @RequestParam String accountName,
                                 @RequestParam String commentContent, Authentication auth){
        commentService.publishComment(id, commentContent, auth.getName());
        String redirectUrl = "/accounts/"+ accountName +"/wall";
        return "redirect:" + redirectUrl;
    }
}
