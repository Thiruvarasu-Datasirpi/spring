package com.example.demo.Service;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired

    private StudentRepository studentRepository;

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            StudentDTO studentDTO = new StudentDTO();
            BeanUtils.copyProperties(student, studentDTO);
            return studentDTO;
        }
        return null;
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO();
            BeanUtils.copyProperties(student, studentDTO);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);
        student = studentRepository.save(student);
        StudentDTO createdStudentDTO = new StudentDTO();
        BeanUtils.copyProperties(student, createdStudentDTO);
        return createdStudentDTO;
    }

    public StudentDTO updateStudent(Long id, StudentDTO updatedStudentDTO) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            BeanUtils.copyProperties(updatedStudentDTO, existingStudent);
            existingStudent = studentRepository.save(existingStudent);
            StudentDTO updatedStudent = new StudentDTO();
            BeanUtils.copyProperties(existingStudent, updatedStudent);
            return updatedStudent;
        }
        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
