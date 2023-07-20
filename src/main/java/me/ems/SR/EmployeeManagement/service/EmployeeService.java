package me.ems.SR.EmployeeManagement.service;

import jakarta.transaction.Transactional;
import me.ems.SR.EmployeeManagement.dto.EmployeeDTO;
import me.ems.SR.EmployeeManagement.entity.Employee;
import me.ems.SR.EmployeeManagement.repo.EmployeeRepo;
import me.ems.SR.EmployeeManagement.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public String updateEmployee(EmployeeDTO employeeDTO){
        if (employeeRepo.existsById(employeeDTO.getEmpID())){
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RESP_SUCCESS;
        }else {
            return VarList.RESP_DATA_NOT_FOUND;
        }
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employeesList = employeeRepo.findAll();
        return modelMapper.map(employeesList, new TypeToken<ArrayList<EmployeeDTO>>(){
        }.getType());
    }

    public EmployeeDTO searchEmployee(int empID){
        if (employeeRepo.existsById(empID)){
            Employee employee = employeeRepo.findById(empID).get();
            return modelMapper.map(employee, EmployeeDTO.class);
        }else {
            return null;
        }
    }

    public String deleteEmployee(int empID){
        if (employeeRepo.existsById(empID)){
            employeeRepo.deleteById(empID);
            return VarList.RESP_SUCCESS;
        } else {
            return VarList.RESP_DATA_NOT_FOUND;
        }
    }
}
