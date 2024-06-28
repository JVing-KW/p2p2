<template>
    <div v-if="result">
        <div class="row" style="padding-bottom: 20px; border-bottom: 1px solid #eaeaea;">
            <div class="col-3">
                <div class="thumb-info-side-image-wrapper">
                    <img src="@/assets/profile.webp" class="img-fluid" alt="" style="width: 180px;">
                </div>
                <!-- 나중에 야돈대신 들어갈 자리 :src="{{result.rsmInfo.rsm_img_file_url}}" -->
            </div>
            <div class="col-9 table-container" >
                <table class="table table-bordered" style="margin-bottom: 0">
                    <thead>
                        <tr>
                            <th colspan="3">
                                <h2 class="font-weight-bold" style="margin-bottom: 10px;">{{result.rsmInfo.rsm_tl}}</h2>
                                <span>-최종 수정일자&nbsp;&nbsp;:&nbsp;&nbsp;{{ formatDateYMD(result.rsmInfo.insrt_dtm) }}</span><span style="margin-left: 20px;">-포지션 수락 여부&nbsp;&nbsp;:&nbsp;&nbsp;{{ result.mbrInfo.pstn_prpsl_accept_yn = 'Y'? '수락':'거절'}}</span>
                            </th>
                        </tr>
                    </thead>
                    <tbody style="text-align: center;">
                        <tr>
                            <th>
                                {{ result.mbrInfo.mbr_name }}
                            </th>
                            <th>
                                {{ result.mbrInfo.mbr_mp }}
                            </th>
                            <th>
                                {{ result.mbrInfo.mbr_eml_adrs }}
                            </th>
                        </tr>
                        <tr>
                            <td>
                                tbd
                            </td>
                            <td>
                                tbd
                            </td>
                            <td>
                                tbd
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row" style="padding-top:20px">
            <table class="table table-bordered" style="text-align: center;">
                <tr>
                    <th>등록한 이력서&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{result.myState.rsm_cnt}}</th>
                    <th>추천 공고&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(로직 준비중)</th>
                    <th>스크랩 공고&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{result.myState.scrap_cnt}}</th>
                    <th>받은 제안&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{result.myState.prpsl_cnt}}</th>
                </tr>
            </table>
        </div>
        <div class="row" style="padding-top:20px; border-bottom: 1px solid #eaeaea;">
            <div class="col pb-3">
                <table class="table table-bordered table-apply-state">
                    <thead>
                        <tr>
                            <th rowspan="2">
                                전체
                            </th>
                            <th colspan="2">
                                진행중
                            </th>
                            <th colspan="2">
                                면접
                            </th>
                            <th rowspan="2">
                                불합격
                            </th>
                            <th rowspan="2">
                                합격
                            </th>
                            <th rowspan="2">
                                마감
                            </th>
                        </tr>
                        <tr>
                            <th>열람</th>
                            <th>미열람</th>
                            <th>대기</th>
                            <th>완료</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                {{ applyStateSum }}
                            </td>
                            <td>
                                {{ result.EachCndtnApplyCount.cndtn_402 || 0 }}
                            </td>
                            <td>
                                {{ result.EachCndtnApplyCount.cndtn_401 || 0 }}
                            </td>
                            <td>
                                {{ result.EachCndtnApplyCount.cndtn_405 || 0 }}
                            </td>
                            <td>
                                {{ result.EachCndtnApplyCount.cndtn_406 || 0 }}
                            </td>
                            <td>
                                {{ result.EachCndtnApplyCount.cndtn_408 || 0 }}
                            </td>
                            <td>
                                {{ result.EachCndtnApplyCount.cndtn_407 || 0 }}
                            </td>
                            <td>
                                (준비중)
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row" style="padding-top:20px;">
            <Calendar :events="calendarEvents" />
        </div>
    </div>

</template>

<script setup>
    import {onMounted, ref, computed} from 'vue';
    import Calendar from './MypageCalendar.vue';
    import {api} from '@/axios';
    import { formatDateYMD } from '@/tools';
 
    let result = ref(null);
    let applyStateSum = computed(() => getApplyStateSum());
    let calendarEvents = computed(() => makeCalendarDatas());

    onMounted(async() => {
            result.value = await api.$get("/user/mypage/", {
                params: {
                    mbr_sq : 1,
                    month : 6
                }
            });
    });

    function getApplyStateSum(){
        if (!result.value || !result.value.EachCndtnApplyCount) {
            return 0;
        }

        let sum = 0;
        for(const key in result.value.EachCndtnApplyCount){
            sum += result.value.EachCndtnApplyCount[key];
        }
        return sum;
    }

    function makeCalendarDatas(){
        if (!result.value || !result.value.calendarData) {
            return [];
        }

        let events = [];

        for(const temp of result.value.calendarData){
            let event = {
                id : '',
                title : '',
                date : '',
                borderColor : '',
                description : ''
             };

             event.id = temp.jbp_sq;
             switch(temp.tbl_type){
                case 'S' : 
                    event.title = '스크랩공고마감';
                    event.borderColor = '#6495ed'
                    break;
                case 'I' :
                    event.title = '면접';
                    event.borderColor = '#ff69b4'
                    break;
                case 'PP' : 
                    event.title = '제안받은공고마감';
                    event.borderColor = '#7fff00'
                    break;
                case 'AC' : 
                    event.title = '지원공고'
                    event.borderColor = '#a9a9a9'
                    break;
             }
             event.date = temp.dtm;
             event.description = temp.jbp_tl;

             events.push(event);
        }

        return events;
    }

</script>

<style>
.table-container{
    display: flex;
    align-items: center; /* 세로 축 가운데 정렬 */
    justify-content: center; /* 가로 축 가운데 정렬 */
}

.table-apply-state th, .table-apply-state td{
    text-align: center;
    vertical-align: middle;
}
</style>