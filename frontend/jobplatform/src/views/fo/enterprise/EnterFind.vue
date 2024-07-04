<template>
    <div role="main" class="main">

        <section class="page-header page-header-modern bg-color-grey page-header-lg">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 align-self-center p-static order-2 text-center">
                        <h1 class="font-weight-bold text-dark">아이디, 비밀번호 찾기</h1>
                    </div>
                </div>
            </div>
        </section>

        <div id="examples" class="container py-2">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <div class="tabs">
                        <ul class="nav nav-tabs nav-justified flex-column flex-md-row">
                            <li class="nav-item">
                                <a class="nav-link active text-center" href="#popular10" data-bs-toggle="tab">아이디 찾기</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-center" href="#recent10" data-bs-toggle="tab">비밀번호 찾기</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div id="popular10" class="tab-pane active">
                                <form @submit.prevent="submitForm('findId')" id="findId">
                                    <div class="row">
                                        <div class="form-group col">
                                            <label class="form-label text-color-dark text-3">이름 <span class="text-color-danger">*</span></label>
                                            <input type="text" v-model="mbrName" class="form-control form-control-lg text-4" required>
                                            <div v-if="errorMbrName" class="error">{{ errorMbrName }}</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-9">
                                            <label class="form-label text-color-dark text-3">이메일 <span class="text-color-danger">*</span></label>
                                            <input type="email" v-model="mbrEmlAdrs" class="form-control form-control-lg text-4" required>
                                            <div v-if="errorMbrEmlAdrs" class="error">{{ errorMbrEmlAdrs }}</div>
                                        </div>
                                        <div class="form-group col-3">
                                            <div class="form-label text-color-dark text-3">&nbsp;</div>
                                            <button type="button" v-on:click="emlSend" class="btn btn-quaternary mb-2">인증요청</button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-9">
                                            <label class="form-label text-color-dark text-3">인증 코드 <span class="text-color-danger">*</span></label>
                                            <input type="text" v-model="emailKey" @input="updateEmailCheck" class="form-control form-control-lg text-4" required>
                                        </div>
                                        <div class="form-group col-3">
                                            <div class="form-label text-color-dark text-3">&nbsp;</div>
                                            <button type="button" v-on:click="emlChck" class="btn btn-quaternary mb-2">확인</button>
                                        </div>
                                        <div v-if="errorMbrEmlChck" class="error">{{ errorMbrEmlChck }}</div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col">
                                            <button type="submit" class="btn btn-dark btn-modern w-100 text-uppercase rounded-0 font-weight-bold text-3 py-3" data-loading-text="Loading...">아이디 조회</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div id="recent10" class="tab-pane">
                                <form @submit.prevent="submitForm('findPw')" id="findPw">
                                    <div class="row">
                                        <div class="form-group col">
                                            <label class="form-label text-color-dark text-3">아이디 <span class="text-color-danger">*</span></label>
                                            <input type="text" v-model="mbrId" class="form-control form-control-lg text-4" required placeholder="영문, 숫자를 포함한 4자 이상 20자 이내">
                                            <div v-if="errorMbrId" class="error">{{ errorMbrId }}</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col">
                                            <label class="form-label text-color-dark text-3">이름 <span class="text-color-danger">*</span></label>
                                            <input type="text" v-model="mbrName" class="form-control form-control-lg text-4" required>
                                            <div v-if="errorMbrName" class="error">{{ errorMbrName }}</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-9">
                                            <label class="form-label text-color-dark text-3">이메일 <span class="text-color-danger">*</span></label>
                                            <input type="email" v-model="mbrEmlAdrs" class="form-control form-control-lg text-4" required>
                                            <div v-if="errorMbrEmlAdrs" class="error">{{ errorMbrEmlAdrs }}</div>
                                        </div>
                                        <div class="form-group col-3">
                                            <div class="form-label text-color-dark text-3">&nbsp;</div>
                                            <button type="button" v-on:click="emlSend" class="btn btn-quaternary mb-2">인증요청</button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-9">
                                            <label class="form-label text-color-dark text-3">인증 코드 <span class="text-color-danger">*</span></label>
                                            <input type="text" v-model="emailKey" @input="updateEmailCheck" class="form-control form-control-lg text-4" required>
                                        </div>
                                        <div class="form-group col-3">
                                            <div class="form-label text-color-dark text-3">&nbsp;</div>
                                            <button type="button" v-on:click="emlChck" class="btn btn-quaternary mb-2">확인</button>
                                        </div>
                                        <div v-if="errorMbrEmlChck" class="error">{{ errorMbrEmlChck }}</div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col">
                                            <button type="submit" class="btn btn-dark btn-modern w-100 text-uppercase rounded-0 font-weight-bold text-3 py-3" data-loading-text="Loading...">비밀번호 조회</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { sendEmlFind, findMbrId, findMbrPswrd } from '@/api/member';

const mbrId = ref('');
const mbrName = ref('');
const mbrEmlAdrs = ref('');

const emailKey = ref('');
const emailCode = ref('');

const errorMbrName = ref('');
const errorMbrEmlAdrs = ref('');
const errorMbrEmlChck = ref('');

const router = useRouter();

const emlSend = async () => {
    emailCode.value = '';

    try {
        const response = await sendEmlFind(mbrEmlAdrs.value);
        if (!/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/.test(mbrEmlAdrs.value)) {
            errorMbrEmlAdrs.value = '이메일 형식이 올바르지 않습니다.';
        } else if (response.data.exist) {
            alert(response.data.exist);
        } else if (response.status === 200) {
            alert('인증코드가 발송되었습니다.');
            const key = response.data.key;
            alert(key);
            emailCode.value = key;
        } else {
            alert('잘못된 이메일입니다');
        }
    } catch (error) {
        console.error('이메일 전송 중 오류 발생:', error);
        alert('이메일 전송 중 오류가 발생했습니다.');
    }
};

const updateEmailCheck = (event) => {
    emailKey.value = event.target.value;
};

const emlChck = () => {
    if (emailCode.value === emailKey.value) {
        alert('인증완료');
    } else {
        alert('인증코드가 일치하지 않습니다.');
    }
};

const submitForm = async (formId) => {
    errorMbrName.value = '';
    errorMbrEmlAdrs.value = '';
    errorMbrEmlChck.value = '';

    // 유효성 검사
    if (!/^[A-Za-z가-힣]{1,6}$/.test(mbrName.value)) {
        errorMbrName.value = '이름이 올바르지 않습니다.';
        return; // 폼 제출 중단
    }
    if (!/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/.test(mbrEmlAdrs.value)) {
        errorMbrEmlAdrs.value = '이메일 형식이 올바르지 않습니다.';
        return; // 폼 제출 중단
    }
    if (emailCode.value !== emailKey.value) {
        errorMbrEmlChck.value = '인증코드가 일치하지 않습니다.';
        return; // 폼 제출 중단
    }

    const memberData = {
        mbrName: mbrName.value,
        mbrEmlAdrs: mbrEmlAdrs.value,
    };
    if (formId === 'findPw') {
        memberData.mbrId = mbrId.value;
    }

    try {
        if (formId === 'findId') {
            const response = await findMbrId(memberData);
            handleFormSubmitResponse(response);
        } else if (formId === 'findPw') {
            const response = await findMbrPswrd(memberData);
            handleFormSubmitResponse(response);
        }
    } catch (error) {
        alert('Failed to find member: ' + error.message);
    }
};

const handleFormSubmitResponse = (response) => {
    if (response.status === 200) {
        alert(response.data);
        router.push('/member/login');
    } else {
        alert(response.data);
    }
};

</script>

<style scoped>
    .error {
        color: red;
    }
</style>