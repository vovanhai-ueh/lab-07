package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.backend.services.ProductServices;
import vn.edu.iuh.fit.frontend.dto.CartItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public String showProductListPaging(
            HttpSession session,
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);

        Page<Product> candidatePage = productServices.findPaginated(currentPage - 1,
                pageSize, "name", "asc");

        model.addAttribute("productPage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        //them so phan tu co trong cart
//        model.addAttribute("itemsOnCart", 0);
        return "client/index";
    }

    @GetMapping("/add2cart/{id}")
    public String add2Cart(HttpSession session, Model model, @PathVariable("id") long id) {
        Object obj = session.getAttribute("items");

        Product product = productRepository.findById(id).get();

        HashMap<Long, CartItem> cart = null;

        if (obj == null)
            cart = new HashMap<>();
        else
            cart = (HashMap<Long, CartItem>) obj;

        CartItem item = new CartItem(product, 1);
        if (cart.get(product.getProduct_id()) != null)
            item.setAmount(item.getAmount() + 1);
        cart.put(product.getProduct_id(),item);

        session.setAttribute("items", cart);

        session.setAttribute("itemsOnCart", cart.size());

        return "redirect:/shopping";
    }
}
