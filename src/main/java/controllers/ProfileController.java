package controllers;

import dao.DAOFactory.DAOFactory;
import entity.User;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/profile")
public class ProfileController {

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView get(ModelAndView model) {
        String login = (String) session().getAttribute("elogin");
        model.setViewName("pageProfile");
        User existedUser = DAOFactory.getInstance(1).getUserDAO().getUserByLogin(login);
        if (existedUser != null) {
            model.addObject("user", existedUser);
        }
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "region", required = false) String region,
                                     @RequestParam(value = "gender", required = false) String gender,
                                     @RequestParam(value = "comment", required = false) String comment,
                                     @RequestParam(value = "agreement", required = false) String agreement
    ) {
        HttpSession session = session();
        ModelAndView model = new ModelAndView();
        String login = (String) session.getAttribute("elogin");

        Map<String, Object> errorMap = FormValidator.checkDataforUpdatingForm(name, region, gender, comment, agreement);
        User updatedUser = (User) errorMap.get("newUser");
        updatedUser.setLogin(login.toLowerCase());

        String userError = (String) errorMap.get("userError");
        if (userError == null) {
            session.setAttribute("name", updatedUser.getName());
            DAOFactory.getInstance(1).getUserDAO().updateUser(updatedUser);
            model.setViewName("pageSuccessProfileUpdate");
        } else {
            model.addAllObjects(errorMap);
            model.addObject("user", updatedUser);
            model.setViewName("pageProfile");
        }
        return model;
    }


    //Util methods
    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }
}
