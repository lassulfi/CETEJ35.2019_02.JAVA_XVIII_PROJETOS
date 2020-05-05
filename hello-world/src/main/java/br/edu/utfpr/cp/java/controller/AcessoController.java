package br.edu.utfpr.cp.java.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.utfpr.cp.java.entity.UsuarioPrincipal;

@Controller
public class AcessoController {

    @GetMapping("/admin")
    public String adminPage() {
        return "/adminPage.html";
    }

    @GetMapping("/private")
    public String privatePage(
        HttpServletRequest request, 
        // HttpServletResponse response, 
        Authentication usuarioPrincipal){
        // var usuarioPrincipal = (UsuarioPrincipal) SecurityContextHolder
        //     .getContext().getAuthentication().getPrincipal();
        // response.addCookie(new Cookie("usuarioAtual", usuarioPrincipal.getUsername()));

        request.getSession().setAttribute("usuarioAtual", (UserDetails) usuarioPrincipal.getPrincipal());

        return "/privatePage.html";
    }

    @GetMapping("/public")
    public String publicPage() {
        return "/publicPage.html";
    }

    @GetMapping("/user")
    public String userPage(Model memoria, 
        // @CookieValue String usuarioAtual
        HttpServletRequest request) {
        var usuarioAtual = (UsuarioPrincipal) request.getSession().getAttribute("usuarioAtual");

        // memoria.addAttribute("usuario", usuarioAtual);
        memoria.addAttribute("roles", usuarioAtual.getAuthorities().toString());
        
        return "/userPage";
    }
}