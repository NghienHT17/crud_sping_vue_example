package demo.repository;

import demo.model.StudentEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long>{
//    List<StudentEntity> findAllByActive(boolean active);
    List<StudentEntity> findAllByName(String name);
    List<StudentEntity> findByAge(int age);



}


