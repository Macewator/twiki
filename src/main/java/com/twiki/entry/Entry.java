package com.twiki.entry;

import com.twiki.account.Account;
import com.twiki.util.EntryStatus;
import com.twiki.util.EntryType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDate entryCreateDate;
    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;
    @Enumerated(value = EnumType.STRING)
    private EntryType entryType;

    @ManyToOne
    @JoinColumn(name = "entry_owner_id")
    private Account entryOwner;

    @ManyToMany
    @JoinTable(name = "favorites_entries",joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "entry_id"))
    private Set<Account> accounts = new HashSet<>();

    public Entry() {
    }

    public Entry(String content, LocalDate entryCreateDate, EntryStatus entryStatus, EntryType entryType) {
        this.content = content;
        this.entryCreateDate = entryCreateDate;
        this.entryStatus = entryStatus;
        this.entryType = entryType;
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

    public LocalDate getEntryCreateDate() {
        return entryCreateDate;
    }

    public void setEntryCreateDate(LocalDate entryCreateDate) {
        this.entryCreateDate = entryCreateDate;
    }

    public EntryStatus getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(EntryStatus entryStatus) {
        this.entryStatus = entryStatus;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }

    public Account getEntryOwner() {
        return entryOwner;
    }

    public void setEntryOwner(Account entryOwner) {
        this.entryOwner = entryOwner;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
