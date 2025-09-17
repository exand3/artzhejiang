package cn.com.zhejiangart.controller.news;

import cn.com.zhejiangart.mapper.NewsMapper;
import cn.com.zhejiangart.model.dto.NewsDetailDTO;
import cn.com.zhejiangart.model.dto.NewsListDTO;
import cn.com.zhejiangart.model.po.NewsListPO;
import cn.com.zhejiangart.model.vo.News;
import cn.com.zhejiangart.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 新闻控制器
 */
@RestController
@RequestMapping("/api/news")
@Tag(name = "新闻相关接口")
public class NewsController {

    @Resource
    private NewsMapper newsMapper;

    /**
     * 获取新闻列表
     *
     * @param newsListPO 查询参数
     * @return 新闻列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取新闻列表", description = "展示新闻列表，支持分页和分类查询，默认展示分类为1的新闻")
    public Result<?> getNewsList(NewsListPO newsListPO) {
        try {
            // 构建分页对象
            Page<News> page = new Page<>(newsListPO.getPageNum(), newsListPO.getPageSize());

            // 构建查询条件
            QueryWrapper<News> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cate_id", newsListPO.getCateId());

            // 执行分页查询
            Page<News> newsPage = newsMapper.selectPage(page, queryWrapper);

            // 转换为列表项格式
            List<NewsListDTO.NewsListItem> records = newsPage.getRecords().stream()
                    .map(NewsListDTO.NewsListItem::fromNews)
                    .collect(Collectors.toList());

            // 构建返回数据
            NewsListDTO newsListDTO = new NewsListDTO();
            newsListDTO.setRecords(records);
            newsListDTO.setTotal(newsPage.getTotal());
            newsListDTO.setPageNum((int) page.getCurrent());
            newsListDTO.setPageSize((int) page.getSize());
            newsListDTO.setTotalPage((int) newsPage.getPages());

            return Result.success(newsListDTO);
        } catch (Exception e) {
            return Result.error(500, "获取新闻列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取新闻详情
     *
     * @param id 新闻ID
     * @return 新闻详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取新闻详情", description = "根据ID获取新闻详情")
    public Result<?> getNewsDetail(
            @Parameter(name = "id", description = "新闻ID", required = true) @PathVariable Integer id) {
        try {
            // 查询新闻详情
            News news = newsMapper.selectById(id);
            if (news == null) {
                return Result.error(404, "新闻不存在");
            }

            // 构建返回数据
            NewsDetailDTO newsDetailDTO = new NewsDetailDTO();
            newsDetailDTO.setNews(news);

            return Result.success(newsDetailDTO);
        } catch (Exception e) {
            return Result.error(500, "获取新闻详情失败：" + e.getMessage());
        }
    }
}