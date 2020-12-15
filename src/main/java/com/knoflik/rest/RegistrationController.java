package com.knoflik.rest;

import com.knoflik.entities.User;
import com.knoflik.services.PackageParsingService;
import com.knoflik.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private PackageParsingService packageParsingService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String registration(final Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("userForm") final User userForm,
                          final HttpServletRequest request,
                          final BindingResult bindingResult,
                          final Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError",
                    "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }
}
