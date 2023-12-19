package tw.com.mapleshop.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SpCart implements Serializable {

    private static final long serialVersionUID=1L;

    // 主鍵ID
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 商品ID
    private Integer goodsId;

    // 數量
    private Integer quantity;

    // 價格
    private Float cost;

    // 用戶id
    private Integer userId;

    // 創建時間
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 修改時間
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
