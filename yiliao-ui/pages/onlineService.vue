<template>
  <view class="page">
    <u-navbar
      placeholder
      title="在线客服"
      :border="false"
      autoBack
      fixed
      safe-area-inset-top
      bgColor="#4b80af"
      leftIconColor="#fff"
      leftIconSize="32"
      height="52px"
      titleStyle="color:#fff;font-weight:500;font-size:32rpx;"
    >
    </u-navbar>
    <view class="wrap">
      <web-view :src="config.online_service"></web-view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      config: {},//配置
    };
  },
  async onShow() {
    await this.$onLaunched;
    this.config = uni.getStorageSync("system_config");
  },
  methods: {
  },
};
</script>

<style scoped lang="scss">
</style>
