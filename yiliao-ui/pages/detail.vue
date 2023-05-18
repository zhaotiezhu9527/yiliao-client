<template>
  <view class="page">
    <van-nav-bar
      title="Notice"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/notice')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="title">{{ items.title }}</view>
      <view class="dateTime">{{ items.createTime }}</view>
      <view class="content">
        {{ items.content }}
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      items: {},
    };
  },
  onLoad() {
    uni.showLoading();
    this.$api.user_notice_info(this.$route.query.id).then((res) => {
      if (res.data.code == 0) {
        this.items = res.data.data;
      } else {
        this.$base.show(res.data.msg);
      }
    });
  },
  methods: {},
};
</script>

<style scoped lang="scss">
.wrap {
  box-sizing: border-box;
}
.title {
  text-align: center;
  padding: 40upx 30upx 0;
  font-size: 28upx;
  color: #404040;
  font-weight: 700;
}
.dateTime {
  font-size: 24upx;
  text-align: right;
  color: #bfbfbf;
  padding: 40upx 30upx;
}
.content {
  padding: 0 20upx;
  font-size: 24upx;
  line-height: 1.5;
  text-indent: 2em;
}
</style>
