<template>
    <div>
        <!-- 轮播图 -->
        <div class="swipePart">
            <van-swipe :autoplay="3000" lazy-render>
                <van-swipe-item v-for="image in images" :key="image">
                    <van-image class="image" :src="image" />
                </van-swipe-item>
            </van-swipe>
        </div>
        <!-- 九宫格 -->
        <div>
            <van-grid :column-num="2">
                <van-grid-item icon="description" text="预约挂号" to="/register" />
                <van-grid-item icon="search" text="检验结果查询" to="/results" />
                <van-grid-item icon="gold-coin-o" text="门诊缴费" tp="/pay" />
                <van-grid-item icon="contact" text="在线客服" tp="/customer" />
            </van-grid>
        </div>
    </div>
</template>
<script>
export default {
    setup() {
    const images = [
        require('../assets/image1.png'),
        require('../assets/image2.png'),
    ];
    return { images };
  },
    data:()=>{
        return{
            grids:[

            ],
        }
    },
}
</script>
<style scoped>
.swipePart{
    width: 100%;
    height: 30%;
}
.van-swipe{
    width: 100%;
    height: 100%;
}
.image{
    width: 100%;
    height: 100%;
}
</style>