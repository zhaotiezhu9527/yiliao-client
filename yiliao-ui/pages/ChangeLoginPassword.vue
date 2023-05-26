<template>
  <view class="page">
    <u-navbar
      placeholder
      title="修改登录密码"
      :border="false"
      autoBack
      fixed
      safe-area-inset-top
      bgColor="#4b80af"
      leftIconColor="#fff"
      leftIconSize="32"
      height="52px"
      titleStyle="color:#fff;font-weight:500;font-size:32rpx;"
    >
    </u-navbar>
    <view class="wrap">
      <view class="from-input">
        <label>原登录密码</label>
        <input
          type="password"
          v-model="oldPwd"
          class="input-text"
          placeholder="请输入登录密码"
        />
      </view>
      <view class="from-input">
        <label>新登录密码</label>
        <input
          type="password"
          v-model="newPwd"
          class="input-text"
          placeholder="请输入新登录密码"
        />
      </view>
      <view class="from-input">
        <label>确认密码</label>
        <input
          type="password"
          v-model="password"
          class="input-text"
          placeholder="请输入确认密码"
        />
      </view>
      <u-button class="btn-class" block @click="login" :loading="loading">
        完成修改
      </u-button>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      newPwd: "",
      oldPwd: "",
      password: "",
    };
  },
  methods: {
    login() {
      if (!this.oldPwd) {
        return this.$base.show("请输入原登录密码~");
      } else if (!this.newPwd || this.newPwd.length < 6) {
        return this.$base.show("请输入新登录密码且长度大于6~");
      } else if (this.oldPwd === this.newPwd) {
        return this.$base.show("新登录密码不能与原密码相同~");
      } else if (this.password != this.newPwd) {
        return this.$base.show("两次输入密码不一致~");
      }
      const DATA_OBJ = {
        oldPwd: this.oldPwd,
        newPwd: this.newPwd,
      };
      this.loading = true;
      this.$api
        .user_updatePwd(DATA_OBJ)
        .then((res) => {
          if (res.data.code == 0) {
            this.$base.show(res.data.msg);
            uni.removeStorage({
              key: "token",
              success: function (res) {
                this.newPwd = "";
                this.oldPwd = "";
                this.password = "";
                uni.redirectTo({
                  url: "/pages/login",
                });
              },
            });
          }
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  .from-input {
    margin: 40rpx;
    display: flex;
    height: 90rpx;
    label {
      width: 200rpx;
      margin-right: 20rpx;
      font-weight: 500;
    }
    .input-text {
      flex: 1;
      text-align: right;
    }
  }
}
</style>
