package com.nowcoder.community.entity;

/*
封装分页相关信息
 */
public class Page {
    // 当前页码
    private int current = 1;
    // 显示上限
    private int limit = 10;
    //数据的总数（用于计算总的页数）
    private int rows;
    //查询路径（复用分页的链接）
    private String path;

    public int getCurrent() {
        return current;
    }

    public int getLimit() {
        return limit;
    }

    public int getRows() {
        return rows;
    }

    public String getPath() {
        return path;
    }

    public void setCurrent(int current) {
        if (current >= 1)
            this.current = current;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100)
            this.limit = limit;
    }

    public void setRows(int rows) {
        if (rows >= 0)
            this.rows = rows;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /*
    获取当前页的起始行
     */
    public int getOffset() {
        return (current - 1) * limit;
    }

    /*
    获取总页数
     */
    public int getTotal() {
        return rows % limit == 0 ? rows / limit : rows / limit + 1;
    }

    /*
    獲取起始页码
     */
    public int getFrom() {
        return current - 2 < 1 ? 1 : current - 2;
    }

    /*
    获取结束页码
     */
    public int getTo() {
        return current + 2 > getTotal() ? getTotal() : current + 2;
    }
}
