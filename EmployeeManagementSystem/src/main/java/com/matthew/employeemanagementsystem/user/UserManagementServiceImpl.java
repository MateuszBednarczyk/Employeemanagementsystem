package com.matthew.employeemanagementsystem.user;

import com.matthew.employeemanagementsystem.configuration.SuffixConfiguration;
import com.matthew.employeemanagementsystem.department.DepartmentManagementService;
import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.domain.types.DepartmentType;
import com.matthew.employeemanagementsystem.domain.types.RoleType;
import com.matthew.employeemanagementsystem.role.RoleManagementService;
import com.matthew.employeemanagementsystem.user.dtos.RegisterNewUserRequestDTO;
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
        UserEntity newUser = new UserEntity(requestDTO.username(), encodePassword(requestDTO.password()));
        newUser.getRoles().add(roleManagementService.createRoleEntity(requestDTO.role()));
        newUser.getDepartmentEntities().add(getDepartmentEntity(requestDTO));

        return newUser;
    }

    private DepartmentEntity getDepartmentEntity(RegisterNewUserRequestDTO requestDTO) {

        return departmentManagementService.getDepartmentEntity(DepartmentType.valueOf(requestDTO.department()));
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
