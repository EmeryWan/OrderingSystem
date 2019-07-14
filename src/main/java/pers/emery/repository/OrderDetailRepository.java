package pers.emery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.RepositoryDefinition;
import pers.emery.dataobject.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
//@RepositoryDefinition(domainClass = OrderDetail.class, idClass = String.class)
//public interface OrderDetailRepository {

    List<OrderDetail> findByOrOrderId(String orderId);

}
