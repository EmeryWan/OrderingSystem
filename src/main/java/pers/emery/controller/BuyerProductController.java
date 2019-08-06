package pers.emery.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.emery.VO.ProductInfoVO;
import pers.emery.VO.ProductVO;
import pers.emery.VO.ResultVO;
import pers.emery.dataobject.ProductCategory;
import pers.emery.dataobject.ProductInfo;
import pers.emery.service.CategoryService;
import pers.emery.service.ProductService;
import pers.emery.utils.ResultVOUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 卖家商品
 *
 * @author emery
 */
@Slf4j
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public BuyerProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    /**
     * 查询所有商品
     * @return
     */
    @GetMapping("/list")
    public ResultVO list() {


        // 查询所有的上架商品
        List<ProductInfo> productInfoListList = productService.findUpAll();
        System.out.println(productInfoListList);


        // 查询上架商品中包含的类目
        List<Integer> categoryTypeList = productInfoListList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        System.out.println(categoryTypeList);
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        System.out.println(productCategoryList);

        // 拼接数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoListList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);

            productVOList.add(productVO);
        }

        System.out.println(productVOList);

        return ResultVOUtil.success(productVOList);
    }
}
