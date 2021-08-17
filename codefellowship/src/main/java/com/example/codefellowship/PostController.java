package com.example.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/addpost")
    public RedirectView addpost(@RequestParam(value = "body")String body,
                                Principal p, Model m){
        // DateFormat dateFormat= new SimpleDateFormat("mm/dd/yy HH:mm:ss");
        Date time=new Date();
        Post newPost=new Post(body,time,applicationUserRepository.findByUsername(p.getName()));
        postRepository.save(newPost);
        m.addAttribute("post",newPost);
        return new RedirectView("/profile");
    }
}
