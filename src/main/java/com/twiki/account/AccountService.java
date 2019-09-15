package com.twiki.account;

import com.twiki.Security.AccountRoles;
import com.twiki.Security.AccountRolesRepository;
import com.twiki.util.AccountStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class AccountService {

    private static final String DEFAULT_ROLE = "ROLE_USER";

    private AccountRepository accountRepository;
    private AccountRolesRepository accountRolesRepository;
    private PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, AccountRolesRepository accountRolesRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountRolesRepository = accountRolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    void saveAccount(Account account){
        AccountRoles accountRole = accountRolesRepository.findByRole(DEFAULT_ROLE);
        account.getAccountRoles().add(accountRole);
        String passwordHash = passwordEncoder.encode(account.getPassword());
        account.setPassword(passwordHash);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCreateDate(LocalDate.now());
        accountRepository.save(account);
    }

    Account getAccountByName(String name){
        return accountRepository.findByName(name)
                .orElseThrow(()-> new NoSuchElementException("user not founded"));
    }

    Account getAccountByLogin(String login){
        return accountRepository.findByLogin(login)
                .orElseThrow(()-> new NoSuchElementException("user not founded"));
    }

    String getAccountNameByLogin(String login){
        return accountRepository.findAccountName(login);
    }
}
