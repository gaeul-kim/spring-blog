package xyz.sangsik.blog.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private String DEFAULT_URL;

    public CustomLoginSuccessHandler(String defaultUrl) {
        this.DEFAULT_URL = defaultUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        getRedirectStrategy().sendRedirect(request, response, decideRedirectStrategy(request, response));
    }

    private String decideRedirectStrategy(HttpServletRequest request, HttpServletResponse response) {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        HttpSession session = request.getSession();

        if (session != null) {
            String prevPage = (String) session.getAttribute("prevPage");
            if (prevPage != null) {
                session.removeAttribute("prevPage");
                return prevPage;
            }
        }

        if (savedRequest != null) {
            requestCache.removeRequest(request, response);
            return savedRequest.getRedirectUrl();
        }

        return this.DEFAULT_URL;
    }


}