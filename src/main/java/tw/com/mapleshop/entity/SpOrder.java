package tw.com.mapleshop.entity;

import java.math.BigDecimal;

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
 * 訂單表
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SpOrder implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主鍵id
     */
        @TableId(value = "order_id", type = IdType.AUTO)
      private Integer orderId;

      /**
     * 下訂單會員id
     */
      private Integer userId;

      /**
     * 訂單編號
     */
      private String orderNumber;

      /**
     * 訂單總金額
     */
      private Float orderPrice;

      /**
     * 訂單是否已經發貨
     */
      private String isSend;

      /**
     * consignee收貨人地址
     */
      private String cgnAddress;

      /**
     * 訂單狀態： 0未付款、1已付款
     */
      private String payStatus;

      /**
     * 記錄生成時間
     */
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      /**
       * 修改時間
       */
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;

}
