<template>
<van-nav-bar
            left-text="返回"
            left-arrow
            @click-left="onClickLeft"
        />
<h3>Q1.如何进行身份信息变更？</h3>
<p>为保障用户信息安全，目前只能更改联系电话或地区，如涉及身份信息更改（如姓名、身份证）请拨打客服热线4006677400办理。 </p>
<h3>Q2.挂号账户和就诊患者必须一致吗？</h3>
<p>为照顾一些不会使用智能机进行挂号的患者，本医院允许挂号账户和就诊患者不同。</p>
<h3>Q3.可以提前几天进行挂号？</h3>
<p>最多可以提前七天，只能挂本周的号；每周日早晨八点进行放号，一次性放出接下来一整周的号源。</p>
<h3>Q4.网上预约挂号需要收费吗</h3>
<p>网上预约不需要收费，请患者凭借身份证到医院现场缴费。</p>
</template>
<script>
export default {
    setup() {
        
    },
    methods:{
        onClickLeft(){
            this.$router.push({path:"/home"});
        },
    }
}
</script>
<style scoped>

</style>