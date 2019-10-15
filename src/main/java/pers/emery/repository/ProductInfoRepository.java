package pers.emery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.emery.dataobject.ProductInfo;

import java.util.List;

/**
 * @author emery
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

//    List<ProductInfo> findByCategoryTypeAnd

}
