<template>
  <view class="page">
    <u-navbar
      placeholder
      title="公司简介"
      :border="false"
      autoBack
      fixed
      leftIconSize="0"
      safe-area-inset-top
      bgColor="#4b80af"
      height="52px"
      titleStyle="color:#fff;font-weight:500;font-size:32rpx;"
    >
    </u-navbar>
    <view class="wrap">
      <scroll-view scroll-y class="scroll">
        <image
          class="image"
          mode="widthFix"
          src="../static/img/zhenshi_about_post.png"
        />
      </scroll-view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {};
  },
  onLoad() {},
  methods: {},
};
</script>

<style scoped lang="scss">
.scroll {
  height: calc(100vh - 210rpx + var(--status-bar-height));
}
.image {
  width: 100vw;
}
.text{
  font-size: 50rpx;
}
</style>
