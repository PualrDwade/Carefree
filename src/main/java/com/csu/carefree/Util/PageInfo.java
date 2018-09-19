package com.csu.carefree.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装分页的基本信息
 * @author Kai
 *
 */
public class PageInfo <E> {

    //总记录数
    private int total;
    //当前页
    private int currentPage=1;
    //每页的记录数
    private int pageSize=8;
    //总页数
    private int maxPage;

    private boolean isFirstPage;

    private boolean isLastPage;

    //当前页数据
    private List<E> pageData=new ArrayList<>();

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }


    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getMaxPage() {
        int page=total/pageSize;
        if(total%pageSize!=0)
        {
            page=page+1;
        }
        maxPage=page;
        return maxPage;
    }

    public List<E> getPageData() {
        return pageData;
    }
    public void setPageData(List<E> list , int pageSize ,int pageNum) {

        this.pageSize = pageSize;
        total = list.size();
        maxPage = getMaxPage();
        currentPage = pageNum;
        int index = pageNum-1;

        if (list.size() / pageSize == 0 || list.size() / pageSize != 0 && pageNum != maxPage )
            pageData =  list.subList(index * pageSize , index * pageSize + pageSize);
        else
        if ( pageNum == maxPage )
            pageData = list.subList(index * pageSize , index * pageSize + list.size() % pageSize);

        isFirstPage = pageNum == 1;
        isLastPage = pageNum == maxPage;

        System.out.println(pageData);
        System.out.println(list);

    }

}
