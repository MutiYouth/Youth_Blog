package com.linn.home.dao;

import com.linn.home.entity.Link;
import com.linn.home.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 通知dao层
 * Created by Administrator on 2018-02-28.
 */
@Repository("noticeDao")
public interface NoticeDao {

    /**
     * 查找通知
     * @return
     * @throws Exception
     */
    Notice findActiveNotice();

    /**
     * 查找通知列表
     * @param
     * @return
     */
    List<Notice> findNoticeList();

    /**
     * 添加通知
     * @param notice
     * @return
     * @throws Exception
     */
    int addNotice(Notice notice);

    /**
     * 更新通知
     * @param notice
     * @return
     * @throws Exception
     */
    int updateNoticeById(Notice notice);

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
