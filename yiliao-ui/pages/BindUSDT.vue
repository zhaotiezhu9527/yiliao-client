<template>
  <view class="page">
    <van-nav-bar
      title="绑定USDT地址"
      left-arrow
      :border="false"
      fixed
      placeholder
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="usdt-title">请输入USDT钱包地址</view>
      <view class="from-input">
        <input
          type="text"
          class="input-text"
          placeholder="请输入USDT钱包地址"
        />
      </view>
      <view class="btn-class">提交绑定</view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped lang="scss">
.wrap {
  .usdt-title {
    margin: 60upx 50upx;
    color: #577fab;
    font-weight: 600;
    font-size: 40upx;
  }
  .from-input {
    margin: 40upx;
    height: 90upx;
    .input-text {
      height: 64upx;
      border-bottom: 1px solid #bbb;
      font-size: 24upx;
      font-weight: 600;
    }
  }
}
</style>
