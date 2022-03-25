package demo.controller;

import demo.model.StudentEntity;
import demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    //lay tat ca thong tin sinh vien
    @GetMapping()
    public ResponseEntity<List<StudentEntity>> getAllStudents(@RequestParam(required = false) String name) {
        System.out.println("get all students....");

        try {
            List<StudentEntity> studentList;

            if (name == null) {
                studentList = studentService.findAll();
            }
            else {
                studentList= studentService.findByNameContain(name);
            }
            if(studentList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studentList,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    lay thong tin sinh vien theo phan trang
    @GetMapping("/getByPaging")
    public ResponseEntity<List<StudentEntity>> getStudentsByPaging(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<StudentEntity> list = studentService.getStudentsByPaging(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<StudentEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable long id) {
        Optional<StudentEntity> studentGettedById = studentService.findById(id);
        if (studentGettedById ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentGettedById.get(),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<StudentEntity> postStudent(@RequestBody StudentEntity newStudent) {
        try{
            studentService.save(newStudent);
            return new ResponseEntity<>(newStudent,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //sua thong tin sinh vien
    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable long id, @RequestBody StudentEntity dataStudent) {
        System.out.println("update student with id = " + id + "!");
        Optional<StudentEntity> updatingStudent = studentService.update(dataStudent);
        if (updatingStudent==null){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(updatingStudent.get().getAge()+"....."+updatingStudent.get().getName()+"...."+updatingStudent.get().isActive());
        return new ResponseEntity(updatingStudent,HttpStatus.OK);

    }


    //xoa sinh vien
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable long id) {
        System.out.println("delete student with ID= " + id + "!");
        try {
            studentService.deleteById(id);
            return new ResponseEntity("student hass been deleted!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllStudent(){
        System.out.println("delete all student!");
        List<StudentEntity> studentList = studentService.findAll();
        if(studentList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            studentService.deleteAllStudents();
            return new ResponseEntity<>(HttpStatus.OK);
        }
  }

//<<<<<<< HEAD
    //lay thong tin cua 1 sinh vien


    //them sinh vien
//    @GetMapping("/age/{age}")
//    public List findByAge(@PathVariable int age){
//        List students = studentService.findByAge(age);
//        return students;
//    }

//=======
//    @GetMapping("/getById/{id}")
//    public ResponseEntity<StudentEntity> getStudentById(@PathVariable("id")long id){
//        Optional<StudentEntity> studentData = studentService.findById(id);
//    if(studentData.isPresent()){
//        return new ResponseEntity<>(studentData.get(),HttpStatus.OK);
//    }else{
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//    }
//    //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 : token git
//
//
//    @PostMapping("/create")
//    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity studentEntity){
//        try{
//            StudentEntity newStudent = studentService.save(studentEntity);
//            log.info(String.valueOf(newStudent));
//            return new ResponseEntity<>(newStudent,HttpStatus.CREATED);
//        }catch(Exception e){
//            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//
//
//        }
//    }
//
//    @PutMapping("updateById/{id}")
//    public ResponseEntity<StudentEntity> updateStudent(@PathVariable("id") long id,@RequestBody StudentEntity studentEntity){
//       Optional<StudentEntity> studentData = studentService.findById(id);
//       if(studentData.isPresent()){
//           StudentEntity updatedStudent = studentData.get();
//           updatedStudent.setName(studentEntity.getName());
//           updatedStudent.setClassName(studentEntity.getClassName());
//           return new ResponseEntity<>(studentService.save(updatedStudent),HttpStatus.OK);
//       }
//       else{
//           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//       }
//    }
//
//    @DeleteMapping("deleteById/{id}")
//    public ResponseEntity<StudentEntity> deleteStudent(@PathVariable("id")long id){
//        try{
//            studentService.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//



    /*>>>>>>> 63ff9d03a03c20980beb7cdc0d276e92a7e47f0c*/
}


////https://www.bezkoder.com/spring-boot-pagination-filter-jpa-pageable/
//<<<<<<< HEAD
////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 : token git
//=======
//>>>>>>> 63ff9d03a03c20980beb7cdc0d276e92a7e47f0c
