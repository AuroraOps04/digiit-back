package com.example.demo.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.domain.Project;
import com.example.demo.domain.Response;
import com.example.demo.domain.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepostiroy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
@SaCheckLogin
public class ProjectController extends BaseController {
    private final ProjectRepository projectRepository;
    private final UserRepostiroy userRepostiroy;


    // 刪除工程
    @DeleteMapping("/{id:\\d+}")
    public Response remove(@PathVariable("id") Integer id){
       projectRepository.deleteById(id);
       return success();
    }


    // 创建或者更新
    @PostMapping
    public Response createOrUpdate(@RequestBody Project project) {
        Optional<User> user = userRepostiroy.findById(StpUtil.getLoginIdAsInt());
        if (!user.isPresent()) {
            return businessError("用户不存在");
        }
        project.setOwner(user.get());
        // project.setOwnerId(user.get().getId());
        return success(projectRepository.save(project));
    }

    // 查询列表
    @GetMapping
    public Response list(){
        Optional<User> byId = userRepostiroy.findById(StpUtil.getLoginIdAsInt());
        if(!byId.isPresent()){
            return businessError("用户不存在");
        }
        return success(byId.get().getProjectList());
    }

    // 根据主键查询
   @GetMapping("/{id:\\d+}")
    public Response getById(@PathVariable("id") Integer id){
       Optional<Project> byId = projectRepository.findById(id);
       if(!byId.isPresent()){
           return notFound();
       }
       return success(byId.get());
   }


}
