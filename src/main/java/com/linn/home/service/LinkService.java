package com.linn.home.service;

import com.github.pagehelper.PageInfo;
import com.linn.home.entity.Link;

import java.util.List;

/**
 * 友链service层
 */
public interface LinkService {

    /**
     * 查找最新的通知
     * @return 实体
     * @throws Exception
     */
    PageInfo findLinkList(PageInfo pageInfo);

    /**
     * 添加友链
     * @param link
     * @return
     * @throws Exception
     */
    int addLink(Link link);

    /**
     * 修改友链
     * @param link
     * @return
     * @throws Exception
     */
    int updateLinkById(Link link);

    /**
     * 删除友链
     * @param id
     * @return
     * @throws Exception
     */
    int deleteLinkById(int id);

    /**
     * 查找所有友链
     * @return
     */
    List<Link> findLinkAll();

    /**
     * 根据id查找友链
     * @param linkId
     * @return
     */
    Link findLinkById(Integer linkId);
}