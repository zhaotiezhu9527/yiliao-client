<script>
export default {
  onLaunch: function () {
    let token = uni.getStorageSync("token");
    if (token) {
      this.$api.user_info().then(({ data }) => {
        if (data.code == 0) {
          uni.setStorage({
            key: "infos",
            data: data.data,
            success: () => {
              this.systemFn();
            },
          });
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
          //获取app版本应用
          // #ifdef APP-PLUS
          let that_app = uni.getAppBaseInfo();
          let new_app = data.data.version.find(
            (item) => item.platForm === uni.getSystemInfoSync().platform
          );
          if (that_app.appWgtVersion !== new_app.version) {
            uni.showModal({
              title: "提示",
              content: "有最新版本，是否下载",
              confirmText: "确定",
              cancelText: "取消",
              success: (res) => {
                if (res.confirm) {
                  plus.runtime.openURL(new_app.downloadUrl);
                  plus.runtime.quit();
                } else if (res.cancel) {
                  plus.runtime.quit(); //强制退出应用
                }
              },
            });
          } else {
            this.$isResolve();
          }
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
    let route = [];
    let path = "";
    // #ifdef APP-PLUS
    route = ["pages/login", "/", "pages/register"];
    let routes = getCurrentPages();
    if (routes.length) {
      path = routes[routes.length - 1].route;
    } else {
      path = "/";
    }
    // #endif
    // #ifdef H5
    route = ["/pages/login", "/pages/register"];
    path = this.$route.path;
    // #endif

    let time = uni.getStorageSync("dateTime");
    let that = new Date().getTime();
    let max = 60 * 1000 * 15; // 15分钟
    let token = uni.getStorageSync("token");
    // if (
    //   (!token && !route.includes(path)) ||
    //   (time && that - time > max && !route.includes(path))
    // ) {
    //   this.$base.show("登录已过期~");
    // }
    if (!token && !route.includes(path)) {
      this.$base.show("登录已过期~");
      uni.removeStorage({
        key: "token",
        success: (res) => {
          uni.redirectTo({
            url: "/pages/login",
          });
        },
      });
    }
  },
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
