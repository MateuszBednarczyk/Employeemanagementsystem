package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.domain.enums.RoleType;
import com.matthew.employeemanagementsystem.dtos.user.UserResponseDTO;
import com.matthew.employeemanagementsystem.mapper.UserModelMapper;
import com.matthew.employeemanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFindingServiceImpl implements UserFindingService {

    private final UserRepository userRepository;
    private final UserModelMapper userModelMapper;

    @Override
    public UserEntity getUserEntity(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException(username + "User not found");
        });
    }

    @Override
    public List<UserResponseDTO> findUsersByRoleTypeSuperAdmin() {
        return findUsersByRoleAndConvertEntitiesToDTOs(RoleType.ROLE_SUPERADMIN);
    }

    private List<UserResponseDTO> findUsersByRoleAndConvertEntitiesToDTOs(RoleType roleType) {
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();
        userRepository.findAllByRoleRoleType(roleType).forEach(userEntity -> userResponseDTOs.add(userModelMapper.mapUserEntityToUserResponseDTO(userEntity)));

        return userResponseDTOs;
    }

    @Override
    public List<UserResponseDTO> findUsersByRoleTypeAdmin() {
        return findUsersByRoleAndConvertEntitiesToDTOs(RoleType.ROLE_ADMIN);
    }

    @Override
    public List<UserResponseDTO> findUsersByRoleTypeModerator() {
        return findUsersByRoleAndConvertEntitiesToDTOs(RoleType.ROLE_MODERATOR);
    }
}
