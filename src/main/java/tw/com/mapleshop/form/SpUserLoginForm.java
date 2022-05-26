package tw.com.mapleshop.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class SpUserLoginForm {
    @NotEmpty(message = "用戶名不能為空")
    private String username;
    @NotEmpty(message = "密碼不能為空")
    private String password;
}
