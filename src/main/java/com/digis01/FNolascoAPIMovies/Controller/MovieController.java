package com.digis01.FNolascoAPIMovies.Controller;

import com.digis01.FNolascoAPIMovies.ML.Result;
import com.digis01.FNolascoAPIMovies.ML.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/Movies")
public class MovieController {
    
    private RestTemplate restTemplate = new RestTemplate();
    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkNTI2YzYwMGEwNTc4ZWI0OGRiZTg3ZjkzZDhlOTQ2NyIsIm5iZiI6MTc0NzMzNzY5Ny44NDcwMDAxLCJzdWIiOiI2ODI2NDFlMTg0NTg1Yzk5MTQzMTU5OTAiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.zIyxI1UbM3O19KDdoYvHVL0A6KCRzzwdF9aQn-uBzLM";
    
    @GetMapping
    public String Index(Model model){
        return "MovieIndex";
    }

            
    @GetMapping
    public String LogIn(@ModelAttribute Usuario usuario, Model model){
        model.addAttribute("model", model);
        return "UserLogin";
    }
    
    @PostMapping("/login")
    public String LogIn(@ModelAttribute Usuario usuario, Model model, HttpSession session){
        Result result = new Result();
        try{
            HttpHeaders httpHeaderToken = new HttpHeaders();
            httpHeaderToken.setBearerAuth(token);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaderToken);
            
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        
        
        return null;
    }
    
}
