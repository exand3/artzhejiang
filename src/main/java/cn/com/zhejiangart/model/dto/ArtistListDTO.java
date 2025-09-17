package cn.com.zhejiangart.model.dto;

import cn.com.zhejiangart.model.vo.User;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "ArtistListDTO", description = "美术家列表DTO")
public class ArtistListDTO {

    @Schema(description = "美术家列表")
    private List<ArtistListItem> records;

    @Schema(description = "总记录数")
    private Long total;

    @Schema(description = "当前页码")
    private Integer pageNum;

    @Schema(description = "每页数量")
    private Integer pageSize;

    @Schema(description = "总页数")
    private Integer totalPage;

    @Data
    @Schema(name = "ArtistListItem", description = "美术家列表项")
    public static class ArtistListItem {

        @Schema(description = "美术家ID")
        private Integer id;

        @Schema(description = "美术家姓名")
        private String nickname;

        @Schema(description = "美术家头像")
        private String avatar;

        @Schema(description = "美术家简介")
        private String identityDesc;

        /**
         * 从User实体创建ArtistListItem
         *
         * @param user User实体
         * @return ArtistListItem
         */
        public static ArtistListItem fromUser(User user) {
            if (user == null) {
                return null;
            }
            ArtistListItem item = new ArtistListItem();
            item.setId(user.getId());
            item.setNickname(user.getNickname());
            item.setAvatar(user.getAvatar());
            item.setIdentityDesc(user.getIdentityDesc());
            return item;
        }
    }
}