import { createRouter, createWebHistory } from 'vue-router'
import UserMainView from '@/views/fo/user/UserMainView.vue'
import MypageView from '@/views/fo/user/MypageView.vue'
import MypageHome from '@/components/fo/user/mypage/MypageHome.vue'
import MypagePPAcception from '@/components/fo/user/mypage/MypagePPAcception.vue'
import EnterMemberRegister from '@/views/fo/enterprise/EnterMemberRegister.vue'
import SampleView from '@/views/devSample/SampleView.vue'
import EnterLogin from '@/views/fo/enterprise/EnterLogin.vue'
import ApplyCompanyList from '@/components/fo/user/mypage/ApplyCompanyList.vue'
import JobPostingListView from '@/views/fo/enterprise/JobPostingListView.vue'
import JobPostingInsert from '@/views/fo/enterprise/JobPostingInsertForm.vue'
import JobPostingDetail from '@/views/fo/enterprise/JobPostingDetailView.vue'
import JobPostingUpdate from '@/views/fo/enterprise/JobPostingUpdateForm.vue'
import EnterFind from '@/views/fo/enterprise/EnterFind.vue'

const routes = [
  {
    path: '/',
    component: UserMainView
  },
  {
    path: '/mypage',
    component: MypageView,
    children: [
      {
        path: 'home',
        component: MypageHome
      },
      {
        path: 'ppAcception',
        component: MypagePPAcception
      },
      // 지연님 컴포넌트 경로
      {
        path: 'applycompanylist',
        component: ApplyCompanyList,
      }
    ]
  },
  // 기우님 컴포넌트 경로
  {
    path: '/enter/register',
    name: "enterRegister",
    component: EnterMemberRegister
  },
  {
    path: '/sample',
    component: SampleView
  },
  {
    path: '/enter/login',
    name: "enterLogin",
    component: EnterLogin
  }, {
    path: '/enter/find',
    name: 'enterFind',
    componet: EnterFind
  },
  // 세추님 컴포넌트 경로
  {
    path: '/board/list/jobPosting',
    name: JobPostingListView,
    component: JobPostingListView
  },
  {
    path: '/board/jobPostingInsert',
    name: JobPostingInsert,
    component: JobPostingInsert
  },
  {
    path: '/board/detail/jobPosting/:jbpSq',
    name: JobPostingDetail,
    component: JobPostingDetail
  },
  {
    path: '/board/jobPostingUpdate/:jbpSq',
    name: JobPostingUpdate,
    component: JobPostingUpdate
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: 'active'
})

export default router
