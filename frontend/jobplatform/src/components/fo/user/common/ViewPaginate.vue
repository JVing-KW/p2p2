<template>
    <div>
        <div>
            현재 선택된 페이지 : {{ props.currentPage}}
            페이지 수 : {{ props.pageCount }}
            <VuePaginate
                    :page-count="props.pageCount"
                    :page-range="3"
                    :margin-pages="2"
                    :click-handler="changePage" 
                    :prev-text="'<<'"
                    :next-text="'>>'"
                    :container-class="'pagination'"
                    :page-class="'page-item'">
            </VuePaginate>
        </div>
    </div>
  </template>
  
  <script setup>

  import { defineProps , defineEmits } from 'vue';
  import VuePaginate from 'vuejs-paginate-next';

  const emit = defineEmits(['pageNum']);

const changePage = (pageNum) => {
  emit('pageNum', pageNum);
  console.log(pageNum);
}


const props = defineProps(['pageCount','currentPage']);

  </script>
  
  <style>
 
  </style>