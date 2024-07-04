<template>    
    <div class="container py-2 mb-3" style="background-color: #f4f4f4;">
        <div class="row align-items-center">
            <div class="col-12 pb-3 ">
                <div class="d-flex justify-content-between align-items-center mb-2">
                    <span class="badge text-white p-2"
                    :class="{'bg-primary':props.data.jbpCndtn == '진행중',
                        'bg-danger':props.data.jbpCndtn !=='진행중' 
                    }">{{ props.data.jbpCndtn }}</span> <!--공고상태-->
                    <span class="bg-white p-2 border" style="margin-left: auto;">지원자 수: {{  props.data.applyCount }}</span>
                </div>
                <h5 class="mb-2">{{ props.data.entrprsName }}</h5>
                <p class="mb-2">{{ props.data.jbpTl }}</p>
                <a href="#" class="popup-with-zoom-anim text-primary">지원한 이력서 | {{ props.data.rsmTl }}</a>
                <hr class="my-2">
                <div class="d-flex justify-content-between align-items-center pt-2">
                    <div class="d-flex align-items-center">
                        <p class="mb-0">지원일 | {{  props.data.apyDtm }}</p>
                        <div v-if="props.data.codeName == '열람'">
                            <span class="badge bg-white text-primary ms-2 p-2 border">열람</span>
                        </div>
                        <div v-else>
                            <span class="badge bg-white text-primary ms-2 p-2 border">미열람</span>
                        </div>
                    </div>
                    {{  props.data.apySq }}
                    {{  props.data.codeName }}
                    {{  applyCancel }}
                    <div v-if="applyCancel === '지원취소'">
                        <button class="btn btn-gray btn-sm border">취소완료</button>
                    </div>
                    <div v-else>
                        <button class="btn btn-danger btn-sm" @click="delapply">지원취소</button>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>

    
</template>

<script setup>
import axios from 'axios';
import { defineProps, ref } from 'vue';

const props = defineProps(['data'])

const applyCancel = ref(props.data.codeName); // 지원취소


const delapply = () => { // 지원취소
    console.log(props.data.apySq);
    axios.post("http://localhost:80/mypage/delApply",{apySq : props.data.apySq})
    .then(res => {
        console.log(res);
        applyCancel.value = '지원취소'
    })
    .catch((error) => {
        console.log(error);
    });

};


</script>

<style scoped>
.border-bottom {
    border-bottom: 1px solid #ddd;
}
.badge {
    font-size: 14px;
}
</style>