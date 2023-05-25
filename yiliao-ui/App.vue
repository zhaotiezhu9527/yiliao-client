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
          //获取app版本号
          // #ifdef APP-PLUS
          // let that_app = parseInt(plus.runtime.version.split(".").join(""));
          // let new_app = parseInt(data.version.split(".").join(""));
          // if (that_app < new_app) {
          //   console.log(uni.getSystemInfoSync().platform);
          //   uni.showModal({
          //     title: "提示",
          //     content: "有最新版本，是否下载",
          //     confirmText: "确定",
          //     cancelText: "取消",
          //     success: (res) => {
          //       if (res.confirm) {
          //         plus.runtime.openURL(this.items.url);
          //         plus.runtime.quit();
          //       } else if (res.cancel) {
          //         plus.runtime.quit(); //强制退出应用
          //       }
          //     },
          //   });
          // } else {
          //   this.$isResolve();
          // }
          this.$isResolve();
          // #endif
          // #ifdef H5
          this.$isResolve();
          // #endif
        } else {
          this.$isResolve();
        }
      });
    },
  },
  onShow: function () {
    // 获取token是否过期
    let token = uni.getStorageSync("token");
    this.systemFn();
    if (!token) {
      uni.redirectTo({
        url: "/pages/login",
      });
    }
  },
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
