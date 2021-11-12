package com.LibreriaApp.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Aleidy Alfonzo
 */
@Controller
public class MainController {
    
    @GetMapping("/")
    public String inicio(){
        return "index.html";
    }

}
