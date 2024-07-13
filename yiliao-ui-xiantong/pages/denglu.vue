<template>
  <view class="page">
    <u-navbar
      placeholder
      title="登录"
      :border="false"
      autoBack
      fixed
      leftIconSize="0"
      safe-area-inset-top
      bgColor="#4b80af"
      height="100rpx"
      titleStyle="color:#fff;font-weight:500;font-size:32rpx;"
    >
    </u-navbar>
    <view class="wrap">
      <view class="logo">
        <image
          class="img"
          mode="widthFix"
          src="../static/img/logo_new.jpg"
        />
      </view>
      <view class="from">
        <u-form ref="uForm" labelPosition="left" labelWidth="124">
          <u-form-item label="账号">
            <u-input
              type="text"
              placeholder="请输入登录账号"
              clearable
              border="none"
              v-model="userPhone"
            ></u-input>
          </u-form-item>
          <u-form-item label="密码">
            <u-input
              type="password"
              placeholder="请输入登录密码"
              clearable
              border="none"
              v-model="password"
            ></u-input>
          </u-form-item>
        </u-form>
        <view class="btns">
          <u-button
            class="custom-style"
            color="#4b80af"
            block
            @click="login"
            :loading="loading"
          >
            登录
          </u-button>
          <view class="register" @click="register"> 注册账号 </view>
        </view>
        <view class="version-class">版本号 : v{{ version }}</view>
      </view>
    </view>
  </view>
</template>
<script>
export default {
  data() {
    return {
      password: "",
      userPhone: "",
      loading: false,
      version: "",
    };
  },
  methods: {
    register() {
      uni.navigateTo({
        url: "/pages/zhuce",
      });
    },
    login() {
      if (!this.userPhone) {
        return this.$base.show("请输入登录账号~");
      } else if (!this.password) {
        return this.$base.show("请输入登录密码~");
      }
      const DATA_OBJ = {
        loginPwd: this.password.replace(/\s*/g,""),
        userName: this.userPhone.replace(/\s*/g,""),
      };
      this.loading = true;
      this.$api
        .user_login(DATA_OBJ)
        .then((res) => {
          if (res.data.code == 0) {
            // 记录token过期时间
            uni.setStorageSync("dateTime", new Date().getTime());
            uni.setStorage({
              key: "token",
              data: res.data.token,
              success: function () {
                uni.switchTab({ url: "/pages/gerenzhongxin" });
              },
            });
          }
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
  onShow() {
    this.version = uni.getAppBaseInfo().appVersionCode
  },
};
</script>

<style scoped lang="scss">
view,
text {
  font-size: 32rpx;
}
.logo {
  display: flex;
  justify-content: center;
  padding-top: 60rpx;
  .img {
    width: 50%;
  }
}
.btns {
  text-align: center;
  padding: 40rpx 0;
  .custom-style {
    border-radius: 10rpx;
    margin-bottom: 20rpx;
    font-size: 32rpx;
    height: 88rpx;
  }
  .register {
    color: #4b80af;
    font-size: 32rpx;
    margin-top: 40rpx;
  }
}
.from {
  padding: 40rpx;
  width: 100%;
  box-sizing: border-box;
  .image {
    height: 60rpx;
  }
}
.version-class{
  margin-top: 400rpx;
  text-align: center;
  font-size: 24rpx;
  color: #ddd;
}
</style>
