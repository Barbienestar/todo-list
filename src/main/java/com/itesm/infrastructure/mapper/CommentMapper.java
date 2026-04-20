package com.itesm.infrastructure.mapper;

import com.itesm.domain.models.Comment;
import com.itesm.infrastructure.persistence.entity.CommentEntity;

/**
 * CommentMapper
 */
public class CommentMapper {
    public static Comment toDomain(CommentEntity entity) {
        Comment comment = new Comment();
        comment.setContent(entity.getContent());
        comment.setUserName(entity.getUser().getFullName());
        return comment;
    }
}
