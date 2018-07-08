package com.linn.home.service;

import com.github.pagehelper.PageInfo;
import com.linn.home.entity.Notice;

import java.util.List;

/**
 * 通知service层
 */
public interface NoticeService {

    /**
     * 查找最新的通知
     * @return 实体
     * @throws Exception
     */
    Notice findActiveNotice();

    /**
     * 查找通知列表
     * @return
     * @throws Exception
     */
    PageInfo findNoticeList(PageInfo pageInfo);

    /**
     * 添加通知
     * @return
     * @throws Exception
     */
    int addNotice(Notice notice);

    /**
     * 更新通知
     * @return
     * @throws Exception
     */
    int updateNotice(Notice notice);

    /**
     * 删除通知
     * @param id
     * @return
     * @throws Exception
     */
    int deleteNoticeById(int id);

    /**
     * 通过id查找通知
     * @param noticeId
     * @return
     */
    Notice findNoticeById(Integer noticeId);

    /**
     * 通知是否激活显示
     * @param notice
     */
    void updateNotcieIsActive(Notice notice);
}