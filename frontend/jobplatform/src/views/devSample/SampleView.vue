<template>
    <div>
      <h4>회원가입</h4>
      <form @submit.prevent="postTest">
        <!-- 데이터 바인딩 -->
        <input type="text" v-model="name" placeholder="이름" />
        <input type="text" v-model="password" placeholder="비밀번호" />
        <button type="submit">회원가입</button>
      </form>
    </div>

    <h4>조회</h4>

    <form @submit.prevent="getTest">
      <input type="text" v-model="id" placeholder="번호로 조회" />
      <button type="submit">조회</button>
      <p>{{ responseId }}</p>
      <p>{{ responsePassword }}</p>
    </form>

    <h4>삭제 테스트</h4>
    <form @submit.prevent="deleteTest">
      <input type="text" v-model="deleteId" placeholder="번호로 삭제" />
      <button type="submit">조회</button>
    </form>
    <h4>번호로 조회 후 수정 확인해보기</h4>

    <!--
    <form @submit.prevent="postTest">
      
      <input type="text" v-model="name" />
      <input type="text" v-model="password" />
      <button type="submit">회원가입</button>
    </form> -->
  </template>

  <!-- composition api-->
  <script setup>
  import { ref } from "vue";
  import axios from "axios";

  const id = ref();
  const deleteId = ref();
  const name = ref("");
  const password = ref("");

  const responseId = ref("");
  const responsePassword = ref("");

  // const getTest = axios.get("/sample/sample").then(console.log("get")).catch();

  const postTest = () => {
    axios
      .post("http://localhost:80/sample/sample", {
        name: name.value,
        password: password.value,
      })
      .then((response) => {
        alert(response.data.id);
      })
      .catch();
  };
  const getTest = () => {
    axios
      .get("http://localhost:80/sample/sample", {
        params: {
          id: id.value,
        },
      })
      .then((response) => {
        alert(
          "Id : " + response.data.name + " password :" + response.data.password
        );
      })
      .catch();
  };

  const deleteTest = () => {
    axios
      .delete("http://localhost:80/sample/sample", {
        params: {
          id: deleteId.value,
        },
      })
      .then((response) => {
        alert(
          "Id : " +
            response.data.name +
            " password :" +
            response.data.password +
            "삭제됨"
        );
      })
      .catch();
  };

  </script>
  <style>
  </style>