package pers.emery.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import pers.emery.enums.ProductStatusEnum;
import pers.emery.utils.EnumUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author emery
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    /**
     * 库存
     */
    private Integer productStock;

    private String productDescription;

    /**
     * 图片
     */
    private String productIcon;

    /**
     * 商品状态 0 正常 1 下架
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /**
     * 类目编号
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

}
