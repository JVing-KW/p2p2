<template>
    <ul class="pagination float-end">
      <li class="page-item" v-for="page in totalPages" :key="page" :class="{ active: page === currentPage }">
        <a class="page-link" href="#" @click.prevent="goToPage(page)">{{ page }}</a>
      </li>
    </ul>
  </template>
  
  <script>
  
  /* eslint-disable */
  export default {
    props: {
      currentPage: {
        type: Number,
        required: true
      },
      totalPages: {
        type: Number,
        required: true
      },
      goToPage: {
        type: Function,
        required: true
      }
    },
    methods: {
      goToPage(page) {
        this.$emit('goToPage', page);
      }
    }
  };
  </script>
  
  <style scoped>
  
   
  </style>
  