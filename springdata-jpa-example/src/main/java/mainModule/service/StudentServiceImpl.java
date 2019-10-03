package mainModule.service;

import mainModule.modal.Student;
import mainModule.modal.Telephone;
import mainModule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl {
    @Autowired
    StudentRepository studentRepository;

    public Student save(Student student){
        return studentRepository.save(student);
    }

    public Student update(Student newStudent, Integer id) {

            return studentRepository.findById(id)
                    .map(Student -> {
                        Student.setName(newStudent.getName());
                        Student.setAddress(newStudent.getAddress());
                        Student.setTelephoneList(newStudent.getTelephoneList());
                        return studentRepository.save(Student);
                    })
                    .orElseGet(() -> {
                        newStudent.setSid(id);
                        return studentRepository.save(newStudent);
                    });

    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Integer id){
        return studentRepository.findById(id);
    }


}
