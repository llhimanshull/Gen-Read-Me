package com.ReadMe.Controller;

import com.ReadMe.Service.ReadmeService;
import com.ReadMe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReadmeController {

    @Autowired
    private ReadmeService readmeService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model , OAuth2AuthenticationToken authentication) {

        userService.storeUser(model , authentication);
        return "index";
    }

    @GetMapping("/generate")
    public String generatePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser");
        model.addAttribute("isLoggedIn", isAuthenticated);
        return "result";
    }

    @PostMapping("/generate")
    public String generate(@RequestParam String repoUrl, Model model) {
        String markdown = readmeService.generateReadmeFromRepo(repoUrl);
        String html = readmeService.convertMarkdownToHtml(markdown);


        model.addAttribute("readme", markdown);
        model.addAttribute("content", html);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser");
        model.addAttribute("isLoggedIn",isAuthenticated);

        return "result";
    }
}
