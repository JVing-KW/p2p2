<template>
    <div class="search-area">
      <input type="search" v-model="searchTerm" placeholder="검색어를 입력하세요" :class="{ 'shake': searchTermError }">
      <select v-model="searchField">
        <option value="jbpTl_jbpCntnt">제목+내용</option>
        <option value="jbpTl">제목</option>
        <option value="jbpCntnt">내용</option>
      </select>
      <button @click="performSearch">검색</button>
      <p v-if="searchTermError" class="error-message">검색어를 입력해주세요.</p>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { api } from '@/axios.js';
  /* eslint-disable */
  // emits 선언
  const emit = defineEmits(['searchResult']);
  
  const searchTerm = ref('');
  const searchField = ref('jbpTl_jbpCntnt');
  const searchTermError = ref(false); // 검색어 입력 오류 여부를 저장할 변수
  
  const performSearch = () => {
    if (searchTerm.value === '') {
      searchTermError.value = true; // 검색어가 비어 있으면 에러 표시
      return; // 검색하지 않음
    } else {
      searchTermError.value = false; // 검색어가 있으면 오류 메시지 숨기기
    }
  
    api.$get('/board/search', {
      params: {
        searchTerm: searchTerm.value,
        searchField: searchField.value
      }
    }).then(response => {
      console.log('Search response:', response);
      emit('searchResult', response);
    }).catch(error => {
      console.error('Error searching:', error);
    });
  }
  </script>
  
  <style scoped>
  .search-area {
    margin-bottom: 20px;
  }
  
  .error-message {
    color: red;
    margin-top: 5px;
  }
  
  .shake {
    animation: shake 0.5s ease;
  }
  
  @keyframes shake {
    0%, 100% { transform: translateX(0); }
    10%, 30%, 50%, 70%, 90% { transform: translateX(-10px); }
    20%, 40%, 60%, 80% { transform: translateX(10px); }
  }
  </style>
  