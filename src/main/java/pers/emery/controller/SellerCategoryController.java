package pers.emery.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pers.emery.dataobject.ProductCategory;
import pers.emery.enums.DeleteStatusEnum;
import pers.emery.enums.ResultEnum;
import pers.emery.exception.SellException;
import pers.emery.form.CategoryForm;
import pers.emery.service.CategoryService;
import pers.emery.utils.ResultVOUtil;
import pers.emery.vo.ResultVO;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/seller")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category/index")
    public ModelAndView index(@RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                              @RequestParam(value = "hasDelete", defaultValue = "-1") Integer hasDelete,
                              Map<String, Object> map) {

        List<ProductCategory> categoryList = categoryService.findAll();

        if (hasDelete != -1) {
            categoryList = categoryList.stream()
                    .filter(category -> category.getHasDelete().equals(hasDelete))
                    .collect(Collectors.toList());
        }

        map.put("categoryList", categoryList);
        map.put("DeleteStatusEnumList", DeleteStatusEnum.values());
        map.put("hasDelete", hasDelete);

        return new ModelAndView("category/index");
    }

    @RequestMapping("/category/add")
    public ModelAndView set() {
        return new ModelAndView("category/set");
    }

    /**
     * 查看某个类目
     * 或
     * 去添加页面
     */
    @RequestMapping("/category/set/{id}")
    public ModelAndView set(@PathVariable(required = false) String id,
                            Map<String, Object> map) {
        if (!StringUtils.isEmpty(id)) {
            ProductCategory category = categoryService.findById(Integer.valueOf(id));
            map.put("categoryInfo", category);
        }

        return new ModelAndView("category/set", map);
    }

    /**
     * 添加
     */
    @PostMapping("/category")
    @ResponseBody
    public ResultVO save(@RequestBody @Valid CategoryForm categoryForm, BindingResult bindingResult) {

        log.info(categoryForm.toString());

        if (bindingResult.hasErrors()) {
            ResultVOUtil.error(ResultEnum.CATEGORY_SAVE_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(categoryForm, productCategory);

        log.info(productCategory.toString());

        try {
            categoryService.save(productCategory);
        } catch (SellException e) {
            ResultVOUtil.error(e.getCode(), e.getMessage());
        }

        return ResultVOUtil.success();
    }


    /**
     * 逻辑删除
     */
    @DeleteMapping("/category/{id}")
    @ResponseBody
    public ResultVO remove(@PathVariable Integer id) {

        log.info("delete ----> {}", id);

        try {
            categoryService.delete(id);
        } catch (SellException e) {
            ResultVOUtil.error(e.getCode(), e.getMessage());
        }

        return ResultVOUtil.success();
    }

    /**
     * 修改
     */
    @PutMapping("/category")
    @ResponseBody
    public ResultVO update(@RequestBody @Valid CategoryForm categoryForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResultVOUtil.error(ResultEnum.CATEGORY_SAVE_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        ProductCategory productCategory = categoryService.findById(categoryForm.getCategoryId());
        BeanUtils.copyProperties(categoryForm, productCategory);
        categoryService.save(productCategory);

        return ResultVOUtil.success();
    }


    /**
     * 恢复
     */
    @PutMapping("/category/{id}")
    @ResponseBody
    public ResultVO recover(@PathVariable Integer id) {
        try {
            categoryService.recover(id);
        } catch (SellException e) {
            ResultVOUtil.error(e.getCode(), e.getMessage());
        }

        return ResultVOUtil.success();
    }

}
