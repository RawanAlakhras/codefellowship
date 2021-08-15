package com.example.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String getHome(){


            return "index.html";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "login.html";
    }

    @GetMapping("/signup")
    public String getSignup(){

        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView signup(@RequestParam(value = "username") String username,
                               @RequestParam (value = "password") String password,
                               @RequestParam (value = "firstName") String firstName,
                               @RequestParam (value = "lastName") String lastName,
                               @RequestParam (value = "dateOfBirth") String dateOfBirth,
                               @RequestParam (value = "bio") String bio){
        ApplicationUser newUser=new ApplicationUser(username,bCryptPasswordEncoder.encode(password),
                firstName,lastName,dateOfBirth,bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/login");
    }

    @GetMapping("/profile")
    public String getProfile(Principal p, Model m){
        m.addAttribute("user",applicationUserRepository.findByUsername(p.getName()));


        return "profile.html";
    }
}
