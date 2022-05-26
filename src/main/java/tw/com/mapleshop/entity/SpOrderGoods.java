package tw.com.mapleshop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品訂單關聯表
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SpOrderGoods implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主鍵id
     */
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 訂單id
     */
      private Integer orderId;

      /**
     * 商品id
     */
      private Integer goodsId;

      /**
     * 購買單個商品數量
     */
      private Integer goodsNumber;

      /**
     * 商品小計價格
     */
      private Float goodsTotalPrice;


}
