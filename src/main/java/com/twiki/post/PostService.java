package com.twiki.post;

import com.twiki.account.Account;
import com.twiki.account.AccountRepository;

import com.twiki.util.UserEntry;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PostService {

    private PostRepository postRepository;
    private AccountRepository accountRepository;
    private UserEntry userEntry;

    public PostService(PostRepository postRepository, AccountRepository accountRepository, UserEntry userEntry) {
        this.postRepository = postRepository;
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
}
