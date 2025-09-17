package cn.com.zhejiangart.controller.index;

import cn.com.zhejiangart.util.UploadUtils;
import cn.com.zhejiangart.util.Result;
import cn.com.zhejiangart.util.ThreadUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器
 */
@RestController
@RequestMapping("/api/upload")
@Tag(name = "文件上传接口")
public class UploadController {
    
    @Resource
    private UploadUtils uploadUtils;
    
    /**
     * 上传单个文件
     *
     * @param file 文件
     * @return 上传结果
     */
    @PostMapping("/single")
    @Operation(summary = "上传单个文件", description = "上传单个文件")
    public Result<?> uploadSingle(
            @Parameter(description = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        try {
            // 获取当前用户ID
            Object userIdObj = ThreadUtils.getId("userId");
            Integer userId = userIdObj instanceof String ? Integer.valueOf((String) userIdObj) : (Integer) userIdObj;
            
            String filePath = uploadUtils.uploadFile(file, userId);
            
            Map<String, Object> data = new HashMap<>();
            data.put("file_path", filePath);
            data.put("file_name", file.getOriginalFilename());
            data.put("file_size", file.getSize());
            
            return Result.success(data);
        } catch (Exception e) {
            return Result.error(500, "文件上传失败：" + e.getMessage());
        }
    }
    
    /**
     * 上传多个文件
     *
     * @param files 文件数组
     * @return 上传结果
     */
    @PostMapping("/multiple")
    @Operation(summary = "上传多个文件", description = "上传多个文件")
    public Result<?> uploadMultiple(
            @Parameter(description = "文件数组", required = true)
            @RequestParam("files") MultipartFile[] files) {
        try {
            // 获取当前用户ID
            Object userIdObj = ThreadUtils.getId("userId");
            Integer userId = userIdObj instanceof String ? Integer.valueOf((String) userIdObj) : (Integer) userIdObj;
            
            Map<String, Object> result = new HashMap<>();
            result.put("total", files.length);
            
            int successCount = 0;
            StringBuilder errorMsg = new StringBuilder();
            
            for (int i = 0; i < files.length; i++) {
                try {
                    String filePath = uploadUtils.uploadFile(files[i], userId);
                    successCount++;
                } catch (Exception e) {
                    errorMsg.append("文件 ").append(files[i].getOriginalFilename())
                            .append(" 上传失败：").append(e.getMessage()).append("; ");
                }
            }
            
            result.put("success", successCount);
            result.put("failed", files.length - successCount);
            result.put("error_msg", errorMsg.toString());
            
            if (successCount == files.length) {
                return Result.success(result);
            } else if (successCount > 0) {
                // 修改为只传递data参数
                return Result.success(result);
            } else {
                return Result.error(500, "文件上传全部失败：" + errorMsg.toString());
            }
        } catch (Exception e) {
            return Result.error(500, "文件上传失败：" + e.getMessage());
        }
    }
}