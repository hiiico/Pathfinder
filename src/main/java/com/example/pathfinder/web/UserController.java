package com.example.pathfinder.web;

import com.example.pathfinder.model.enums.UserLevel;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.web.dto.UserLoginDto;
import com.example.pathfinder.web.dto.UserRegister;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/register")
    public ModelAndView viewRegister() {
        ModelAndView mnv = new ModelAndView("register");
        mnv.addObject("registerData", new UserRegister());
        mnv.addObject("levels", UserLevel.values());
        return mnv;
    }

    @PostMapping("users/register")
    public String doRegister(
            @Valid UserRegister data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserRegisterDto", bindingResult);

            return "redirect:users/register";
        }
        userService.register(data);
        return "redirect:/users/login";
    }

    @GetMapping("users/login")
    public ModelAndView viewLogin() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginData", new UserLoginDto());
        return modelAndView;
    }

    @GetMapping("users/login-error")
    public ModelAndView viewLoginError() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("loginData", new UserLoginDto());
        return modelAndView;
    }

    @GetMapping("users/profile")
    private ModelAndView viewProfile() {
        ModelAndView mnv = new ModelAndView("profile");
        mnv.addObject("profileData", userService.getProfileData());
        return mnv;
    }

}
