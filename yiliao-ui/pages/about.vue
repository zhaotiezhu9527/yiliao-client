<template>
  <view class="page">
    <van-nav-bar placeholder :border="false" fixed safe-area-inset-top>
      <template #left><text class="headr_title">公司简介</text></template>
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
