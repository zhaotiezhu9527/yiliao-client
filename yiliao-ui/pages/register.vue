<template>
  <view class="page">
    <van-nav-bar
      left-arrow
      :border="false"
      placeholder
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
          >
            <template #label>
              <image
                class="img"
                src="../static/img/user3.png"
                mode="widthFix"
              />
            </template>
          </van-field>
        </view>
        <view class="input">
          <van-field
            v-model="password"
            label="密码"
            type="password"
            placeholder="请输入登录密码"
          >
            <template #label>
              <image
                class="img"
                src="../static/img/pass2.png"
                mode="widthFix"
              />
            </template>
          </van-field>
        </view>
        <view class="input">
          <van-field
            v-model="confirmLoginPwd"
            label="密码"
            type="password"
            placeholder="请确认登录密码"
          >
            <template #label>
              <image
                class="img"
                src="../static/img/pass2.png"
                mode="widthFix"
              />
            </template>
          </van-field>
        </view>
        <view class="input">
          <van-field
            v-model="payPwd"
            label="密码"
            type="password"
            placeholder="请输入支付密码"
          >
            <template #label>
              <image
                class="img"
                src="../static/img/money2.png"
                mode="widthFix"
              />
            </template>
          </van-field>
        </view>
        <view class="input">
          <van-field
            v-model="inviteCode"
            label="密码"
            type="number"
            placeholder="推荐人ID（必填）"
          >
            <template #label>
              <image
                class="img2"
                src="../static/img/user2.png"
                mode="widthFix"
              />
            </template>
          </van-field>
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
      icon: "",
      show: false,
      columns: [],
      loading: false,
      confirmLoginPwd:'',//确认登录密码
      payPwd:'',//支付密码
      inviteCode:'',//推荐人id
    };
  },
  onLoad() {},
  methods: {
    login() {
      if (this.userPhone === "") {
        return this.$base.show("请输入登录账号~");
      } else if (this.password === "") {
        return this.$base.show("请输入登录密码~");
      } else if (this.password != this.confirmLoginPwd) {
        return this.$base.show("两次输入密码不一致~");
      } else if (this.payPwd === "") {
        return this.$base.show("请输入支付密码~");
      } else if (this.inviteCode === "") {
        return this.$base.show("请输入推荐人ID~");
      }
      const DATA_OBJ = {
        loginPwd: this.password,
        userName: this.userPhone,
        confirmLoginPwd: this.confirmLoginPwd,
        payPwd: this.payPwd,
        inviteCode: this.inviteCode,
      };
      this.loading = true;
      this.$api.user_register(DATA_OBJ).then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          this.$base.storage("token", res.data.token);
          uni.switchTab({ url: "/pages/personal" });
        }
      });
    },
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
  .img {
    width: 44upx;
  }
  .img2 {
    width: 40upx;
  }
  /deep/.van-field__label {
    width: 60upx;
    display: flex;
    justify-content: center;
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
