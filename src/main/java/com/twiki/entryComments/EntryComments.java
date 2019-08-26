package com.twiki.entryComments;

import com.twiki.account.Account;

import javax.persistence.*;

@Entity
public class EntryComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entry_owner_id")
    private Account commentedEntryOwner;

    @ManyToOne
    @JoinColumn(name = "comment_owner_id")
    private Account commentEntryOwner;

    public EntryComments() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getCommentedEntryOwner() {
        return commentedEntryOwner;
    }

    public void setCommentedEntryOwner(Account commentedEntryOwner) {
        this.commentedEntryOwner = commentedEntryOwner;
    }

    public Account getCommentEntryOwner() {
        return commentEntryOwner;
    }

    public void setCommentEntryOwner(Account commentEntryOwner) {
        this.commentEntryOwner = commentEntryOwner;
    }
}
