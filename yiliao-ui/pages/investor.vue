<template>
  <view class="page">
    <van-nav-bar
      placeholder
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
    >
      <template #left><text class="title">项目投资</text></template>
    </van-nav-bar>
    <view class="wrap">
      <view class="list">
        <view
          class="view"
          v-for="(item, index) in 10"
          :key="index"
          @click="routePath"
        >
          <image class="img" src="../static/img/txt.jpg" mode="widthFix" />
          <view class="name">
            <text>保</text>
            <text>安苏萌·注射用人生长激素</text>
          </view>
          <view class="rate">
            <view class="li">
              <view class="num"><text>5.88</text>%</view>
              <view class="con">日化利率</view>
            </view>
            <view class="li">
              <view class="num"><text>15</text>分钟</view>
              <view class="con">投资期限</view></view
            >
            <view class="li">
              <view class="num">￥<text>1000.00</text>元</view>
              <view class="con">起投金额</view></view
            >
          </view>
          <view class="investor">
            <view class="con">
              <text>项目规模：20000万元</text>
              <text>每日还息：到齐还本</text>
            </view>
            <view class="btn">立即投资</view>
          </view>
          <view class="progress">
            <view class="txt">项目进度：</view>
            <van-progress class="prog" percentage="50" :show-pivot="false" />
            <view class="number">21.05%</view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {};
  },
  onLoad() {},
  methods: {
    routePath() {
      uni.navigateTo({
        url: "/pages/info",
      });
    },
  },
};
</script>

<style scoped lang="scss">
@import "../static/list.scss";
.page {
  background-color: #fafafa;
}
.title {
  font-size: 32upx;
  color: #fff;
}
</style>
