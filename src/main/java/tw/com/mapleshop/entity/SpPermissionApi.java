package tw.com.mapleshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
  @EqualsAndHashCode(callSuper = false)
    public class SpPermissionApi implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer psId;

    private String psApiService;

    private String psApiAction;

    private String psApiPath;

    private Integer psApiOrder;


}
