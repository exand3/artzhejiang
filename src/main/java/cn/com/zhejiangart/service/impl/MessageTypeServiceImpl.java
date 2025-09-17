package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.MessageType;
import cn.com.zhejiangart.mapper.MessageTypeMapper;
import cn.com.zhejiangart.service.IMessageTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息类型表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class MessageTypeServiceImpl extends ServiceImpl<MessageTypeMapper, MessageType> implements IMessageTypeService {

}
