package com.twiki.entry.post;

import com.twiki.account.Account;
import com.twiki.account.AccountRepository;
import com.twiki.entry.comment.Comment;
import com.twiki.entry.comment.CommentRepository;
import com.twiki.util.UserEntry;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PostService {

    private PostRepository postRepository;
    private AccountRepository accountRepository;
    private UserEntry userEntry;
    private CommentRepository commentRepository;

    public PostService(PostRepository postRepository, AccountRepository accountRepository, UserEntry userEntry, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.accountRepository = accountRepository;
        this.userEntry = userEntry;
    }

    public void publishPost(String postContent, String accountLogin) {
        Account postOwner = accountRepository.findByLogin(accountLogin)
                .orElseThrow(() -> new NoSuchElementException("user not founded"));
        Post post = userEntry.prepareNewPost();
        userEntry.createPost(postContent, postOwner);
        postRepository.save(post);
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
