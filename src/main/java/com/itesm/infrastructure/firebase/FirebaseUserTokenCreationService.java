package com.itesm.infrastructure.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.itesm.domain.repository.UserTokenCreationService;

import io.quarkus.arc.profile.UnlessBuildProfile;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@UnlessBuildProfile("test")
public class FirebaseUserTokenCreationService implements UserTokenCreationService {
    @Override
    public String createUser(String email, String password) {
        try {
            UserRecord.CreateRequest createRequest =
                    new UserRecord.CreateRequest().setEmail(email).setPassword(password);
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(createRequest);
            return userRecord.getUid();
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Failed to create Firebase user: " + e.getMessage(), e);
        }
    }
}
