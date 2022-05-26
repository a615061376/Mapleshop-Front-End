package tw.com.mapleshop.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class SpConsigneeForm {
    @NotEmpty(message = "收貨人名稱不能為空")
    private String cgnName;
    @NotEmpty(message = "郵遞區號不能為空")
    private String cgnCode;
    @NotEmpty(message = "地址不能為空")
    private String cgnAddress;
    @NotEmpty(message = "手機號碼不能為空")
    private String cgnTel;

    private String cgnEmail;

    private String other;
}
