package pers.emery.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/seller/product")
public class SellerProductController {

    private ProductService productService;

    private CategoryService categoryService;

    @Autowired
    public SellerProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/test")
    public String test() {
        return "food/index";
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        // 分页信息
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    /**
     * 上架
     */
    @RequestMapping("/on_sale")
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

    /**
     * 下架
     */
    @RequestMapping("/off_sale")
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

    /**
     * 添加，修改页面
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId, Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        // 查询所有类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index", map);
    }

    /**
     * 添加，
     * 更新
     */
    @RequestMapping("/save")
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
                productInfo = productService.findOne(form.getProductId());
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

}
