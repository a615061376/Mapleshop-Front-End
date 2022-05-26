package tw.com.mapleshop.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class SpUserRegisterForm {

    @NotEmpty(message = "用戶名不能為空")
    private String username;
    @NotEmpty(message = "密碼不能為空")
    private String password;
    @NotEmpty(message = "電子信箱不能為空")
    private String userEmail;
    @NotEmpty(message = "手機號不能為空")
    private String userTel;
}
