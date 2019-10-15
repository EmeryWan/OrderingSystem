package pers.emery.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 类目 含商品
 *
 * @author emery
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 5156917415764806627L;

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
