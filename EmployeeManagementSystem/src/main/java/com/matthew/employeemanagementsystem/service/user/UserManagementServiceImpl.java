package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.configuration.SuffixConfiguration;
import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.domain.enums.RoleType;
import com.matthew.employeemanagementsystem.dtos.user.*;
import com.matthew.employeemanagementsystem.exception.role.RoleDoesntHavePermissionToThisFeatureException;
import com.matthew.employeemanagementsystem.exception.user.*;
import com.matthew.employeemanagementsystem.mapper.UserModelMapper;
import com.matthew.employeemanagementsystem.repository.UserRepository;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import com.matthew.employeemanagementsystem.service.role.RoleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository userRepository;
    private final SuffixConfiguration suffixConfiguration;
    private final DepartmentFacade departmentFacade;
    private final RoleFacade roleFacade;
    private final UserFindingService userFindingService;
    private final UserModelMapper userModelMapper;

    @Override
    public UserResponseDTO registerNewUser(Principal loggedUser, RegisterNewUserRequestDTO requestDTO) throws IllegalArgumentException {
        UserEntity loggedUserEntity = userFindingService.getUserEntity(loggedUser.getName());
        checkIfRequestIsAboutAdminOrSuperAdminAndIfRequestingUserHasPermission(requestDTO, loggedUserEntity);
        checkIfUserWithGivenUsernameAlreadyExists(requestDTO.username());
        UserEntity newUserEntity = createEntityToSave(requestDTO);
        userRepository.save(newUserEntity);

        return userModelMapper.mapUserEntityToUserResponseDTO(newUserEntity);
    }

    private void checkIfRequestIsAboutAdminOrSuperAdminAndIfRequestingUserHasPermission(RegisterNewUserRequestDTO requestDTO, UserEntity loggedUserEntity) {
        if (requestDTO.role().contains("ADMIN") || requestDTO.role().contains("SUPERADMIN")) {
            checkIfUserHasPermissionToAddSuperAdminOrAdmin(loggedUserEntity);
        }
    }

    private void checkIfUserHasPermissionToAddSuperAdminOrAdmin(UserEntity loggedUserEntity) {
        if (!isUserSuperAdmin(loggedUserEntity.getRoles())) {
            throw new RoleDoesntHavePermissionToThisFeatureException();
        }
    }

    private boolean isUserSuperAdmin(List<RoleEntity> usersRole) {
        return usersRole.contains(roleFacade.findByRoleType(RoleType.ROLE_SUPERADMIN));
    }

    @Override
    public void deleteUser(Principal principal, DeleteUserRequestDTO requestDTO) {
        UserEntity requestingUser = userFindingService.getUserEntity(principal.getName());
        List<RoleEntity> allowedRoles = Arrays.asList(roleFacade.findByRoleType(RoleType.ROLE_SUPERADMIN), roleFacade.findByRoleType(RoleType.ROLE_ADMIN));
        if (requestingUser.getRoles().containsAll(allowedRoles)) {
            userRepository.deleteByUsername(requestDTO.username());
        } else {
            throw new UserDoesNotHavePermissionException(requestingUser.getUsername());
        }
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        UserEntity userEntity = userFindingService.getUserEntity(requestDTO.username());
        isCredentialsCorrect(requestDTO.password(), userEntity.getPassword());

        return userModelMapper.mapUserEntityToLoginResponseDTO(userEntity);
    }

    private void isCredentialsCorrect(String givenPassword, String actualPassword) {
        if (!isGivenOldPasswordCorrect(givenPassword, actualPassword)) {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private UserEntity createEntityToSave(RegisterNewUserRequestDTO requestDTO) throws IllegalArgumentException {
        UserEntity newUserEntity = new UserEntity(requestDTO.username(), encodePassword(requestDTO.password()));
        RoleEntity role = roleFacade.createRoleEntity(requestDTO.role());
        addRoleToUserEntity(newUserEntity, role);
        newUserEntity.getDepartmentEntities().add(departmentFacade.getDepartmentEntity(requestDTO.department()));

        return newUserEntity;
    }

    public void addRoleToUserEntity(UserEntity newUserEntity, RoleEntity role) {
        newUserEntity.getRoles().add(role);
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

    public void setupSuperAdminUser(RegisterNewUserRequestDTO requestDTO) throws IllegalArgumentException {
        checkIfUserWithGivenUsernameAlreadyExists(requestDTO.username());
        UserEntity newUserEntity = createEntityToSave(requestDTO);
        userRepository.save(newUserEntity);
    }

    @Override
    public void changeUserPassword(Principal loggedUser, ChangeUserPasswordRequestDTO requestDTO) {
        UserEntity userEntity = userFindingService.getUserEntity(loggedUser.getName());
        if (isGivenOldPasswordCorrect(requestDTO.oldPassword(), userEntity.getPassword()) && isPasswordRepeatingCorrect(requestDTO)) {
            userEntity.setPassword(encodePassword(requestDTO.newPassword()));
        } else {
            throw new PasswordDoesntMatchException();
        }
    }

    private boolean isGivenOldPasswordCorrect(String charPassword, String actualPassword) {
        if (suffixConfiguration.bCryptPasswordEncoder().matches(charPassword, actualPassword)) {

            return true;
        } else {
            throw new PasswordDoesntMatchException();
        }
    }

    private boolean isPasswordRepeatingCorrect(ChangeUserPasswordRequestDTO requestDTO) {
        if (requestDTO.repeatOldPassword().equals(requestDTO.oldPassword()) && requestDTO.repeatNewPassword().equals(requestDTO.newPassword())) {

            return true;
        } else {
            throw new BadRepeatingException();
        }
    }
}
