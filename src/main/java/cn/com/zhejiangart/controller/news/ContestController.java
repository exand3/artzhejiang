package cn.com.zhejiangart.controller.news;

import cn.com.zhejiangart.model.dto.*;
import cn.com.zhejiangart.model.vo.NumberList;
import cn.com.zhejiangart.model.vo.PaperList;
import cn.com.zhejiangart.model.vo.Vote;
import cn.com.zhejiangart.service.INumberListService;
import cn.com.zhejiangart.service.IPaperListService;
import cn.com.zhejiangart.service.IVoteService;
import cn.com.zhejiangart.util.Result;
import cn.com.zhejiangart.util.ThreadUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 大赛控制器
 */
@RestController
@RequestMapping("/api/news/contest")
@Tag(name = "大赛相关接口")
public class ContestController {

    @Autowired
    private INumberListService numberListService;

    @Autowired
    private IPaperListService paperListService;

    @Autowired
    private IVoteService voteService;

    /**
     * 分页获取大赛列表
     *
     * @param page 页码
     * @param size 每页大小
     * @return 大赛列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取大赛列表", description = "分页获取大赛列表，包含图片、标题、状态、开始时间和结束时间等信息")
    public Result<Page<ContestListDTO>> getContestList(
            @Parameter(name = "页码", example = "1") @RequestParam(defaultValue = "1") Integer page,
            @Parameter(name = "每页大小", example = "10") @RequestParam(defaultValue = "10") Integer size) {
        try {
            // 构造分页对象
            Page<NumberList> numberListPage = new Page<>(page, size);
            
            // 查询大赛列表
            LambdaQueryWrapper<NumberList> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(NumberList::getIsDel, 0) // 未删除的
                    .orderByDesc(NumberList::getId); // 按ID倒序排列
            
            Page<NumberList> resultPage = numberListService.page(numberListPage, queryWrapper);

            // 转换为DTO
            Page<ContestListDTO> dtoPage = new Page<>(page, size, resultPage.getTotal());
            List<ContestListDTO> dtoList = resultPage.getRecords().stream().map(numberList -> {
                ContestListDTO dto = new ContestListDTO();
                BeanUtils.copyProperties(numberList, dto);
                
                // 设置状态 (根据当前时间判断)
                Integer status = 0; // 默认筹备中
                Integer currentTime = (int) (System.currentTimeMillis() / 1000);
                if (numberList.getStartTime() != null && currentTime >= numberList.getStartTime()) {
                    if (numberList.getEndTime() != null && currentTime <= numberList.getEndTime()) {
                        status = 1; // 进行中
                    } else if (numberList.getEndTime() != null && currentTime > numberList.getEndTime()) {
                        status = 2; // 已结束
                    }
                }
                dto.setStatus(status);
                
                return dto;
            }).collect(Collectors.toList());
            
            dtoPage.setRecords(dtoList);
            return Result.success(dtoPage);
        } catch (Exception e) {
            return Result.error(500, "获取大赛列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取大赛详情
     *
     * @param id 大赛ID
     * @return 大赛详情
     */
    @GetMapping("/detail/{id}")
    @Operation(summary = "获取大赛详情", description = "根据ID获取大赛详情信息")
    public Result<ContestDetailDTO> getContestDetail(
            @Parameter(name = "大赛ID", required = true) @PathVariable Integer id) {
        try {
            NumberList numberList = numberListService.getById(id);
            if (numberList == null || numberList.getIsDel() != null && numberList.getIsDel().equals(1)) {
                return Result.error(404, "大赛不存在或已被删除");
            }

            ContestDetailDTO dto = new ContestDetailDTO();
            BeanUtils.copyProperties(numberList, dto);
            return Result.success(dto);
        } catch (Exception e) {
            return Result.error(500, "获取大赛详情失败：" + e.getMessage());
        }
    }

    /**
     * 获取参赛作品列表
     *
     * @param contestId 大赛ID
     * @return 参赛作品列表
     */
    @GetMapping("/works/{contestId}")
    @Operation(summary = "获取参赛作品列表", description = "获取指定大赛的参赛作品列表，包含投票数和是否已投票标识")
    public Result<List<ContestWorkDTO>> getContestWorks(
            @Parameter(name = "大赛ID", required = true) @PathVariable Integer contestId) {
        try {
            // 获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);

            // 查询参赛作品列表
            LambdaQueryWrapper<PaperList> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PaperList::getNumberId, contestId)
                    .eq(PaperList::getStart, 1) // 审核通过的作品
                    .eq(PaperList::getIsDel, 0) // 未删除的作品
                    .orderByDesc(PaperList::getCounts); // 按投票数倒序排列

            List<PaperList> paperLists = paperListService.list(queryWrapper);

            // 转换为DTO
            List<ContestWorkDTO> dtoList = paperLists.stream().map(paperList -> {
                ContestWorkDTO dto = new ContestWorkDTO();
                BeanUtils.copyProperties(paperList, dto);
                
                // 判断当前用户是否已投票
                if (userId != null) {
                    LambdaQueryWrapper<Vote> voteQuery = new LambdaQueryWrapper<>();
                    voteQuery.eq(Vote::getPaperId, paperList.getId())
                            .eq(Vote::getUserId, userId);
                    Vote vote = voteService.getOne(voteQuery);
                    dto.setVoted(vote != null);
                }
                
                return dto;
            }).collect(Collectors.toList());

            return Result.success(dtoList);
        } catch (Exception e) {
            return Result.error(500, "获取参赛作品列表失败：" + e.getMessage());
        }
    }

    /**
     * 投票接口
     *
     * @param workId 作品ID
     * @return 投票结果
     */
    @PostMapping("/vote/{workId}")
    @Operation(summary = "给作品投票", description = "给指定作品投票，每个用户只能对同一作品投票一次")
    public Result<String> voteForWork(
            @Parameter(name = "作品ID", required = true) @PathVariable Integer workId) {
        try {
            // 获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 检查作品是否存在且审核通过
            PaperList paperList = paperListService.getById(workId);
            if (paperList == null || !paperList.getStart().equals(1) || paperList.getIsDel().equals(1)) {
                return Result.error(404, "作品不存在或未审核通过");
            }

            // 检查是否已投票
            LambdaQueryWrapper<Vote> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Vote::getPaperId, workId)
                    .eq(Vote::getUserId, userId);
            Vote existingVote = voteService.getOne(queryWrapper);
            if (existingVote != null) {
                return Result.error(400, "您已对该作品投过票");
            }

            // 创建投票记录
            Vote vote = new Vote();
            vote.setPaperId(workId);
            vote.setUserId(userId);
            boolean saved = voteService.save(vote);

            if (saved) {
                // 更新作品投票数
                paperList.setCounts(paperList.getCounts() != null ? paperList.getCounts() + 1 : 1);
                paperListService.updateById(paperList);
                return Result.success("投票成功");
            } else {
                return Result.error(500, "投票失败");
            }
        } catch (Exception e) {
            return Result.error(500, "投票失败：" + e.getMessage());
        }
    }

    /**
     * 获取获奖作品列表
     *
     * @param contestId 大赛ID
     * @return 获奖作品列表
     */
    @GetMapping("/winning-works/{contestId}")
    @Operation(summary = "获取获奖作品列表", description = "获取指定大赛的获奖作品列表")
    public Result<List<ContestWinningWorkDTO>> getWinningWorks(
            @Parameter(name = "大赛ID", required = true) @PathVariable Integer contestId) {
        try {
            // 查询获奖作品列表（有获奖名次的作品）
            LambdaQueryWrapper<PaperList> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PaperList::getNumberId, contestId)
                    .eq(PaperList::getStart, 1) // 审核通过的作品
                    .eq(PaperList::getIsDel, 0) // 未删除的作品
                    .isNotNull(PaperList::getPrizeStatus) // 有获奖名次
                    .ne(PaperList::getPrizeStatus, 0) // 名次不为0
                    .orderByAsc(PaperList::getPrizeStatus); // 按名次正序排列

            List<PaperList> paperLists = paperListService.list(queryWrapper);

            // 转换为DTO
            List<ContestWinningWorkDTO> dtoList = paperLists.stream().map(paperList -> {
                ContestWinningWorkDTO dto = new ContestWinningWorkDTO();
                BeanUtils.copyProperties(paperList, dto);
                return dto;
            }).collect(Collectors.toList());

            return Result.success(dtoList);
        } catch (Exception e) {
            return Result.error(500, "获取获奖作品列表失败：" + e.getMessage());
        }
    }

    /**
     * 投稿作品
     *
     * @param submissionWorkDTO 投稿作品信息
     * @return 投稿结果
     */
    @PostMapping("/submit-work")
    @Operation(summary = "投稿作品", description = "投稿作品，回显真实姓名、身份证、简介、出生日期、手机号码和邮箱等信息")
    public Result<String> submitWork(@RequestBody SubmissionWorkDTO submissionWorkDTO) {
        try {
            // 获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 构造投稿作品对象
            PaperList paperList = new PaperList();
            BeanUtils.copyProperties(submissionWorkDTO, paperList);
            paperList.setUserId(userId);
            paperList.setStart(java.math.BigDecimal.valueOf(0)); // 默认待审核状态
            paperList.setCounts(0); // 默认投票数为0
            paperList.setIsDel(java.math.BigDecimal.valueOf(0)); // 默认未删除
            paperList.setVisits(0); // 默认访问量为0
            paperList.setLikes(0); // 默认点赞数为0
            
            // 保存投稿作品
            boolean saved = paperListService.save(paperList);
            if (saved) {
                return Result.success("作品投稿成功");
            } else {
                return Result.error(500, "作品投稿失败");
            }
        } catch (Exception e) {
            return Result.error(500, "作品投稿失败：" + e.getMessage());
        }
    }

    /**
     * 获取用户投稿作品信息（用于回显）
     *
     * @return 投稿作品信息
     */
    @GetMapping("/my-submission")
    @Operation(summary = "获取我的投稿作品信息", description = "获取当前用户投稿作品信息，用于回显真实姓名、身份证、简介、出生日期、手机号码和邮箱等信息")
    public Result<SubmissionWorkDTO> getMySubmission() {
        try {
            // 获取当前登录用户ID
            Integer userId = ThreadUtils.getId("userId", Integer.class);
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }

            // 查询用户最新的投稿作品
            LambdaQueryWrapper<PaperList> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PaperList::getUserId, userId)
                    .orderByDesc(PaperList::getId)
                    .last("LIMIT 1");

            PaperList paperList = paperListService.getOne(queryWrapper);
            if (paperList == null) {
                return Result.success(new SubmissionWorkDTO()); // 返回空对象
            }

            SubmissionWorkDTO dto = new SubmissionWorkDTO();
            BeanUtils.copyProperties(paperList, dto);
            return Result.success(dto);
        } catch (Exception e) {
            return Result.error(500, "获取投稿作品信息失败：" + e.getMessage());
        }
    }
}