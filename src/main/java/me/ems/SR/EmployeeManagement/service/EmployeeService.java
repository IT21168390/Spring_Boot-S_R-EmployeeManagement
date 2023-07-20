package me.ems.SR.EmployeeManagement.service;

import jakarta.transaction.Transactional;
import me.ems.SR.EmployeeManagement.dto.EmployeeDTO;
import me.ems.SR.EmployeeManagement.entity.Employee;
import me.ems.SR.EmployeeManagement.repo.EmployeeRepo;
import me.ems.SR.EmployeeManagement.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService {
   @Autowired
   private EmployeeRepo employeeRepo;

   @Autowired
   private ModelMapper modelMapper;

    public String saveEmployee(EmployeeDTO employeeDTO) {
        if(employeeRepo.existsById(employeeDTO.getEmpID())){
            return VarList.RESP_DUPLICATED;
        }
        else{
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RESP_SUCCESS;
        }

    }
}