package tw.com.mapleshop.result;

public enum ResponseEnum {
    USER_INFO_NULL(300,"用戶資訊不能為空"),
    EMAIL_ERROR(301,"電子郵箱格式錯誤"),
    PHONE_ERROR(302,"手機格式錯誤"),
    USERNAME_EXISTS(303,"用戶名已存在"),
    USER_REGISTER_ERROR(304,"添加用戶失敗"),
    USERNAME_NOT_EXISTS(305,"帳號/用戶名不存在"),
    PASSWORD_ERROR(306,"密碼錯誤"),
    PARAMETER_NULL(307,"商品參數為空"),
    NOT_LOGIN(308,"用戶未登入"),
    CARTADD_ERROR(309,"添加購物車失敗"),
    GOODS_NOT_EXISTS(310,"商品不存在"),
    GOODS_NUMBER_ERROR(311, "商品庫存不足"),
    CART_UPDATE_ERROR(312, "更新購物車失敗"),
    CART_UPDATE_PARAMETER_ERROR(313,"更新購物車參數異常"),
    CART_UPDATE_STOCK_ERROR(314, "更新商品庫存失敗"),
    CART_REMOVE_ERROR(315,"刪除購物車失敗"),
    ORDERS_CREATE_ERROR(316, "創建訂單失敗"),
    ORDERGOODS_CREATE_ERROR(317, "創建訂單詳情失敗"),
    CGN_INFO_NULL(318,"收貨人資訊不能為空"),
    CGN_INFO_ERROR(319,"添加收貨人資訊失敗");

    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
