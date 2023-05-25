<template>
  <view class="page">
    <u-navbar
      placeholder
      title="提现"
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
      <view class="card-num" @click="show = true">
        <view class="flex">
          {{ title }}
          <u-icon class="icon" color="#000" size="14" name="arrow-down-fill" />
        </view>
      </view>
      <view class="card-num">
        <view style="display: flex" v-if="type === 2">
          USDT地址<label class="u-line-1">{{ infos.walletAddr }}</label>
        </view>
        <view v-else class="u-line-1">
          银行卡 <label>{{ infos.bankCardNum }}</label>
        </view>
      </view>
      <view class="content">
        <view>提现金额</view>

        <view class="money">
          <label>¥</label>
          <u-input
            v-model="amount"
            border="none"
            type="number"
            placeholder="请输入提现金额"
            @input="update"
          />
        </view>
        <view class="text">可提现金额{{ infos.balance }}元</view>
      </view>
      <view class="pay">
        <label>支付密码</label>
        <u-input
          v-model="pwd"
          border="none"
          password
          placeholder="请输入支付密码"
        />
      </view>
      <view class="btns">
        <u-button
          class="btn-class"
          color="#4b80af"
          block
          @click="login"
          :loading="loading"
        >
          确认提现
        </u-button>
      </view>
    </view>
    <u-picker
      title="选择提现方式"
      :show="show"
      show-toolbar
      :style="{ height: '50%' }"
      :columns="columns"
      itemHeight="90"
      confirmColor="#4b80af"
      @confirm="onConfirm"
      @cancel="show = false"
    />
  </view>
</template>

<script>
export default {
  data() {
    return {
      columns: [
        [
          { text: "提现到USDT钱包", value: 2 },
          { text: "提现到银行卡", value: 1 },
        ],
      ],
      show: false,
      type: 2,
      amount: undefined,
      pwd: "",
      title: "提现到USDT钱包",
      infos: {},
      loading: false,
    };
  },
  async onShow() {
    await this.$onLaunched;
    this.infos = uni.getStorageSync("infos");
  },
  methods: {
    onConfirm(e) {
      this.title = e.value[0].text;
      this.type = e.value[0].value;
      this.show = false;
    },
    update(value) {
      if (!value) return false;
      if (Number(value) >= this.infos.balance) {
        this.amount = Number(this.infos.balance);
      } else if (Number(value) <= 0) {
        this.amount = 0;
      } else {
        this.amount = value;
      }
    },
    login() {
      if (!this.amount) {
        return this.$base.show("请输入提现金额~");
      } else if (!this.amount > this.infos.balance) {
        return this.$base.show("输入的金额不可大于可提现的金额~");
      } else if (!this.pwd || this.pwd.length < 6) {
        return this.$base.show("请输入正确的支付密码~");
      } else if(!this.infos.bankCardNum && this.type === 1){
        return this.$base.show("请先绑定银行卡~");
      } else if(!this.infos.walletAddr && this.type === 2){
        return this.$base.show("请先绑定USDT地址~");
      }
      const DATA_OBJ = {
        type: this.type,
        amount: this.amount,
        pwd: this.pwd,
      };
      this.loading = true;
      this.$api.user_withdraw(DATA_OBJ).then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          this.$base.show(res.data.msg);
          this.amount = undefined;
          this.pwd = "";
        }
      });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  background-color: #f7f8fa;
  height: 100%;
  .select {
    margin-top: 20rpx;
  }
  .card-num {
    width: 100%;
    height: 80rpx;
    line-height: 80rpx;
    padding-left: 15rpx;
    background-color: #fff;
    margin-top: 20rpx;
    box-sizing: border-box;
    label {
      margin-left: 20rpx;
      width: calc(100% - 180rpx);
    }
    .flex {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .icon {
      padding-right: 30rpx;
    }
  }
  .content {
    margin-top: 20rpx;
    background-color: #fff;
    padding: 15rpx;
    font-size: 24rpx;
    .money {
      margin-top: 30rpx;
      height: 50rpx;
      display: flex;
      align-items: center;
      label {
        width: 80rpx;
        font-size: 40rpx;
        font-weight: 500;
        margin-left: 20rpx;
      }
      input {
        font-size: 40rpx;
      }
    }
    .text {
      font-size: 28rpx;
      font-weight: 500;
      margin-top: 30rpx;
    }
  }
  .pay {
    margin-top: 30rpx;
    height: 80rpx;
    display: flex;
    align-items: center;
    background-color: #fff;
    padding: 10rpx;
    margin-bottom: 66rpx;
    label {
      width: 180rpx;
      font-weight: 500;
      font-size: 32rpx;
      line-height: 48rpx;
    }
  }
}
.btns {
  padding: 40rpx 0 0;
}
</style>
