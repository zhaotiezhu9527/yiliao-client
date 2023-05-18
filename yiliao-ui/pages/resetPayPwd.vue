<template>
  <view class="page">
    <van-nav-bar
      title="Reset transaction password"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/set')"
    >
    </van-nav-bar>
    <view class="wrap">
      <van-field
        v-model="form.confirmPassword"
        type="password"
        placeholder="Please enter a new password"
      />
      <van-field
        v-model="form.newPassword"
        type="password"
        placeholder="Please enter a confirmation password"
      />

      <!-- <van-field
        v-model="form.verificationCode"
        type="digit"
        placeholder="Phone verification code"
      >
        <template #button>
          <van-button
            class="code"
            color="#2ba589"
            size="small"
            @click="codeChange"
            :loading="codeloading"
            :disabled="code < 60"
          >
            {{ code < 60 ? code + "s" : "Send code" }}
          </van-button>
        </template>
      </van-field> -->

      <view class="btns">
        <van-button
          color="#4b80af"
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
let time = null;
export default {
  data() {
    return {
      form: {
        confirmPassword: "",
        newPassword: "",
        verificationCode: "",
        areaCode: "",
      },
      code: 60,
      codeloading: false,
      loading: false,
    };
  },
  onLoad() {
    this.form.areaCode = uni.getStorageSync("areaCode");
  },
  methods: {
    codeChange() {
      this.$store.code = uni.getStorageSync("areaCode");
      let vim = uni.getStorageSync("phone");
      if (!this.$base.phone(vim)) {
        return this.$base.show("Please enter a valid mobile phone number");
      }
      let DATA_OBJ = {
        phone: vim,
        type: 4,
        areaCode: this.$store.code,
      };
      this.codeloading = true;
      this.$api.sms_send(DATA_OBJ).then((res) => {
        this.codeloading = false;
        if (res.data.code == 0) {
          this.$base.show(res.data.msg, "success");
          time = setInterval(() => {
            this.code--;
            if (this.code <= 0) {
              clearInterval(time);
              this.code = 60;
            }
          }, 1000);
        } else {
          this.$base.show(res.data.msg);
        }
      });
    },
    submit() {
      if (this.form.confirmPassword === "") {
        return this.$base.show("Please enter a confirmation password");
      } else if (this.form.newPassword === "") {
        return this.$base.show("Please enter a new password");
      }
      // else if (this.form.verificationCode.length < 6) {
      //      return this.$base.show("Phone verification code");
      //    }
      this.loading = true;
      this.$api.user_update_withdraw_pwd(this.form).then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          this.$base.show(res.data.msg, "success");
          this.form = {
            confirmPassword: "",
            newPassword: "",
            verificationCode: "",
          };
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
