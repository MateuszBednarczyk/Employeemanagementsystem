package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.configuration.SuffixConfiguration;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.department.AddModeratorToDepartmentRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.*;
import com.matthew.employeemanagementsystem.exception.role.RoleDoesntHavePermissionToThisFeatureException;
import com.matthew.employeemanagementsystem.exception.user.*;
import com.matthew.employeemanagementsystem.mapper.UserModelMapper;
import com.matthew.employeemanagementsystem.repository.UserRepository;
import com.matthew.employeemanagementsystem.repository.VerificationTokenRepository;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import com.matthew.employeemanagementsystem.service.security.RoleService;
import com.matthew.employeemanagementsystem.service.verificationtoken.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;

@RequiredArgsConstructor
@Service
@Transactional
class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository userRepository;
    private final SuffixConfiguration suffixConfiguration;
    private final DepartmentFacade departmentFacade;
    private final UserFindingService userFindingService;
    private final UserModelMapper userModelMapper;
    private final VerificationService verificationService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final RoleService roleService;

    @Override
    public UserResponseDTO registerNewUser(HttpServletRequest request, Principal loggedUser, RegisterNewUserRequestDTO requestDTO) throws IllegalArgumentException {
        UserEntity loggedUserEntity = userFindingService.getUserEntity(loggedUser.getName());
        checkIfRequestIsAboutAdminOrSuperAdminAndIfRequestingUserHasPermission(requestDTO, loggedUserEntity);
        checkIfUserWithGivenUsernameAlreadyExists(requestDTO.username());
        UserEntity newUserEntity = createEntityToSave(requestDTO);
        verificationService.generateVerificationTokenAndSendVerificationMail(request, newUserEntity);
        userRepository.save(newUserEntity);
        addUserAsAModeratorInDepartment(requestDTO);

        return userModelMapper.mapUserEntityToUserResponseDTO(newUserEntity);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        UserEntity userEntity = userFindingService.getUserEntity(requestDTO.username());
        isCredentialsCorrect(requestDTO.password(), userEntity.getPassword());

        return userModelMapper.mapUserEntityToLoginResponseDTO(userEntity);
    }

    @Override
    public void deleteUser(Principal loggedUser, DeleteUserRequestDTO requestDTO) {
        UserEntity requestingUser = userFindingService.getUserEntity(loggedUser.getName());
        UserEntity requestedUser = userFindingService.getUserEntity(requestDTO.username());
        if (roleService.isUserSuperAdminOrAdmin(requestingUser)) {
            verificationTokenRepository.deleteByUserId(requestedUser.getId());
            departmentFacade.deleteUserFromAllModeratorsList(requestedUser);
            userRepository.deleteByUsername(requestDTO.username());
        } else {
            throw new UserDoesNotHavePermissionException(requestingUser.getUsername());
        }
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

    private void addUserAsAModeratorInDepartment(RegisterNewUserRequestDTO requestDTO) {
        if (requestDTO.role().contains("MODERATOR")) {
            departmentFacade.addUserEntityToModeratorList(new AddModeratorToDepartmentRequestDTO(requestDTO.username(), requestDTO.department()));
        }
    }

    private void checkIfRequestIsAboutAdminOrSuperAdminAndIfRequestingUserHasPermission(RegisterNewUserRequestDTO requestDTO, UserEntity loggedUserEntity) {
        if (roleService.isGivenRoleSuperAdminOrAdmin(requestDTO.role())) {
            checkIfUserHasPermissionToAddSuperAdminOrAdmin(loggedUserEntity);
        }
    }

    private void checkIfUserHasPermissionToAddSuperAdminOrAdmin(UserEntity loggedUserEntity) {
        if (!roleService.isUserSuperAdmin(loggedUserEntity)) {
            throw new RoleDoesntHavePermissionToThisFeatureException();
        }
    }

    private void isCredentialsCorrect(String givenPassword, String actualPassword) {
        if (!isGivenOldPasswordCorrect(givenPassword, actualPassword)) {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private UserEntity createEntityToSave(RegisterNewUserRequestDTO requestDTO) throws IllegalArgumentException {
        UserEntity newUserEntity = new UserEntity(requestDTO.username(), encodePassword(requestDTO.password()), requestDTO.email(), requestDTO.role());
        newUserEntity.getDepartments().add(departmentFacade.getDepartmentEntity(requestDTO.department()));

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

    public void setupSuperAdminUser(RegisterNewUserRequestDTO requestDTO) throws IllegalArgumentException {
        checkIfUserWithGivenUsernameAlreadyExists(requestDTO.username());
        UserEntity newUserEntity = createEntityToSave(new RegisterNewUserRequestDTO(requestDTO.username(), requestDTO.password(), requestDTO.email(), requestDTO.department(), requestDTO.role()));
        newUserEntity.setEnabled(true);
        userRepository.save(newUserEntity);
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
