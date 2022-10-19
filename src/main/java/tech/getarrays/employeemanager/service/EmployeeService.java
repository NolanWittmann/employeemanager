package tech.getarrays.employeemanager.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.getarrays.employeemanager.exeption.UserNotFoundException;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.repo.EmployeeRepo;

@Component
public class EmployeeService {

  private final EmployeeRepo employeeRepo;

  @Autowired
  public EmployeeService(EmployeeRepo employeeRepo) {
    this.employeeRepo = employeeRepo;
  }

  /** Fonction qui permet d'ajouter un employer à la BDD
   * @param employee : Un employer
   * @return une sauvegarde d'employer dans la BDD
   */
  public Employee addEmployee(Employee employee) {
    employee.setEmployeeCode(UUID.randomUUID().toString());
    return employeeRepo.save(employee);
  }


  /** Fonction qui liste les employer present dans la BDD.
   * @return a list of employees (GET)
   */
  public List<Employee> findAllEmployees() {
    return employeeRepo.findAll();
  }

  /** Fonction qui permet de mettre à jour un employer
   * @param employee : un employer
   * @return un employer mis à jour
   */
  public Employee updateEmployee(Employee employee) {
    return employeeRepo.save(employee);
  }

  /**
   *Fonction qui retourne les infos d'un employer par rapport à son ID.
   * @param id : Id de l'employer
   * @return un employer
   */
  public Employee findEmployeeById(Long id) {
    return employeeRepo.findEmployeeById(id)
        .orElseThrow(()
            -> new UserNotFoundException("User by id" + id + "was not found"));
  }

  /**
   *Fonction qui supprime un employer par rapport à son ID
   * @param id : Id de l'employer
   * @return : rien
   */
  public void deleteEmployee(Long id) {
    employeeRepo.deleteEmployeeById(id);
  }
}
