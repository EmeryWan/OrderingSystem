package pers.emery.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数不正确"),

    LOGIN_FAIL(3, "登录失败, 登录信息不正确"),

    LOGOUT_SUCCESS(4, "登出成功"),

    WECHAT_MP_ERROR(5, "微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(6, "微信支付异步通知金额校验不通过"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),

    PRODUCT_STATUS_ERROR(12, "商品状态不正确"),

    ORDER_NOT_EXIST(13, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(14, "订单详情不存在"),

    ORDER_UPDATE_FAIL(15, "订单更新失败"),

    ORDER_DETAIL_EMPTY(16, "订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),

    ORDER_STATUS_ERROR(18, "订单状态不正确"),

    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),

    ORDER_CANCEL_SUCCESS(20, "订单取消成功"),

    ORDER_FINISH_SUCCESS(21, "订单完结成功"),

    CART_EMPTY(22, "购物车为空"),

    CATEGORY_SAVE_ERROR(23, "类目添加失败"),

    CATEGORY_TYPE_EXISTS(24, "类目编号已存在"),

    CATEGORY_NOT_EXISTS(25, "类目不存在"),

    CATEGORY_STATUS_ERROR(26, "类目状态不正确"),
    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
