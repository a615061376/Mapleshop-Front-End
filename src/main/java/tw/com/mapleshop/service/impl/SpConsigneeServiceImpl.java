package tw.com.mapleshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import tw.com.mapleshop.entity.SpConsignee;

import tw.com.mapleshop.entity.SpUser;
import tw.com.mapleshop.exception.SpException;
import tw.com.mapleshop.form.SpConsigneeForm;
import tw.com.mapleshop.mapper.SpConsigneeMapper;
import tw.com.mapleshop.result.ResponseEnum;
import tw.com.mapleshop.service.SpConsigneeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tw.com.mapleshop.utils.RegexValidateUtil;
import tw.com.mapleshop.vo.SpConsigneeVO;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SpConsigneeServiceImpl extends ServiceImpl<SpConsigneeMapper, SpConsignee> implements SpConsigneeService {

    @Autowired
    private SpConsigneeMapper spConsigneeMapper;

    @Override
    public SpConsignee input(SpConsigneeForm spConsigneeForm, SpUser spUser) {
        // 手機格式校驗
        if (!RegexValidateUtil.checkPhone(spConsigneeForm.getCgnTel())){
            log.info("【訂單資訊】手機格式錯誤");
            throw new SpException(ResponseEnum.PHONE_ERROR);
        }
        // 電子郵箱格式校驗
        if (!RegexValidateUtil.checkEmail(spConsigneeForm.getCgnEmail())) {
            log.info("【訂單資訊】電子郵箱格式錯誤");
            throw new SpException(ResponseEnum.EMAIL_ERROR);
        }
        //存儲數據
        SpConsignee spConsignee = new SpConsignee();
        BeanUtils.copyProperties(spConsigneeForm, spConsignee);
        spConsignee.setUserId(spUser.getUserId());
        int insert = this.spConsigneeMapper.insert(spConsignee);
        if (insert != 1){
            log.info("【訂單資訊】訂單資訊添加失敗");
            throw new SpException(ResponseEnum.CGN_INFO_ERROR);
        }
        return spConsignee;
    }

    @Override
    public List<SpConsigneeVO> findAddressByUser(Integer userId) {
        QueryWrapper<SpConsignee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<SpConsignee> spConsigneeList = this.spConsigneeMapper.selectList(queryWrapper);
        List<SpConsigneeVO> spConsigneeVOList = new ArrayList<>();
        for (SpConsignee spConsignee : spConsigneeList) {
            SpConsigneeVO spConsigneeVO = new SpConsigneeVO();
            BeanUtils.copyProperties(spConsignee, spConsigneeVO);
            spConsigneeVOList.add(spConsigneeVO);
        }
        return spConsigneeVOList;
    }
}
