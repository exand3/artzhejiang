package cn.com.zhejiangart.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.com.zhejiangart.model.vo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员�? Mapper 接口
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
