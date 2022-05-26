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
 * 會員表
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SpUser implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 用戶/自增id
     */
      @TableId(value = "user_id", type = IdType.AUTO)
      private Integer userId;

      /**
     * 登錄名
     */
      private String username;

      /**
     * 登錄密碼
     */
      private String password;

      /**
     * 郵箱
     */
      private String userEmail;

      /**
     * 性別
     */
      private String userSex;

      /**
     * 手機
     */
      private String userTel;

      /**
     * 愛好
     */
      private String userHobby;

      /**
     * 簡介
     */
      private String userIntroduce;

      /**
     * 創建時間
     */
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      /**
     * 修改時間
     */
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime updateTime;

      /**
       * 圖片
       */
      private String fileName;
}
