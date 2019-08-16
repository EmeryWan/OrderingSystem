package pers.emery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pers.emery.dataobject.ProductCategory;
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
    public Optional<ProductCategory> findById(Integer categoryId) {
        return repository.findById(categoryId);
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
    @CacheEvict(cacheNames = "category", key = "'all'")
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
