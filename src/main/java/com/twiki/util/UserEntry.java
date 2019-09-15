package com.twiki.util;

import com.twiki.account.Account;
import com.twiki.entry.comment.Comment;
import com.twiki.entry.post.Post;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@SessionScope
public class UserEntry {

    private Post post;
    private Comment comment;

    public UserEntry() {
    }

    public Post prepareNewPost(){
        post = new Post();
        return post;
    }

    public Comment prepareNewComment(){
        comment = new Comment();
        return comment;
    }

    public void createPost(String entryContent, Account postOwner){
        String entryDate = createUserEntryDate();
        post.setCreateDate(entryDate);
        post.setContent(entryContent);
        post.setPostOwner(postOwner);
        post.setStatus(EntryStatus.ORIGINAL);
        post.setType(EntryType.POST_ENTRY);
    }

    public void createComment(String entryContent, Account commentOwner, Post post){
        String entryDate = createUserEntryDate();
        comment.setCreateDate(entryDate);
        comment.setContent(entryContent);
        comment.setCommentOwner(commentOwner);
        comment.setPost(post);
        comment.setStatus(EntryStatus.ORIGINAL);
        comment.setType(EntryType.COMMENT_ENTRY);
    }

    private String createUserEntryDate(){
        LocalDateTime entryDate = LocalDateTime.now();
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
        return entryDate.format(datePattern);
    }


    /*public void clearEntry(){
        entry.setContent("");
        entry.setEntryCreateDate(null);
        entry.setEntryStatus(null);
        entry.setEntryType(null);
    }*/
}
