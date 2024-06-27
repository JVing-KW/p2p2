import { createRouter, createWebHistory } from 'vue-router'
import UserMainView from '@/views/fo/user/UserMainView.vue'
import MypageView from '@/views/fo/user/MypageView.vue'
import MypageHome from '@/components/fo/user/mypage/MypageHome.vue'

const routes = [
  { path : '/', component : UserMainView},
  { path : '/mypage',
    component : MypageView,
    children:[
      {
        path : 'home',
        component : MypageHome
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
