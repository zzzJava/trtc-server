package com.zzz.videoserver.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description:
 * @author: zhouyi
 * @create: 2019/3/23
 */
@lombok.Data
@ApiModel("带分页的数据")
public class Data<T> {
  @ApiModelProperty(value = "数据")
  private T data;
  @ApiModelProperty(value = "分页")
  private Pager pager;

  public Data(T data, Pager pager) {
    this.data = data;
    this.pager = pager;
  }
}
