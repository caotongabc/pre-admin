package com.xd.pre.modules.sys.service;

import com.xd.pre.modules.sys.domain.SysCourse;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程表 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-08-10
 */

public interface ISysCourseService extends IService<SysCourse> {

    /**
     * 保存课程
     *
     * @param sysCourse
     * @return
     */
    boolean saveCourse(SysCourse sysCourse);


    /**
     * 获取正常租户
     *
     * @return
     */
    List<SysCourse> getNormalCourse();
}
