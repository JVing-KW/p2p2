<template>
  <CommonHeader/>
  <div role="main" class="main">
    <router-view/>
  </div>
  <CommonFooter/>
</template>

<script setup>
  import CommonHeader from "@/components/fo/user/CommonHeader.vue";
  import CommonFooter from "@/components/fo/user/CommonFooter.vue";
</script>

