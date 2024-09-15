/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ConexionBD_3572.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorLogin {
    @GetMapping("/login")
    public String login() {
        return "login"; //login.html
    }
    
    @GetMapping("/acceso-denegado")
    public String Denegado() {
        return "denegado"; //denegado.html
    }
}
