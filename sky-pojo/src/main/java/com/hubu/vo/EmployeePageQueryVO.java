package com.hubu.vo;/*
 * @Auther:long
 * @Date:2025/10/13
 * @Description:
 * @VERSON:1.8
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "返回时分页数据传递的数据模型")
public class EmployeePageQueryVO implements Serializable {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "账号")
    private String username;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "帐号状态")
    private Integer status;
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;
}
