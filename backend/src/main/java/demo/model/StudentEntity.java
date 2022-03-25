package demo.model;


import javax.persistence.*;

@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name="active")
    private boolean active;


    public StudentEntity() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public StudentEntity(String name, int age) {
        this.name = name;
        this.age = age;
        this.active = isActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "sinh vien co ten la: "+this.name+" co ID la:"+this.id+" co tuoi la: "+this.age+"co trang thai la: "+this.isActive();
    }
}
