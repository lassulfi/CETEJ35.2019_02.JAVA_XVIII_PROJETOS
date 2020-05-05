package br.edu.utfpr.cp.java.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.edu.utfpr.cp.java.entity.UsuarioPrincipal;

@Component
public class LoginListener implements 
    ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        var usuario = (UsuarioPrincipal) event.getAuthentication().getPrincipal();        
    
        var request = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        request.getRequest().getSession().setAttribute("usuarioAtual", usuario);
    }
}