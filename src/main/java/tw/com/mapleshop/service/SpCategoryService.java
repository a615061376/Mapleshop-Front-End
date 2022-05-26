package tw.com.mapleshop.service;

import org.springframework.stereotype.Service;
import tw.com.mapleshop.entity.SpCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import tw.com.mapleshop.entity.SpGoods;
import tw.com.mapleshop.vo.SpCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
public interface SpCategoryService extends IService<SpCategory> {

    public List<SpCategoryVO> buildCategoryMenu();
    public List<SpCategoryVO> findAllGoodsByCategoryLevelOne();
}
