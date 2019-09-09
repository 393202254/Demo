package com.example.demo.service;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.util.PageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PageUtil getPages(int currentPage)
//查询问题封装到questionDTO
    {
        PageUtil pageUtil=new PageUtil();
        List<Question> questionList = questionMapper.select((currentPage-1)*pageUtil.getPageSize(),pageUtil.getPageSize());
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.findByID(question.getCreator());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
      pageUtil.setQuestionDTO(questionDTOList);
      int totalcount=questionMapper.gettotalCount();
      //前端需要返回 当前页 数据条数
        pageUtil.countTotalPage(totalcount);//计算总页数
        pageUtil.getTotalPage();
        pageUtil.setCurrentPage(currentPage);
        pageUtil.nextPage();
        pageUtil.lastPage();

        return pageUtil;
    }
}
