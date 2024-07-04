<template>
    <!-- <div role="main" class="main container py-2"> -->
        <section class="page-header page-header-modern bg-color-grey page-header-lg">
            <!-- 페이지 헤더 -->
            <div class="container">
                <div class="row">
                    <div class="col-md-12 align-self-center p-static order-2 text-center">
                        <h1 class="font-weight-bold text-dark">입사지원 현황</h1>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- 검색창 -->
        
        <div
      class="header-nav-features header-nav-features-no-border col-lg-5 col-xl-6 px-0 ms-0 mb-4" style="display: flex;">
      <div class="header-nav-feature  pe-lg-4" >
        <div class="search-with-select">
          <div class="search-form-wrapper input-group">
            <input
              class="form-control text-1"
              type="search"
              v-model="search"
              placeholder="검색할 내용을 입력하세요"
            />
            <div class="search-form-select-wrapper">
              <div class="custom-select-1">
                <select v-model="searchType" class="form-control form-select">
                  <option value="brdTl">공고 제목</option>
                  <option value="brdCntnt">이력서 제목</option>
                </select>
              </div>
              <button
                class="btn"
                type="button"
                aria-label="Search"
                @click="boardSearch"
              >
                <i
                  class="icons icon-magnifier header-nav-top-icon text-color-dark"
                ></i>
              </button>
            </div>
          </div>
          
        </div>

      </div>
            <div class="custom-select-1 ml-10">
                <select class="form-control form-select" v-model="select">
                  <option value="전체" selected >전체 공고</option>
                  <option value="진행중">진행중인 공고</option>
                  <option value="마감">마감된 공고</option>
                </select>
              </div>
    </div>
    <div class="mb-3">
        <span class="bg-white p-2 border" >지원완료 : {{  totalApplyCount }}</span>
        <span class="bg-white p-2 border">열 람 : 0</span>
        <span class="bg-white p-2 border">미열람 : 0</span>
        <span class="bg-white p-2 border">지원 취소 : 0</span>
    </div>
    
        <form @submit.prevent="submit">
            <input type="text" value="" id="userId" v-model="state.form.id" >
            <input type="submit" value="전송">
        </form>

        <div v-if="filteredApplyInfo.length > 0">
            <JoinCompanyCondition 
            v-for="item in paginatedApplyInfo"
            :key="item.apy_sq"
            :data="item" 
            />
        </div>
        <div v-else-if="filteredApplyInfo.length == 0">
          <h4>조회된 내용이 없습니다</h4>
        </div>
        <div v-if="filteredApplyInfo.length != 0">
          <ViewPaginate
          :pageCount="pageCount" 
          :currentPage="currentPage"
          @pageNum="handlePageChange"
          />
        </div>


    <!-- </div> -->
    <!-- 입사지원 현황 -->
</template>

<script setup>
import JoinCompanyCondition from '@/components/fo/user/mypage/JoinCompanyCondition.vue';
import ViewPaginate from '@/components/fo/user/common/ViewPaginate.vue';
import axios from "axios";
import { reactive, ref, computed } from "vue";

const state = reactive({
    form : {
        id:"",
    }
})

const applyInfo = ref([]); // 사용자 지원정보
const pageCount = ref(); // 보여줄 총 페이지 수
const currentPage = ref(1); // 현재 페이지

const select = ref('전체'); // 셀렉트박스 (전체, 진행중, 마감)
const itemsPerPage = 5; // 한 페이지에 보여줄 지원정보 5개

const totalApplyCount = ref(''); // 지원완료
// const applyCancel =ref(''); //지원 취소

const submit = () => { // 사용자 id 검색으로 정보가져오기
    axios.post("http://localhost:80/mypage/test",state.form).then(async (res) => {
        console.log(res.data);
        currentPage.value = 1;
        if(res.data === null || res.data.length === 0){
          alert("사용자 없음");
          applyInfo.value = [];
          totalApplyCount.value = '';
        }else{
          applyInfo.value = res.data.applyInfo;
          totalApplyCount.value = res.data.totalApplyCount;
          pageCount.value = Math.ceil(res.data.applyInfo.length / itemsPerPage);
        
        }
    }).catch((error) => {
        console.log(error);
       
    });
};

const filteredApplyInfo = computed(() =>{  // 공고상태 선택
  if(select.value === '진행중') {
    return applyInfo.value.filter(item => item.jbpCndtn === '진행중'); // item의 항목중 jbpCndtn이 '진행중'인것만 반환
  }else if (select.value === '마감') {
    return applyInfo.value.filter(item => item.jbpCndtn === '마감'); // item의 항목중 jbpCndtn이 '마감'인것만 반환
  }else{
    return applyInfo.value;
  }
});

const handlePageChange = (pageNum) => { // 클릭한 현재 페이지
  currentPage.value = pageNum;
}

// 페이징된 지원 정보 계산
const paginatedApplyInfo = computed(() => {
  const startIndex = (currentPage.value - 1)*itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  return filteredApplyInfo.value.slice(startIndex, endIndex);
});


</script>

<style scoped>
.main {
    padding: 20px 0;
}
.page-header {
    margin-bottom: 20px;
}
</style>