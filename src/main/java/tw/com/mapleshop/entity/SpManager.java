package tw.com.mapleshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 管理員表
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SpManager implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主鍵id
     */
        @TableId(value = "mg_id", type = IdType.AUTO)
      private Integer mgId;

      /**
     * 名稱
     */
      private String mgName;

      /**
     * 密碼
     */
      private String mgPwd;

      /**
     * 註冊時間
     */
      private Integer mgTime;

      /**
     * 角色id
     */
      private Integer roleId;

    private String mgMobile;

    private String mgEmail;

      /**
     * 1：表示啟用 0:表示禁用
     */
      private Integer mgState;


}
