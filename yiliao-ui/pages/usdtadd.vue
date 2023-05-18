<template>
  <view class="page">
    <van-nav-bar
      title="Withdraw address"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="card">
        <image class="image" mode="widthFix" src="../static/images/bank.png" />
        <view class="content">
          <view class="txt">{{ info.userPhone }}</view>
          <view class="txt">TRC-20：{{ form.addr }}</view>
        </view>
      </view>
      <view class="list">
        <view class="value">Network:</view>
        <view class="value">
          <van-radio-group v-model="radio" direction="horizontal">
            <van-radio checked-color="#e6a23c" :name="1"> TRC-20 </van-radio>
          </van-radio-group>
        </view>
      </view>
      <van-field
        v-model="form.addr"
        type="text"
        placeholder="Withdraw address"
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
          :loading="loading"
          @click="submit"
        >
          Rebind the Withdraw address
        </van-button>
      </view>
      <view class="footer">
        <view class="text">
          The TRC20 transfer fee is 1USDT per transaction.
        </view>
        <!-- <view class="text">
          A taxa de transferência ERC20 é de 30USDT por transacção.
        </view> -->
      </view>
      <view class="tips" v-html="content"> </view>
    </view>
  </view>
</template>

<script>
let time = null;
export default {
  data() {
    return {
      radio: 1,
      codeloading: false,
      code: 60,
      loading: false,
      info: {},
      form: {
        addr: "",
        verificationCode: "",
        areaCode: "",
      },
    };
  },
  onLoad() {
    this.info = uni.getStorageSync("info");
    this.form.areaCode = uni.getStorageSync("areaCode");
    uni.getStorage({
      key: "config",
      success: (val) => {
        this.content = val.data.withdrawRemake;
      },
    });
    this.$api.user_getBindAddr().then((res) => {
      if (res.data.code == 0) {
        this.form.addr = res.data.addr;
      } else {
        this.$base.show(res.data.msg);
      }
    });
  },
  methods: {
    codeChange() {
      this.$store.code = uni.getStorageSync("areaCode");
      if (!this.$base.phone(this.info.userPhone)) {
        return this.$base.show("Please enter a valid mobile phone number");
      }
      let DATA_OBJ = {
        phone: this.info.userPhone,
        type: 5,
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
      if (this.form.addr === "") {
        return this.$base.show("Withdraw address");
      }
      // else if (this.form.verificationCode.length < 6) {
      //      return this.$base.show("Phone verification code");
      //    }
      this.loading = true;
      this.$api.user_bindAddr(this.form).then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          this.$base.show(res.data.msg, "success");
          // this.form.verificationCode = "";
        } else {
          this.$base.show(res.data.msg);
        }
      });
    },
  },
};
</script>

<style scoped lang="scss">
.card {
  padding: 10upx 30upx 0;
  display: flex;
  align-items: center;
  .image {
    width: 144upx;
  }
  .content {
    margin-left: 20upx;
    .txt {
      color: #404040;
      font-size: 24upx;
      &:nth-child(2) {
        padding-top: 20upx;
      }
    }
  }
}
.footer {
  padding: 30upx 20upx;
  box-sizing: border-box;
  background: #dff2f0;
  .text {
    font-size: 24upx;
    color: #404040;
    line-height: 1.2;
  }
}
.tips {
  padding: 50upx 20upx;
  // color: #c20000;
  // font-size: 24upx;
  // line-height: 1.5;
}

.code {
  border-radius: 15upx;
  height: 60upx;
  .van-button__text {
    font-size: 24upx;
  }
}
/deep/.van-field__control {
  font-size: 28upx;
}
.van-cell::after {
  border-color: #b1bfc3;
}
.btns {
  padding: 40upx 40upx 20upx;
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
.list {
  display: flex;
  align-items: center;
  line-height: 110upx;
  padding: 0 30upx;
  .value {
    display: flex;
    align-items: center;
    font-size: 28upx;
    &:nth-child(2) {
      padding-left: 30upx;
    }
  }
  /deep/.van-radio {
    margin-left: 16upx;
  }
  /deep/.van-radio__label {
    margin-left: 16upx;
    font-size: 28upx;
  }
}
</style>
