package com.also.framework.security.mapper;

import com.also.framework.security.domain.auth.BaseRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BaseRoleMapper extends BaseMapper<BaseRole> {

    @Select("select * from sys_role where id in (select role_id from sys_user_role where user_id=#{userId} ) ")
    List<BaseRole> selectRoleByUserId(@Param("userId") long userId);
}
