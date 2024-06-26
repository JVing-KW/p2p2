<template>
    <div class="container py-4">
        <div class="row">
            <MypageSidebar/>
            <div class="col-lg-9">
                <div class="row">
                    <div class="col pb-3">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>
                                        #
                                    </th>
                                    <th>
                                        First Name
                                    </th>
                                    <th>
                                        Last Name
                                    </th>
                                    <th>
                                        Username
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        1
                                    </td>
                                    <td>
                                        Mark
                                    </td>
                                    <td>
                                        Otto
                                    </td>
                                    <td>
                                        @mdo
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        2
                                    </td>
                                    <td>
                                        Jacob
                                    </td>
                                    <td>
                                        Thornton
                                    </td>
                                    <td>
                                        @fat
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        3
                                    </td>
                                    <td>
                                        Larry
                                    </td>
                                    <td>
                                        the Bird
                                    </td>
                                    <td>
                                        @twitter
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>

<script setup>
    import MypageSidebar from "../../../components/fo/user/MypageSidebar";
</script>