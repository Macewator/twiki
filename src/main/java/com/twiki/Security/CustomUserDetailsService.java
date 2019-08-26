package com.twiki.Security;

import com.twiki.account.Account;
import com.twiki.account.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CustomUserDetailsService  implements UserDetailsService {

    private AccountRepository  accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByLogin(login);
        if(account.isPresent()){
            return new org.springframework.security.core.userdetails.User(
                    account.get().getLogin(),
                    account.get().getPassword(),
                    convertAuthorities(account.get().getAccountRoles()));
        }else {
            throw  new UsernameNotFoundException("Account not found");
        }
    }

    private Set<GrantedAuthority> convertAuthorities(Set<AccountRoles> accountRoles){
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (AccountRoles ar: accountRoles){
            authorities.add(new SimpleGrantedAuthority(ar.getRole()));
        }
        return authorities;
    }
}
