package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.User;
import cn.com.zhejiangart.mapper.UserMapper;
import cn.com.zhejiangart.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
