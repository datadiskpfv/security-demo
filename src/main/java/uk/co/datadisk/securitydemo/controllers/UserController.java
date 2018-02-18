package uk.co.datadisk.securitydemo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uk.co.datadisk.securitydemo.domain.entities.User;
import uk.co.datadisk.securitydemo.services.SecurityService;
import uk.co.datadisk.securitydemo.services.UserService;
import uk.co.datadisk.securitydemo.validators.UserValidator;

import java.util.Collection;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //@Autowired
    //private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        log.debug("You requested to registrar");
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        log.debug("Processing the registration");
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.debug("You have validation errors");
            return "registration";
        }

        userService.save(userForm);

        //securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        log.debug("Accessing login GET");

        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        log.debug("Accessing login GET");
        return "home";
    }

    @GetMapping({"/","/welcome"})
    public String welcome(Model model) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        log.debug("Accessing the welcome web page: " + authorities);
        return "welcome";
    }
}