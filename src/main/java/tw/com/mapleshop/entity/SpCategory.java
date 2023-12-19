package tw.com.mapleshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SpCategory implements Serializable {

    private static final long serialVersionUID=1L;

    //分類唯一ID
    @TableId(value = "cat_id", type = IdType.AUTO)
    private Integer catId;

    // 分類名稱
    private String catName;

    //分類父ID
    private Integer catPid;

    // 分類層級 0: 頂級 1:二級 2:三級
    private Integer catLevel;

    // 是否刪除 1為刪除
    private Integer catDeleted;

    private String catIcon;

    private String catSrc;


}
