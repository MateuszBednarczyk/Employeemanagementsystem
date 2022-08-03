package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentDTOForObjectMapper;
import com.matthew.employeemanagementsystem.dtos.department.DepartmentResponseDTO;
import com.matthew.employeemanagementsystem.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class DepartmentFindingServiceImpl implements DepartmentFindingService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public DepartmentEntity getDepartmentEntity(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName).orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }

    @Override
    public DepartmentResponseDTO findDepartmentEntityByNameAndReturnAsDTO(String departmentName) {
        DepartmentEntity foundEntity = getDepartmentEntity(departmentName);

        return new DepartmentResponseDTO(foundEntity.getDepartmentName(), foundEntity.getEmployeesList());
    }

    @Override
    public List<DepartmentDTOForObjectMapper> findAllDepartments() {
        List<DepartmentDTOForObjectMapper> responseDTOList = new ArrayList<>();
        for (DepartmentEntity departmentEntity : departmentRepository.findAll()) {
            responseDTOList.add(modelMapper.map(departmentEntity, DepartmentDTOForObjectMapper.class));
        }

        return responseDTOList;
    }
}
