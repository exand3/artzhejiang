package cn.com.zhejiangart.controller.user;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import cn.com.zhejiangart.model.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.zhejiangart.model.vo.News;
import cn.com.zhejiangart.model.vo.Show;
import cn.com.zhejiangart.service.IShowService;
import cn.com.zhejiangart.service.INewsService;
import cn.com.zhejiangart.service.IBankListService;
import cn.com.zhejiangart.service.IUserService;
import cn.com.zhejiangart.util.Result;
import cn.com.zhejiangart.util.ThreadUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/user")
@Tag(name = "用户相关接口", description = "提供用户个人信息、钱包、银行卡、收藏等相关功能")
public class UserController {

    @Autowired
    private IBankListService bankListService;

    @Autowired
    private IShowService showService;
    
    @Autowired
    private INewsService newsService;

    /**
     * 获取用户个人信息
     *
     * @return 用户个人信息
     */
    @GetMapping("/profile")
    @Operation(summary = "获取用户个人信息", description = "获取当前登录用户的个人信息")
    public Result<UserProfileDTO> getUserProfile() {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 查询用户信息（示例中使用模拟数据）
            UserProfileDTO profileDTO = new UserProfileDTO();
            profileDTO.setUserId(String.valueOf(userId));
            profileDTO.setPhone("13800138000");
            profileDTO.setCateId("艺术爱好者");
            profileDTO.setRealName("张三");
            profileDTO.setNickname("艺术爱好者");
            profileDTO.setGender("1");
            profileDTO.setBirthday(LocalDate.now());
            profileDTO.setEmail("zhangsan@example.com");
            profileDTO.setIdentityDesc("艺术收藏家");
            profileDTO.setIdentify("1");
            profileDTO.setAvatar("/static/images/avatar.jpg");

            return Result.success(profileDTO);
        } catch (Exception e) {
            return Result.error(500, "获取用户信息失败：" + e.getMessage());
        }
    }

    /**
     * 获取用户详细信息
     *
     * @return 用户详细信息
     */
    @GetMapping("/getUserInfo")
    @Operation(summary = "获取用户详细信息", description = "获取当前登录用户的详细信息")
    public Result<UserProfileDTO> getUserInfo() {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 查询用户信息（示例中使用模拟数据）
            UserProfileDTO profileDTO = new UserProfileDTO();
            profileDTO.setUserId(String.valueOf(userId));
            profileDTO.setPhone("13800138000");
            profileDTO.setCateId("艺术爱好者");
            profileDTO.setRealName("张三");
            profileDTO.setNickname("艺术爱好者");
            profileDTO.setGender("1");
            profileDTO.setBirthday(LocalDate.now());
            profileDTO.setEmail("zhangsan@example.com");
            profileDTO.setIdentityDesc("艺术收藏家");
            profileDTO.setIdentify("1");

            return Result.success(profileDTO);
        } catch (Exception e) {
            return Result.error(500, "获取用户信息失败：" + e.getMessage());
        }
    }

    /**
     * 更新用户信息
     *
     * @param profileDTO 用户信息
     * @return 更新结果
     */
    @PostMapping("/updateUserInfo")
    @Operation(summary = "更新用户信息", description = "更新当前登录用户的个人信息")
    public Result<String> updateUserInfo(@RequestBody UserProfileDTO profileDTO) {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 更新用户信息（示例中仅返回成功）
            return Result.success("用户信息更新成功");
        } catch (Exception e) {
            return Result.error(500, "更新用户信息失败：" + e.getMessage());
        }
    }

    /**
     * 获取用户钱包信息
     *
     * @return 钱包信息
     */
    @PostMapping("/wallet")
    @Operation(summary = "获取用户钱包信息", description = "获取当前登录用户的钱包信息")
    public Result<WalletDTO> getWallet() {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 查询钱包信息（示例中使用模拟数据）
            WalletDTO walletDTO = new WalletDTO();
            walletDTO.setMoney(new BigDecimal("0.01"));
            walletDTO.setScore(new BigDecimal("0.01"));
            walletDTO.setDeposit(new BigDecimal("0.01"));

            return Result.success(walletDTO);
        } catch (Exception e) {
            return Result.error(500, "获取钱包信息失败：" + e.getMessage());
        }
    }

    /**
     * 获取用户银行列表
     *
     * @return 银行列表
     */
    @PostMapping("/bankList")
    @Operation(summary = "获取用户银行列表", description = "获取当前登录用户的银行列表")
    public Result<List<BankListDTO>> getBankList() {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 查询银行列表（示例中使用模拟数据）
            BankListDTO bank1 = new BankListDTO();
            bank1.setId("1");
            bank1.setBankNo("1234567890123456");
            bank1.setBankName("中国银行");
            bank1.setBankBranch("北京分行");
            bank1.setUsername("张三");
            bank1.setTelephone("13800138000");
            bank1.setUpdateTime(LocalDateTime.now());

            BankListDTO bank2 = new BankListDTO();
            bank2.setId("2");
            bank2.setBankNo("6543210987654321");
            bank2.setBankName("工商银行");
            bank2.setBankBranch("上海分行");
            bank2.setUsername("张三");
            bank2.setTelephone("13800138000");
            bank2.setUpdateTime(LocalDateTime.now());

            List<BankListDTO> bankList = List.of(bank1, bank2);

            return Result.success(bankList);
        } catch (Exception e) {
            return Result.error(500, "获取银行列表失败：" + e.getMessage());
        }
    }

    /**
     * 添加银行卡
     *
     * @param addBankDTO 银行卡信息
     * @return 添加结果
     */
    @PostMapping("/addBank")
    @Operation(summary = "添加银行卡", description = "为当前登录用户添加银行卡")
    public Result<String> addBank(@RequestBody AddBankDTO addBankDTO) {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 添加银行卡（示例中仅返回成功）
            return Result.success("银行卡添加成功");
        } catch (Exception e) {
            return Result.error(500, "添加银行卡失败：" + e.getMessage());
        }
    }

    /**
     * 获取用户收藏列表
     *
     * @return 收藏列表
     */
    @PostMapping("/collect")
    @Operation(summary = "获取用户收藏列表", description = "获取当前登录用户的收藏列表")
    public Result<List<CollectListDTO>> getCollectList() {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 查询收藏列表（示例中使用模拟数据）
            CollectListDTO collect1 = new CollectListDTO();
            collect1.setId("1");
            collect1.setImage("/static/images/art001.jpg");
            collect1.setTitle("山水画作品");
            collect1.setArtist("李四");
            collect1.setArtistId("1");
            collect1.setCategory("中国画");
            collect1.setPrice(new BigDecimal("10000"));
            collect1.setCount("1");
            collect1.setGoodRange("100x50cm");

            CollectListDTO collect2 = new CollectListDTO();
            collect2.setId("2");
            collect2.setImage("/static/images/art002.jpg");
            collect2.setTitle("油画作品");
            collect2.setArtist("王五");
            collect2.setArtistId("2");
            collect2.setCategory("油画");
            collect2.setPrice(new BigDecimal("15000"));
            collect2.setCount("1");
            collect2.setGoodRange("80x60cm");

            List<CollectListDTO> collectList = List.of(collect1, collect2);

            return Result.success(collectList);
        } catch (Exception e) {
            return Result.error(500, "获取收藏列表失败：" + e.getMessage());
        }
    }

    /**
     * 删除收藏
     *
     * @param collectId 收藏ID
     * @return 删除结果
     */
    @PostMapping("/deleteCollect")
    @Operation(summary = "删除收藏", description = "删除指定的收藏项")
    public Result<String> deleteCollect(
            @Parameter(description = "收藏ID") @RequestBody String collectId) {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 删除收藏（示例中仅返回成功）
            return Result.success("收藏删除成功");
        } catch (Exception e) {
            return Result.error(500, "删除收藏失败：" + e.getMessage());
        }
    }

    /**
     * 更新密码
     *
     * @param updatePasswordDTO 密码信息
     * @return 更新结果
     */
    @PostMapping("/updatePassword")
    @Operation(summary = "更新密码", description = "更新当前登录用户的密码")
    public Result<String> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 验证旧密码（示例中跳过验证）
            if (!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getConfirmPassword())) {
                return Result.error(400, "新密码与确认密码不一致");
            }

            // 更新密码（示例中仅返回成功）
            return Result.success("密码更新成功");
        } catch (Exception e) {
            return Result.error(500, "更新密码失败：" + e.getMessage());
        }
    }

    /**
     * 添加展览
     *
     * @param addShowDTO 展览信息
     * @return 添加结果
     */
    @PostMapping("/addShow")
    @Operation(summary = "添加展览", description = "为当前登录用户添加展览")
    public Result<String> addShow(@RequestBody AddShowDTO addShowDTO) {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 添加展览（示例中仅返回成功）
            return Result.success("展览添加成功");
        } catch (Exception e) {
            return Result.error(500, "添加展览失败：" + e.getMessage());
        }
    }

    /**
     * 获取我的银行卡列表
     * @return 银行卡列表数据
     */
    @PostMapping("/getMyBankCards")
    @Operation(summary = "获取我的银行卡列表", description = "获取当前登录用户的银行卡列表")
    public Result<List<BankCardDTO>> getMyBankCards() {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 根据用户ID查询银行卡列表
            List<cn.com.zhejiangart.model.vo.BankList> bankLists = bankListService.lambdaQuery()
                    .eq(cn.com.zhejiangart.model.vo.BankList::getUserId, userId)
                    .list();

            // 转换为DTO列表
            List<BankCardDTO> bankCardDTOs = bankLists.stream().map(bankList -> {
                BankCardDTO dto = new BankCardDTO();
                BeanUtils.copyProperties(bankList, dto);
                return dto;
            }).collect(Collectors.toList());

            return Result.success(bankCardDTOs);
        } catch (Exception e) {
            return Result.error(500, "获取银行卡列表失败：" + e.getMessage());
        }
    }

    /**
     * 申请展览
     * @param showApplicationDTO 展览申请信息
     * @return 申请结果
     */
    @PostMapping("/applyShow")
    @Operation(summary = "申请展览", description = "用户申请举办展览")
    public Result<String> applyShow(@RequestBody ShowApplicationDTO showApplicationDTO) {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 构造展览对象
            Show show = new Show();
            BeanUtils.copyProperties(showApplicationDTO, show);
            show.setUserId(userId);
            show.setStatus("0"); // 默认状态为筹备中
            show.setShenhe("0"); // 默认审核状态为待审核
            show.setCreateTime(java.time.LocalDateTime.now());

            // 保存展览申请
            boolean saved = showService.save(show);
            if (saved) {
                return Result.success("展览申请提交成功");
            } else {
                return Result.error(500, "展览申请提交失败");
            }
        } catch (Exception e) {
            return Result.error(500, "展览申请提交失败：" + e.getMessage());
        }
    }

    /**
     * 获取当前用户申请的展览列表(仅包含审核通过的)
     * @return 展览列表
     */
    @GetMapping("/getMyShows")
    @Operation(summary = "获取我的展览列表", description = "获取当前用户申请的展览列表（仅包含审核通过的）")
    public Result<List<ShowListDTO>> getMyShows() {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 根据用户ID查询展览列表，仅包含审核通过的
            List<Show> shows = showService.lambdaQuery()
                    .eq(Show::getUserId, userId)
                    .eq(Show::getShenhe, "1") // 仅查询审核通过的展览
                    .orderByDesc(Show::getCreateTime)
                    .list();

            // 转换为DTO列表
            List<ShowListDTO> showListDTOs = shows.stream().map(show -> {
                ShowListDTO dto = new ShowListDTO();
                BeanUtils.copyProperties(show, dto);
                return dto;
            }).collect(Collectors.toList());

            return Result.success(showListDTOs);
        } catch (Exception e) {
            return Result.error(500, "获取展览列表失败：" + e.getMessage());
        }
    }
    
    /**
     * 获取当前用户所有的展览申请列表(包含所有审核状态)
     * @return 展览申请列表
     */
    @GetMapping("/getMyShowApplications")
    @Operation(summary = "获取我的展览申请列表", description = "获取当前用户所有的展览申请列表（包含所有审核状态）")
    public Result<List<ShowListDTO>> getMyShowApplications() {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 根据用户ID查询所有展览申请列表
            List<Show> shows = showService.lambdaQuery()
                    .eq(Show::getUserId, userId)
                    .orderByDesc(Show::getCreateTime)
                    .list();

            // 转换为DTO列表
            List<ShowListDTO> showListDTOs = shows.stream().map(show -> {
                ShowListDTO dto = new ShowListDTO();
                BeanUtils.copyProperties(show, dto);
                return dto;
            }).collect(Collectors.toList());

            return Result.success(showListDTOs);
        } catch (Exception e) {
            return Result.error(500, "获取展览申请列表失败：" + e.getMessage());
        }
    }
    
    /**
     * 投稿新闻
     * @param newsSubmissionDTO 新闻投稿信息
     * @return 投稿结果
     */
    @PostMapping("/submitNews")
    @Operation(summary = "投稿新闻", description = "用户投稿新闻")
    public Result<String> submitNews(@RequestBody NewsSubmissionDTO newsSubmissionDTO) {
        try {
            // 从ThreadLocal中获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 构造新闻对象
            News news = new News();
            BeanUtils.copyProperties(newsSubmissionDTO, news);
            news.setAdminId(userId); // 设置作者为当前用户
            news.setStatus("0"); // 默认状态为待审核
            news.setCreateTime((int) (System.currentTimeMillis() / 1000)); // 设置创建时间
            news.setUpdateTime((int) (System.currentTimeMillis() / 1000)); // 设置更新时间

            // 保存新闻投稿
            boolean saved = newsService.save(news);
            if (saved) {
                return Result.success("新闻投稿提交成功");
            } else {
                return Result.error(500, "新闻投稿提交失败");
            }
        } catch (Exception e) {
            return Result.error(500, "新闻投稿提交失败：" + e.getMessage());
        }
    }
}