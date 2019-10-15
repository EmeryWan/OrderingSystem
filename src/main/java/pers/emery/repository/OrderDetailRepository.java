package pers.emery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.emery.dataobject.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     *
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
