<template>
    <section id="examples" class="section section-default">
      <div class="container">
        <section class="card card-admin">
          <header class="card-header">
            <h2 class="card-title">공고 등록</h2>
          </header>
        </section>
  
        <div class="mb-3">
          <label for="jbpTl" class="form-label">제목</label>
          <input type="text" v-model="jbpTl" class="form-control" id="jbpTl" placeholder="제목을 입력하세요">
        </div>
  
        <div class="row">
          <div class="col-md-3 mb-3">
            <label for="jobName" class="form-label">직업명</label>
            <input type="text" v-model="jobName" class="form-control" id="jobName" placeholder="직업명을 입력하세요">
          </div>
          <div class="col-md-3 mb-3">
            <label for="cr" class="form-label">경력</label>
            <select v-model="cr" class="form-control" id="cr">
              <option value="801">경력무관</option>
              <option value="802">신입</option>
              <option value="803">경력</option>
            </select>
          </div>
          <div class="col-md-3 mb-3">
            <label for="edctn" class="form-label">학력</label>
            <select v-model="edctn" class="form-control" id="edctn">
              <option value="606">학력무관</option>
              <option value="605">고등학교 이상</option>
              <option value="604">대학교(2,3년제) 이상</option>
              <option value="603">대학교(4년제) 이상</option>
              <option value="602">석사</option>
              <option value="601">박사</option>
            </select>
          </div>
          <div class="col-md-3 mb-3">
            <label for="sklName" class="form-label">기술명</label>
            <input type="text" v-model="sklName" class="form-control" id="sklName" placeholder="기술명을 입력하세요">
          </div>
        </div>
  
        <div class="row">
    <div class="col-md-2 mb-3">
      <label for="workArea" class="form-label">근무지역</label>
      <input type="text" v-model="workArea" class="form-control" id="workArea" placeholder="근무지를 입력하세요">
    </div>
    <div class="col-md-2 mb-3">
      <label for="workForm" class="form-label">근무형태</label>
      <select v-model="workForm" class="form-control" id="workForm">
        <option value="정규직">정규직</option>
        <option value="계약직">계약직</option>
        <option value="인턴">인턴</option>
      </select>
    </div>
    <div class="col-md-3 mb-3">
      <label class="form-label">급여</label>
      <div class="input-group">
        <input type="text" v-model="slry" class="form-control" id="slry" placeholder="연봉" :disabled="interviewAgreement">
        <div class="input-group-text">
          <input type="checkbox" v-model="interviewAgreement"> 면접 후 협의
        </div>
      </div>
    </div>
    <div class="col-md-2 mb-3">
      <label for="workStartTime" class="form-label">출근 시간</label>
      <input type="time" v-model="workStartTime" class="form-control" id="workStartTime">
    </div>
    <div class="col-md-2 mb-3">
      <label for="workEndTime" class="form-label">퇴근 시간</label>
      <input type="time" v-model="workEndTime" class="form-control" id="workEndTime">
    </div>
  </div>
  
  
        <div class="mb-3">
          <label for="jbpCntnt" class="form-label">내용</label>
          <textarea class="form-control" v-model="jbpCntnt" id="jbpCntnt" rows="3" placeholder="내용을 입력하세요"></textarea>
        </div>
        <div class="row">
    <div class="col-md-6 mb-3">
      <label for="regstrStrtDtm" class="form-label">공고 시작일</label>
      <input type="date" v-model="regstrStrtDtm" class="form-control" id="regstrStrtDtm" :min="minRegstrStrtDtm">
    </div>
    <div class="col-md-6 mb-3">
      <label for="regstrDlnDtm" class="form-label">공고 마감일</label>
      <input type="date" v-model="regstrDlnDtm" class="form-control" id="regstrDlnDtm" :min="minRegstrStrtDtm">
    </div>
  </div>
  
  <div class="row">
    <div class="col-md-4 mb-3">
      <label for="picName" class="form-label">담당자 이름</label>
      <input type="text" v-model="picName" class="form-control" id="picName" placeholder="담당자 이름을 입력하세요">
    </div>
    <div class="col-md-4 mb-3">
      <label for="picMp" class="form-label">담당자 연락처</label>
      <input type="text" v-model="picMp" class="form-control" id="picMp" placeholder="담당자 연락처를 입력하세요">
    </div>
    <div class="col-md-4 mb-3">
      <label for="picEml" class="form-label">담당자 이메일</label>
      <input type="email" v-model="picEml" class="form-control" id="picEml" placeholder="담당자 이메일을 입력하세요">
    </div>
  </div>
  
  
  <div class="mb-3">
    <label for="jbpEndYn" class="form-label">마감 여부</label>
  <select v-model="jbpEndYn" class="form-control" id="jbpEndYn">
            <option value="Y">예</option>
            <option value="N">아니오</option>
          </select>
        </div>
        <div class="mb-3">
          <label for="jbpCndtn" class="form-label">공고 상태</label>
          <textarea class="form-control" v-model="jbpCndtn" id="jbpCndtn" rows="3" ></textarea>
        </div>
        <div>
          <button @click="submitPost">작성</button>
        </div>
      </div>
    </section>
  </template>
    
  <script setup>
  import { ref, computed } from 'vue';
  import { api } from '@/axios.js';
  import { useRouter } from 'vue-router';
  
  const jbpSq = ref(0); // jbpSq를 ref()로 선언
  const entrprsSq = ref(1);
  const jbpTl = ref('');
  const jbpCntnt = ref('');
  const cr = ref(801);
  const sklName = ref('');
  const jobName = ref('');
  const edctn = ref(606);
  const workArea = ref('');
  const workForm = ref('정규직');
  const slry = ref('');
  const workStartTime = ref('');
  const workEndTime = ref('');
  const workHour = ref('');
  const regstrStrtDtm = ref('');
  const regstrDlnDtm = ref('');
  const picName = ref('');
  const picMp = ref('');
  const picEml = ref('');
  const jbpEndYn = ref('N');
  const jbpCndtn = ref('');
  const insrtMbrSq = ref(1);
  const interviewAgreement = ref(false);
  
  const router = useRouter();
  
  // 오늘 날짜를 사용하여 최소 등록 시작일 계산
  const today = new Date();
  const minRegstrStrtDtm = computed(() => {
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  });
  
  // 게시물 작성 요청 처리
  const submitPost = () => {
    if (jbpTl.value.trim() === '' || jbpCntnt.value.trim() === '') {
      alert('제목과 내용을 확인하세요.');
      return;
    }
  
    api.$post('/board/jobPostingInsert', {
        jbpSq: jbpSq.value,
        enterpriseMember: {
          entrprsSq: entrprsSq.value,
        },
        jbpTl: jbpTl.value,
        jbpCntnt: jbpCntnt.value,
        cr: cr.value,
        sklName: sklName.value,
        jobName: jobName.value,
        edctn: edctn.value,
        workArea: workArea.value,
        workForm: workForm.value,
        slry: interviewAgreement.value ? '면접 후 협의' : slry.value,
        workHour: `${workStartTime.value} ~ ${workEndTime.value}`,
        regstrStrtDtm: regstrStrtDtm.value,
        regstrDlnDtm: regstrDlnDtm.value,
        picName: picName.value,
        picMp: picMp.value,
        picEml: picEml.value,
        jbpEndYn: jbpEndYn.value,
        jbpCndtn: jbpCndtn.value,
        insrtMbrSq: insrtMbrSq.value,
      })
      .then(response => {
        const { detailUrl, jbpSq: insertedJbpSq } = response; // 응답에서 값 추출
        jbpSq.value = insertedJbpSq; // jbpSq 업데이트
        console.log("글 번호: " + jbpSq.value);
        alert('공고가 등록되었습니다.');
        // 필요한 값 초기화
        clearForm();
        // 상세 페이지로 이동
        router.push(detailUrl);
      })
      .catch(error => {
        console.error('게시물 작성 오류:', error);
        alert('게시물 작성 중 오류가 발생했습니다. 다시 시도해주세요.');
      });
  };
  
  
  // 폼 초기화 함수
  const clearForm = () => {
    jbpTl.value = '';
    jbpCntnt.value = '';
    cr.value = 801;
    sklName.value = '';
    jobName.value = '';
    edctn.value = 606;
    workArea.value = '';
    workForm.value = '정규직';
    slry.value = '';
    workStartTime.value = '';
    workEndTime.value = '';
    workHour.value = '';
    regstrStrtDtm.value = '';
    regstrDlnDtm.value = '';
    picName.value = '';
    picMp.value = '';
    picEml.value = '';
    jbpEndYn.value = 'N';
    jbpCndtn.value = '';
    insrtMbrSq.value = 1;
    interviewAgreement.value = false;
  };
  </script>