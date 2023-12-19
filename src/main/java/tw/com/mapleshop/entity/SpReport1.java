package tw.com.mapleshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SpReport1 implements Serializable {

    private static final long serialVersionUID=1L;

    // 主鍵id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 用戶數
    private Integer rp1UserCount;

    // 地區
    private String rp1Area;

    private LocalDate rp1Date;


}
