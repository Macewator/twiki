package com.twiki.post;

import com.twiki.account.Account;
import com.twiki.comment.Comment;
import com.twiki.util.EntryStatus;
import com.twiki.util.EntryType;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Post implements Comparable<Post>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String createDate;
    private int likes;
    private int shares;
    @Enumerated(value = EnumType.STRING)
    private EntryStatus status;
    @Enumerated(value = EnumType.STRING)
    private EntryType type;

    @ManyToOne
    @JoinColumn(name = "post_owner_id")
    private Account postOwner;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments = new TreeSet<>();

    @ManyToMany
    @JoinTable(name = "favorites_post", joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<Account> accounts = new HashSet<>();

    public Post() {
    }

    public Post(String content, String createDate, int likes, int shares, EntryStatus status, EntryType type) {
        this.content = content;
        this.createDate = createDate;
        this.likes = likes;
        this.shares = shares;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
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

    public Account getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(Account postOwner) {
        this.postOwner = postOwner;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public int compareTo(Post post) {
        DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
        return LocalDateTime.parse(this.createDate, datePattern)
                .compareTo(LocalDateTime.parse(post.createDate, datePattern));
    }
}
