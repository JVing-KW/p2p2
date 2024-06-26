import { createRouter, createWebHistory } from 'vue-router'
import MypageMain from '../views/fo/user/MypageView.vue'

const routes = [
  {path : '/', component:MypageMain}
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
