package tw.com.mapleshop.exception;

import tw.com.mapleshop.result.ResponseEnum;

public class SpException extends  RuntimeException {

    public  ResponseEnum responseEnum;

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public SpException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
    }

}
