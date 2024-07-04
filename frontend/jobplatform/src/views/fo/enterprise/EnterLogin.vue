<template>
    <section class="page-header page-header-modern bg-color-grey page-header-lg">
      <div class="container">
        <div class="row">
          <div class="col-md-12 align-self-center p-static order-2 text-center">
            <h1 class="font-weight-bold text-dark">기업회원 로그인</h1>
          </div>
        </div>
      </div>
    </section>
  
    <section class="loginSection">
      <div class="card mb-4">
        <div class="card-body">
          <div class="row">
            <div class="col">
              <div class="contact-form form-style-3">
                <div class="contact-form-success alert alert-success d-none mt-4">
                  <strong>Success!</strong> Your message has been sent to us.
                </div>
                <div class="contact-form-error alert alert-danger d-none mt-4">
                  <strong>Error!</strong>There was an error sending your
                  message.<span class="mail-error-message text-1 d-block"></span>
                </div>
  
                <!-- 로그인 폼 시작-->
                <form @submit.prevent="LoginSubmit">
                  <div class="row">
                    <div class="form-group col">
                      <label class="form-label mb-1 text-2">아이디</label>
                      <input
                        type="text"
                        data-msg-required="Please enter the subject."
                        maxlength="100"
                        class="form-control text-3 h-auto py-2"
                        name="id"
                        v-model="entrprsId"
                        placeholder="아이디를 입력해주세요"
                        autoComplete="off"
                        @focus="handleFocus"
                        @blur="handleBlur"
                      />
                      <!-- @focus 클릭했을 때 실행 @blur 클릭이 끝났을 때 실행 -->
                    </div>
                  </div>
                  <div class="row">
                    <div class="form-group col">
                      <label class="form-label mb-1 text-2">비밀번호</label>
                      <input
                        type="password"
                        value=""
                        data-msg-required="Please enter the subject."
                        maxlength="100"
                        class="form-control text-3 h-auto py-2"
                        placeholder="비밀번호를 입력해주세요"
                        autoComplete="off"
                        v-model="entrprsPswrd"
                        @focus="handleFocus"
                        @blur="handleBlur"
                      />
                    </div>
                  </div>
                  <div class="d-flex justify-content-center">
                    <input
                      type="submit"
                      value="로그인"
                      class="btn btn-primary w-100 mb-3"
                      data-loading-text="Loading..."
                    />
                  </div>
                </form>
                <!-- 로그인 폼 끝-->
  
                <ul
                  class="d-flex justify-content-between nav nav-pills"
                  id="mainNav"
                >
                  <li class="dropdown pe-5">
                    <router-link to="/enter/register" class="dropdown-item">
                      회원가입</router-link
                    >
                  </li>
                  <li>
                    <router-link to="/enter/find" class="dropdown-item"
                      >아이디, 비밀번호 찾기</router-link
                    >
                  </li>
                </ul>
                <hr />
                <div class="align-self-center">
                  <ul
                    class="d-flex justify-content-center nav nav-pills"
                    id="mainNav"
                  >
                    <li class="dropdown">
                      <a href="#" @click="naverLogin" class="dropdown-item"
                        ><img
                          :src="require('@/assets/naverlogo/btnG_아이콘원형.png')"
                          class="socialLogo"
                      /></a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- <MemberLoginNCallback /> -->
  </template>
  
  <script setup>
  import axios from "axios";
  import {  ref } from "vue";
  import { useRouter } from "vue-router";
  import { useCookies } from "vue3-cookies";
  import store from "@/store";

  const {cookies } = useCookies();

  const router = useRouter();
 
  
  const entrprsId = ref("");
  const entrprsPswrd = ref("");

  const cookieValue = ref("");

  
  const handleFocus = (event) => {
    event.target.placeholder = "";
  };
  
  const handleBlur = (event) => {
    event.target.placeholder =
      event.target.name === "id"
        ? "아이디를 입력해주세요"
        : "비밀번호를 입력해주세요";
  };
  
  const LoginSubmit = async () => {

    const loginData = {

      entrprsId: entrprsId.value,
      entrprsPswrd: entrprsPswrd.value,

    };

     axios
       .post("http://localhost:80/enter/login", loginData, {
         withCredentials: true,
       })
      
       .then(async(response) => {

     
        store.commit("setEnter", response.data); // store에 기업 정보 저장(pk, id)
 
  
       await router.push('/');
      })
       .catch();
      
  };
  
  
  
  // https://henniee.tistory.com/m/238 링크 참조
  const client_id = "TIwA7WnbAvnjEwnbPGZm"; // 본인 또는 회사 아이디로 교체
  const redirect_uri = "http://localhost:8080/member/loginNCallback";
  
  // state 난수 생성
  const generateRandomState = () => {
    const characters =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    let state = "";
    for (let i = 0; i < 16; i++) {
      const randomIndex = Math.floor(Math.random() * characters.length);
      state += characters.charAt(randomIndex);
    }
    return state;
  };
  

  const naverLogin = async () => {
    const state = generateRandomState();
    const apiURL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${client_id}&redirect_uri=${redirect_uri}&state=${state}`;
   
    window.location.href = apiURL;
  };
  </script>
  
  <style>
  .loginSection {

    display: flex;
    justify-content: center;
    align-items: center;
  }
  .socialLogo {
    max-width: 45px;
    height: auto;
  }
  </style>