package tw.com.mapleshop.vo;

import lombok.Data;
import tw.com.mapleshop.entity.SpCategory;
import tw.com.mapleshop.entity.SpGoods;

import java.util.List;

@Data
public class SpCategoryVO {

    private Integer catId;

    private String catName;

    private Integer catPid;

    private List<SpCategoryVO> children;

    private List<SpGoods> spGoodsList;

    public SpCategoryVO(SpCategory spCategory) {
        this.catId = spCategory.getCatId();
        this.catName = spCategory.getCatName();
        this.catPid = spCategory.getCatPid();
    }
}
