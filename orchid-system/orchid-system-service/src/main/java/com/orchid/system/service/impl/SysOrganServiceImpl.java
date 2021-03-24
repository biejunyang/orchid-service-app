package com.orchid.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchid.core.exception.ExceptionBuilder;
import com.orchid.mybatis.util.AssertUtils;
import com.orchid.system.dao.SysOrganDao;
import com.orchid.system.entity.SysOrgan;
import com.orchid.system.service.SysOrganService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 组织机构信息表(SysOrgan)表服务实现类
 *
 * @author makejava
 * @since 2021-03-23 10:23:45
 */
@Service("sysOrganService")
public class SysOrganServiceImpl extends ServiceImpl<SysOrganDao, SysOrgan> implements SysOrganService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrgan(SysOrgan organ) {
        SysOrgan oldOrgan = this.getById(organ.getId());
        AssertUtils.columnNotUsed(this.baseMapper, organ, "机构名称", SysOrgan::getName, oldOrgan);
        AssertUtils.columnNotUsed(this.baseMapper, organ, "机构编码", SysOrgan::getCode, oldOrgan);

        String oldPids=organ.getPids();
        String[] strs=StrUtil.isNotEmpty(oldPids) ? oldPids.split(",") : null;
        Long oldParentId= strs!=null ? Long.valueOf(strs[strs.length-1]) : null;

        if(!ObjectUtil.equal(oldParentId, organ.getParentId())){
            organ.setPids(null);
            if(organ.getParentId()!=null){
                SysOrgan parentOrgan=this.getById(organ.getParentId());
                if(parentOrgan==null){
                    throw ExceptionBuilder.build("上级机构不存在!");
                }

                if(organ.getId().equals(organ.getParentId())){
                    throw ExceptionBuilder.build("上级机构不能是自己!");
                }

                if(StrUtil.isNotEmpty(parentOrgan.getPids()) && parentOrgan.getPids().contains(organ.getId().toString())){
                    throw ExceptionBuilder.build("上级机构不能是自己的子机构!");
                }

                String pids= (StrUtil.isNotEmpty(parentOrgan.getPids()) ? parentOrgan.getPids()+"," : "") + parentOrgan.getId();
                organ.setPids(pids);
            }

            List<SysOrgan> childrenOrgans=getChildrenOrgans(oldOrgan);
            if(CollectionUtil.isNotEmpty(childrenOrgans)){
                childrenOrgans.forEach(o -> {
                    String str=o.getPids().replaceAll(oldPids, StrUtil.isNotEmpty(organ.getPids()) ? organ.getPids() : "");
                    if(StrUtil.isNotEmpty(str)){
                        o.setPids(str);
                    }else{
                        o.setPids(null);
                    }
                });
                this.updateBatchById(childrenOrgans);
            }
        }

        this.updateById(organ);
    }


    /**
     * 获取所有子机构列表
     * @param organ
     * @return
     */
    public List<SysOrgan> getChildrenOrgans(SysOrgan organ){
        String pids= (StrUtil.isNotEmpty(organ.getPids()) ? organ.getPids()+"," : "") + organ.getId();
        return this.list(Wrappers.<SysOrgan>lambdaQuery().likeRight(SysOrgan::getPids, pids));
    }
}
