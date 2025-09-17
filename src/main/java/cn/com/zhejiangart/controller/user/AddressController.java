package cn.com.zhejiangart.controller.user;

import cn.com.zhejiangart.model.dto.AddressDTO;
import cn.com.zhejiangart.model.vo.Address;
import cn.com.zhejiangart.service.IAddressService;
import cn.com.zhejiangart.util.Result;
import cn.com.zhejiangart.util.ThreadUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户地址控制器
 */
@RestController
@RequestMapping("/user/address")
@Tag(name = "用户地址管理")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    /**
     * 获取当前用户的所有地址列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取用户地址列表")
    public Result<List<AddressDTO>> getAddressList() {
        try {
            Integer userId = (Integer) ThreadUtils.getId("userId");
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }
            
            QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            List<Address> addresses = addressService.list(queryWrapper);
            
            // 转换为DTO列表
            List<AddressDTO> addressDTOList = new ArrayList<>();
            for (Address address : addresses) {
                AddressDTO addressDTO = new AddressDTO();
                BeanUtils.copyProperties(address, addressDTO);
                addressDTOList.add(addressDTO);
            }
            
            return Result.success(addressDTOList);
        } catch (Exception e) {
            return Result.error(500, "获取地址列表失败：" + e.getMessage());
        }
    }

    /**
     * 添加新地址
     */
    @PostMapping("/add")
    @Operation(summary = "添加新地址")
    public Result<Boolean> addAddress(@RequestBody AddressDTO addressDTO) {
        try {
            Integer userId = (Integer) ThreadUtils.getId("userId");
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }
            
            // 检查用户当前地址数量，最多只能有10个地址
            QueryWrapper<Address> countWrapper = new QueryWrapper<>();
            countWrapper.eq("user_id", userId);
            int addressCount = Math.toIntExact(addressService.count(countWrapper));
            if (addressCount >= 10) {
                return Result.error(400, "最多只能添加10个地址");
            }
            
            Address address = new Address();
            BeanUtils.copyProperties(addressDTO, address);
            address.setUserId(userId);
            address.setCreateTime((int) (System.currentTimeMillis() / 1000));
            
            boolean saveResult = addressService.save(address);
            if (saveResult) {
                return Result.success(true);
            } else {
                return Result.error(500, "添加地址失败");
            }
        } catch (Exception e) {
            return Result.error(500, "添加地址异常：" + e.getMessage());
        }
    }

    /**
     * 更新地址信息
     */
    @PostMapping("/update")
    @Operation(summary = "更新地址信息")
    public Result<Boolean> updateAddress(@RequestBody AddressDTO addressDTO) {
        try {
            Integer userId = (Integer) ThreadUtils.getId("userId");
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }
            
            if (addressDTO.getId() == null) {
                return Result.error(400, "地址ID不能为空");
            }
            
            // 检查地址是否属于当前用户
            Address existingAddress = addressService.getById(addressDTO.getId());
            if (existingAddress == null || !userId.equals(existingAddress.getUserId())) {
                return Result.error(403, "无权修改该地址");
            }
            
            Address address = new Address();
            BeanUtils.copyProperties(addressDTO, address);
            
            boolean updateResult = addressService.updateById(address);
            if (updateResult) {
                return Result.success(true);
            } else {
                return Result.error(500, "更新地址失败");
            }
        } catch (Exception e) {
            return Result.error(500, "更新地址异常：" + e.getMessage());
        }
    }

    /**
     * 删除地址
     */
    @PostMapping("/delete/{id}")
    @Operation(summary = "删除地址")
    public Result<Boolean> deleteAddress(@PathVariable Integer id) {
        try {
            Integer userId = (Integer) ThreadUtils.getId("userId");
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }
            
            // 检查地址是否属于当前用户
            Address existingAddress = addressService.getById(id);
            if (existingAddress == null || !userId.equals(existingAddress.getUserId())) {
                return Result.error(403, "无权删除该地址");
            }
            
            boolean deleteResult = addressService.removeById(id);
            if (deleteResult) {
                return Result.success(true);
            } else {
                return Result.error(500, "删除地址失败");
            }
        } catch (Exception e) {
            return Result.error(500, "删除地址异常：" + e.getMessage());
        }
    }

    /**
     * 获取单个地址详情
     */
    @GetMapping("/detail/{id}")
    @Operation(summary = "获取地址详情")
    public Result<AddressDTO> getAddressDetail(@PathVariable Integer id) {
        try {
            Integer userId = (Integer) ThreadUtils.getId("userId");
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }
            
            Address address = addressService.getById(id);
            if (address == null) {
                return Result.error(404, "地址不存在");
            }
            
            if (!userId.equals(address.getUserId())) {
                return Result.error(403, "无权查看该地址");
            }
            
            AddressDTO addressDTO = new AddressDTO();
            BeanUtils.copyProperties(address, addressDTO);
            
            return Result.success(addressDTO);
        } catch (Exception e) {
            return Result.error(500, "获取地址详情异常：" + e.getMessage());
        }
    }

    /**
     * 设置默认地址
     */
    @PostMapping("/setDefault/{id}")
    @Operation(summary = "设置默认地址")
    public Result<Boolean> setDefaultAddress(@PathVariable Integer id) {
        try {
            Integer userId = (Integer) ThreadUtils.getId("userId");
            if (userId == null) {
                return Result.error(401, "用户未登录");
            }
            
            // 检查地址是否属于当前用户
            Address address = addressService.getById(id);
            if (address == null || !userId.equals(address.getUserId())) {
                return Result.error(403, "无权设置该地址为默认地址");
            }
            
            // 先将该用户所有地址设为非默认
            QueryWrapper<Address> updateWrapper = new QueryWrapper<>();
            updateWrapper.eq("user_id", userId);
            Address updateAddress = new Address();
            updateAddress.setIsUsually("1");
            addressService.update(updateAddress, updateWrapper);
            
            // 再将指定地址设为默认
            address.setIsUsually("0");
            boolean updateResult = addressService.updateById(address);
            
            if (updateResult) {
                return Result.success(true);
            } else {
                return Result.error(500, "设置默认地址失败");
            }
        } catch (Exception e) {
            return Result.error(500, "设置默认地址异常：" + e.getMessage());
        }
    }
}