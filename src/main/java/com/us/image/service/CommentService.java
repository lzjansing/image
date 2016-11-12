package com.us.image.service;

import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.image.dao.CommentDao;
import com.us.image.entities.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
@Service
@Transactional(readOnly = true)
public class CommentService extends CrudService<CommentDao, Comment> {

    public Comment get(String id) {
        return super.get(id);
    }

    public List<Comment> findList(Comment comment) {
        return super.findList(comment);
    }

    public Page<Comment> findPage(Page<Comment> page, Comment comment) {
        return super.findPage(page, comment);
    }

    @Transactional(readOnly = false)
    public void save(Comment comment) {
        super.save(comment);
    }

    @Transactional(readOnly = false)
    public void delete(Comment comment) {
        super.delete(comment);
    }

}