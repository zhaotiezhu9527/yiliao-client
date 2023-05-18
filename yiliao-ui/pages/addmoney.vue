<template>
  <view class="page">
    <van-nav-bar
      title="Withdraw"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="list van-hairline--bottom">
        <view class="value">Network:</view>
        <view class="value">
          <van-radio-group v-model="radio" direction="horizontal">
            <van-radio checked-color="#e6a23c" :name="1">TRC20</van-radio>
          </van-radio-group>
        </view>
      </view>
      <view class="list van-hairline--bottom">
        <view class="value">Balance</view>
        <view class="value"> {{ info.balance }} </view>
      </view>
      <van-field
        v-model="form.money"
        type="number"
        placeholder="Please enter the withdraw quantity"
      />
      <van-field
        v-model="form.withdrawPwd"
        type="password"
        placeholder="Please enter the payment password"
      />
      <!-- <van-field
        v-model="form.verificationCode"
        type="digit"
        placeholder="Código de verificación del teléfono"
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
            {{ code < 60 ? code + "s" : "Enviar código" }}
          </van-button>
        </template>
      </van-field> -->
      <view class="tips" v-html="content">
        <!-- <view class="text">
          Cantidad mínima de retirada de efectivo 10 USDT</view
        >
        <view class="text">
          La tasa de transferencia de TRC20 es de 1USDT por transacción.
        </view>
        <view class="text">
          Por favor, confirme que la dirección de retirada ha sido vinculada.
        </view>
        <view class="text">
          la retirada se completará en un plazo de 24 horas después de las 17:00
          UTC
        </view> -->
      </view>
      <view class="btns">
        <van-button
          color="#4b80af"
          round
          block
          :loading="loading"
          @click="submit"
        >
          confirm
        </van-button>
      </view>
    </view>
    <van-dialog
      v-model="show"
      showCancelButton
      confirmButtonText="confirm"
      confirmButtonColor="#4b80af"
      cancelButtonText="cancel"
      @confirm="confirm"
      @cancel="cancel"
    >
      <view class="dialog">
        <view class="content">
          <view class="txt">{{ info.withdrawMsg }}</view>
        </view>
      </view>
    </van-dialog>
  </view>
</template>

<script>
let time = null;
export default {
  data() {
    return {
      code: 60,
      codeloading: false,
      loading: false,
      radio: 1,
      info: {},
      show: false,
      form: {
        money: "",
        // verificationCode: "",
        withdrawPwd: "",
        areaCode: "",
      },
      content: "",
    };
  },
  onLoad() {
    this.$api.user_info().then((res) => {
      if (res.data.code == 0) {
        this.info = res.data.data;
        if (this.info.withdrawAuth === 0) {
          this.show = true;
        }
      } else {
        this.$base.show(res.data.msg);
      }
    });
    this.form.areaCode = uni.getStorageSync("areaCode");
    uni.getStorage({
      key: "config",
      success: (val) => {
        this.content = val.data.withdrawRemake;
      },
    });
    this.$api.user_balance().then((res) => {
      if (res.data.code == 0) {
        this.info.balance = res.data.balance;
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
        type: 6,
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
      if (this.form.money === "") {
        return this.$base.show("Please enter the withdraw quantity");
      } else if (this.form.withdrawPwd.length !== 4) {
        return this.$base.show("Please enter your payment password");
      }
      // else if (this.form.verificationCode.length < 6) {
      //   return this.$base.show("Código de verificación del teléfono");
      // }
      this.loading = true;
      this.$api.user_withdraw(this.form).then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          this.$base.show(res.data.msg, "success");
          this.form = {
            money: "",
            // verificationCode: "",
            withdrawPwd: "",
          };
        } else {
          this.$base.show(res.data.msg);
        }
      });
    },
    confirm() {
      // #ifdef H5
      window.open(this.info.agentTelegramLink);
      // #endif
    },
    cancel() {
      this.$base.BackPage("/pages/personal");
    },
  },
};
</script>

<style scoped lang="scss">
.dialog {
  .content {
    padding: 40upx 30upx;
    text-align: center;
    .txt {
      font-size: 28upx;
    }
  }
}
.tips {
  padding: 20upx;
  // color: #c20000;
  // font-size: 24upx;
  // line-height: 2.5;
  .text {
    padding-left: 60upx;
    position: relative;
    &:nth-child(1)::before {
      content: "PS:";
      color: #c20000;
      font-size: 24upx;
      position: absolute;
      left: 10upx;
      top: 0;
    }
  }
}
.code {
  border-radius: 15upx;
  height: 60upx;
  .van-button__text {
    font-size: 28upx;
  }
}
/deep/.van-field__control {
  font-size: 28upx;
}
.van-cell::after {
  border-color: #b1bfc3;
}
.btns {
  padding: 0 40upx;
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
.van-cell::after {
  border-color: #ebedf0;
}
.list {
  display: flex;
  align-items: center;
  line-height: 110upx;
  margin: 0 30upx;
  .value {
    width: 40%;
    display: flex;
    align-items: center;
    font-size: 28upx;
    &:nth-child(2) {
      width: 60%;
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
