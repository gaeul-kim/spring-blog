package xyz.sangsik.blog.web.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private String DEFAULT_URL;

    public CustomAuthenticationFailureHandler(String defaultUrl) {
        this.DEFAULT_URL = defaultUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("password", request.getParameter("password"));
        request.getRequestDispatcher(DEFAULT_URL).forward(request, response);
    }
}
