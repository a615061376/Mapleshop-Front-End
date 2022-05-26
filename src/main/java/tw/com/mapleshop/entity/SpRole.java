package tw.com.mapleshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SpRole implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "role_id", type = IdType.AUTO)
      private Integer roleId;

      /**
     * 角色名稱
     */
      private String roleName;

      /**
     * 許可權ids,1,2,5
     */
      private String psIds;

      /**
     * 控制器-操作,控制器-操作,控制器-操作
     */
      private String psCa;

    private String roleDesc;


}
