package pers.emery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pers.emery.dataobject.ProductCategory;
import pers.emery.enums.DeleteStatusEnum;
import pers.emery.enums.ResultEnum;
import pers.emery.exception.SellException;
import pers.emery.repository.ProductCategoryRepository;
import pers.emery.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ProductCategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductCategory findById(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public ProductCategory findByCategoryType(Integer categoryType) {
        return repository.findByCategoryType(categoryType);
    }

    @Override
    @Cacheable(cacheNames = "category", key = "'all'")
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public List<ProductCategory> findByHasDelete(Integer hasDelete) {
        return repository.findByHasDelete(hasDelete);
    }

    @Override
    @CacheEvict(cacheNames = "category", key = "'all'")
    public ProductCategory save(ProductCategory productCategory) {

        // 说明为新增加
        if (productCategory.getCategoryId() == null) {
            // 先判断该类目编号是否存在
            ProductCategory category = findByCategoryType(productCategory.getCategoryType());
            if (category != null) {
                throw new SellException(ResultEnum.CATEGORY_TYPE_EXISTS);
            }
        }

        return repository.save(productCategory);
    }

    @Override
    @CacheEvict(cacheNames = "category", key = "'all'")
    public void delete(Integer categoryId) {
        ProductCategory category = findById(categoryId);
        if (category == null) {
            throw new SellException(ResultEnum.CATEGORY_NOT_EXISTS);
        }
        if (category.getHasDelete().equals(DeleteStatusEnum.YES.getCode())) {
            throw new SellException(ResultEnum.CATEGORY_STATUS_ERROR);
        }

        category.setHasDelete(DeleteStatusEnum.YES.getCode());

        save(category);
    }

    @Override
    @CacheEvict(cacheNames = "category", key = "'all'")
    public void recover(Integer categoryId) {
        ProductCategory category = findById(categoryId);
        if (category == null) {
            throw new SellException(ResultEnum.CATEGORY_NOT_EXISTS);
        }
        if (category.getHasDelete().equals(DeleteStatusEnum.NO.getCode())) {
            throw new SellException(ResultEnum.CATEGORY_STATUS_ERROR);
        }

        category.setHasDelete(DeleteStatusEnum.NO.getCode());
        save(category);
    }

}
