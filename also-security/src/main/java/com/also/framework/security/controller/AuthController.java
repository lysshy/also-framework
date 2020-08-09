package com.also.framework.security.controller;

import com.also.framework.security.domain.ResultCode;
import com.also.framework.security.domain.ResultJson;
import com.also.framework.security.domain.auth.ResponseUserToken;
import com.also.framework.security.domain.auth.BaseRole;
import com.also.framework.security.domain.auth.BaseUser;
import com.also.framework.security.domain.auth.UserDetail;
import com.also.framework.security.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * @author JoeTao
 * createAt: 2018/9/17
 */

@RestController
@Api(value = "登陆注册及刷新token")
@RequestMapping("/api/v1")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
    public ResultJson<ResponseUserToken> login(
            @Valid @RequestBody BaseUser user){
        final UserDetail response = authService.login(user.getUsername(), user.getPassword());
        return ResultJson.ok(response);
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "登出", notes = "退出登陆")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public ResultJson logout(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        authService.logout(token);
        return ResultJson.ok();
    }

    @GetMapping(value = "/user")
    @ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public ResultJson getUser(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        return ResultJson.ok(userDetail);
    }

    @PostMapping(value = "/sign")
    @ApiOperation(value = "用户注册")
    public ResultJson sign(@RequestBody BaseUser user) {
//        if (StringUtils.isAnyBlank(user.getName(), user.getPassword())) {
//            return ResultJson.failure(ResultCode.BAD_REQUEST);
//        }
//        UserDetail userDetail = new UserDetail();
//        userDetail.setUsername(user.getUsername());
//        userDetail.setPassword(user.getPassword());
//        BaseRole role = new BaseRole();
//        role.setId(1L);
//        userDetail.setRoles(Arrays.asList(role));
        return ResultJson.ok(authService.register(user));
    }
//    @GetMapping(value = "refresh")
//    @ApiOperation(value = "刷新token")
//    public ResultJson refreshAndGetAuthenticationToken(
//            HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        ResponseUserToken response = authService.refresh(token);
//        if(response == null) {
//            return ResultJson.failure(ResultCode.BAD_REQUEST, "token无效");
//        } else {
//            return ResultJson.ok(response);
//        }
//    }
}
