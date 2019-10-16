package controllers;

import dao.DAOFactory.DAOFactory;
import entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView get() {
        ModelAndView model = new ModelAndView("pageProductList");
        model.addObject("productList", DAOFactory.getInstance(1).getProductDAO().getAllProducts());
        return model;
    }

    @RequestMapping(value = "/category/{catId}", method = RequestMethod.GET)
    public ModelAndView getCategory(@PathVariable String catId) {
        ModelAndView model = new ModelAndView("pageProductList");
        model.addObject("productList", DAOFactory.getInstance(1).getProductDAO().getProductsByCategory(Integer.parseInt(catId)));
        return model;
    }

    @RequestMapping(value = "/detail/{productId}", method = RequestMethod.GET)
    public ModelAndView getDetail(@PathVariable String productId) {
        ModelAndView model = new ModelAndView("pageDetailProduct");
        Product product = DAOFactory.getInstance(1).getProductDAO().getProductById(Integer.parseInt(productId));
        if (product != null) {
            model.addObject("productById", product);
        } else {
            model.setViewName("pageNotFound");
        }
        return model;
    }
}
