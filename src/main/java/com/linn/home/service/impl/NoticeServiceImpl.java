package com.linn.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linn.home.dao.NoticeDao;
import com.linn.home.entity.Notice;
import com.linn.home.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeDao noticeDao;

    @Override
    public Notice findActiveNotice() {

        return noticeDao.findActiveNotice();
    }

    @Override
    public PageInfo findNoticeList(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Notice> notices = noticeDao.findNoticeList();
        pageInfo = new PageInfo(notices);
        return pageInfo;
    }

    @Override
    public int addNotice(Notice notice) {
        return noticeDao.addNotice(notice);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeDao.updateNoticeById(notice);
    }

    @Override
    public int deleteNoticeById(int id) {
        return noticeDao.deleteNoticeById(id);
    }

    @Override
    public Notice findNoticeById(Integer noticeId) {
        return noticeDao.findNoticeById(noticeId);
    }

    @Override
    public void updateNotcieIsActive(Notice notice) {
        noticeDao.updateNotcieIsActive(notice);
    }
}