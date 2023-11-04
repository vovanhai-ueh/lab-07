package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.models.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Product>, JpaSpecificationExecutor<ProductPrice> {
}