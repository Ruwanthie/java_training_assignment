package mainModule;

import mainModule.modal.Address;
import mainModule.modal.Student;
import mainModule.modal.Telephone;
import mainModule.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sms")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @RequestMapping(value = "/hello")
    public String greeting(){
        return "hello springboot";
    }

    //To save the records to db - POST METHOD
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public Student save(@RequestBody Student student){

        for (Telephone tel: student.getTelephoneList()) {
            tel.setStudent(student);
        }
        return studentService.save(student);
    }

   /* //to get the records - GET METHOD
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Student getStudent(){
        Student student = new Student();
        student.setName("Saman");
        Address address = new Address();
        address.setCity("Galle");
        student.setAddress(address);

        List<Telephone> telephones = new ArrayList<>();
        Telephone telephone = new Telephone();
        telephone.setNumber("0754505251");
        telephones.add(telephone);
        student.setTelephoneList(telephones);
        return student;

    }*/

    //to fetch the all records - GET METHOD using jpa repository
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> fetchAll(Optional<Integer> id){
        return studentService.findAll();
    }

    //to fetch a particular record by id - GET METHOD using jpa repository
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Optional<Student> fetchAll(@PathVariable Integer id){
        return studentService.findById(id);
    }
}
