package com.codegym.controllers;

import com.codegym.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    HttpSession httpSession;

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @GetMapping("/signin")
    public String getSigninForm(@CookieValue(defaultValue = "") String email, @CookieValue(defaultValue = "") String password, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        model.addAttribute("message", "");
        return "sign-in";
    }

    @PostMapping("/signin")
    public String doSignin(@ModelAttribute User user, @RequestParam(defaultValue = "") String rememberMe, HttpServletResponse response, Model model) {
        if (user.getEmail().equals("admin@codegym.vn") && user.getPassword().equals("123456")) {
            httpSession.setAttribute("isSignedIn", true);

            if (rememberMe.equals("remember-me")) {
                Cookie savedEmail = new Cookie("email", user.getEmail());
                Cookie savedPassword = new Cookie("password", user.getPassword());

                response.addCookie(savedEmail);
                response.addCookie(savedPassword);
            }
            model.addAttribute("message", "login Successfully!!!");
            return "redirect:/";
        } else {
            model.addAttribute("message", "login Failed!!!");
            return "sign-in";
        }
    }
}
