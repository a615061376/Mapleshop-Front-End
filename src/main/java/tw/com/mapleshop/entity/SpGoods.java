package tw.com.mapleshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SpGoods implements Serializable {

    private static final long serialVersionUID=1L;

    // 主鍵id
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    // 商品名稱
    private String goodsName;

    // 商品價格
    private Float goodsPrice;

    // 商品數量
    private Integer goodsNumber;

    // 商品重量
    private Integer goodsWeight;

    // 類型id
    private Integer catId;

    //商品詳情介紹
    private String goodsIntroduce;

    // 圖片logo大圖
    private String goodsBigLogo;

    // 圖片logo小圖
    private String goodsSmallLogo;

    // 0:正常  1:刪除
    private String isDel;

    // 添加商品時間
    @TableField(fill = FieldFill.INSERT)
    private Integer addTime;

    // 修改商品時間
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updTime;

    // 軟刪除標誌欄位
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer deleteTime;

    // 一級分類id
    private Integer catOneId;

    // 二級分類id
    private Integer catTwoId;

    // 三級分類id
    private Integer catThreeId;

    // 熱賣數量
    private Integer hotMumber;

    // 是否促銷
    private Integer isPromote;

    // 商品狀態 0: 未通過 1: 審核中 2: 已審核
    private Integer goodsState;

    // 商品圖片
      private String fileName;

}
