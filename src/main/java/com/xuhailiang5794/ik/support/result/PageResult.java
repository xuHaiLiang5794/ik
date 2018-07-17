package com.xuhailiang5794.ik.support.result;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * <pre>
 * 列表返回结果
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/7/16
 */
@Data
public class PageResult extends BaseResult<List> {
    /**
     * 数据总量
     */
    private long totalCount;

    /**
     * 数据总页数
     */
    private int totalPageCount;

    /**
     * 每页数据量
     */
    private int pageSize;

    /**
     * 当前页
     */
    private int currentPageIndex;

    private Page page;

    public PageResult(Page page) {
        this.setTotalCount(page.getTotal());
        this.setCurrentPageIndex(page.getPageNum());
        this.setPageSize(page.getPageSize());
        this.setTotalPageCount(page.getPages());
        this.setData(page.getResult());
//        this.setTotalPageCount(((Double) Math.ceil(totalCount * 1.0 / pageSize)).intValue());
    }
}
