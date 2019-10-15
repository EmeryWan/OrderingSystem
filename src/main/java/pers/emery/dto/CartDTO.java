package pers.emery.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 购物车
 *
 * @author emery
 */
@Data
@AllArgsConstructor
public class CartDTO implements Serializable {

    private static final long serialVersionUID = -1317611338714950295L;

    /**
     * 商品Id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer productQuantity;

}
