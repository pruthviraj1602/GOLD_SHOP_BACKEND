package com.backend.services;

import com.backend.modals.Permissions;
import com.backend.modals.Privilege;
import com.backend.modals.Role;
import com.backend.modals.dto.response.ApiResponse;
import com.backend.modals.dto.request.RoleDto;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    Role createRole(RoleDto roleDto);

    List<RoleDto> getAllRoles();

    List<Permissions> createPermissions(List<Permissions> permissions);

    Privilege createPrivilege(Privilege privilege);

    RoleDto getRoleByRoleName(String roleName);

    Role updateRole(RoleDto roleDto) ;

    Role findById(UUID roleId);

    ApiResponse<?> deleteRole(UUID roleId);
}
