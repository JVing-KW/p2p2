<template>
    <div v-if="result">
        <div class="row">
            <div class="col-6">
                <h4 style=" margin-bottom: 0; font-size: 23px; font-weight: bolder;">
                    포지션 제안 받기
                </h4>
            </div>
            <div class="col-6" style="display : flex">
                <input type="checkbox" style="margin-left:auto; zoom:1.7;">
            </div>
        </div>
        <div class="row" style="margin-top: 35px;">
            <div class="col-6" style="padding-top: 7px;">
                <h4 style=" margin-bottom: 0;">
                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-arrow-right-square-fill" viewBox="0 0 16 16">
                        <path d="M0 14a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2zm4.5-6.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5a.5.5 0 0 1 0-1"/>
                    </svg>
                    &nbsp;&nbsp;대표이력서
                </h4>
            </div>
            <div class="col-6" style="display : flex; padding:0">
                <button class="btn btn-outline btn-rounded btn-primary mb-2" style="margin-left:auto; font-size: medium; padding: 2px 10px;">변경</button>
            </div>
        </div>
        <div class="row" style="margin-top: 10px; border: 1px solid #eaeaea; padding: 20px 10px;">
            <div class="col-3" style="display: flex; align-items: center; justify-items: center;">
                <div class="thumb-info-side-image-wrapper">
                    <img src="@/assets/profile.webp" class="img-fluid" alt="" style="width: 100px;">
                </div>
                <!-- 나중에 야돈대신 들어갈 자리 :src="{{result.rsmInfo.rsm_img_file_url}}" -->
            </div>
            <div class="col-9 table-container" >
                <table class="table">
                    <thead>
                        <tr>
                            <th colspan="3">
                                <h2 class="font-weight-bold" style="margin-bottom: 10px;">대표이력서 이름</h2>
                            </th>
                        </tr>
                    </thead>
                    <tbody style="text-align: center;">
                        <tr>
                            <th>
                                웅뇽뇽
                            </th>
                            <th>
                                웅뇽뇽
                            </th>
                            <th>
                                웅뇽뇽
                            </th>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row" style="margin-top: 25px;">
            <div class="col">
                <h4 style=" margin-bottom: 0;">
                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-arrow-right-square-fill" viewBox="0 0 16 16">
                        <path d="M0 14a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2zm4.5-6.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5a.5.5 0 0 1 0-1"/>
                    </svg>
                    &nbsp;&nbsp;제안 받을 근무지역 선택
                </h4>
            </div>
        </div>
        <div class="row" style="margin-top: 20px; border: 1px solid #eaeaea; padding: 15px;">
            <div class="col-3" style="height: 250px; border-right: 1px solid #eaeaea; overflow-y: auto">
                <ul v-for="(parentArea, i) in result.areaList" :key="i">
                    <li v-if="parentArea.area_sq == parentArea.prnt_area_sq" @click="chooseParentArea(parentArea.area_sq)">{{parentArea.area_name.split("전체").join('')}}</li>
                </ul>
            </div>
            <div class="col-9" style="height: 250px; overflow-y: auto;">
                <div v-if="parentAreaCode != 0">
                    <div v-for="(area, i) in result.areaList" :key="i">
                        <span><input type="checkbox" :id="area.area_sq" :value="area.area_sq"><label :for="area_sq">{{area.area_name}}</label></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="row"></div>
    </div>
</template>

<script setup>
    import { ref, onMounted } from 'vue';
    import { api } from '@/axios.js';

    let result = ref(null);
    let parentAreaCode = ref(0);

    onMounted(async() => {
        result.value = await api.$get("/user/mypage/ppAcception", {
                params: {
                    mbr_sq : 1
                }
            });
    })

    function chooseParentArea(area_sq){
        parentAreaCode.value = area_sq;
    }

</script>