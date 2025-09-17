package cn.com.zhejiangart.util;

import cn.com.zhejiangart.config.UploadConfig;
import cn.com.zhejiangart.model.vo.SystemUploadfile;
import cn.com.zhejiangart.service.ISystemUploadfileService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 上传工具类
 */
@Component
public class UploadUtils {
    
    @Resource
    private UploadConfig uploadConfig;
    
    @Resource
    private ISystemUploadfileService systemUploadfileService;
    
    // OSS客户端
    private OSS ossClient;
    
    @PostConstruct
    public void init() {
        // 初始化OSS客户端
        String endpoint = uploadConfig.getOss().getEndpoint();
        String accessKeyId = uploadConfig.getOss().getAccessKeyId();
        String accessKeySecret = uploadConfig.getOss().getAccessKeySecret();
        
        if (endpoint != null && !endpoint.isEmpty() && 
            accessKeyId != null && !accessKeyId.isEmpty() && 
            accessKeySecret != null && !accessKeySecret.isEmpty()) {
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        }
    }
    
    /**
     * 上传文件
     * 
     * @param file 文件
     * @return 文件访问路径
     * @throws IOException IO异常
     * @throws IllegalArgumentException 参数异常
     */
    public String uploadFile(MultipartFile file) throws IOException, IllegalArgumentException {
        return uploadFile(file, null);
    }
    
    /**
     * 上传文件
     * 
     * @param file 文件
     * @param userId 用户ID（可选）
     * @return 文件访问路径
     * @throws IOException IO异常
     * @throws IllegalArgumentException 参数异常
     */
    public String uploadFile(MultipartFile file, Integer userId) throws IOException, IllegalArgumentException {
        // 检查文件是否为空
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("文件不能为空");
        }
        
        // 检查文件大小
        if (file.getSize() > uploadConfig.getAllowSize()) {
            throw new IllegalArgumentException("文件大小超过限制");
        }
        
        // 检查文件类型
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.lastIndexOf(".") == -1) {
            throw new IllegalArgumentException("文件名不合法");
        }
        
        String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        Set<String> allowExts = new HashSet<>();
        String[] exts = uploadConfig.getAllowExt().split(",");
        for (String ext : exts) {
            allowExts.add(ext.toLowerCase().trim());
        }
        
        if (!allowExts.contains(fileExt)) {
            throw new IllegalArgumentException("不支持的文件类型");
        }
        
        // 检查MIME类型
        String contentType = file.getContentType();
        if (contentType == null) {
            throw new IllegalArgumentException("文件MIME类型不合法");
        }
        
        Set<String> allowMimes = new HashSet<>();
        String[] mimes = uploadConfig.getAllowMime().split(",");
        for (String mime : mimes) {
            allowMimes.add(mime.toLowerCase().trim());
        }
        
        if (!allowMimes.contains(contentType.toLowerCase())) {
            throw new IllegalArgumentException("不支持的文件MIME类型");
        }
        
        // 根据上传类型处理文件
        String uploadType = uploadConfig.getType();
        String filePath;
        switch (uploadType) {
            case "local":
                filePath = uploadLocal(file, fileExt);
                break;
            case "oss":
                filePath = uploadOss(file, fileExt);
                break;
            default:
                throw new IllegalArgumentException("不支持的上传类型: " + uploadType);
        }
        
        // 保存文件信息到数据库
        saveFileToDatabase(file, uploadType, filePath, userId);
        
        return filePath;
    }
    
    /**
     * 保存文件信息到数据库
     * 
     * @param file 上传的文件
     * @param uploadType 上传类型
     * @param filePath 文件路径
     * @param userId 用户ID
     */
    private void saveFileToDatabase(MultipartFile file, String uploadType, String filePath, Integer userId) {
        try {
            SystemUploadfile uploadfile = new SystemUploadfile();
            uploadfile.setUploadType(uploadType);
            uploadfile.setOriginalName(file.getOriginalFilename());
            uploadfile.setUrl(filePath);
            uploadfile.setFileSize((int) file.getSize());
            uploadfile.setMimeType(file.getContentType());
            uploadfile.setFileExt(getFileExtension(file.getOriginalFilename()));
            uploadfile.setSha1(calculateSHA1(file.getBytes()));
            uploadfile.setCreateTime((int) (System.currentTimeMillis() / 1000));
            uploadfile.setUploadTime((int) (System.currentTimeMillis() / 1000));
            uploadfile.setUpdateTime((int) (System.currentTimeMillis() / 1000));
            if (userId != null) {
                uploadfile.setUserId(userId);
            }
            
            // 如果是图片文件，尝试获取图片信息
            if (file.getContentType() != null && file.getContentType().startsWith("image")) {
                // 简化处理，实际项目中可以使用图像处理库获取真实宽高
                uploadfile.setImageWidth("0");
                uploadfile.setImageHeight("0");
                uploadfile.setImageType(file.getContentType());
                uploadfile.setImageFrames(1);
            }
            
            systemUploadfileService.save(uploadfile);
        } catch (Exception e) {
            // 记录日志，但不中断上传流程
            e.printStackTrace();
        }
    }
    
    /**
     * 获取文件扩展名
     * 
     * @param fileName 文件名
     * @return 文件扩展名
     */
    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
    
    /**
     * 计算文件SHA1值
     * 
     * @param bytes 文件字节数组
     * @return SHA1值
     */
    private String calculateSHA1(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] sha1Bytes = md.digest(bytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : sha1Bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
    
    /**
     * 本地上传
     * 
     * @param file 文件
     * @param fileExt 文件扩展名
     * @return 文件访问路径
     * @throws IOException IO异常
     */
    private String uploadLocal(MultipartFile file, String fileExt) throws IOException {
        // 创建日期目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String datePath = sdf.format(new Date());
        
        // 创建上传目录
        String localPath = uploadConfig.getLocalPath();
        File uploadDir = new File(localPath, datePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        
        // 生成唯一文件名
        String fileName = UUID.randomUUID().toString().replace("-", "") + "." + fileExt;
        
        // 保存文件
        File dest = new File(uploadDir, fileName);
        file.transferTo(dest);
        
        // 返回访问路径
        return "/uploads/" + datePath + "/" + fileName;
    }
    
    /**
     * OSS上传
     * 
     * @param file 文件
     * @param fileExt 文件扩展名
     * @return 文件访问路径
     * @throws IOException IO异常
     */
    private String uploadOss(MultipartFile file, String fileExt) throws IOException {
        // 检查OSS客户端是否已初始化
        if (ossClient == null) {
            throw new IllegalStateException("OSS客户端未初始化，请检查OSS配置");
        }
        
        // 创建日期目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String datePath = sdf.format(new Date());
        
        // 生成唯一文件名
        String fileName = UUID.randomUUID().toString().replace("-", "") + "." + fileExt;
        String objectName = datePath + "/" + fileName;
        
        // 获取存储桶名称
        String bucketName = uploadConfig.getOss().getBucket();
        if (bucketName == null || bucketName.isEmpty()) {
            throw new IllegalStateException("OSS存储桶名称未配置");
        }
        
        // 上传文件
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, 
                new ByteArrayInputStream(file.getBytes()));
        ossClient.putObject(putObjectRequest);
        
        // 返回文件访问路径
        String domain = uploadConfig.getOss().getDomain();
        if (domain != null && !domain.isEmpty()) {
            return "https://" + domain + "/" + objectName;
        } else {
            return "https://" + bucketName + "." + uploadConfig.getOss().getEndpoint() + "/" + objectName;
        }
    }
    
    /**
     * 销毁OSS客户端
     */
    @PreDestroy
    public void destroy() {
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }
}