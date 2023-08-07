package com.marmot.common.list.sdk.request;

import com.marmot.common.list.sdk.query.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @Author:zhaozhou
 * @Date: 2023/07/18
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonListPageReq {

    @NotBlank(message = "[sysCode] should not blank")
    private String sysCode;


    @NotNull(message = "[typeId] should not empty")
    private Long typeId;


    private QueryParam queryParam;

    private Integer pageNo;

    private Integer pageSize;

}
