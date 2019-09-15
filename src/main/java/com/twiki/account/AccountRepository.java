package com.twiki.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByLogin(String login);

    Optional<Account> findByName(String name);

    @Query("SELECT a.name FROM Account a WHERE a.login = :login")
    String findAccountName(@Param("login") String login);
}
