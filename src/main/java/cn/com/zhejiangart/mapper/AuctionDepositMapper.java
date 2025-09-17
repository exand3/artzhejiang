package cn.com.zhejiangart.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.com.zhejiangart.model.vo.AuctionDeposit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 拍卖保证金表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Mapper
public interface AuctionDepositMapper extends BaseMapper<AuctionDeposit> {

}
