package cn.com.zhejiangart.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.com.zhejiangart.model.vo.Show;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 我要举办展览 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Mapper
public interface ShowMapper extends BaseMapper<Show> {

}
