package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.configuration.SuffixConfiguration;
import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.domain.types.RoleType;
import com.matthew.employeemanagementsystem.dtos.user.DeleteUserRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.UserResponseDTO;
import com.matthew.employeemanagementsystem.exception.user.EmptyPasswordException;
import com.matthew.employeemanagementsystem.exception.user.UserDoesNotHavePermissionException;
import com.matthew.employeemanagementsystem.exception.user.UsernameTakenException;
import com.matthew.employeemanagementsystem.repository.UserRepository;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import com.matthew.employeemanagementsystem.service.role.RoleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository userRepository;
    private final SuffixConfiguration suffixConfiguration;
    private final DepartmentFacade departmentFacade;
    private final RoleFacade roleFacade;

    @Override
    @Transactional
    public UserResponseDTO registerNewUser(RegisterNewUserRequestDTO requestDTO) throws IllegalArgumentException {
        checkIfUserWithGivenUsernameAlreadyExists(requestDTO.username());
        UserEntity newUserEntity = createEntityToSave(requestDTO);
        userRepository.save(newUserEntity);

        return new UserResponseDTO(newUserEntity.getUsername(), newUserEntity.getDepartmentEntities(), newUserEntity.getRoles());
    }

    @Override
    @Transactional
    public void deleteUser(DeleteUserRequestDTO requestDTO) {
        UserEntity requestingUser = userRepository.findByUsername(requestDTO.principal().getName()).orElseThrow(() -> new UsernameNotFoundException(requestDTO.principal().getName()));
        List<RoleEntity> allowedRoles = Arrays.asList(roleFacade.findByRoleType(RoleType.ROLE_ADMIN), roleFacade.findByRoleType(RoleType.ROLE_MODERATOR));
        if (requestingUser.getRoles().containsAll(allowedRoles)) {
            userRepository.deleteByUsername(requestDTO.username());
        } else {
            throw new UserDoesNotHavePermissionException(requestingUser.getUsername());
        }
    }

    private UserEntity createEntityToSave(RegisterNewUserRequestDTO requestDTO) throws IllegalArgumentException {
        UserEntity newUserEntity = new UserEntity(requestDTO.username(), encodePassword(requestDTO.password()));
        RoleEntity role = roleFacade.createRoleEntity(requestDTO.role());
        newUserEntity.getRoles().add(role);
        newUserEntity.getDepartmentEntities().add(departmentFacade.getDepartmentEntity(requestDTO.department()));

        return newUserEntity;
    }

    private void checkIfUserWithGivenUsernameAlreadyExists(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new UsernameTakenException(username);
        });
    }

    private String encodePassword(String password) {
        if (password != null) {
            return suffixConfiguration.bCryptPasswordEncoder().encode(password);
        } else {
            throw new EmptyPasswordException();
        }
    }
}
