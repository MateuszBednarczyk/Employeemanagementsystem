package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.configuration.SuffixConfiguration;
import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.UserResponseDTO;
import com.matthew.employeemanagementsystem.repository.UserRepository;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import com.matthew.employeemanagementsystem.service.role.RoleManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;

@RequiredArgsConstructor
@Service
class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository userRepository;
    private final SuffixConfiguration suffixConfiguration;
    private final DepartmentFacade departmentFacade;
    private final RoleManagementService roleManagementService;

    @Override
    public UserResponseDTO registerNewUser(RegisterNewUserRequestDTO requestDTO) throws UnexpectedException {
        checkIfUserWithGivenUsernameAlreadyExists(requestDTO.username());
        UserEntity newUserEntity = createEntityToSave(requestDTO);
        userRepository.save(newUserEntity);

        return new UserResponseDTO(newUserEntity.getUsername(), newUserEntity.getDepartmentEntities(), newUserEntity.getRoles());
    }

    private UserEntity createEntityToSave(RegisterNewUserRequestDTO requestDTO) throws UnexpectedException {
        UserEntity newUserEntity = new UserEntity(requestDTO.username(), encodePassword(requestDTO.password()));
        RoleEntity role = roleManagementService.createRoleEntity(requestDTO.role());
        newUserEntity.getRoles().add(role);
        newUserEntity.getDepartmentEntities().add(departmentFacade.getDepartmentEntity(requestDTO.department()));

        return newUserEntity;
    }

    private void checkIfUserWithGivenUsernameAlreadyExists(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new RuntimeException("Username taken");
        });
    }

    private String encodePassword(String password) throws UnexpectedException {
        if (password != null) {
            return suffixConfiguration.bCryptPasswordEncoder().encode(password);
        } else {
            throw new UnexpectedException("Password empty");
        }
    }
}
