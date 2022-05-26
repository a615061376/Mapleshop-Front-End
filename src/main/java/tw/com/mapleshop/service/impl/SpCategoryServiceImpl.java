package tw.com.mapleshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import tw.com.mapleshop.entity.SpCategory;
import tw.com.mapleshop.entity.SpGoods;
import tw.com.mapleshop.mapper.SpCategoryMapper;
import tw.com.mapleshop.mapper.SpGoodsMapper;
import tw.com.mapleshop.service.SpCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tw.com.mapleshop.vo.SpCategoryVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JHuang
 * @since 2022-01-24
 */
@Service
public class SpCategoryServiceImpl extends ServiceImpl<SpCategoryMapper, SpCategory> implements SpCategoryService {

    @Autowired
    private SpCategoryMapper spCategoryMapper;

    @Autowired
    private SpGoodsMapper spGoodsMapper;

    //構建商品分類菜單
    @Override
    public List<SpCategoryVO> buildCategoryMenu() {
        //1. 查詢所有的分類數據
        List<SpCategory> spCategoryList = this.spCategoryMapper.selectList(null);
        //2. 數據類型轉換成VO
        List<SpCategoryVO> spCategoryVOList = spCategoryList.stream().map(SpCategoryVO::new).collect(Collectors.toList());
        //3. 父子級菜單封裝
        List<SpCategoryVO> levelOneList = buildMenu(spCategoryVOList);

        return levelOneList;
    }

    @Override
    public List<SpCategoryVO> findAllGoodsByCategoryLevelOne() {
        QueryWrapper<SpCategory> queryWrapper = new QueryWrapper();
        queryWrapper.eq("cat_level",0);
        List<SpCategory> spCategoryList = this.spCategoryMapper.selectList(queryWrapper);
        List<SpCategoryVO> spCategoryVOList = spCategoryList.stream().map(SpCategoryVO::new).collect(Collectors.toList());
        getLevelOneGoods(spCategoryVOList);
        return spCategoryVOList;
    }

    //查詢一級分類對應的商品訊息
    public void getLevelOneGoods(List<SpCategoryVO> list) {
        for (SpCategoryVO vo : list) {
            QueryWrapper<SpGoods> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cat_one_id",vo.getCatId());
            List<SpGoods> spGoodsList = this.spGoodsMapper.selectList(queryWrapper);
            vo.setSpGoodsList(spGoodsList);
        }
    }


    // 構建子菜單
    public List<SpCategoryVO> buildMenu(List<SpCategoryVO> list) {
        // 首先找到一級菜單
        List<SpCategoryVO> levelOneList = list.stream().filter(c -> c.getCatPid() == 0).collect(Collectors.toList());
        for (SpCategoryVO vo : levelOneList) {
            recursion(list, vo);
        }
        return levelOneList;
    }

    // 遞歸分類
    public void recursion (List<SpCategoryVO> list, SpCategoryVO vo) {
        // 找到子菜單
        List<SpCategoryVO> children = getChildren(list, vo);
        vo.setChildren(children);
        // 繼續查找子菜單
        if (children.size() > 0) {
            for (SpCategoryVO child : children) {
                recursion(list, child);
            }
        }
    }

    // 獲取子菜單
    public List<SpCategoryVO> getChildren(List<SpCategoryVO> list, SpCategoryVO vo) {
        List<SpCategoryVO> children = new ArrayList<>();
        Iterator<SpCategoryVO> iterator = list.iterator();
        while (iterator.hasNext()) {
            SpCategoryVO next = iterator.next();
            if (next.getCatPid().equals(vo.getCatId())) {
                children.add(next);
            }
        }
        return children;
    }
}
