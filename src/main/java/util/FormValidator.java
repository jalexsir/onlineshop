package util;


import dao.DAOFactory.DAOFactory;
import entity.User;

import java.util.HashMap;
import java.util.Map;


public class FormValidator {
    public static boolean checkLoginAndPassword(String login, String password) {
        boolean flag = false;
        if (!login.isEmpty() && !password.isEmpty()) {
            if (login.matches("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$") && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,10}$")) {
                flag = true;
            }
        }
        return flag;
    }

    public static Map<String, Object> checkDataforRegistrationForm(String login, String password,
                                                                   String repassword, String name,
                                                                   String region, String gender,
                                                                   String comment, String agreement) {
        boolean isError = false;
        User user = new User();


        //Map errorMap contains String error messages and User Object
        Map<String, Object> errorMap = new HashMap<>();

        /**********Check Block Start***********/
        //check login
        if (!login.isEmpty() && regexChecker(login, "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$")) {
            user.setLogin(login.toLowerCase());
        } else {
            isError = true;
            errorMap.put("errorLogin", "Login isn't correct, must be e-mail");
        }

        //check existed login in db
        if (!isError) {
            if (DAOFactory.getInstance(1).getUserDAO().getUserByLogin(user.getLogin()) != null) {
                isError = true;
                errorMap.put("errorLogin", "This login is already registered");
            }
        }

        //check password and repassword
        if ((!password.isEmpty() || !repassword.isEmpty()) && (regexChecker(password, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,10}$")
                && regexChecker(password, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,10}$"))) {
            if (password.equals(repassword)) {
                user.setPassword(password);
            } else {
                isError = true;
                errorMap.put("errorPass", "Password aren't similar");
            }
        } else {
            isError = true;
            errorMap.put("errorPass", "Password or Repassword isn't correct");
        }

        //check name
        if (!name.isEmpty() && regexChecker(name, "[a-zA-Z]{2,15}")) {
            user.setName(name);
        } else {
            isError = true;
            errorMap.put("errorName", "Name format isn't correct");
        }

        //check region
        if (!region.isEmpty() && regexChecker(region, "[a-zA-Z]{4,15}")) {
            user.setRegion(region);
        } else {
            isError = true;
            errorMap.put("errorRegion", "City format isn't correct");
        }

        //Check gender
        if (gender != null) {
            user.setGender((gender.equals("male") ? true : false));
        } else {
            isError = true;
            errorMap.put("errorGender", "Sex is empty");
        }

        //Check comment
        if (!comment.isEmpty() && regexChecker(comment, ".{4,250}")) {
            user.setComment(comment);
        } else {
            isError = true;
            errorMap.put("errorComment", "Comment is empty or so big");
        }

        //Check agreement
        if (agreement == null) {
            isError = true;
            errorMap.put("errorAgreement", "No agreement");
        }

        /**********Check Block End***********/


        if (isError) {
            errorMap.put("userError", "flag");
        }
        errorMap.put("createdUser", user);

        return errorMap;
    }

    public static Map<String, Object> checkDataforUpdatingForm(String name, String region,
                                                               String gender, String comment, String agreement) {
        boolean isError = false;
        User user = new User();

        //Map errorMap contains String error messages and User object
        Map<String, Object> errorMap = new HashMap<>();

        /**********Check Block Start***********/

        //check name
        if (!name.isEmpty() && regexChecker(name, "[a-zA-Z]{2,15}")) {
            user.setName(name);
        } else {
            isError = true;
            errorMap.put("errorName", "Name format isn't correct");
        }

        //check region
        if (!region.isEmpty() && regexChecker(region, "[a-zA-Z]{4,15}")) {
            user.setRegion(region);
        } else {
            isError = true;
            errorMap.put("errorRegion", "City format isn't correct");
        }

        //Check gender
        if (!gender.equals("")) {
            user.setGender((gender.equals("male") ? true : false));
        } else {
            isError = true;
            errorMap.put("errorGender", "Sex is empty");
        }

        //Check comment
        if (!comment.isEmpty() && regexChecker(comment, ".{4,250}")) {
            user.setComment(comment);
        } else {
            isError = true;
            errorMap.put("errorComment", "Comment is empty or so big");
        }

        //Check agreement
        if (agreement == null) {
            isError = true;
            errorMap.put("errorAgreement", "No agreement");
        }

        /**********Check Block End***********/


        if (isError) {
            errorMap.put("userError", "flag");
        }
        errorMap.put("newUser", user);

        return errorMap;
    }


    //Checker matches
    private static boolean regexChecker(String text, String regex) {
        return text.matches(regex);
    }
}
