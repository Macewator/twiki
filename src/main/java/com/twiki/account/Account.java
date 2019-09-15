package com.twiki.account;

import com.twiki.Security.AccountRoles;
import com.twiki.entry.post.Post;
import com.twiki.observedAccounts.ObservedAccounts;
import com.twiki.util.AccountStatus;
import com.twiki.util.AccountType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    private String login;
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message = "The password must contain 8 characters, including one upper case letter and one special character")
    private String password;
    private String name;
    private String accountDescription;
    @DateTimeFormat
    private LocalDate createDate;
    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;
    private String avatar;
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    @OneToMany(mappedBy = "postOwner", cascade = CascadeType.ALL)
    private Set<Post> posts = new TreeSet<>();

    @ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
    private Set<Post> favoritesPosts = new TreeSet<>();

    @ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
    private Set<Post> sharedPosts = new TreeSet<>();

    @OneToMany(mappedBy = "observerAccount", cascade = CascadeType.ALL)
    private Set<ObservedAccounts> observedAccounts = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "access_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "account_role_id"))
    private Set<AccountRoles> accountRoles = new HashSet<>();

    public Account() {
    }

    public Account(@Email String login, @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
            String password, String name, String accountDescription, LocalDate createDate, AccountStatus accountStatus,
                   String avatar, AccountType accountType) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.accountDescription = accountDescription;
        this.createDate = createDate;
        this.accountStatus = accountStatus;
        this.avatar = avatar;
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Post> getFavoritesPosts() {
        return favoritesPosts;
    }

    public void setFavoritesPosts(Set<Post> favoritesPosts) {
        this.favoritesPosts = favoritesPosts;
    }

    public Set<Post> getSharedPosts() {
        return sharedPosts;
    }

    public void setSharedPosts(Set<Post> sharedPosts) {
        this.sharedPosts = sharedPosts;
    }

    public Set<ObservedAccounts> getObservedAccounts() {
        return observedAccounts;
    }

    public void setObservedAccounts(Set<ObservedAccounts> observedAccounts) {
        this.observedAccounts = observedAccounts;
    }

    public Set<AccountRoles> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(Set<AccountRoles> accountRoles) {
        this.accountRoles = accountRoles;
    }
}
