import { createStore } from 'vuex';

const store = createStore({
  state: {
    member: JSON.parse(sessionStorage.getItem("member")) || null,// 사용자 정보 저장할 상태

    enterMember: [] // 배열 형태로 저장
  },
  //commit(함수명,인자)
  mutations: {
    setEnter(state, payLoad) {
      state.enterMember = payLoad;

    },
    setMember(state, payLoad) {
      state.member = payLoad;
      sessionStorage.setItem("member", JSON.stringify(payLoad)); 
    },
    clearMember(state) {
      state.member = null;
    },
    updateMemberInfo(state, updatedInfo) {
      state.member = { ...state.member, ...updatedInfo };
      sessionStorage.setItem("member", JSON.stringify(state.member));
    }
  },
  getters: {
    isLoginMember(state) {
      return state.member !== null; 

    }, isLoginEnter(state) {
      return state.enterMember !== null;

    }
    ,
    getMember(state) {
      return state.member; // 사용자 정보 반환
    },
    enterMember(state) {
      return state.member; // 사용자 정보 반환
    }
  }
})
export default store;