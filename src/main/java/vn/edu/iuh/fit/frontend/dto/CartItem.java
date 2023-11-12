package vn.edu.iuh.fit.frontend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.backend.models.Product;

import java.io.Serializable;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class CartItem implements Serializable {
    private Product product;
    private int amount;
}
