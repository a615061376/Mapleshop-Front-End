package tw.com.mapleshop.service;

import tw.com.mapleshop.entity.SpCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import tw.com.mapleshop.vo.SpCategoryVO;

import java.util.List;

public interface SpCategoryService extends IService<SpCategory> {

    public List<SpCategoryVO> buildCategoryMenu();
    public List<SpCategoryVO> findAllGoodsByCategoryLevelOne();
}
