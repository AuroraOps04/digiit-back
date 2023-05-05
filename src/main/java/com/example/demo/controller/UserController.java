package com.example.demo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.domain.Response;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepostiroy;
import com.example.demo.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends BaseController {
    private final UserRepostiroy userRepostiroy;

    // 用户列表
    @GetMapping
    public Response list() {
        return success(userRepostiroy.findAll());
    }

    // 登录
    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        User loginUser = userRepostiroy.findByUsername(user.getUsername());
        if (null == loginUser) {
            return businessError("账户不存在");
        }
        if (!PasswordEncoder.check(user.getPassword(), loginUser.getPassword())) {
            return businessError("用户名或密码错误");
        }
        StpUtil.login(loginUser.getId());
        return success();

    }


    // 用戶详情
    @GetMapping("/info")
    @SaCheckLogin
    public Response userInfo() {
        int id = StpUtil.getLoginIdAsInt();
        Optional<User> user = userRepostiroy.findById(id);
        if (!user.isPresent()) {
            return businessError("用户不存在");
        }
        System.out.println(123);
        return success(user);
    }


    // 刪除用戶
    @DeleteMapping("/{id:\\d+}")
    @SaCheckLogin
    public Response remove(@PathVariable("id") Integer id) {
        userRepostiroy.deleteById(id);
        return success();
    }


    // 注册
    @PostMapping("/register")
    public Response register(@RequestBody User user) {
        User exitsUser = userRepostiroy.findByUsername(user.getUsername());
        if (null != exitsUser) {
            return businessError("用户已存在");
        }
        user.setPassword(PasswordEncoder.encode(user.getPassword()));

        return success(userRepostiroy.save(user));
    }


    // 更新
    @PutMapping("/{id:\\d+}")
    public Response update(@PathVariable("id") Integer id,@RequestBody User user){
        if(!userRepostiroy.findById(id).isPresent()){
            return businessError("用户不存在");
        }
       return success(userRepostiroy.save(user));
    }


    // 退出登录
    @DeleteMapping("/logout")
    public Response logout() {
        StpUtil.logout();
        return success();
    }

}
