package com.backend.repositories;

import com.backend.modals.Permissions;
import com.backend.modals.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Permission;
import java.util.Collection;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, UUID> {
    Collection<Object> getPermissionsByRole(Role savedRole);
}
