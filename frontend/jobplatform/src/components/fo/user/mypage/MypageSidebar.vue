<template>
    <div class="col-lg-3">
        <aside class="sidebar" style="position: fixed;">
            <h5 class="font-weight-semi-bold pt-4">마이페이지</h5>
            <ul class="nav nav-list flex-column mb-5">
                <li class="nav-item"><router-link class="nav-link" to="home">현황 및 일정</router-link ></li>
                <li class="nav-item"><router-link class="nav-link" to="#">이력서 관리</router-link >
                    <ul>
                        <li class="nav-item"><router-link class="nav-link" to="#">이력서 등록</router-link ></li>
                        <li class="nav-item"><router-link class="nav-link" to="#">이력서 목록</router-link ></li>
                    </ul>
                </li>
                <li class="nav-item"><router-link class="nav-link" to="applycompanylist">입사지원현황</router-link ></li>
                <li class="nav-item"><router-link class="nav-link" to="#">포지션 제안</router-link >
                    <ul>
                        <li class="nav-item"><router-link class="nav-link active" to="ppAcception">포지션 제안 설정</router-link ></li>
                        <li class="nav-item"><router-link class="nav-link" to="#">제안 받은 포지션</router-link ></li>
                    </ul>
                </li>
                <li class="nav-item"><router-link class="nav-link" to="#">스크랩 공고</router-link ></li>
                <li class="nav-item">
                    <router-link class="nav-link" to="#">회원정보 관리</router-link >
                    <ul>
                        <li class="nav-item"><router-link class="nav-link" to="#">회원정보 수정</router-link ></li>
                        <li class="nav-item"><router-link class="nav-link" to="#">회원정보 변경</router-link ></li>
                        <li class="nav-item"><router-link class="nav-link" to="#">회원탈퇴</router-link ></li>
                    </ul>
                </li>
            </ul>
        </aside>
    </div>
</template>