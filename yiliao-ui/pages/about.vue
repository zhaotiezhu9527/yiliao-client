<template>
  <view class="page">
    <van-nav-bar
      placeholder
      title="公司简介"
      :border="false"
      fixed
      safe-area-inset-top
    >
    </van-nav-bar>
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
