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
@import url("./node_modules/vant/lib/index.css");
@import "@/uni_modules/uview-ui/index.scss";
view,
text {
  box-sizing: border-box;
}
.van-field__control {
  font-size: 28upx;
}
/deep/.u-navbar__content {
  z-index: 1024;
}
/deep/.u-form-item {
  font-size: 32upx !important;
  padding-top: 20upx !important;
}
/deep/.u-form-item__body {
  padding: 28upx 0 !important;
}
/deep/.u-form-item__body__left__content__label {
  display: flex;
  align-items: center;
  width: 124upx;
  font-size: 32upx !important;
}
/deep/.uni-input-placeholder,
/deep/.uni-input-input {
  font-size: 32upx !important;
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
