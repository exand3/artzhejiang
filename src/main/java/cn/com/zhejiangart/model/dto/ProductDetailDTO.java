package cn.com.zhejiangart.model.dto;

import cn.com.zhejiangart.model.vo.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "ProductDetailDTO", description = "艺术品详情DTO")
public class ProductDetailDTO {

    @Schema(description = "艺术品详情")
    private Product product;

    @Schema(description = "猜你喜欢作品列表")
    private List<ProductListItem> guessYouLikeProducts;
    
    @Data
    @Schema(name = "ProductListItem", description = "艺术品列表项")
    public static class ProductListItem {
        
        @Schema(description = "艺术品ID")
        private Integer id;
        
        @Schema(description = "艺术品名称")
        private String goodsName;
        
        @Schema(description = "艺术品标题")
        private String goodsCap;
        
        @Schema(description = "艺术品价格")
        private java.math.BigDecimal price;
        
        @Schema(description = "艺术品图片")
        private String indexPic;
        
        /**
         * 从Product实体创建ProductListItem
         * @param product Product实体
         * @return ProductListItem
         */
        public static ProductListItem fromProduct(Product product) {
            if (product == null) {
                return null;
            }
            ProductListItem item = new ProductListItem();
            item.setId(product.getId());
            item.setGoodsName(product.getGoodsName());
            item.setGoodsCap(product.getGoodsCap());
            item.setPrice(product.getPrice());
            item.setIndexPic(product.getIndexPic());
            return item;
        }
    }
}