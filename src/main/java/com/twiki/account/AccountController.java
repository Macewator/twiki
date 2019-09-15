package com.twiki.account;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String home(Authentication auth, Model model) {
        if (auth != null) {
            model.addAttribute("accountName", accountService.getAccountNameByLogin(auth.getName()));
        }
        return "login";
    }

    @GetMapping("/accounts/register")
    public String registerForm(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/accounts/register")
    public String registerAccount(@ModelAttribute @Valid Account account, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        accountService.saveAccount(account);
        return "login";
    }

    @GetMapping("/accounts/{accountName}/wall")
    public String displayAccountWall(@PathVariable String accountName, Model model) {
        try {
            model.addAttribute("account", accountService.getAccountByName(accountName));
        } catch (NoSuchElementException e) {
            model.addAttribute("exception", e.getMessage());
        }
        return "account";
    }
}
