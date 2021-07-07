<template>
    <div>
        <van-nav-bar
            left-text="返回"
            left-arrow
            @click-left="onClickLeft"
        />
       <van-tabs v-model:active="activeName" @click="onClick">
        <van-tab v-for="op in option" :key="op.id" :disabled="disabled" :title=op.text :name=op.id>
            <!-- <div v-if="op.name>=day"> -->
                <van-divider />
                <div>
                    <div style="font-size:25px">上午
                        <van-divider />
                        <van-cell
                        v-for="it in morning"
                        :key="it.value"
                        :title= it.title
                        value ="预约挂号"
                        :id="it.value"
                        
                        is-link
                        @click="onCellClick"
                        />
                    </div>
                    <van-divider />
                    <div style="font-size:25px">下午
                        <van-divider />
                    <van-cell
                        v-for="it in afternoon"
                        :key="it.value"
                        :title= it.title
                        value ="预约挂号"
                        is-link
                        :id="it.title"
                        @click="onCellClick"
                    />
                </div>
                </div>
                
            <!-- </div> -->
        </van-tab>
        </van-tabs>
    </div>
</template>
<script>
import { ref } from 'vue';
import { Dialog } from 'vant';
export default{
    setup(){
        const option = [
      { text: '星期日', value: 0 },
      { text: '星期一', value: 1 },
      { text: '星期二', value: 2 },
      { text: '星期三', value: 3 },
      { text: '星期四', value: 4 },
      { text: '星期五', value: 5 },
      { text: '星期六', value: 6 },
    ];
        const activeName = ref(0);
        const day = new Date().getDay();
        return{
            option,
            activeName,
            day,
        }
    },

    data(){
        return{
            deptID:0,
            disabled: false,
            day:0,
            morning:{},
            afternoon:{},
        }
    },
    created(){
        this.deptID = window.sessionStorage.getItem("data");
        this.judgeDay();
    },
    methods:{
        async getDoc(name){
            console.log(this.deptID);
            const {data: res1} = await this.$http.get("/userRegist1?deptID="+this.deptID+"&k="+name);
            this.morning = res1;
            const {data: res2} = await this.$http.get("/userRegist2?deptID="+this.deptID+"&k="+name);
            this.afternoon = res2;
         
        },
        onClickLeft(){
            this.$router.push({path:"/register"});
        },
        judgeDay(){
            var myDate = new Date();
            this.day = myDate.getDay();
        },
        onClick(name){
            if(name<this.day){
                Dialog.alert({
                    title: '提示',
                    message: '请选择正确的日期',
                    }).then(() => {
                    // on close
                });
            }
            else{
                this.getDoc(name);
                
            }
            
        },
        onCellClick(e){
            this.$router.push({path:"/confirmRegist"});
            console.log(e.currentTarget.id);
            window.sessionStorage.setItem("doc",e.currentTarget.id);
        }
    },

}
</script>
<style scoped>

</style>>
