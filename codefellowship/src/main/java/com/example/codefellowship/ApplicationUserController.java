package com.example.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

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

        //Ensure that user registration also logs users into your app automatically.
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/profile")
    public String getProfile(Principal p, Model m){
        m.addAttribute("user",applicationUserRepository.findByUsername(p.getName()));
        return "profile.html";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable ("id") Long id, Model m){
        m.addAttribute("user", applicationUserRepository.findById(id).get());
        return "userSearch.html";
    }

    @GetMapping ("/allUser")
    public String getAllUsers(Model m,Principal p){
        ApplicationUser getUser=applicationUserRepository.findByUsername(p.getName());

        m.addAttribute("users",applicationUserRepository.findAll());
        m.addAttribute("logedinUser",getUser);
        return "allUser.html";
    }

    @PostMapping("/follow/{id}")
    public RedirectView followUser(Principal p,@PathVariable Long id){
        ApplicationUser getUser=applicationUserRepository.findByUsername(p.getName());
       ApplicationUser user=applicationUserRepository.findById(id).get();
       getUser.addUserToFollow(user);
       applicationUserRepository.save(getUser);
        return new RedirectView("/allUser");
    }
    @PostMapping("/unfollow/{id}")
    public RedirectView unfollowUser(Principal p,@PathVariable Long id){
        ApplicationUser getUser=applicationUserRepository.findByUsername(p.getName());
        ApplicationUser user=applicationUserRepository.findById(id).get();
        getUser.removeUserFromFollow(user);
        applicationUserRepository.save(getUser);
        return new RedirectView("/allUser");
    }
    @GetMapping("/feed")
    public String getFeed(Principal p,Model m){
        m.addAttribute("feeds",applicationUserRepository.findByUsername(p.getName()));
        return "feed.html";
    }
    @GetMapping("/getUser/{id}")
    public String getuser(@PathVariable Long id, Model m){
        m.addAttribute("user",applicationUserRepository.findById(id).get());
        return "userProfile.html";
    }


}
