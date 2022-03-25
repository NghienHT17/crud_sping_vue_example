<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by Name"
               v-model="name"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
                  @click="searchName"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Students List</h4>
      <ul class="list-group">
        <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(student, index) in students"
            :key="index"
            @click="setActiveStudent(student, index)"
        >
          {{ student.name }}
        </li>
      </ul>
      <button class="m-3 btn btn-sm btn-danger" @click="removeAllStudents">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentStudent">
        <h4>Student</h4>
        <div>
          <label><strong>Name:</strong></label> {{ currentStudent.name }}
        </div>
        <div>
          <label><strong>Age:</strong></label> {{ currentStudent.age }}
        </div>
        <div>
          <label><strong>Status:</strong></label> {{ currentStudent.active ? "Active" : "Inactive" }}
        </div>
        <a class="badge badge-warning"
           :href="'/students/' + currentStudent.id"
        >
          <!--          //link đến component-->
          Edit
        </a>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Student...</p>
      </div>
    </div>
  </div>
</template>
<script>
import StudentDataService from "../services/StudentDataService";
export default {
  name: "students-list",
  data() {
    return {
      students: [],
      currentStudent: null,
      currentIndex: -1,
      name: ""
    };
  },
  methods: {
    retrieveStudents() {
      StudentDataService.getAll()
          .then(response => {
            this.students = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },
    refreshList() {
      this.retrieveStudents();
      this.currentStudent = null;
      this.currentIndex = -1;
    },
    setActiveStudent(student, index) {
      this.currentStudent = student;
      this.currentIndex = index;
    },
    removeAllStudents() {
      StudentDataService.deleteAll()
          .then(response => {
            console.log(response.data);
            this.refreshList();
          })
          .catch(e => {
            console.log(e);
          });
    },
    searchName() {
      StudentDataService.findByName(this.name)
          .then(response => {
            this.students = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.retrieveStudents();//thực thi trước khi tạo côpnent
  }
};
</script>
<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>