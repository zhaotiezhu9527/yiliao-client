<template>
  <view class="page">
    <van-nav-bar left-arrow :border="false" fixed safe-area-inset-top>
      <template #left><text class="title">登录</text></template>
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
            placeholder="请输入登录账号"
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
        <view class="btns">
          <van-button color="#4b80af" block @click="login" :loading="loading">
            登录
          </van-button>
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
      areaCode: "",
      icon: "",
      show: false,
      columns: [],
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
      this.$store.code = this.areaCode;
      if (this.areaCode === "") {
        return this.$base.show("Please select a country");
      } else if (!this.$base.phone(this.userPhone)) {
        return this.$base.show("Please enter correctly Phone");
      } else if (this.password === "") {
        return this.$base.show("Please enter Password");
      }
      const DATA_OBJ = {
        password: this.password,
        userPhone: this.userPhone,
        areaCode: this.areaCode,
      };
      this.loading = true;
      this.$api.user_login(DATA_OBJ).then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          this.$base.storage("token", res.data.token);
          this.$base.storage("areaCode", this.areaCode);
          uni.switchTab({ url: "/pages/personal" });
        }
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
}
/deep/.van-field__label {
  display: flex;
  align-items: center;
  width: 100upx;
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
    padding-top: 20upx;
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
