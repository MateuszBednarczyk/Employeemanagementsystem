package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.configuration.SuffixConfiguration;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.repository.UserRepository;
import com.matthew.employeemanagementsystem.service.department.DepartmentManagementService;
import com.matthew.employeemanagementsystem.service.role.RoleManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;

@RequiredArgsConstructor
@Service
class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository userRepository;
    private final SuffixConfiguration suffixConfiguration;
    private final DepartmentManagementService departmentManagementService;
    private final RoleManagementService roleManagementService;

    @Override
    public ResponseEntity<String> registerNewUser(RegisterNewUserRequestDTO requestDTO) throws UnexpectedException {
        checkIfUserWithGivenUsernameAlreadyExists(requestDTO.username());
        userRepository.save(createEntityToSave(requestDTO));

        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    private UserEntity createEntityToSave(RegisterNewUserRequestDTO requestDTO) throws UnexpectedException {
        UserEntity newUserEntity = new UserEntity(requestDTO.username(), encodePassword(requestDTO.password()));
        newUserEntity.getRoles().add(roleManagementService.createRoleEntity(requestDTO.role()));
        newUserEntity.getDepartmentEntities().add(departmentManagementService.getDepartmentEntity(requestDTO.department()));

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
