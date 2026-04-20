package com.itesm.infrastructure.mapper;

import com.itesm.domain.models.User;
import com.itesm.infrastructure.persistence.entity.UserEntity;

/**
 * UserMapper
 */
public class UserMapper {
    public static User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setFullName(entity.getFullName());
        user.setEmail(entity.getEmail());
        user.setActive(entity.isActive());
        user.setProviderUid(entity.getProviderUid());
        user.setRole(entity.getRole());
        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setFullName(user.getFullName());
        entity.setEmail(user.getEmail());
        entity.setActive(user.isActive());
        entity.setProviderUid(user.getProviderUid());
        entity.setRole(user.getRole());
        return entity;
    }
}
