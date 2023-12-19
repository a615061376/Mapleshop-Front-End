package tw.com.mapleshop.service;

import tw.com.mapleshop.entity.SpCart;
import com.baomidou.mybatisplus.extension.service.IService;
import tw.com.mapleshop.entity.SpOrder;
import tw.com.mapleshop.entity.SpUser;
import tw.com.mapleshop.vo.SpCartVO;

import java.util.List;

public interface SpCartService extends IService<SpCart> {

    public Boolean add(SpCart spCart);
    public List<SpCartVO> findVOListByUserId(Integer userId);
    public Boolean update(Integer id, Integer quantity, Float cost);
    public Boolean delete(Integer id);
    public SpOrder commit(String cgnAddress, SpUser spUser);
}
