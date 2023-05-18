<template>
  <view class="page">
    <van-nav-bar
      title="register"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/login')"
    >
    </van-nav-bar>
    <view class="wrap">
      <van-field
        v-model="form.userPhone"
        type="tel"
        placeholder="Please enter a valid mobile phone number"
      >
        <template slot="label">
          <image :src="icon" mode="widthFix" class="image" />
          <view class="lable" @click="areaChange()">
            + {{ form.areaCode }}
          </view>
          <van-icon
            @click="areaChange()"
            style="padding-left: 10upx"
            name="arrow-down"
          />
        </template>
      </van-field>
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
      <van-field
        v-model="form.password"
        type="password"
        placeholder="login password"
      />
      <van-field
        v-model="form.confirmPassword"
        type="password"
        placeholder="Confirm login password"
      />
      <van-field
        v-model="form.paymentPwd"
        type="password"
        placeholder="Payment password"
      />
      <van-field
        v-model="confirmpaymentPwd"
        type="password"
        placeholder="Confirm payment password"
      />
      <van-field
        v-model="form.referralId"
        type="text"
        placeholder="Referral ID"
      />
      <view class="btns">
        <van-button color="#2ba589" @click="register" :loading="loading" block>
          Register
        </van-button>
        <van-button color="#ef264f" @click="login" block>
          Do you already have an account? Login immediately
        </van-button>
      </view>
    </view>
    <van-popup v-model="show" position="bottom">
      <van-picker
        title="País"
        show-toolbar
        :columns="columns"
        value-key="name"
        @cancel="show = false"
        @confirm="onConfirm"
        confirm-button-text="Confirm"
        cancel-button-text="Cancel"
      >
        <template #option="item">
          <view class="item">
            <view class="content">
              <image mode="widthFix" class="icon" :src="item.img" />
              <view class="code">{{ item.name }}</view>
            </view>
            <view class="code">+{{ item.code }}</view>
          </view>
        </template>
      </van-picker>
    </van-popup>
  </view>
</template>

<script>
let time = null;
export default {
  data() {
    return {
      code: 60,
      show: false,
      columns: [],
      codeloading: false,
      loading: false,
      confirmpaymentPwd: "",
      is_ok: true, //是否为第一次
      icon: "",
      form: {
        confirmPassword: "",
        password: "",
        userPhone: "",
        verificationCode: "",
        paymentPwd: undefined,
        referralId: undefined,
        areaCode: "",
      },
    };
  },
  onLoad() {
    this.form.referralId = this.$route.query.inviteCode;
    let date = null;
    date = setInterval(() => {
      uni.getStorage({
        key: "config",
        success: (val) => {
          if (val.data.contrys) {
            clearInterval(date);
            this.columns = val.data.contrys;
            if (this.columns.length) {
              this.areaCode = this.columns[0].code;
              this.icon = this.columns[0].img;
            }
          }
        },
      });
    }, 500);
  },
  methods: {
    areaChange() {
      this.show = true;
      if (this.is_ok) {
        this.is_ok = false;
        this.form.areaCode = this.columns[0].code;
      }
    },
    onConfirm(value, index) {
      this.form.areaCode = value.code;
      this.$store.code = value.code;
      this.icon = value.img;
      this.show = false;
    },
    codeChange() {
      this.$store.code = this.form.areaCode;
      if (!this.$base.phone(this.form.userPhone)) {
        return this.$base.show("Please enter a valid mobile phone number");
      }
      let DATA_OBJ = {
        phone: this.form.userPhone,
        type: 1,
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
    register() {
      this.$store.code = this.form.areaCode;
      if (!this.$base.phone(this.form.userPhone)) {
        return this.$base.show("Please enter a valid mobile phone number");
      } else if (this.form.areaCode === "") {
        return this.$base.show("Please select a country");
      } 
	  // else if (this.form.verificationCode.length < 6) {
   //      return this.$base.show("Phone verification code");
   //    } 
	  else if (this.form.password === "") {
        return this.$base.show("login password");
      } else if (this.form.confirmPassword !== this.form.password) {
        return this.$base.show("Confirm login password");
      } else if (this.form.paymentPwd === "") {
        return this.$base.show("payment password");
      } else if (this.confirmpaymentPwd !== this.form.paymentPwd) {
        return this.$base.show("Confirm payment password");
      }
      this.loading = true;
      this.$api.user_register(this.form).then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          this.$base.show(res.data.msg, "success");
          clearInterval(time);
          this.login();
        }
      });
    },
    login() {
      uni.redirectTo({
        url: "/pages/login",
      });
    },
  },
};
</script>

<style scoped lang="scss">
.item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 40upx;
  width: 100%;
  box-sizing: border-box;
  .content {
    display: flex;
    align-items: center;
    justify-content: center;
    .code {
      padding-left: 20upx;
    }
  }
  .icon {
    width: 60upx;
  }
  .code {
    font-size: 28upx;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}
/deep/.van-field__label {
  display: flex;
  align-items: center;
  .image {
    width: 50upx;
    padding-right: 10upx;
  }
}
.van-field__control::placeholder,
.van-field__control::-webkit-placeholder,
.van-field__control::-moz-placeholder,
.van-field__control:-ms-input-placeholder {
  color: #fff;
}

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
    border-radius: 10upx;
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
