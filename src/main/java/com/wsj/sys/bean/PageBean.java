package com.wsj.sys.bean;

import java.util.List;

/**
 * Created by Jimmy on 2017/6/27.
 */
public class PageBean<T> {
    private int start;
    private int limit;
    private int result;
    private List<T> rows;

    public PageBean() {
        super();
    }

    public PageBean(int start, int limit, int result, List<T> rows) {
        super();
        this.start = start;
        this.limit = limit;
        this.result = result;
        this.rows = rows;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
