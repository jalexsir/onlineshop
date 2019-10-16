package controllers;

import dao.DAOFactory.DAOFactory;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import util.FormValidator;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationAndAuthController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get() {
        ModelAndView model = new ModelAndView("pageRegistrationAndAuth");
        HttpSession session = session();
        session.setAttribute("isReg", "reg");
        if (session.getAttribute("authLogin") != null && session.getAttribute("enteringError") != null) {
            model.addObject("authLogin", session.getAttribute("authLogin"));
            model.addObject("enteringError", session.getAttribute("enteringError"));
            session.removeAttribute("authLogin");
            session.removeAttribute("enteringError");
        }
        return model;
    }

    @RequestMapping(method = RequestMethod.POST, params = "key=auth")
    public String authUser(ModelMap model, @RequestParam(value = "login", required = false) String login,
                           @RequestParam(value = "password", required = false) String password) {
        User user = null;
        HttpSession session = session();
        if (login != null) {
            if (FormValidator.checkLoginAndPassword(login, password) &&
                    ((user = DAOFactory.getInstance(1).getUserDAO().
                            getUserByLoginAndPass(login.toLowerCase(), password))) != null) {
                session.setAttribute("elogin", login);
                session.setAttribute("name", user.getName());
                session.removeAttribute("isReg");
                return new ModelAndView("redirect:/").getViewName();
            } else {
                model.addAttribute("enteringError", "Wrong login or password");
                model.addAttribute("login", login);
                if (session.getAttribute("isReg") == null) {
                    session.setAttribute("authLogin", login);
                    session.setAttribute("enteringError", "Wrong login or password");
                    return new ModelAndView("redirect:/").getViewName();
                }
            }
        }
        return "pageRegistrationAndAuth";
    }

    @RequestMapping(method = RequestMethod.POST, params = "key=reg")
    public ModelAndView registerUser(@RequestParam(value = "login", required = false) String login,
                                     @RequestParam(value = "password", required = false) String password,
                                     @RequestParam(value = "repassword", required = false) String repassword,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "region", required = false) String region,
                                     @RequestParam(value = "gender", required = false) String gender,
                                     @RequestParam(value = "comment", required = false) String comment,
                                     @RequestParam(value = "agreement", required = false) String agreement
    ) {
        ModelAndView model = new ModelAndView();
        HttpSession session = session();

        if (session.getAttribute("isReg") != null) {
            session.removeAttribute("isReg");
        }
        Map<String, Object> errorMap = FormValidator.checkDataforRegistrationForm(login, password, repassword, name, region,
                gender, comment, agreement);
        String existError = (String) errorMap.get("userError");
        User createdUser = (User) errorMap.get("createdUser");
        if (existError == null) {
            DAOFactory.getInstance(1).getUserDAO().insertUser(createdUser);
            model.setViewName("pageSuccessRegistration");
        } else {
            model.setViewName("pageRegistrationAndAuth");
            model.addObject("newUser", createdUser);
            model.addAllObjects(errorMap);
        }
        return model;
    }

    //Util methods
    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }
}
