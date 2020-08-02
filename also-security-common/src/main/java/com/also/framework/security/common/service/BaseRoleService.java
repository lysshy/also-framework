package com.also.framework.security.common.service;




import com.also.framework.security.common.domain.auth.BaseRole;

import java.util.List;

public interface BaseRoleService {

    BaseRole selectById(long id);

    List<BaseRole> selectByUserId(long id);

    void insert(BaseRole baseRole);
}
