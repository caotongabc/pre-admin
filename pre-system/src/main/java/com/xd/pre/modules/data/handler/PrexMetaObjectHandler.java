package com.xd.pre.modules.data.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.time.LocalTime.now;

/**
 * @Classname PrexMetaObjectHandler
 * @Description  字段自动填充器
 * @Author Created by Lihaodong (alias:小东啊) im.lihaodong@gmail.com
 * @Date 2019-11-13 16:25
 * @Version 1.0
 */
@Slf4j
@Component
public class PrexMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //避免使用metaObject.setValue()
        //Timestamp createTime = new Timestamp(new Date().getTime());
        this.setFieldValByName("delFlag", "0", metaObject);
       // this.setFieldValByName("createTime",createTime,metaObject);
        //this.setFieldValByName("updateTime",createTime,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("operator", "Tom", metaObject);
        //Timestamp createTime1 = new Timestamp(new Date().getTime());
      // this.setFieldValByName("updateTime",createTime1,metaObject);
    }
}
