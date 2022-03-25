
import Vue from "vue";
import Router from "vue-router";
// import {createRouter} from "vue-router";
// let App = createApp();
// const router = createRouter();
// app.use(router)

import { createWebHistory, createRouter } from "vue-router";
const routes =  [

        {
            path: "/",
            alias: "/students",
            name: "students",
            component: () => import("./components/StudentList")
        },
        {
            path: "/students/:id",
            name: "student-details",
            component: () => import("./components/Student")
        },
        {
            path: "/add",
            name: "add",
            component: () => import("./components/AddStudent")
        }
    ];
const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router;