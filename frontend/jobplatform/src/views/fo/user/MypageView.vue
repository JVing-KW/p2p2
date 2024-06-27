<template>
    <div class="container py-4">
        <div class="row">
            <MypageSidebar/>
            <div class="col-lg-9">
                <router-view/>
            </div>
        </div>
    </div>
</template>

<script setup>
    import MypageSidebar from "@/components/fo/user/mypage/MypageSidebar";
</script>