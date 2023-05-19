<script>
export default {
  onLaunch: function () {
    this.$base.authorityPage(this);
  },
  onShow: function () {},
  onHide: function () {},
};
</script>
<style>
/*每个页面公共css */
@import url("./node_modules/vant/lib/index.css");
.van-field__control {
  font-size: 28upx;
}
.van-nav-bar__content {
  height: 104upx;
}
.van-nav-bar {
  background: #4b80af;
  z-index: 1024;
}
.van-nav-bar__title,
.van-nav-bar .van-icon {
  color: #ffffff;
}
.van-nav-bar__title {
  font-size: 32upx;
}
.uni-app--showleftwindow + .uni-tabbar-bottom {
  display: block !important;
}
.van-cell {
  padding: 28upx 32upx;
  line-height: 48upx;
  font-size: 30upx;
}
</style>
