package com.example.ConexionBD_3572.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

 @Controller
public class Catalogo {
    
     @GetMapping("/catalogo/")
     public String inicio(){
         
         return "catalogo";
     }
     
     
}