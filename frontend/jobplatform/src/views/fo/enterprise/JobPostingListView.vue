<template>
    <div>
      <section class="page-header page-header-modern bg-color-grey page-header-md">
        <div class="container">
          <div class="row">
            <div class="col-md-12 align-self-center p-static order-2 text-center">
              <h1 class="text-dark font-weight-bold text-8">일반게시판</h1>
              <span class="sub-title text-dark">자유로운 글 작성!</span>
            </div>
            <div class="col-md-12 align-self-center order-1">
              <ul class="breadcrumb d-block text-center">
                <li><a href="/">Home</a></li>
                <li class="active">일반게시판</li>
              </ul>
            </div>
          </div>
        </div>
      </section>
  
      <section class="card card-admin">
        <div class="container py-4">
          <div class="d-flex justify-content-end">
            <router-link to="/board/jobPostingInsert" class="btn btn-primary">등록</router-link>
          </div>
  
          <!-- 검색 옵션 UI -->
  
  
          <!-- 게시글 목록 -->
          <div class="list-container">
            <div class="list-header">
              <div class="search-options">
                <div class="dropdown-wrapper">
                  <button @click="toggleDropdown('region')">지역</button>
                  <div v-if="showDropdown.region" class="dropdown">
                    <label><input type="checkbox" v-model="selectedLocations" value="서울전체"> 서울전체</label>
                    <label><input type="checkbox" v-model="selectedLocations" value="서울시 용산구"> 서울시 용산구</label>
                    <!-- 필요한 만큼 추가 -->
                  </div>
                </div>
  
                <div class="dropdown-wrapper">
                  <button @click="toggleDropdown('career')">경력</button>
                  <div v-if="showDropdown.career" class="dropdown">
                    <label><input type="checkbox" v-model="selectedCareers" value="경력무관"> 경력무관</label>
                    <label><input type="checkbox" v-model="selectedCareers" value="신입"> 신입</label>
                    <label><input type="checkbox" v-model="selectedCareers" value="경력"> 경력</label>
                  </div>
                </div>
  
                <div class="dropdown-wrapper">
                  <button @click="toggleDropdown('education')">학력</button>
                  <div v-if="showDropdown.education" class="dropdown">
                    <label><input type="checkbox" v-model="selectedEducations" value="학력무관"> 학력무관</label>
                    <label><input type="checkbox" v-model="selectedEducations" value="고등학교 이상"> 고등학교 이상</label>
                    <label><input type="checkbox" v-model="selectedEducations" value="대학교(2,3년제) 이상"> 대학교(2,3년제) 이상</label>
                    <label><input type="checkbox" v-model="selectedEducations" value="대학교(4년제) 이상"> 대학교(4년제) 이상</label>
                    <label><input type="checkbox" v-model="selectedEducations" value="석사"> 석사</label>
                    <label><input type="checkbox" v-model="selectedEducations" value="박사"> 박사</label>
                    <!-- 필요한 만큼 추가 -->
                  </div>
                </div>
  
                <div class="dropdown-wrapper">
                  <button @click="toggleDropdown('jobType')">직종</button>
                  <div v-if="showDropdown.jobType" class="dropdown">
                    <label><input type="checkbox" v-model="selectedJobTypes" value="개발"> 개발</label>
                    <label><input type="checkbox" v-model="selectedJobTypes" value="마케팅"> 마케팅</label>
                    <!-- 필요한 만큼 추가 -->
                  </div>
                </div>
  
                <!-- <button @click="performSearchBySelectedOptions">검색</button> -->
              </div>
              <SearchComponent @searchResult="handleSearchResult" />
              <div>
                <select v-model="sortOption" @change="fetchSortedItems">
                  <option value="regstrStrtDtm">최신순</option>
                  <option value="apply">지원자순</option>
                  <option value="hits">조회수순</option>
                </select>
              </div>
            </div>
            <div class="list-body">
              <div v-if="paginatedItems.length > 0">
                <div v-for="(item, idx) in paginatedItems" :key="idx" class="custom">
                  <div><router-link :to="`/board/detail/jobPosting/${item.jbpSq}`">{{ item.jbpTl }}</router-link></div>
                  <div>{{ item.edctn }}</div>
                  <div>{{ item.cr }}</div>
                  <div>{{ item.jobName }}</div>
                </div>
              </div>
              <!-- 검색 결과가 없을 때 표시될 메시지 -->
              <div v-else class="text-center py-5">
                <p class="text-muted">결과가 없습니다.</p>
              </div>
            </div>
          </div>
          <div class="pagenation-wrapper">
            <BasePagination :currentPage="state.currentPage" :totalPages="totalPages" @goToPage="goToPage" />
          </div>
        </div>
      </section>
    </div>
  </template>
  
  <script setup>
  import { ref, reactive, computed, watch, onMounted } from 'vue';
  import { api } from '@/axios.js';
  import BasePagination from "@/components/common/BasePagination.vue";
  import SearchComponent from '@/components/fo/enterprise/SearchComponent.vue';
  
  const state = reactive({
    items: [],
    currentPage: 1,
    itemsPerPage: 12
  });
  
  const sortOption = ref('regstrStrtDtm');
  
  // 초기 데이터 로드
  const fetchItems = async () => {
    try {
      const response = await api.$get('/board/list/jobPosting', {
        params: { sortBy: sortOption.value }
      });
      state.items = response; 
    } catch (error) {
      console.error('Error fetching items:', error);
    }
  };
  
  // 컴포넌트가 마운트된 후 데이터 로드
  onMounted(() => {
    fetchItems();
  });
  
  const fetchSortedItems = () => {
    state.currentPage = 1; // 페이지를 첫 페이지로 초기화
    fetchItems();
  };
  
  // 페이지네이션 계산
  const paginatedItems = computed(() => {
    const startIndex = (state.currentPage - 1) * state.itemsPerPage;
    const endIndex = startIndex + state.itemsPerPage;
    return filteredItems.value.slice(startIndex, endIndex);
  });
  
  // 전체 페이지 수 계산
  const totalPages = computed(() => Math.ceil(filteredItems.value.length / state.itemsPerPage));
  
  // 선택된 옵션들
  const selectedLocations = ref([]);
  const selectedCareers = ref([]);
  const selectedEducations = ref([]);
  const selectedJobTypes = ref([]);
  
  // 드롭다운 메뉴 표시 상태
  const showDropdown = reactive({
    region: false,
    career: false,
    education: false,
    jobType: false
  });
  
  // 필터된 아이템들 계산
  const filteredItems = computed(() => {
    if (!state.items) return [];
    return state.items.filter(item => {
      let isValid = true;
  
      if (selectedLocations.value.length > 0 && !selectedLocations.value.includes(item.workArea)) {
        isValid = false;
      }
      if (selectedCareers.value.length > 0 && !selectedCareers.value.includes(item.cr)) {
        isValid = false;
      }
      if (selectedEducations.value.length > 0 && !selectedEducations.value.includes(item.edctn)) {
        isValid = false;
      }
      if (selectedJobTypes.value.length > 0 && !selectedJobTypes.value.includes(item.jobName)) {
        isValid = false;
      }
  
      return isValid;
    });
  });
  
  // 페이지 변경 함수
  function goToPage(page) {
    if (page >= 1 && page <= totalPages.value) {
      state.currentPage = page;
    }
  }
  
  // Watchers
  watch(selectedLocations, () => {
    state.currentPage = 1;
  });
  watch(selectedCareers, () => {
    state.currentPage = 1;
  });
  watch(selectedEducations, () => {
    state.currentPage = 1;
  });
  watch(selectedJobTypes, () => {
    state.currentPage = 1;
  });
  
  watch(sortOption, fetchSortedItems);
  
  // 검색 결과 처리 함수
  const handleSearchResult = (searchResults) => {
    state.items = searchResults;
    state.currentPage = 1;
  }
  
  // 드롭다운 토글 함수
  function toggleDropdown(type) {
    for (const key in showDropdown) {
      if (key === type) {
        showDropdown[key] = !showDropdown[key];
      } else {
        showDropdown[key] = false;
      }
    }
  }
  
  
  </script>
  
  <style scoped>
  .list-header {
    display: flex;
  }
  .list-header > div {
    margin-left: 50px;
  }
  .list-body {
    margin-top: 50px;
  }
  .custom {
    background-color: #ffffff;
    border-radius: 0.5rem;
    padding: 1rem;
    margin: 15px;
    width: calc(25% - 30px); /* 4개의 아이템이 한 줄에 나타나도록 설정 */
    border: 1px solid #002C7B;
    text-align: center;
    float: left;
  }
  .row {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  
  .search-options {
    margin-top: 20px;
    display: flex;
    align-items: center;
  }
  
  .dropdown-wrapper {
    position: relative; /* 부모 요소에 대해 위치를 상대적으로 설정 */
  }
  
  .search-options button {
    padding: 8px 16px;
    font-size: 16px;
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-right: 10px;
  }
  
  .search-options button:hover {
    background-color: #2980b9;
  }
  
  .dropdown {
    position: absolute;
    top: 100%; /* 버튼 바로 아래에 위치하도록 설정 */
    left: 0;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
    margin-top: 5px;
  }
  
  .dropdown label {
    display: block;
    padding: 10px;
  }
  
  .dropdown label:hover {
    background-color: #f1f1f1;
  }
  
  
  
  </style>
  