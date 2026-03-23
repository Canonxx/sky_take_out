package com.hubu.dto;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "地址簿数据传输对象")
public class AddressBookDTO implements Serializable {

    @Schema(description = "地址id")
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "收货人")
    private String consignee;

    @Schema(description = "性别")
    private String sex;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "省编码")
    private String provinceCode;

    @Schema(description = "省名称")
    private String provinceName;

    @Schema(description = "市编码")
    private String cityCode;

    @Schema(description = "市名称")
    private String cityName;

    @Schema(description = "区编码")
    private String districtCode;

    @Schema(description = "区名称")
    private String districtName;

    @Schema(description = "详细地址")
    private String detail;

    @Schema(description = "标签（家/公司等）")
    private String label;

    @Schema(description = "是否默认地址（0否 1是）")
    private Integer isDefault;
}
