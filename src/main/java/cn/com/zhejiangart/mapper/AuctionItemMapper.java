package cn.com.zhejiangart.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.com.zhejiangart.model.vo.AuctionItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 拍卖品表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Mapper
public interface AuctionItemMapper extends BaseMapper<AuctionItem> {

}
