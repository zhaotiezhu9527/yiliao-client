<template>
  <view class="page">
    <van-nav-bar
      title="Login password"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/set')"
    >
    </van-nav-bar>
    <view class="wrap">
      <van-field
        v-model="form.orgPassword"
        type="password"
        placeholder="Please enter login password"
      />
      <van-field
        v-model="form.newPassword"
        type="password"
        placeholder="Please enter a new password"
      />
      <van-field
        v-model="form.confirmPassword"
        type="password"
        placeholder="Please enter a confirmation password"
      />

      <view class="btns">
        <van-button
          color="#5ec0b3"
          round
          block
          @click="submit"
          :loading="loading"
        >
          Complete modification
        </van-button>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      form: {
        confirmPassword: "",
        newPassword: "",
        orgPassword: "",
      },
      loading: false,
    };
  },
  onLoad() {},
  methods: {
    submit() {
      if (this.form.orgPassword === "") {
        return this.$base.show("Please enter login password");
      } else if (this.form.newPassword === "") {
        return this.$base.show("Please enter a new password");
      } else if (this.form.confirmPassword !== this.form.newPassword) {
        return this.$base.show(
          "Please enter a confirmation password"
        );
      }
      this.loading = true;
      this.$api.user_update_password(this.form).then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          this.$base.show(res.data.msg, "success");
          uni.redirectTo({
            url: "/pages/login",
          });
        } else {
          this.$base.show(res.data.msg);
        }
      });
    },
  },
};
</script>

<style scoped lang="scss">
.code {
  border-radius: 15upx;
  height: 60upx;
  .van-button__text {
    font-size: 24upx;
  }
}
.van-field__control {
  font-size: 30upx;
}
.van-cell::after {
  border-color: #b1bfc3;
}
.btns {
  padding: 40upx 40upx 0;
  .van-button {
    margin-bottom: 30upx;
  }
  .van-button__content span {
    font-size: 30upx;
  }
}
.image {
  height: 60upx;
}
</style>
