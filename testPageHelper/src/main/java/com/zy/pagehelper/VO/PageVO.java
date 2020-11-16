package com.zy.pagehelper.VO;

import lombok.Data;

/**
 * 分页查询对象
 */
@Data
public class PageVO {
	
	private Integer pageNum = 1;//页码
	
	private Integer pageSize = 10;//每页记录数
	
	
}
