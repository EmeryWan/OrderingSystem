package pers.emery.vo;

import lombok.Data;

/**
 *
 * @author emery
 * @param <T>
 */
@Data
public class ResultVO<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 内容
     */
    private T data;

}
