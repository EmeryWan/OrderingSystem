package pers.emery.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author emery
 * @param <T>
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -8833037746961444565L;

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
