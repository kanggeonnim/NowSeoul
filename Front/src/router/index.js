import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";

import Admin from "@/components/chat/Admin.vue";
import User from "@/components/chat/User.vue";

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = sessionStorage.getItem("accessToken");

  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }
  if (!isValidToken.value || userInfo.value === null) {
    next({ name: "user-login" });
  } else {
    next();
  }
};

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
      path: "/board",
      name: "board",
      // component: TheBoardView,
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/TheBoardView.vue"),
      redirect: { name: "article-list" },
      children: [
        {
          path: "list",
          name: "article-list",
          component: () => import("@/components/boards/BoardList.vue"),
        },
        {
          path: "view/:articleno",
          name: "article-view",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardDetail.vue"),
        },
        {
          path: "write",
          name: "article-write",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardWrite.vue"),
        },
        {
          path: "modify/:articleno",
          name: "article-modify",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardModify.vue"),
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
      children: [
        {
          path: "Admin",
          component: Admin
        },
        {
          path: "chat",
          component: User
        }
      ]
    },
    {
      path: "/join",
      name: "join",
      component: () => import("../components/login/Join.vue"),
    },
    {
      path: "/kakao/callback",
      component: () => import("../components/login/kakaoView.vue"),
    },
  ],
});

export default router;
