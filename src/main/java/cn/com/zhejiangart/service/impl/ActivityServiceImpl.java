package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.Activity;
import cn.com.zhejiangart.mapper.ActivityMapper;
import cn.com.zhejiangart.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动主表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

}
