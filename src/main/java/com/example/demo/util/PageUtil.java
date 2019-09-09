package com.example.demo.util;

import com.example.demo.dto.QuestionDTO;
import com.sun.xml.internal.ws.api.pipe.NextAction;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageUtil {
    private int totalPage;//总页数
    private int currentPage ;//当前页
    // private int totalcount;//查询总数量
    List<QuestionDTO> questionDTO;
    private List<Integer> pages = new ArrayList<>();
    private int pageSize = 5;
    private int page = 0;//标记页数
    private boolean nextPage;
    private boolean lastPage;

    public void countTotalPage(int totalcount) {
        if (totalcount % pageSize != 0) {
            totalPage = totalcount / pageSize + 1;
        } else totalPage = totalcount / pageSize;
        if (currentPage == totalPage) {
            pages.add(page);
        } else {
            for (int i = 1; i <= totalPage; i++) {
                page = page + 1;
                pages.add(page);
            }
        }
    }

    public void nextPage(){
        if (currentPage<totalPage){
            nextPage=true;
        }else  nextPage=false;
    }
    public void lastPage(){
        if (1 <currentPage){
           lastPage=true;
        }else lastPage=false;
    }
}


