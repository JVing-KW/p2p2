<template>
    <div class="row" style="padding-bottom: 20px; border-bottom: 1px solid #eaeaea;">
        <div class="col-3">
            <div class="thumb-info-side-image-wrapper">
                <img src="@/assets/profile.webp" class="img-fluid" alt="" style="width: 180px;">
            </div>
        </div>
        <div class="col-9 table-container" >
            <table class="table table-bordered" style="margin-bottom: 0">
                <thead>
                    <tr>
                        <th colspan="3">
                            <h2 class="font-weight-bold" style="margin-bottom: 10px;">((대표이력서 제목))</h2>
                            <span>-최종 수정일자 : 2024.06.25</span><span style="margin-left: 20px;">-포지션 수락 여부 : 수락</span>
                        </th>
                    </tr>
                </thead>
                <tbody style="text-align: center;">
                    <tr>
                        <th>
                            야도란
                        </th>
                        <th>
                            010-1234-1234
                        </th>
                        <th>
                            google@naver.com
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
                <th>등록한 이력서&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10</th>
                <th>추천 공고&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;20</th>
                <th>스크랩 공고&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;30</th>
                <th>받은 제안&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0</th>
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
                            100
                        </td>
                        <td>
                            20
                        </td>
                        <td>
                            20
                        </td>
                        <td>
                            20
                        </td>
                        <td>
                            10
                        </td>
                        <td>
                            10
                        </td>
                        <td>
                            10
                        </td>
                        <td>
                            10
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row" style="padding-top:20px;">
        <Calendar/>
    </div>
</template>

<script setup>
    import Calendar from './MypageCalendar.vue';
    // import {onMounted, ref} from 'vue'
    // import {api} from '@/axios'
 
    // let result = ref(null);

    // onMounted(() => {
    //     result.value = api.$get("/user/mypage/", {
    //         params: {
    //             mbr_sq : 1,
    //             month : 6
    //         }
    //     });
    // });
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