package pers.emery.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import pers.emery.enums.DeleteStatusEnum;
import pers.emery.utils.EnumUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品类目
 * @author emery
 */
@Entity
@DynamicUpdate
@Data
//@Table(name = "table_product_category")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 183952576791314816L;

    /**
     * 类目id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;

    private Integer hasDelete;

    private Date createTime;

    private Date updateTime;

    {
        hasDelete = DeleteStatusEnum.NO.getCode();
    }

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    @JsonIgnore
    public DeleteStatusEnum getDeleteStatusEnum() {
        return EnumUtil.getByCode(hasDelete, DeleteStatusEnum.class);
    }

}
