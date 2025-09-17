package cn.com.zhejiangart.model.dto;

import cn.com.zhejiangart.model.vo.News;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "NewsListDTO", description = "新闻列表DTO")
public class NewsListDTO {

    @Schema(description = "新闻列表")
    private List<NewsListItem> records;

    @Schema(description = "总记录数")
    private Long total;

    @Schema(description = "当前页码")
    private Integer pageNum;

    @Schema(description = "每页数量")
    private Integer pageSize;

    @Schema(description = "总页数")
    private Integer totalPage;

    @Data
    @Schema(name = "NewsListItem", description = "新闻列表项")
    public static class NewsListItem {

        @Schema(description = "新闻ID")
        private Integer id;

        @Schema(description = "新闻标题")
        private String name;

        @Schema(description = "新闻图片")
        private String image;
        
        @Schema(description = "发布时间")
        private Integer createTime;

        /**
         * 从News实体创建NewsListItem
         *
         * @param news News实体
         * @return NewsListItem
         */
        public static NewsListItem fromNews(News news) {
            if (news == null) {
                return null;
            }
            NewsListItem item = new NewsListItem();
            item.setId(news.getId());
            item.setName(news.getName());
            item.setImage(news.getImage());
            item.setCreateTime(news.getCreateTime());
            return item;
        }
    }
}