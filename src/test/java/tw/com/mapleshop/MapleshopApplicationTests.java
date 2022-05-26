package tw.com.mapleshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.mapleshop.service.SpCategoryService;

import tw.com.mapleshop.vo.SpCategoryVO;

import java.util.List;

@SpringBootTest
class MapleshopApplicationTests {

    @Autowired
    private SpCategoryService service;

    @Test
    void contextLoads() {
        List<SpCategoryVO> spCategoryVOList = this.service.buildCategoryMenu();
        int i = 0;
    }

}
