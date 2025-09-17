package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.Message;
import cn.com.zhejiangart.mapper.MessageMapper;
import cn.com.zhejiangart.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息基础表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
