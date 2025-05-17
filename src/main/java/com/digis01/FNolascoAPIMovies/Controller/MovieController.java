package com.digis01.FNolascoAPIMovies.Controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/Movies")
public class MovieController {
    
    @GetMapping
    public String Index(Model model){
        return "MovieIndex";
    }
    
    @GetMapping("/login")
    public String LogIn(){
        return "UserLogin";
    }
    
}
