package pers.emery.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 类目 含商品
 *
 * @author emery
 */
@Data
public class ProductVO {

    /**
     * 商品类目名
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 商品类型名
     */
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
