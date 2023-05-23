<script>
export default {
  onLaunch: function () {
    let token = uni.getStorageSync("token");
    if (token) {
      this.$api.user_info().then(({ data }) => {
        this.systemFn();
        if (data.code == 0) {
          this.$base.storage("infos", data.data);
        }
      });
    } else {
      this.systemFn();
    }
  },
  methods: {
    systemFn() {
      this.$api.system_config().then(({ data }) => {
        if (data.code == 0) {
          this.$base.storage("system_config", data.data);
          uni.setNavigationBarTitle({
            title: data.data.jiancheng,
          });
          this.$isResolve();
        } else {
          this.$isResolve();
        }
      });
    },
  },
  onShow: function () {},
  onHide: function () {},
};
</script>
<style lang="scss">
/*每个页面公共css */
@import "@/uni_modules/uview-ui/index.scss";
view,
text {
  box-sizing: border-box;
}
/deep/.u-navbar__content {
  z-index: 1024;
}
/deep/.u-form-item {
  font-size: 32rpx !important;
  padding-top: 20rpx !important;
}
/deep/.u-form-item__body {
  padding: 28rpx 0 !important;
}
/deep/.u-form-item__body__left__content__label {
  display: flex;
  align-items: center;
  width: 124rpx;
  font-size: 32rpx !important;
}
/deep/.uni-input-placeholder,
/deep/.uni-input-input {
  font-size: 32rpx !important;
}
.uni-app--showleftwindow + .uni-tabbar-bottom {
  display: block !important;
}
</style>
