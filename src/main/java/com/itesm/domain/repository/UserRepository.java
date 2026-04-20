package com.itesm.domain.repository;

import com.itesm.domain.models.User;
import java.util.Optional;
import java.util.UUID;

/**
 * UserRepository
 */
public interface UserRepository {
    public User save(User user);
    public Optional<User> find(UUID id);
    public Optional<User> findByEmail(String email);
    public Optional<User> findByProviderUid(String providerUid);
    public void delete(UUID id);
}
