package com.twiki.account;

import com.twiki.Security.AccountRoles;
import com.twiki.Security.AccountRolesRepository;
import com.twiki.entry.Entry;
import com.twiki.util.UserEntry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AccountService {

    private static final String DEFAULT_ROLE = "ROLE_USER";

    private AccountRepository accountRepository;
    private AccountRolesRepository accountRolesRepository;
    private PasswordEncoder passwordEncoder;
    private UserEntry userEntry;

    public AccountService(AccountRepository accountRepository, AccountRolesRepository accountRolesRepository, PasswordEncoder passwordEncoder, UserEntry userEntry) {
        this.accountRepository = accountRepository;
        this.accountRolesRepository = accountRolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.userEntry = userEntry;
    }

    void saveAccount(Account account){
        AccountRoles accountRole = accountRolesRepository.findByRole(DEFAULT_ROLE);
        account.getAccountRoles().add(accountRole);
        String passwordHash = passwordEncoder.encode(account.getPassword());
        account.setPassword(passwordHash);
        accountRepository.save(account);
    }

    Account getAccount(String login){
        return accountRepository.findByLogin(login)
                .orElseThrow(()-> new NoSuchElementException("user not founded"));
    }

    Entry prepareEntry(){
        return userEntry.prepareEntry();
    }

    void publishEntry(Entry entry, String name) {
        Account account = getAccount(name);
        account.getEntries().add(entry);
        accountRepository.save(account);
    }
}
