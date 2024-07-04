<template>
    <section id="examples" class="section section-default">
      <div class="container">
        <section class="card card-admin">
          <header class="card-header">
            <div class="card-actions">
              <a href="#" class="card-action card-action-toggle" data-card-toggle=""></a>
              <a href="#" class="card-action card-action-dismiss" data-card-dismiss=""></a>
            </div>
            <h2 class="card-title">공고</h2>
          </header>
        </section>
  
        <div v-if="loading" class="text-center">Loading...</div>
        <div v-else>
          <!-- 첫 번째 줄 -->
          <div class="row mt-3">
            <div class="col-md-3">
              <div class="mb-3">
                <label for="jbpTl" class="form-label">제목</label>
                <input type="text" v-model="jbp.jbpTl" class="form-control" id="jbpTl" placeholder="제목" readonly />
              </div>
            </div>
            <div class="col-md-3">
              <div class="mb-3">
                <label for="enterpriseName" class="form-label">기업 이름</label>
                <input type="text" v-model="jbp.enterpriseName" class="form-control" id="enterpriseName" placeholder="기업 이름" readonly />
              </div>
            </div>
            <div class="col-md-2">
              <div class="mb-3">
                <label class="form-label">조회수: {{ jbp.hits }}</label>
              </div>
            </div>
            <div class="col-md-4">
              <div class="mb-3">
                <label class="form-label">등록일: {{ formatDate(jbp.regstrStrtDtm) }}</label>
              </div>
            </div>
          </div>
  
          <!-- 두 번째 줄 -->
          <div class="row mt-3">
            <div class="col-md-4">
              <div class="mb-3">
                <label for="cr" class="form-label">경력</label>
                <input type="text" v-model="jbp.cr" class="form-control" id="cr" placeholder="경력" readonly />
              </div>
            </div>
            <div class="col-md-4">
              <div class="mb-3">
                <label for="edctn" class="form-label">학력</label>
                <input type="text" v-model="jbp.edctn" class="form-control" id="edctn" placeholder="학력" readonly />
              </div>
            </div>
            <div class="col-md-4">
              <div class="mb-3">
                <label for="workArea" class="form-label">근무지역</label>
                <input type="text" v-model="jbp.workArea" class="form-control" id="workArea" placeholder="근무지역" readonly />
              </div>
            </div>
          </div>
  
          <!-- 세 번째 줄 -->
          <div class="row mt-3">
            <div class="col-md-4">
              <div class="mb-3">
                <label for="workForm" class="form-label">근무형태</label>
                <input type="text" v-model="jbp.workForm" class="form-control" id="workForm" placeholder="근무형태" readonly />
              </div>
            </div>
            <div class="col-md-4">
              <div class="mb-3">
                <label for="slry" class="form-label">급여</label>
                <input type="text" v-model="jbp.slry" class="form-control" id="slry" placeholder="급여" readonly />
              </div>
            </div>
          </div>
          <div class="col-md-12">
            <div class="mb-1">
              <label for="jbpCntnt" class="form-label">공고 내용</label>
              <textarea v-model="jbp.jbpCntnt" class="form-control" id="jbpCntnt" placeholder="공고 내용" readonly rows="5"></textarea>
            </div>
          </div>
  
          <!-- 수정, 삭제 버튼 -->
          <div class="row mt-3">
            <div class="col d-flex justify-content-end">
              <button class="btn btn-success" @click="goUpdatePage">수정</button>
              <button class="btn btn-danger ms-2" @click="confirmDelete">삭제</button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { api } from '@/axios.js';
  import { useRoute, useRouter } from 'vue-router';
  
  const route = useRoute();
  const router = useRouter();
  
  const loading = ref(true);
  const jbp = ref({
    jbpSq: 0,
    enterpriseName: "",
    jbpTl: "",
    hits: 0,
    cr: "",
    edctn: "",
    workArea: "",
    workForm: "",
    slry: "",
    jbpCntnt: "", // 공고 내용 필드
    regstrStrtDtm: ""
  });
  
  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  };
  
  const fetchJobPostingDetail = async () => {
    const jbpSq = route.params.jbpSq;
  
    try {
      const response = await api.$get(`/board/detail/jobPosting/${jbpSq}`);
      jbp.value = response;
    } catch (error) {
      console.error('Error fetching job posting detail:', error);
    } finally {
      loading.value = false;
    }
  };
  
  onMounted(() => {
    fetchJobPostingDetail();
  });
  
  const goUpdatePage = () => {
    router.push(`/board/jobPostingUpdate/${jbp.value.jbpSq}`);
  };
  
  const confirmDelete = () => {
    if (window.confirm('게시글을 삭제 하시겠습니까?')) {
      deleteBoard();
      alert('게시글이 삭제되었습니다.');
    }
  };
  
  const deleteBoard = async () => {
    try {
      await api.$delete(`/board/jobPostingDelete/${jbp.value.jbpSq}`);
      console.log('게시글이 삭제 되었습니다');
      router.push('/board/list/jobPosting');
    } catch (error) {
      console.error('게시글 삭제가 실패 되었습니다:', error);
    }
  };
  </script>
  
  <style scoped>
  /* 추가적인 스타일 정의 */
  </style>
  