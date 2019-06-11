package pers.emery.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pers.emery.dataobject.ProductInfo;

import java.util.List;

/**
 * @author emery
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架的商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // 加库存 减库存 上架 下架
    // void increaseStock(List<CardDTO> cartDTOList);

}
