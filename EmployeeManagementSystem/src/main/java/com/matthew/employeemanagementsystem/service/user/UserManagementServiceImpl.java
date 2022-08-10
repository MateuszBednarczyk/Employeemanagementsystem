package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.configuration.SuffixConfiguration;
import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.domain.types.RoleType;
import com.matthew.employeemanagementsystem.dtos.user.*;
import com.matthew.employeemanagementsystem.exception.user.EmptyPasswordException;
import com.matthew.employeemanagementsystem.exception.user.UserDoesNotHavePermissionException;
import com.matthew.employeemanagementsystem.exception.user.UsernameTakenException;
import com.matthew.employeemanagementsystem.repository.UserRepository;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import com.matthew.employeemanagementsystem.service.role.RoleFacade;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserFindingService userFindingService;

    @Override
    @Transactional
    public UserResponseDTO registerNewUser(RegisterNewUserRequestDTO requestDTO) throws IllegalArgumentException {
        checkIfUserWithGivenUsernameAlreadyExists(requestDTO.username());
        UserEntity newUserEntity = createEntityToSave(requestDTO);
        userRepository.save(newUserEntity);

        return modelMapper.map(newUserEntity, UserResponseDTO.class);
    }

    @Override
    @Transactional
    public void deleteUser(DeleteUserRequestDTO requestDTO) {
        UserEntity requestingUser = userFindingService.getUserEntity(requestDTO.username());
        List<RoleEntity> allowedRoles = Arrays.asList(roleFacade.findByRoleType(RoleType.ROLE_ADMIN), roleFacade.findByRoleType(RoleType.ROLE_MODERATOR));
        if (requestingUser.getRoles().containsAll(allowedRoles)) {
            userRepository.deleteByUsername(requestDTO.username());
        } else {
            throw new UserDoesNotHavePermissionException(requestingUser.getUsername());
        }
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        UserEntity userEntity = userFindingService.getUserEntity(requestDTO.username());
        isCredentialsCorrect(requestDTO, userEntity);

        return modelMapper.map(userEntity, LoginResponseDTO.class);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {

    }

    private void isCredentialsCorrect(LoginRequestDTO requestDTO, UserEntity userEntity) {
        if (!suffixConfiguration.bCryptPasswordEncoder().matches(requestDTO.password(), userEntity.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
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
