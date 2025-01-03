package com.example.pathfinder.web;

import com.example.pathfinder.model.enums.UserLevel;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.service.dtos.UserProfileDto;
import com.example.pathfinder.web.dtos.UserLoginDto;
import com.example.pathfinder.web.dtos.UserRegisterDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String viewRegister(Model model) {
        model.addAttribute("registerData", new UserRegisterDto());
        model.addAttribute("levels", UserLevel.values());

        return "register";
    }

    @PostMapping("users/register")
    public String doRegister(
            @Valid UserRegisterDto data,
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

    @PostMapping("users/login")
    private String doLogin(UserLoginDto loginData) {
        userService.login(loginData);
        return "redirect:/";
    }

    @PostMapping("users/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("users/profile")
    private ModelAndView viewProfile() {
        ModelAndView mnv = new ModelAndView("profile");
        mnv.addObject("profileDta", userService.getProfileData());
        return mnv;
    }
}
