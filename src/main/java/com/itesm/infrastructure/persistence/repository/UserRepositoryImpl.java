package com.itesm.infrastructure.persistence.repository;

import com.itesm.domain.models.User;
import com.itesm.domain.repository.UserRepository;
import com.itesm.infrastructure.mapper.UserMapper;
import com.itesm.infrastructure.persistence.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * UserRepositoryImpl
 */
@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity, UUID> {
    @Override
    @Transactional
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        persist(entity);
        return UserMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public Optional<User> find(UUID id) {
        return find("id", id).firstResultOptional().map(UserMapper::toDomain);
    }

    @Override
    @Transactional
    public Optional<User> findByProviderUid(String providerUid) {
        return find("providerUid", providerUid).firstResultOptional().map(UserMapper::toDomain);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional().map(UserMapper::toDomain);
    }
}
