package pers.emery.service;

import pers.emery.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {

    ProductCategory findById(Integer categoryId);

    ProductCategory findByCategoryType(Integer categoryType);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    List<ProductCategory> findByHasDelete(Integer hasDelete);

    ProductCategory save(ProductCategory productCategory);

    void delete(Integer categoryId);

    void recover(Integer categoryId);

}
