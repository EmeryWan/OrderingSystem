package pers.emery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pers.emery.dataobject.ProductInfo;
import pers.emery.enums.ProductStatusEnum;
import pers.emery.repository.ProductInfoRepository;
import pers.emery.service.ProductInfoService;

import java.util.List;

/**
 * @author emery
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    private final ProductInfoRepository repository;

    @Autowired
    public ProductInfoServiceImpl(ProductInfoRepository repository) {
        this.repository = repository;
    }


    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
}
