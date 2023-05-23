<template>
  <view class="page">
    <u-navbar
      placeholder
      :title="title"
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
          height: "calc(100vh - var(--status-bar-height) - 104rpx)",
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
  height: calc(100vh - var(--status-bar-height) - 104rpx);
  /deep/ iframe {
    width: 100% !important;
    height: 100% !important;
    box-sizing: border-box;
    border: 0;
  }
}
</style>
