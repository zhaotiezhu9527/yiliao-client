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
      height="52px"
      titleStyle="color:#fff;font-weight:500;font-size:32rpx;"
    >
    </u-navbar>
    <view class="wrap">
      <view class="logo">
        <image
          class="img"
          mode="widthFix"
          src="../static/img/login_zhenhsi_logo.png"
        />
      </view>
      <view class="from">
        <u--form labelPosition="left" labelWidth="124">
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
              password
              placeholder="请输入登录密码"
              clearable
              border="none"
              v-model="password"
            ></u-input>
          </u-form-item>
        </u--form>
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
    };
  },
  onLoad() {},
  methods: {
    register() {
      uni.navigateTo({
        url: "/pages/register",
      });
    },
    login() {
      if (!this.userPhone) {
        return this.$base.show("请输入登录账号~");
      } else if (!this.password) {
        return this.$base.show("请输入登录密码~");
      }
      const DATA_OBJ = {
        loginPwd: this.password,
        userName: this.userPhone,
      };
      this.loading = true;
      this.$api.user_login(DATA_OBJ).then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          this.$base.storage("token", res.data.token);
          uni.switchTab({ url: "/pages/personal" });
        }
      });
    },
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
</style>
