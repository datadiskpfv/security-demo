package uk.co.datadisk.securitydemo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/shopping")
@PreAuthorize("hasAuthority('SHOPPING')")
public class ShoppingController {

    @GetMapping("/shopping")
    public String shopping(Model model) {
        log.debug("Accessing the shopping web page");
        return "shopping";
    }
}