package com.pagil.teruel_express.jwt;

import com.pagil.teruel_express.model.dto.HomePageDto;
import com.pagil.teruel_express.model.entity.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserContextService {

    private final JwtUserDetailsService userDetailsService;

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            return auth.getName();
        }
        throw new RuntimeException("Usuário não autenticado");
    }

    public Long getCurrentUserId() {
        String username = getCurrentUsername();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails instanceof JwtUserDetails jwtUserDetails) {
            return jwtUserDetails.getId();
        }
        throw new RuntimeException("Não foi possível obter o ID do usuário");
    }

    public Pessoa getCurrentPessoa() {
        String username = getCurrentUsername();
        return userDetailsService.getPessoaLogada(username);
    }

    public HomePageDto getCurrentHomePageInfo() {
        String username = getCurrentUsername();
        return userDetailsService.getNomeTipoPessoaLogado(username);
    }

}
