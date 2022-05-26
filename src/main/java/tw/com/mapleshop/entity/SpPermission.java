package tw.com.mapleshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 許可權表
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SpPermission implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "ps_id", type = IdType.AUTO)
      private Integer psId;

      /**
     * 許可權名稱
     */
      private String psName;

      /**
     * 父類id
     */
      private Integer psPid;

      /**
     * 控制器
     */
      private String psC;

      /**
     * 操作方法
     */
      private String psA;

      /**
     * 許可權等級
     */
      private String psLevel;


}
