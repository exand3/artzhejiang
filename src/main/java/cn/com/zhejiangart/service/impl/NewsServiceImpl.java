package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.News;
import cn.com.zhejiangart.mapper.NewsMapper;
import cn.com.zhejiangart.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

}
