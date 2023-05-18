<template>
  <view class="page">
    <van-nav-bar
      title="Deposit"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="title"> Deposit USDT</view>
      <view class="tabsStyle">
        <view class="tabs van-hairline--surround">
          <!-- active -->
          <view class="item">TRC20</view>
          <!-- <view class="item">ERC20</view> -->
        </view>
      </view>
      <view class="list">
        <view class="value w100">Wallet collection code</view>
      </view>
      <view class="imageStyle">
        <view class="image van-hairline--surround">
          <!-- <image src="../static/images/test.png" class="ewm" mode="widthFix" /> -->
          <uqrcode
            errorCorrectLevel="H"
            :size="124"
            ref="uQRCode"
            :text="form.addr"
          />
        </view>
      </view>
      <view class="list" style="padding-bottom: 20rpx">
        <view class="value w20"> Wallet address</view>
        <view class="value w60 van-ellipsis">
          {{ form.addr }}
        </view>
        <view class="value copy w20" @click="copyChange()"> copy </view>
      </view>
      <view class="input">
        <van-field
          v-model="form.amount"
          label="Montante"
          type="number"
          placeholder="Please input the investment amount"
        />
        <van-field
          v-model="form.txid"
          label="Txid"
          type="text"
          placeholder="Please input the investment txid"
        />
      </view>
      <view class="btns">
        <van-button
          :disabled="!form.addr"
          color="#4b80af"
          round
          block
          @click="submit"
        >
          Submit
        </van-button>
      </view>
      <van-divider
        :style="{
          color: '#4b80af',
          borderColor: '#4b80af',
          padding: '0 32upx',
          'font-size': '30upx',
          margin: '0',
        }"
      >
        Be careful
      </van-divider>
      <view class="tips">
        <view class="text"> Send only USDT to this deposit address. </view>
        <view class="text">
          *Sending coins or tokens other than USDT to this address may result in
          the loss of your deposit.
        </view>
        <view class="text">
          *Coins will be deposited after 1 network confirmations.
        </view>
        <view class="text"> *Minimum deposit amount 1. </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      items: "",
      form: {
        addr: "",
        amount: "",
        txid: "",
      },
    };
  },
  onLoad() {
    uni.showLoading({
      title: "carregamento",
    });
    this.$api.user_getTopupAddr().then((res) => {
      if (res.data.code == 0) {
        this.form.addr = res.data.data;
      } else {
        this.$base.show(res.data.msg);
      }
    });
  },
  methods: {
    submit() {
      if (this.form.amount === "") {
        return this.$base.show("Please input the investment amount");
      } else if (this.form.txid === "") {
        return this.$base.show("Please input the investment txid");
      }
      this.$api.user_topup(this.form).then((res) => {
        if (res.data.code == 0) {
          this.$base.show(res.data.msg, "success");
          this.form.txid = "";
          this.form.amount = "";
          uni.switchTab({
            url: "/pages/personal",
          });
        } else {
          this.$base.show(res.data.msg);
        }
      });
    },
    copyChange() {
      uni.setClipboardData({
        data: this.form.addr,
        success: () => {
          uni.hideToast();
          this.$base.show("Copiar com sucesso!", "success");
        },
      });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  padding: 126upx 0upx 50upx;
  .title {
    padding: 0 20upx;
    font-size: 30upx;
  }
}
.tabsStyle {
  padding-top: 20upx;
  // .van-hairline--surround::after {
  //   border-color: #e6a23c;
  // }
  .tabs {
    width: 90%;
    margin: 0 auto;
    display: flex;
    align-items: center;
    height: 70upx;
    .item {
      width: 100%;
      color: #fff;
      font-size: 30upx;
      display: flex;
      align-items: center;
      justify-content: center;
      height: 70upx;
      background: #073441;
      &.active {
        background: #e6a23c;
      }
    }
  }
}
.imageStyle {
  padding: 80upx 0 0;
  .image {
    padding: 20upx;
    width: 144px;
    height: 144px;
    box-sizing: border-box;
    margin: 0 auto;
    border-radius: 20upx;
    box-shadow: 0 0 20upx 2upx hsla(0, 0, 0, 0.1);
    &.van-hairline--surround::after {
      border-color: #62cbb7;
      border-radius: 40upx;
    }
  }
  .ewm {
    width: 100%;
  }
}
.list {
  width: 100%;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  line-height: 40upx;
  padding: 60upx 20upx 0;
  .title {
    font-size: 28upx;
    width: 20%;
  }
  .value {
    width: 50%;
    font-size: 28upx;
    color: #404040;
    &.w20 {
      width: 20%;
    }
    &.w60 {
      font-size: 24upx;
      width: 60%;
    }
    &.w100 {
      width: 100%;
    }
    &.copy {
      text-align: right;
      padding-right: 30rpx;
      font-weight: 700;
      color: #4b80af;
      box-sizing: border-box;
    }
  }
}
.btns {
  padding: 15upx 40upx 20upx;
  /deep/.van-button {
    height: 100upx;
  }

  .van-button__content span {
    font-size: 30upx;
  }
}
.tips {
  padding: 40upx 32upx;
  color: #404040;
  font-size: 24upx;
  line-height: 2.5;
  .text {
    position: relative;
  }
}
</style>
