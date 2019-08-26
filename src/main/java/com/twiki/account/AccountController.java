package com.twiki.account;

import com.twiki.entry.Entry;
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
    public String home() {
        return "login";
    }

    @GetMapping("/account/register")
    public String registerForm(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/account/register")
    public String registerAccount(@ModelAttribute @Valid Account account, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        accountService.saveAccount(account);
        return "redirect:/account";
    }

    @GetMapping("/account/wall")
    public String displayAccountWall(Authentication auth, Model model) {
        try {
            model.addAttribute("account", accountService.getAccount(auth.getName()));
        } catch (NoSuchElementException e) {
            model.addAttribute("exception", e.getMessage());
        }
        model.addAttribute("entry", accountService.prepareEntry());
        return "account";
    }

    @PostMapping("/account/entry/publish")
    public String publish(@ModelAttribute Entry entry, Authentication auth) {
        accountService.publishEntry(entry, auth.getName());
        return "redirect:/account/wall";
    }
}
