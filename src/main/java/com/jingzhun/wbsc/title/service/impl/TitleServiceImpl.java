package com.jingzhun.wbsc.title.service.impl;

import com.jingzhun.wbsc.category.entity.TCategoryEntity;
import com.jingzhun.wbsc.title.controller.Title;
import com.jingzhun.wbsc.title.service.TitleService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/15 0015.
 */
@Service("TitleServiceImpl")
@Transactional
public class TitleServiceImpl extends CommonServiceImpl implements TitleService {
    private static final Logger log = LoggerFactory.getLogger(TitleServiceImpl.class);

    @Override
    public void update(Integer imageId, String webImgName) {
        String sql="update t_image set web_img_name= '"+webImgName+"'   where id="+imageId;
        log.error("AAAA: "+sql);
        this.updateBySqlString(sql);

    }
}
