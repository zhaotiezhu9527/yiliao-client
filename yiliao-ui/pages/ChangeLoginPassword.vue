<template>
  <view class="page">
    <van-nav-bar
      :border="false"
      placeholder
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/AccountSafe')"
    >
      <template #left>
        <van-icon name="arrow-left" size="18" />
        <text class="headr_title">修改登录密码</text>
      </template>
    </van-nav-bar>
    <view class="wrap">
      <view class="from-input">
        <label>原登录密码</label>
        <input
          type="text"
          v-model="oldPwd"
          class="input-text"
          placeholder="请输入登录密码"
        />
      </view>
      <view class="from-input">
        <label>新登录密码</label>
        <input
          type="text"
          v-model="newPwd"
          class="input-text"
          placeholder="请输入新登录密码"
        />
      </view>
      <view class="from-input">
        <label>确认密码</label>
        <input
          type="text"
          v-model="password"
          class="input-text"
          placeholder="请输入确认密码"
        />
      </view>
      <view class="btns">
        <van-button color="#4b80af" block @click="login" :loading="loading">
          完成修改
        </van-button>
      </view>
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
      this.$api.user_updatePwd(DATA_OBJ).then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          uni.redirectTo({
            url: "/pages/login",
          });
        }
      });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  .from-input {
    margin: 40upx;
    display: flex;
    height: 90upx;
    label {
      width: 200upx;
      margin-right: 20upx;
      font-weight: 500;
    }
    .input-text {
      flex: 1;
      text-align: right;
    }
  }
}
.btns {
  padding: 40upx 40upx 0;
  .van-button {
    border-radius: 10upx;
    margin-bottom: 20upx;
  }
  .van-button__content span {
    font-size: 32upx;
  }
}
</style>
