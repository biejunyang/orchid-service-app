package com.orchid.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色信息值传递对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo {

    private Long roleId;

    private List<Long> privilegeIds;

}
