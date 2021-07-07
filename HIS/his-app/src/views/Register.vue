<template> 
    <div>
        <van-nav-bar
            left-text="返回"
            left-arrow
            @click-left="onClickLeft"
        />
        <van-tree-select
            v-model:active-id="state.activeId"
            v-model:main-active-index="state.activeIndex"
            :items="items"
            @click-item='onitemClick'
            height="100%"
        />
    </div>  
</template>
<script>
import { reactive } from 'vue';
export default {
    setup() {
    const state = reactive({
      activeId: 1,
      activeIndex: 0,
    });
    return{
        state,
    };
    },
    created(){
        this.getDep();
    },
    data(){
        return {
            items:[],
        }
    },
    methods:{
        onClickLeft(){
            this.$router.push({path:"/home"});
        },
        onitemClick(data){
            window.sessionStorage.setItem("data",data.id);
            window.sessionStorage.setItem("dept",data.text);
            console.log(data);
            this.$router.push({path:"/registByDep"});
        },
        async getDep(){
            const {data: res} = await this.$http.get("/getAllDeps");
            this.items = res;
        }
    }
}
</script>
<style scoped>

</style>