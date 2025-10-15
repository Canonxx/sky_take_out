package com.hubu.vo;/*
 * @Auther:long
 * @Date:2025/10/14
 * @Description:
 * @VERSON:1.8
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "分类分页查询时返回的数据模型")
public class CategoryPageQueryVO implements Serializable {
    @Schema(description = "分类id")
    private Long id;
    @Schema(description = "分类名称")
    private String name;
    @Schema(description = "分类类型")
    private Integer type;
    @Schema(description = "排序")
    private Integer sort;
    @Schema(description = "分类状态")
    private Integer status;
    @Schema(description = "最后操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;
}
