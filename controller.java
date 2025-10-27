@RestController
public class Controller {
  private EmployeeService employeeService;
  private AdminService adminService;
  
  public EmployeeController(EmployeeService employeeService, AdminService adminService) {
    this.employeeService = employeeService;
    this.adminService = adminService;
  }
  
  @RequestMapping(value = "/employee/getUserById", method = RequestMethod.POST)
  @ResponseBody
  public User findById(GetUserDto dto) {
    return employeeService.findById(dto.getId());
  }
  
  @RequestMapping(value = "/employee/deleteUserById", method = RequestMethod.POST)
  @ResponseBody
  public User deleteById(DeleteUserDto dto) {
    return employeeService.deteleUserById(dto.getId());
  }
  
  @RequestMapping(value = "/admin/getAllUsers", method = RequestMethod.POST)
  @ResponseBody
  public List<User> findAll() {
    return adminService.findAll();
  }
  
}
