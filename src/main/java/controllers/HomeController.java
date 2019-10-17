package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String get(@RequestParam(value = "logout", required = false) String logout, HttpSession session) {
        if (logout != null) {
            if (session.getAttribute("name") != null) {
                session.invalidate();
            }
        }
        return "pageHome";
    }
}



