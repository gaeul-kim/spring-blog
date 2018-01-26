package xyz.sangsik.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.sangsik.blog.model.ResponseObject.HttpResponse;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("")
    public String home() {
        return "redirect:/posts";
    }

    @ResponseBody
    @PostMapping("/saveCurrentURL")
    public HttpResponse saveCurrentURL(HttpServletRequest request, HttpResponse httpResponse) {
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referer);
        return httpResponse.success();
    }
}
