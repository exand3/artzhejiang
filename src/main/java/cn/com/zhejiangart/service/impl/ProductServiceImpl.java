package cn.com.zhejiangart.service.impl;

import cn.com.zhejiangart.model.vo.Product;
import cn.com.zhejiangart.mapper.ProductMapper;
import cn.com.zhejiangart.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 作品表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-09-09
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
