package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.domain.Response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.net.NetUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/file")
@Slf4j
public class FileController extends BaseController {
  private String uploadFolder = System.getProperty("user.dir") + "/upload/";


  @PostMapping("/upload")
  @ResponseBody
  public Response upload(MultipartFile file, HttpServletRequest request) {
    String localAddr = NetUtil.getLocalhostStr();
    int localPort = request.getLocalPort();
    String originalFilename = file.getOriginalFilename();
    try {
      FileUtil.mkdir(uploadFolder);
      File file2 = FileUtil.file(uploadFolder, originalFilename);
      file.transferTo(file2);
    } catch (IORuntimeException | IOException e) {
      log.error(e.getMessage());
    }
    String url = "http://" + localAddr + ":" + localPort + "/file/download?filename=" + originalFilename;
    return success(url);
  }

  @GetMapping("/download")
  public void download(@RequestParam("filename") String filename, HttpServletResponse response) {
    // IOUtils
    response.setHeader("Content-Type", "application/octet-stream");
    response.setHeader("Content-Disposition", "attachment;filename=" + filename);
    try {
      FileInputStream fileInputStream = new FileInputStream(uploadFolder + filename);
      byte[] readBytes = IoUtil.readBytes(fileInputStream);
      // IoUtil.copy(, response.getOutputStream());
      response.getOutputStream().write(readBytes);
    } catch (IORuntimeException | IOException e) {
      return;
    }
  }
}
