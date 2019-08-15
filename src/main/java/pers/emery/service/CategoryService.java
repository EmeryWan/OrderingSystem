package pers.emery.service;

import pers.emery.dataobject.ProductCategory;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<ProductCategory> findById(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
