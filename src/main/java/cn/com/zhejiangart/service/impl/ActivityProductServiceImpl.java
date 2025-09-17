package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.ActivityProduct;
import cn.com.zhejiangart.mapper.ActivityProductMapper;
import cn.com.zhejiangart.service.IActivityProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动关联的商品 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class ActivityProductServiceImpl extends ServiceImpl<ActivityProductMapper, ActivityProduct> implements IActivityProductService {

}
