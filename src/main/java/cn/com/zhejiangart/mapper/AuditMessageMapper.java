package cn.com.zhejiangart.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.com.zhejiangart.model.vo.AuditMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 审核消息扩展�? Mapper 接口
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Mapper
public interface AuditMessageMapper extends BaseMapper<AuditMessage> {

}
