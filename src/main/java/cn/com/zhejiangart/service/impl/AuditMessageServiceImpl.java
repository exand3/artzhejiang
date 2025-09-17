package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.AuditMessage;
import cn.com.zhejiangart.mapper.AuditMessageMapper;
import cn.com.zhejiangart.service.IAuditMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审核消息扩展表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class AuditMessageServiceImpl extends ServiceImpl<AuditMessageMapper, AuditMessage> implements IAuditMessageService {

}
