<template> 
    <div>
        <van-nav-bar
            left-text="返回"
            left-arrow
            @click-left="onClickLeft"
        />
        <v-tree-select
            v-model:active-id="activeId"
            v-model:main-active-index="activeIndex"
            noChildrenText="更新中"
            :items="items"
            @click-item='onitemClick'
        />
    </div>  
</template>
<script>
export default {
    methods:{
        onClickLeft(){
            this.$router.push({path:"/home"});
        },
        onitemClick(data){
            console.log(data)
        }
    }
}
</script>
<style scoped>

</style>