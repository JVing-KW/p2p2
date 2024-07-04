import axios from 'axios';

const instance = axios.create({
   baseURL: 'http://localhost:80',
});

// 회원가입
function registerMember(memberData) {
   return instance.post('/member/register', memberData);
}

// 아이디 찾기
function findMbrId(memberData) {
   return instance.post('/member/findId', memberData);
}

// 비밀번호 찾기
function findMbrPswrd(memberData) {
   return instance.post('/member/findPswrd', memberData);
}

// 인증코드 이메일 전송 (회원가입)
function sendEmlRegister(mbrEmlAdrs) {
   return instance.post('/member/emlRegister', { mbrEmlAdrs });
}

// 인증코드 이메일 전송 (아이디, 비밀번호 찾기)
function sendEmlFind(mbrEmlAdrs) {
   return instance.post('/member/emlFind', { mbrEmlAdrs });
}

// 약관 가져오기
function getTermsContents() {
   return instance.get('/terms/content');
}

export { registerMember, findMbrId, findMbrPswrd, sendEmlRegister, sendEmlFind, getTermsContents };