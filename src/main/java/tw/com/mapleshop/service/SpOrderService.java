package tw.com.mapleshop.service;

import tw.com.mapleshop.entity.SpOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import tw.com.mapleshop.vo.SpOrderVO;

import java.util.List;

/**
 * <p>
 * 訂單表 服务类
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
public interface SpOrderService extends IService<SpOrder> {

    public List<SpOrderVO> findAllByUser(Integer userId);
}
