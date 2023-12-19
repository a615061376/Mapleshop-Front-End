package tw.com.mapleshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tw.com.mapleshop.entity.SpUser;
import tw.com.mapleshop.exception.SpException;
import tw.com.mapleshop.form.SpUserLoginForm;
import tw.com.mapleshop.form.SpUserRegisterForm;
import tw.com.mapleshop.mapper.SpUserMapper;
import tw.com.mapleshop.result.ResponseEnum;
import tw.com.mapleshop.service.SpUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tw.com.mapleshop.utils.MD5Util;
import tw.com.mapleshop.utils.RegexValidateUtil;

@Service
@Slf4j
public class SpUserServiceImpl extends ServiceImpl<SpUserMapper, SpUser> implements SpUserService {

    @Autowired
    private SpUserMapper spUserMapper;

    @Override
    public SpUser register(SpUserRegisterForm spUserRegisterForm) {
        // 用戶名是否可用
        QueryWrapper<SpUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", spUserRegisterForm.getUsername());
        SpUser one = this.spUserMapper.selectOne(queryWrapper);
        if (one != null) {
            log.info("【用戶註冊】用戶名已存在");
            throw new SpException(ResponseEnum.USERNAME_EXISTS);
        }
        // 電子郵箱格式校驗
        if (!RegexValidateUtil.checkEmail(spUserRegisterForm.getUserEmail())){
            log.info("【用戶註冊】電子郵箱格式錯誤");
            throw new SpException(ResponseEnum.EMAIL_ERROR);
        }
        // 手機格式校驗
        if (!RegexValidateUtil.checkPhone(spUserRegisterForm.getUserTel())){
            log.info("【用戶註冊】手機格式錯誤");
            throw new SpException(ResponseEnum.PHONE_ERROR);
        }
        // 儲存數據
        SpUser spUser = new SpUser();
        BeanUtils.copyProperties(spUserRegisterForm, spUser);
        spUser.setPassword(MD5Util.getSaltMD5(spUser.getPassword()));
        int insert = this.spUserMapper.insert(spUser);
        if (insert != 1) {
            log.info("【用戶註冊】添加用戶失敗");
            throw new SpException(ResponseEnum.USER_REGISTER_ERROR);
        }
        return spUser;
    }

    @Override
    public SpUser login(SpUserLoginForm spUserLoginForm) {
        // 判斷帳號是否存在
        QueryWrapper<SpUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", spUserLoginForm.getUsername());
        SpUser spUser = this.spUserMapper.selectOne(queryWrapper);
        if (spUser == null) {
            log.info("【用戶登入】用戶名錯誤或帳號不存在");
            throw new SpException(ResponseEnum.USERNAME_NOT_EXISTS);
        }
        // 判斷密碼是否正確
        boolean saltverifyMD5 = MD5Util.getSaltverifyMD5(spUserLoginForm.getPassword(), spUser.getPassword());
        if (!saltverifyMD5) {
            log.info("【用戶登入】密碼錯誤");
            throw new SpException(ResponseEnum.PASSWORD_ERROR);
        }
        return spUser;
    }
}
