<script>
export default {
  onLaunch: function () {
    this.$base.authorityPage(this);
    this.$base.storage("config", {});
    this.$api.sys_config().then((res) => {
      if (res.data.code == 0) {
        this.$base.storage("config", res.data.data);
      } else {
        this.$base.show(res.data.msg);
      }
    });
  },
  onShow: function () {},
  onHide: function () {},
};
</script>
<style>
/*每个页面公共css */
@import url("./node_modules/vant/lib/index.css");
.wrap {
  padding-top: 92upx;
}
.van-field__control {
  font-size: 28upx;
}
.van-nav-bar__content {
  height: 92upx;
}
.van-nav-bar {
  background: #4b80af;
}
.van-nav-bar__title,
.van-nav-bar .van-icon {
  color: #ffffff;
}
.van-nav-bar__title {
  font-size: 36upx;
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
