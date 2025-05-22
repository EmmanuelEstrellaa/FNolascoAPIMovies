package com.digis01.FNolascoAPIMovies.Controller;

import com.digis01.FNolascoAPIMovies.ML.Result;
import com.digis01.FNolascoAPIMovies.ML.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Controller
@RequestMapping("/Movies")
public class MovieController {

    private RestTemplate restTemplate = new RestTemplate();
    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkNTI2YzYwMGEwNTc4ZWI0OGRiZTg3ZjkzZDhlOTQ2NyIsIm5iZiI6MTc0NzMzNzY5Ny44NDcwMDAxLCJzdWIiOiI2ODI2NDFlMTg0NTg1Yzk5MTQzMTU5OTAiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.zIyxI1UbM3O19KDdoYvHVL0A6KCRzzwdF9aQn-uBzLM";

    @GetMapping
    public String Index() {
        return "MovieIndex";
    }

    @GetMapping("/login")
    public String LogIn(Usuario usuario, Model model) {
        model.addAttribute("usuario", usuario);
        return "UserLogin";
    }

    @PostMapping("/inicio")
    public ResponseEntity LogIn(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        Result result = new Result();
        try {
            HttpHeaders httpHeaderToken = new HttpHeaders();
            httpHeaderToken.setBearerAuth(token);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaderToken);

            ResponseEntity<Usuario> responseToken = restTemplate.exchange("https://api.themoviedb.org/3/authentication/token/new",
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Usuario>() {
            });

            if (responseToken.getStatusCode().is2xxSuccessful()) {
                // Obtener el request_token de la respuesta
                String requestToken = responseToken.getBody().getRequest_token();

                // Puedes guardarlo en sesión, mandarlo al modelo, etc.
                session.setAttribute("requestToken", requestToken);

                // Aquí podrías redirigir al siguiente paso (por ejemplo, validar login o crear sesión)
                return ResponseEntity.ok("Token generado: " + requestToken);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Fallo al generar token");
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar token: " + ex.getMessage());
        }

    }

}
