<template>
  <view class="page">
    <van-nav-bar
      title="实名认证"
      left-arrow
      :border="false"
      placeholder
      fixed
      safe-area-inset-top
      @click-left="backPage"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="from-input">
        <label>姓名</label>
        <input type="text" class="input-text" placeholder="请输入真实姓名" />
      </view>
      <view class="from-input">
        <label>身份证号码</label>
        <input type="text" class="input-text" placeholder="请输入身份证号码" />
      </view>
      <view class="btn-class">实名认证</view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    // 返回上一页
    backPage() {
      window.history.back();
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  padding-top: 100upx;
  .from-input {
    margin: 40upx;
    display: flex;
    height: 90upx;
    label {
      width: 200upx;
      margin-right: 20upx;
    }
    .input-text {
      flex: 1;
      text-align: right;
    }
  }
}
</style>
