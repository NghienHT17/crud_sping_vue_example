package demo.service;

import demo.model.StudentEntity;
import demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    public StudentRepository studentRepository;

    public List<StudentEntity> findAll() {
        List<StudentEntity> studentEntityList = new ArrayList<StudentEntity>();
        studentRepository.findAll().forEach(studentEntityList::add);

        return studentEntityList;
    }

    public List<StudentEntity>findByNameContain(String name){
        List<StudentEntity> studentListByName = new ArrayList<StudentEntity>();
        studentRepository.findAllByName(name).forEach(studentListByName::add);
        return studentListByName;
    }


    //tim sinh vien theo ID
    public Optional<StudentEntity> findById(Long id) {
        Optional<StudentEntity> studentFindById = studentRepository.findById(id);
        return studentFindById;
    }

    //them sinh vien moi
    public StudentEntity save(StudentEntity newStudent) {
        StudentEntity creatingStudent = studentRepository.save(new StudentEntity(newStudent.getName(), newStudent.getAge()));
        return creatingStudent;
    }


    //update sinh vien
    public Optional<StudentEntity> update(StudentEntity studentData){
        Optional<StudentEntity> tempStudent = studentRepository.findById(studentData.getId());
        if (tempStudent.isPresent()){
//            StudentEntity updateStu = tempStudent.get();
//            updateStu.setName(studentData.getName());
//            updateStu.setAge(studentData.getAge());
//            updateStu.setActive(studentData.isActive());
//            updateStu.toString();
//            return Optional.of(updateStu);
              tempStudent.get().setName(studentData.getName());
              tempStudent.get().setAge(studentData.getAge());
              tempStudent.get().setActive(studentData.isActive());
              studentRepository.save(tempStudent.get());
              return tempStudent;
        }
        else return null;

    }//so sánh với null trong controller

    //xoa sih vien
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public void deleteAllStudents(){
        studentRepository.deleteAll();
    }

    //lay theo trang
    public List<StudentEntity> getStudentsByPaging(int pageNo, int pageSize, String sortBy) {
        Pageable paging =PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        Page<StudentEntity> resultPage = studentRepository.findAll(paging);
        if(resultPage.hasContent()){
            return  resultPage.getContent();
        }else {
            return new ArrayList<StudentEntity>();
        }
    }
    //lay theo danh sach
    public List<StudentEntity> findByAge(int age){
        return studentRepository.findByAge(age);
    }


}

