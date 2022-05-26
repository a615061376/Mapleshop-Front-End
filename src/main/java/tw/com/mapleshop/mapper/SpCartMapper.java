package tw.com.mapleshop.mapper;

import org.springframework.stereotype.Repository;
import tw.com.mapleshop.entity.SpCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JHuang
 * @since 2022-02-20
 */
@Repository
public interface SpCartMapper extends BaseMapper<SpCart> {
    public int update(Integer id, Integer quantity, Float cost);
    public Float getCostByUserId(Integer id);
}
