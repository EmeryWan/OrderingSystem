package pers.emery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pers.emery.dataobject.ProductInfo;
import pers.emery.dto.CartDTO;
import pers.emery.enums.ProductStatusEnum;
import pers.emery.enums.ResultEnum;
import pers.emery.exception.SellException;
import pers.emery.repository.ProductInfoRepository;
import pers.emery.service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * @author emery
 */
@Service
@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {

    private final ProductInfoRepository repository;

    @Autowired
    public ProductServiceImpl(ProductInfoRepository repository) {
        this.repository = repository;
    }


    @Override
    @Cacheable(key = "'id_' + #productId", unless="#result == null")
    public ProductInfo findById(String productId) {
        return repository.findById(productId).orElse(null);
    }

    @Override
    @Cacheable(key = "'up_all'")
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
//    @Cacheable(key = "'all'")
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @CachePut(key = "'id_' + #productInfo.productId")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfoOptional = repository.findById(cartDTO.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }

    }

    /**
     * 商品上架
     */
    @Override
    public ProductInfo onSale(String productId) {
        Optional<ProductInfo> productInfoOptional = repository.findById(productId);

        ProductInfo productInfo = productInfoOptional.orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXIST));

        // 商品不存在
        // if (productInfo == null) {
        //     throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        // }
        // 已经是上架状态
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        // 更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    /**
     * 商品下架
     */
    @Override
    public ProductInfo offSale(String productId) {
        Optional<ProductInfo> productInfoOptional = repository.findById(productId);

        ProductInfo productInfo = productInfoOptional.orElseThrow(() -> new SellException(ResultEnum.PRODUCT_NOT_EXIST));

        // 商品不存在
        // if (productInfo == null) {
        //     throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        // }
        // 已经是下架状态
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        // 更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
}
