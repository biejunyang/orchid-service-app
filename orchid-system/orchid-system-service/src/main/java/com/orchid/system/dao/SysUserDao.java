package com.orchid.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orchid.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-23 20:35:48
 */
public interface SysUserDao extends BaseMapper<SysUser> {

    List<SysUser> findUsers(@Param("userVo") SysUser userVo);

    List<SysUser> findUsers(IPage<SysUser> page, @Param("userVo") SysUser userVo);
}
