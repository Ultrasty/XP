package com.tongji.exam.controller;

import com.tongji.exam.qo.DownloadQo;
import com.tongji.exam.qo.UploadModel;
import com.tongji.exam.qo.UploadModel2;
import com.tongji.exam.utils.FileTransUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@Api(tags = "Upload And Download APIs")
@RequestMapping("/file")
@Slf4j
public class UploadDownloadController {

    /**
     * 单文件上传,可以同时传入参数
     * @param dir
     * @param uploadfile
     * @return
     */
    @ApiOperation("单文件上传,可以同时传入参数")
    @PostMapping("/api/upload/singleAndparas")
    public String uploadFileSingle(@RequestParam("dir") String dir, @RequestParam("file") MultipartFile uploadfile) {
        return FileTransUtil.uploadFile(uploadfile, dir);
    }

    /**
     * 单文件上传,支持同时传入文件读取模式参数
     * @param model
     * @return
     */
    @ApiOperation("单文件上传,支持同时传入文件读取模式参数")
    @PostMapping("/upload/single/model")
    public String singleUploadFileModel(@ModelAttribute("model") UploadModel2 model) {
        return FileTransUtil.uploadFile(model.getFile(), model.getDir());
    }

    /**
     * 多文件上传,支持同时传入参数
     * @param dir
     * @param uploadfiles
     * @return
     */
    @ApiOperation("多文件上传,支持同时传入参数")
    @PostMapping("upload/multiAndparas")
    public String uploadFileMulti(@RequestParam("dir") String dir, @RequestParam("files") MultipartFile[] uploadfiles) {
        return FileTransUtil.uploadFiles(uploadfiles, dir);
    }

    /**
     * 多文件上传,支持同时传入参数
     * @param model
     * @return
     */
    @ApiOperation("多文件上传,支持同时传入参数")
    @PostMapping(value = "/upload/multi/model")
    public String multiUploadFileModel(@ModelAttribute(("model")) UploadModel model) {
        return FileTransUtil.uploadFiles(model.getFiles(), model.getDir());
    }

    /**
     * Get下载文件
     * @param filePath
     * @return
     * @throws IOException
     */
    @ApiOperation("Get下载文件")
    @GetMapping(value = "/download/get")
    public ResponseEntity<InputStreamResource> downloadFileGet(@RequestParam String filePath) throws IOException {
        return FileTransUtil.downloadFile(filePath);
    }

    /**
     * Post下载文件
     * @param downloadQo
     * @return
     * @throws IOException
     */
    @ApiOperation("Post下载文件")
    @PostMapping(value = "/download/post")
    public ResponseEntity<InputStreamResource> downloadFilePost(@RequestBody DownloadQo downloadQo) throws IOException {
        return FileTransUtil.downloadFile(downloadQo.getPath());
    }
}
