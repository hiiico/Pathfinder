package com.example.pathfinder.web;

import com.example.pathfinder.web.dtos.UserRegisterDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @GetMapping("users/register")
    public String viewRegister(Model model) {
        model.addAttribute("registerData", new UserRegisterDto());

        return "register";
    }

    @PostMapping("users/register")
    public String doRegister(
            @Valid UserRegisterDto data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("registerData", data);

            return "register";
        }
        return "redirect:/users/login";
    }

    @GetMapping("users/login")
    public String viewLogin() {
        return "login";
    }
}
