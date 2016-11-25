package com.us.image.dao;

import com.us.common.persistence.CrudDao;
import com.us.common.persistence.annotation.MyBatisDao;
import com.us.image.entities.Comment;

import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
@MyBatisDao
public interface CommentDao extends CrudDao<Comment> {

    public int deleteSelf(Comment comment);

    public List<Comment> findParentList(String[] pids);

}
