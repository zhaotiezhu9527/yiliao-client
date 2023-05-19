<template>
  <view class="page">
    <van-nav-bar
      left-arrow
      placeholder
      :border="false"
      fixed
      title="立即投资"
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/investor')"
    >
    </van-nav-bar>
    <view class="cardStyle">
      <view class="card">
        <view class="item">
          <view>账户可用余额 (元)</view>
          <view>￥0元</view>
        </view>
        <view class="item">
          <view>项目可投金额 (元)</view>
          <view>￥667083444200元</view>
        </view>
      </view>
    </view>
    <view class=""></view>
    <view class="btn">
      <van-button color="#4b80af" block> 立即投资 </van-button>
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
    change() {
      uni.navigateTo({
        url: "/pages/content",
      });
    },
  },
};
</script>
<style scoped lang="scss">
view,
text {
  font-weight: 500;
}
.cardStyle {
  padding: 40upx 30upx;
  .card {
    background-color: #4c80b0;
    border-radius: 10upx;
    padding: 40upx;
    box-sizing: border-box;
    display: flex;
    color: #fff;
    justify-content: center;
    .item {
      width: 50%;
      text-align: center;
      view:nth-child(1) {
        font-size: 26upx;
      }
      view:nth-child(2) {
        padding-top: 40upx;
        font-size: 32upx;
      }
    }
  }
}
.btn {
  padding: 10upx 30upx;
  width: 100%;
  box-sizing: border-box;
  .van-button {
    border-radius: 10upx;
  }
}
</style>
