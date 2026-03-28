package com.springboot.example.school.Auth;

import com.springboot.example.school.Enum.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        // Google provides "email", GitHub provides "login"
        String username = oauth2User.getAttribute("email");
        if (username == null) {
            username = oauth2User.getAttribute("login");
        }

        if (!userRepository.existsByUsername(username)) {
            User user = User.builder()
                    .username(username)
                    .password("")
                    .role(Role.ROLE_USER)
                    .build();
            userRepository.save(user);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(userDetails);

        // redirect to swagger with token as query param
        response.sendRedirect("/swagger-ui/index.html?token=" + token);
    }
}
