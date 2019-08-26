package com.twiki.Security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRolesRepository extends JpaRepository<AccountRoles, Long> {

    AccountRoles findByRole(String role);
}
