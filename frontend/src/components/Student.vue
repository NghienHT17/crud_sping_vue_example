<template>
  <div v-if="currentStudent" class="edit-form">
    <h4>Student</h4>
    <form>
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name"
               v-model="currentStudent.name"
        />
      </div>
      <div class="form-group">
        <label for="age">Age</label>
        <input type="text" class="form-control" id="age"
               v-model="currentStudent.age"
        />
      </div>
      <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentStudent.active ? "Active" : "Inactive" }}
      </div>
    </form>
    <button class="badge badge-primary mr-2"
            v-if="currentStudent.active"
            @click="updateActive(false)"
    >
      InActive
    </button>
    <button v-else class="badge badge-primary mr-2"
            @click="updateActive(true)"
    >
      Active
    </button>
    <button class="badge badge-danger mr-2"
            @click="deleteStudent"
    >
      Delete
    </button>
    <button type="submit" class="badge badge-success"
            @click="updateStudent"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>
  <div v-else>
    <br />
    <p>Please click on a student...</p>
  </div>
</template>
<script>
import StudentDataService from "../services/StudentDataService";
export default {
  name: "student_compo",
  data() {
    return {
      currentStudent: null,
      message: ''
    };
  },
  methods: {
    getStudent(id) {
      StudentDataService.get(id)
          .then(response => {
            this.currentStudent = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },
    updateActive(status) {
      var data = {
        id: this.currentStudent.id,
        name: this.currentStudent.name,
        age: this.currentStudent.age,
        active: status
      };
      StudentDataService.update(this.currentStudent.id, data)
          .then(response => {
            this.currentStudent.active = status;
            console.log(response.data);

          })
          .catch(e => {
            console.log(e);

          });
    },
    updateStudent() {
      StudentDataService.update(this.currentStudent.id, this.currentStudent)
          .then(response => {
            console.log(response.data);
            this.message = 'The student was updated successfully!';

            // this.$router.push({ name: "students" });
          })
          .catch(e => {
            console.log(e);
          });
    },
    deleteStudent() {
      StudentDataService.delete(this.currentStudent.id)
          .then(response => {
            console.log(response.data);
            this.$router.push({ name: "students" });
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    // this.message = '';
    this.getStudent(this.$route.params.id);//mount ngay sau khi component dc khoi chay
  }
};
</script>
<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
