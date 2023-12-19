package tw.com.mapleshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SpAttribute implements Serializable {

    private static final long serialVersionUID=1L;

    // 主鍵id
    @TableId(value = "attr_id", type = IdType.AUTO)
    private Integer attrId;

    // 屬性名稱
    private String attrName;

    // 外鍵，類型id
    private Integer catId;

    // only:輸入框(唯一)  many:後臺下拉清單/前臺單選框
    private String attrSel;

    //manual:手工錄入  list:從列表選擇
    private String attrWrite;

    //可選值清單資訊,例如顏色：白色,紅色,綠色,多個可選值通過逗號分隔
    private String attrVals;

    // 刪除時間標誌
    private Integer deleteTime;


}
