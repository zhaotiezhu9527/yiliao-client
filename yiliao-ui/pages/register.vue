<template>
  <view class="page">
    <van-nav-bar
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage()"
    >
      <template #left>
        <van-icon name="arrow-left" />
        <text class="title">注册</text>
      </template>
    </van-nav-bar>
    <view class="wrap">
      <view class="logo">
        <image
          class="img"
          mode="widthFix"
          src="../static/img/login_zhenhsi_logo.png"
        />
      </view>
      <view class="from">
        <view class="input">
          <van-field
            v-model="userPhone"
            type="tel"
            placeholder="请输入注册账号"
            label="账号"
          >
          </van-field>
        </view>
        <view class="input">
          <van-field
            v-model="password"
            label="密码"
            type="password"
            placeholder="请输入登录密码"
          />
        </view>
        <view class="input">
          <van-field
            v-model="password"
            label="密码"
            type="password"
            placeholder="请确认登录密码"
          />
        </view>
        <view class="input">
          <van-field
            v-model="password"
            label="密码"
            type="password"
            placeholder="请输入支付密码"
          />
        </view>
        <view class="input">
          <van-field
            v-model="password"
            label="密码"
            type="number"
            placeholder="推荐人ID（必填）"
          />
        </view>
        <view class="btns">
          <van-button color="#4b80af" block @click="login" :loading="loading">
            注册
          </van-button>
          <view class="register" @click="register"> 已有账号？返回登录 </view>
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
      areaCode: "",
      icon: "",
      show: false,
      columns: [],
      loading: false,
    };
  },
  onLoad() {},
  methods: {
    login() {},
    register() {
      uni.navigateTo({
        url: "/pages/login",
      });
    },
  },
};
</script>

<style scoped lang="scss">
.logo {
  display: flex;
  justify-content: center;
  padding-top: 60upx;
  .img {
    width: 50%;
  }
}
.title {
  font-size: 32upx;
  color: #fff;
  padding-left: 30upx;
}
/deep/.van-field__label {
  display: flex;
  align-items: center;
  width: 90upx;
}
.btns {
  text-align: center;
  padding: 40upx 40upx 0;
  .van-button {
    border-radius: 10upx;
    margin-bottom: 20upx;
  }
  .van-button__content span {
    font-size: 32upx;
  }
  .register {
    color: #4b80af;
    font-size: 32upx;
    margin-top: 40upx;
  }
}
.from {
  padding: 40upx 0;
  width: 100%;
  box-sizing: border-box;
  .input {
    padding-top: 15upx;
  }
  /deep/.van-field__label {
    span {
      font-size: 32upx;
    }
  }
  /deep/.van-field__control {
    font-size: 32upx;
  }
  .image {
    height: 60upx;
  }
}
</style>
