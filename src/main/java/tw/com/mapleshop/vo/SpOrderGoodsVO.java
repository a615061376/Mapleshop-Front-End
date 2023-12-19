package tw.com.mapleshop.vo;

import lombok.Data;

@Data
public class SpOrderGoodsVO {

    private Integer goodsId;

    private String goodsName;

    private Float goodsPrice;

    private Integer goodsNumber;

    private Float goodsTotalPrice;

    private String fileName;

}
