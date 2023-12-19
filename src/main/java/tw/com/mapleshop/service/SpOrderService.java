package tw.com.mapleshop.service;

import tw.com.mapleshop.entity.SpOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import tw.com.mapleshop.vo.SpOrderVO;

import java.util.List;

public interface SpOrderService extends IService<SpOrder> {

    public List<SpOrderVO> findAllByUser(Integer userId);
}
