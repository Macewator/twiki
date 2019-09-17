package com.twiki.comment;

import com.twiki.account.Account;
import com.twiki.post.Post;
import com.twiki.util.EntryStatus;
import com.twiki.util.EntryType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Comment implements Comparable<Comment>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String createDate;
    @Enumerated(value = EnumType.STRING)
    private EntryStatus status;
    @Enumerated(value = EnumType.STRING)
    private EntryType type;

    @ManyToOne
    @JoinColumn(name = "comment_owner_id")
    private Account commentOwner;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {
    }

    public Comment(String content, String createDate, EntryStatus status, EntryType type) {
        this.content = content;
        this.createDate = createDate;
        this.status = status;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public EntryStatus getStatus() {
        return status;
    }

    public void setStatus(EntryStatus status) {
        this.status = status;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    public Account getCommentOwner() {
        return commentOwner;
    }

    public void setCommentOwner(Account commentOwner) {
        this.commentOwner = commentOwner;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(content, comment.content) &&
                Objects.equals(createDate, comment.createDate) &&
                Objects.equals(commentOwner, comment.commentOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, createDate, commentOwner);
    }

    @Override
    public int compareTo(Comment comment) {
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
        return LocalDateTime.parse(this.createDate, datePattern)
                .compareTo(LocalDateTime.parse(comment.createDate, datePattern));
    }
}
