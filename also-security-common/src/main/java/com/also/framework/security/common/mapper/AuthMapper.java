package com.also.framework.security.common.mapper;


import com.also.framework.security.common.domain.UserDetail;
import com.also.framework.security.common.domain.auth.BaseRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AuthMapper {

    @Select("select * from sys_user where username=#{username}")
    UserDetail findByUsername(@Param("username") String username);

    @Insert("insert into sys_user(username, password) values (#{user.username}, #{user.password})")
    void insertUser(@Param("user") UserDetail user);

    @Insert("insert into sys_user_role(role_id, user_id) values (#{roleId}, #{userId})")
    void insertUserRole(@Param("userId") long userId, @Param("roleId") long roleId);

    @Select("select * from sys_role where id=#{roleId} ")
    BaseRole findRoleById(@Param("roleId") long roleId);

    @Select("select * from sys_role where id in (select role_id from sys_user_role where user_id=#{userId} ) ")
    List<BaseRole> findRoleByUserId(@Param("userId") long userId);
}
