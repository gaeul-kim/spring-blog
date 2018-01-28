package xyz.sangsik.blog.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String referrerUrl = request.getHeader("Referer");
        if (referrerUrl != null) {
            // todo : 인증이 필요한 refererUrl 일 경우 기본 URL로 이동할 수 있도록 변경
            getRedirectStrategy().sendRedirect(request, response, referrerUrl);
        }
        super.onLogoutSuccess(request, response, authentication);
    }
}