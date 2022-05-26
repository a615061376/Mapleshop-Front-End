package tw.com.mapleshop.service;

import tw.com.mapleshop.entity.SpUser;
import com.baomidou.mybatisplus.extension.service.IService;
import tw.com.mapleshop.form.SpUserLoginForm;
import tw.com.mapleshop.form.SpUserRegisterForm;

/**
 * <p>
 * 會員表 服务类
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
public interface SpUserService extends IService<SpUser> {

    public SpUser register(SpUserRegisterForm spUserRegisterForm);
    public SpUser login(SpUserLoginForm spUserLoginForm);
}
