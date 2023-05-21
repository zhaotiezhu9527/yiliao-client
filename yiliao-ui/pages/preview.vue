<template>
  <view class="page">
    <van-nav-bar
      placeholder
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage(rurl)"
    >
      <template #left>
        <van-icon name="arrow-left" size="18" />
        <text class="headr_title">{{ title }}</text>
      </template>
    </van-nav-bar>
    <web-view
      v-if="url"
      :webview-styles="webviewStyles"
      :fullscreen="false"
      class="wrap"
      :src="url"
    ></web-view>
  </view>
</template>
<script>
export default {
  data() {
    return {
      title: "",
      url: "",
      rurl: undefined,
      webviewStyles: {
        progress: {
          color: "#FF3333",
          width: "100vw",
          height: "calc(100vh - var(--status-bar-height) - 104upx)",
        },
      },
    };
  },
  onLoad(e) {
    this.title = e.title;
    this.url = e.url;
    this.rurl = e.rurl;
  },
  methods: {},
};
</script>

<style scoped lang="scss">
.wrap {
  width: 100vw;
  height: calc(100vh - var(--status-bar-height) - 104upx);
  /deep/ iframe {
    width: 100% !important;
    height: 100% !important;
    box-sizing: border-box;
    border: 0;
  }
}
</style>
