package com.twiki.observedAccounts;

import com.twiki.account.Account;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ObservedAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate observingStartDate;

    @ManyToOne
    @JoinColumn(name = "observer_account_id")
    private Account observerAccount;

    @ManyToOne
    @JoinColumn(name = "observed_account_id")
    private Account observedAccount;

    public ObservedAccounts() {
    }

    public ObservedAccounts(LocalDate observingStartDate) {
        this.observingStartDate = observingStartDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getObservingStartDate() {
        return observingStartDate;
    }

    public void setObservingStartDate(LocalDate observingStartDate) {
        this.observingStartDate = observingStartDate;
    }

    public Account getObserverAccount() {
        return observerAccount;
    }

    public void setObserverAccount(Account observerAccount) {
        this.observerAccount = observerAccount;
    }

    public Account getObservedAccount() {
        return observedAccount;
    }

    public void setObservedAccount(Account observedAccount) {
        this.observedAccount = observedAccount;
    }
}
