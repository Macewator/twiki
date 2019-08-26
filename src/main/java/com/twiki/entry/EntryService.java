package com.twiki.entry;

import com.twiki.account.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class EntryService {

    private EntryRepository entryRepository;
    private AccountRepository accountRepository;

    public EntryService(EntryRepository entryRepository, AccountRepository accountRepository) {
        this.entryRepository = entryRepository;
        this.accountRepository = accountRepository;
    }

    void publishEntry(String entryContent, String accountLogin) {

    }
}
