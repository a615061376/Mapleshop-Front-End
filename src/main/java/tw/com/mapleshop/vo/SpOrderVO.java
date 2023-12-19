package tw.com.mapleshop.vo;

import lombok.Data;

import java.util.List;

@Data
public class SpOrderVO {

    private Integer userId;

    private String cgnName;

    private String orderNumber;

    private Float orderPrice;

    private String cgnAddress;

    private List<SpOrderGoodsVO> spOrderGoodsList;

}
