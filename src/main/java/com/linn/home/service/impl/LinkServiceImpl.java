package com.linn.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linn.home.dao.LinkDao;
import com.linn.home.entity.Link;
import com.linn.home.entity.Notice;
import com.linn.home.service.LinkService;
import com.linn.home.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("linkService")
public class LinkServiceImpl implements LinkService {
    @Resource
    private LinkDao linkDao;

    @Override
    public PageInfo findLinkList(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Link> links = linkDao.findLinkList();
        pageInfo = new PageInfo(links);
        return pageInfo;
    }

    @Override
    public int addLink(Link link) {
        return linkDao.addLink(link);
    }

    @Override
    public int updateLinkById(Link link) {
        return linkDao.updateLinkById(link);
    }

    @Override
    public int deleteLinkById(int id) {
        return linkDao.deleteLinkById(id);
    }

    @Override
    public List<Link> findLinkAll() {
        return  linkDao.findLinkList();
    }

    @Override
    public Link findLinkById(Integer linkId) {
        return linkDao.findLinkById(linkId);
    }
}