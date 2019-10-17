package controllers;

import dao.DAOFactory.DAOFactory;
import entity.Cart;
import entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;


@Controller
@RequestMapping("/cart")
public class CartController {

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView getJustCart(HttpSession session) {
        ModelAndView model = new ModelAndView("pageCart");
        if (session.getAttribute("cart") != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            model.addObject("productListCart", cart.getProducts());
            model.addObject("summ", cart.getSumm());
        }
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView changeProductQtyInCart(@RequestParam(value = "productId", required = false) String productId,
                                                  @RequestParam(value = "qnt", required = false) String qnt, HttpSession session) {
        ModelAndView model = new ModelAndView("cart");
        Cart cart = null;
        if (productId != null) {
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
    protected void addToCartFromProductList(@RequestParam(value = "productToBuyId", required = false) String productToBuyId, HttpSession session) {
        ModelAndView model = new ModelAndView();
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
    protected String deleteProductFromCart(@RequestParam(value = "productToDelete", required = false) String productToDelete, HttpSession session) {
        Cart cart;
        cart = getCart(session);
        cart.getProducts().remove(DAOFactory.getInstance(1).getProductDAO().getProductById(Integer.parseInt(productToDelete)));
        if (cart.getSize() != 0) {
            session.setAttribute("cartSize", cart.getSize());
            session.setAttribute("cart", cart);
        } else {
            session.removeAttribute("cartSize");
            session.removeAttribute("cart");
        }
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
}
