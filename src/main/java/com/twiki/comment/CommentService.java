package com.twiki.comment;

import com.twiki.account.Account;
import com.twiki.account.AccountRepository;
import com.twiki.post.Post;
import com.twiki.post.PostRepository;
import com.twiki.util.UserEntry;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CommentService {

    private PostRepository postRepository;
    private AccountRepository accountRepository;
    private UserEntry userEntry;
    private CommentRepository commentRepository;

    public CommentService(PostRepository postRepository, AccountRepository accountRepository, UserEntry userEntry, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.accountRepository = accountRepository;
        this.userEntry = userEntry;
        this.commentRepository = commentRepository;
    }

    public void publishComment(Long id, String commentContent, String accountLogin) {
        Account commentOwner = accountRepository.findByLogin(accountLogin)
                .orElseThrow(() -> new NoSuchElementException("user not founded"));
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("post not founded"));
        Comment comment = userEntry.prepareNewComment();
        userEntry.createComment(commentContent, commentOwner, post);
        commentRepository.save(comment);
    }
}
