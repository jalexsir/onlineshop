package controllers;

import dao.DAOFactory.DAOFactory;
import entity.Cart;
import entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;


@Controller
@RequestMapping("/cart")
public class CartController {

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView getCart() {
        ModelAndView model = new ModelAndView("pageCart");
        HttpSession session = session();
        if (session.getAttribute("cart") != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            model.addObject("productListCart", cart.getProducts());
            model.addObject("summ", cart.getSumm());
        }
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView changeProductQtyInCart(@RequestParam(value = "productId", required = false) String productId,
                                                  @RequestParam(value = "qnt", required = false) String qnt) {
        ModelAndView model = new ModelAndView("pageCart");
        Cart cart = null;
        if (productId != null) {
            HttpSession session = session();
            cart = getCart(session);
            cart.getProducts().put(DAOFactory.getInstance(1).getProductDAO().getProductById(Integer.parseInt(productId)), Integer.parseInt(qnt));
            model.addObject("productListCart", cart.getProducts());
            model.addObject("summ", cart.getSumm());
            session.setAttribute("cartSize", cart.getSize());
            session.setAttribute("cart", cart);
        }
        return model;
    }

    @RequestMapping(method = RequestMethod.POST, params = "addFromProductList=ok")
    protected void addToCartFromProductList(@RequestParam(value = "productToBuyId", required = false) String productToBuyId) {
        ModelAndView model = new ModelAndView();
        HttpSession session = session();

        Product product = DAOFactory.getInstance(1).getProductDAO().getProductById(Integer.parseInt(productToBuyId));
        Cart cart = getCart(session);

        int qty = 0;
        if (cart.getProducts().containsKey(product)) {
            qty = cart.getProducts().get(product);
        }
        cart.getProducts().put(product, ++qty);
        session.setAttribute("backToProducts", "ok");
        session.setAttribute("cart", cart);
        session.setAttribute("cartSize", cart.getSize());
        model.setViewName("pageProductList");
    }

    @RequestMapping(method = RequestMethod.POST, params = "deleteProduct=ok")
    protected String deleteProductFromCart(@RequestParam(value = "productToDelete", required = false) String productToDelete) {
        HttpSession session = session();
        Cart cart;
        cart = getCart(session);
        cart.getProducts().remove(DAOFactory.getInstance(1).getProductDAO().getProductById(Integer.parseInt(productToDelete)));
        session.setAttribute("cartSize", cart.getSize());
        session.setAttribute("cart", cart);
        return new ModelAndView("redirect:/cart").getViewName();
    }


    //Util methods
    private Cart getCart(HttpSession session) {
        Cart cart = null;
        if (session.getAttribute("cart") != null) {
            cart = (Cart) session.getAttribute("cart");
        } else {
            cart = new Cart(new LinkedHashMap<Product, Integer>());
        }
        return cart;
    }

    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }
}
