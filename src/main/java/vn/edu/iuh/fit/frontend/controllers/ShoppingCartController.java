package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.frontend.dto.CartItem;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
    @GetMapping("/view-cart")
    public String view_cart(Model model, HttpSession session) {
        return "client/checkout";
    }

   /* @GetMapping("/add2cart")
    public String add2Cart(HttpSession session, Model model, @ModelAttribute("item") CartItem item) {
        Object obj = session.getAttribute("items");
        List<CartItem> lst = null;
        if (obj == null)
            lst = new ArrayList<>();
        else
            lst = (List<CartItem>) obj;
        lst.add(item);
        ///....

        session.setAttribute("items", lst);
        return "client/index";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        Object obj = session.getAttribute("items");
        List<Product> lst = (List<Product>) obj;
        return "";
    }*/
}
