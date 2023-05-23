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
      <image
        class="image"
        mode="widthFix"
        src="../static/img/zhenshi_about_post.jpg"
      />
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
.image {
  width: 100vw;
}
</style>
