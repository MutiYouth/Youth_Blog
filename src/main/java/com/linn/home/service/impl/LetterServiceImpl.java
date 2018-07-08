package com.linn.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linn.home.dao.LetterDao;
import com.linn.home.entity.Letter;
import com.linn.home.entity.Link;
import com.linn.home.service.LetterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 站内信service层
 * Created by Administrator on 2018-03-09.
 */
@Service("letterService")
public class LetterServiceImpl implements LetterService {

    @Resource
    private LetterDao letterDao;

    @Override
    public int findCountByStatus(int status){
        return letterDao.findCountByStatus(status);
    }

    @Override
    public int updateStatusById(Letter letter){
        return letterDao.updateStatusById(letter);
    }

    @Override
    public PageInfo findLetterList(PageInfo pageInfo){
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Letter> letters = letterDao.findLetterList();
        pageInfo = new PageInfo(letters);
        return pageInfo;
    }

    @Override
    public int addLetter(Letter letter){
        return letterDao.addLetter(letter);
    }

    @Override
    public int deleteLetterById(int id){
        return letterDao.deleteLetterById(id);
    }

    @Override
    public Letter findLetterById(Integer letterId) {
        return letterDao.findLetterById(letterId);
    }
}
