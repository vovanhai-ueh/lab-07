package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.iuh.fit.backend.models.Order;
import vn.edu.iuh.fit.backend.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Order>, JpaSpecificationExecutor<OrderDetail> {
}