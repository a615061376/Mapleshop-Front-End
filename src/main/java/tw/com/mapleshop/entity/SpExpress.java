package tw.com.mapleshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SpExpress implements Serializable {

    private static final long serialVersionUID=1L;

    // 主鍵id
    @TableId(value = "express_id", type = IdType.AUTO)
    private Integer expressId;

    // 訂單id
    private Integer orderId;

    // 訂單快遞公司名稱
    private String expressCom;

    //快遞單編號
    private String expressNu;

    // 記錄生成時間
    private Integer createTime;

    // 記錄修改時間
    private Integer updateTime;


}
