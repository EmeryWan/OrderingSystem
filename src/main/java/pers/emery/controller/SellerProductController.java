package pers.emery.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pers.emery.dataobject.ProductCategory;
import pers.emery.dataobject.ProductInfo;
import pers.emery.exception.SellException;
import pers.emery.form.ProductForm;
import pers.emery.service.CategoryService;
import pers.emery.service.ProductService;
import pers.emery.utils.KeyUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 *
 * @author emery
 */
@Slf4j
@Controller
@RequestMapping("/seller")
public class SellerProductController {

    private ProductService productService;

    private CategoryService categoryService;

    @Autowired
    public SellerProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    /**
     * 列出所有的信息
     */
    @GetMapping("/product/index")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        // 分页信息
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        // PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        // 查询所有类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index", map);
    }

    /**
     * 显示单个商品信息
     */
    @GetMapping("/product/info/{id}")
    public ModelAndView info(@PathVariable String id,
                             Map<String, Object> map) {
        ProductInfo productInfo = productService.findById(id).get();
        map.put("productInfo", productInfo);
        return new ModelAndView("product/info", map);
    }

    /**
     * 添加，修改页面
     */
    @RequestMapping("/product/set")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId, Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findById(productId).get();
            map.put("productInfo", productInfo);
        }

        // 查询所有类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/set", map);
    }

    // 添加更改成 post
    // 更新 更改成 put
    /**
     * 添加，
     *
     * 更新 使用的是 save
     */
    @PostMapping("/product/save")
    public ModelAndView save(@Valid ProductForm form, BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        try {
            ProductInfo productInfo = new ProductInfo();
            if (StringUtils.isEmpty(form.getProductId())) {
                // id=null 新增
                form.setProductId(KeyUtil.genUniqueKey());
            } else {
                productInfo = productService.findById(form.getProductId()).get();
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    // 改成 put 请求
    /**
     * 上架
     */
    @RequestMapping("/product/on")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            // 商品id错误，已经是上架状态
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }


    // 改成 put请求
    /**
     * 下架
     */
    @RequestMapping("/product/off")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

}
