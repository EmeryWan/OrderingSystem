package pers.emery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pers.emery.dataobject.ProductCategory;

import java.util.List;

/**
 * @author emery
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 根据类目传入查询
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    List<ProductCategory> findByHasDelete(Integer hasDelete);

    ProductCategory findByCategoryType(Integer categoryType);

}
