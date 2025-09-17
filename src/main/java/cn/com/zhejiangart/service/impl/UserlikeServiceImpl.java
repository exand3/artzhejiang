package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.Userlike;
import cn.com.zhejiangart.mapper.UserlikeMapper;
import cn.com.zhejiangart.service.IUserlikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 喜欢的作品 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class UserlikeServiceImpl extends ServiceImpl<UserlikeMapper, Userlike> implements IUserlikeService {

}
