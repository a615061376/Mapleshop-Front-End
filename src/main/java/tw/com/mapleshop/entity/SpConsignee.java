package tw.com.mapleshop.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 收貨人表
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SpConsignee implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主鍵id
     */
      @TableId(value = "cgn_id", type = IdType.AUTO)
      private Integer cgnId;

      /**
     * 會員id
     */
      private Integer userId;

      /**
     * 收貨人名稱
     */
      private String cgnName;

      /**
       * 郵遞區號
       */
      private String cgnCode;

      /**
     * 收貨人地址
     */
      private String cgnAddress;

      /**
     * 收貨人電話
     */
      private String cgnTel;

      /**
       * 收貨人信箱
       */
      private String cgnEmail;

      /**
       * 其他
       */
      private String other;

      /**
     * 創建時間
     */
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;
      /**
     * 刪除時間
     */
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;



}
