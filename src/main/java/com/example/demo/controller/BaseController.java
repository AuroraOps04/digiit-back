package com.example.demo.controller;

import com.example.demo.domain.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author AuroraOps04
 * @date 2022/3/9 21:55:47
 */
public class BaseController {

  protected Response success() {
    return ResponseGenerator.success();
  }

  protected Response success(Object data) {
    return ResponseGenerator.success(data);
  }

  protected <T> Response success(Collection<T> data) {
    return ResponseGenerator.success(data, (long) data.size());
  }

  protected Response success(Object data, Long total) {
    return ResponseGenerator.success(data, total);
  }

  protected Response saveOrUpdateError(Serializable id) {
    return ResponseGenerator.businessError(id == null ? "创建失败" : "更新失败");
  }

  protected Response removeError() {
    return ResponseGenerator.businessError("删除失败");
  }

  protected Response removeError(String message) {
    return ResponseGenerator.businessError(message);
  }

  protected Response businessError() {
    return ResponseGenerator.businessError("业务异常");
  }

  protected Response businessError(String msg) {
    return ResponseGenerator.businessError(msg);
  }

  protected Response silent(String message) {
    return ResponseGenerator.silent(message);
  }

  protected Response notFound() {
    return ResponseGenerator.businessError("记录不存在");
  }
}
