package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.EventMessage;
import cn.com.zhejiangart.mapper.EventMessageMapper;
import cn.com.zhejiangart.service.IEventMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动消息扩展表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class EventMessageServiceImpl extends ServiceImpl<EventMessageMapper, EventMessage> implements IEventMessageService {

}
