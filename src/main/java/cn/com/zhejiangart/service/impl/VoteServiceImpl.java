package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.Vote;
import cn.com.zhejiangart.mapper.VoteMapper;
import cn.com.zhejiangart.service.IVoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-10
 */
@Service
public class VoteServiceImpl extends ServiceImpl<VoteMapper, Vote> implements IVoteService {

}
