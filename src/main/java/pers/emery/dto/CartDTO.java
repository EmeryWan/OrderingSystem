package pers.emery.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 购物车
 *
 * @author emery
 */
@Data
@AllArgsConstructor
public class CartDTO {

    private String productId;

    private Integer productQuantity;

}
