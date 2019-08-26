package com.twiki.entry;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/entry")
public class EntryController {

    private EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    /*@PostMapping("/publish")
    public String publish(@RequestParam String entryContent, Authentication auth){
        entryService.publishEntry(entryContent, auth.getName());
    }*/
}
