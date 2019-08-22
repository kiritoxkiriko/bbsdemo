package com.wxm.bbsdemo.entity;

import java.util.List;

public class Page<T> {
    private int pageSize = 20;
    private int currentPage = 1;
    private int pageCount;
    private int totalItemCount;
    List<T> pageItems;

    public Page(int currentPage, int pageSize, int totalItemsCount, List<T> pageItems) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.pageCount = (totalItemsCount + this.pageSize - 1) / this.pageSize;
        this.pageItems = pageItems;
        this.totalItemCount=totalItemsCount;
    }

    public Page(int currentPage, int totalItemsCount, List<T> pageItems) {
        this.currentPage = currentPage;
        this.pageCount = (totalItemsCount + this.pageSize - 1) / this.pageSize;
        this.pageItems = pageItems;
        this.totalItemCount=totalItemsCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getPageItems() {
        return pageItems;
    }

    public void setPageItems(List<T> pageItems) {
        this.pageItems = pageItems;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", pageCount=" + pageCount +
                ", pageItems=" + pageItems +
                '}';
    }
}

