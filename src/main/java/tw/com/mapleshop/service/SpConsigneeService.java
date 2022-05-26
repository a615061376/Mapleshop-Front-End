package tw.com.mapleshop.service;

import tw.com.mapleshop.entity.SpConsignee;
import com.baomidou.mybatisplus.extension.service.IService;
import tw.com.mapleshop.entity.SpUser;
import tw.com.mapleshop.form.SpConsigneeForm;
import tw.com.mapleshop.vo.SpConsigneeVO;

import java.util.List;

/**
 * <p>
 * 收貨人表 服务类
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
public interface SpConsigneeService extends IService<SpConsignee> {

    public SpConsignee input(SpConsigneeForm spConsigneeForm, SpUser spUser);
    public List<SpConsigneeVO> findAddressByUser(Integer userId);
}
