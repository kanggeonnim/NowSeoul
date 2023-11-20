import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "Main",
      component: MainView,
    },
    {
      path: "/",
      name: "Map",
      component: () => import("../views/MapView.vue"),
    },
    {
      path: "/Board",
      name: "Board",
      component: () => import("../views/BoardView.vue"),
      children: [
        {
          path: "list",
          name: "list",
          component: () => import("../components/board/BoardList.vue"),
        },
        {
          path: "write",
          name: "write",
          component: () => import("../components/board/BoardWrite.vue"),
        },
        {
          path: "modify",
          name: "modify",
          component: () => import("../components/board/BoardModify.vue"),
        },
      ],
    },
    {
      path: "/plan",
      name: "plan",
      component: () => import("../views/PlanView.vue"),
      children: [
        {
          path: "planmain",
          name: "planmain",
          component: () => import("../components/plan/PlanMain.vue"),
        },
      ],
    },
    {
      path: "/custom",
      name: "custom",
      component: () => import("../views/CustomView.vue"),
      children: [
        {
          path: "custommain",
          name: "custommain",
          component: () => import("../components/custom/CustomMain.vue"),
        },
      ],
    },
    {
      path: "/mypage",
      name: "mypage",
      component: () => import("../components/login/MyPage.vue"),
    },
    {
      path: "/join",
      name: "join",
      component: () => import("../components/login/Join.vue"),
    },
  ],
});

export default router;
