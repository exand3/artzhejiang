package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.Address;
import cn.com.zhejiangart.mapper.AddressMapper;
import cn.com.zhejiangart.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地址表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
