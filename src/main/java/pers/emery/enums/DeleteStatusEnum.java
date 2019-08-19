package pers.emery.enums;

import lombok.Getter;

/**
 * @author emery
 */

@Getter
public enum  DeleteStatusEnum implements CodeEnum {

    YES(1, "已删除"),

    NO(0, "未删除"),

    ;

    private Integer code;

    private String message;

    DeleteStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
