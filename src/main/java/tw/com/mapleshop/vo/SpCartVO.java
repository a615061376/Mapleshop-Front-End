package tw.com.mapleshop.vo;


import lombok.Data;

@Data
public class SpCartVO {
    private Integer id;

    private Integer goodsId;

    private Integer quantity;

    private Float cost;

    private String goodsName;

    private String fileName;

    private Float goodsPrice;

    private Integer goodsNumber;
}
