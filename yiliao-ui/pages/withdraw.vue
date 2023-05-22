<template>
  <view class="page">
    <van-nav-bar
      placeholder
      :border="false"
      fixed
      title="提现"
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
      <template #left>
        <van-icon name="arrow-left" size="18" />
      </template>
    </van-nav-bar>
    <view class="wrap">
      <view class="card-num" @click="show = true">
        <view class="flex">
          {{ title }}
          <van-icon class="icon" name="play" />
        </view>
      </view>
      <view class="card-num">
        <view style="display: flex" v-if="type === 2">
          USDT地址<label class="van-ellipsis">{{ infos.walletAddr }}</label>
        </view>
        <view v-else class="van-ellipsis">
          银行卡 <label>{{ infos.idCard }}</label>
        </view>
      </view>
      <view class="content">
        <view>提现金额</view>

        <view class="money">
          <label>¥</label>
          <van-field
            v-model="amount"
            type="number"
            placeholder="请输入提现金额"
          />
        </view>
        <view class="text">可提现金额{{ infos.balance }}元</view>
      </view>
      <view class="pay">
        <label>支付密码</label>
        <van-field v-model="pwd" type="digit" placeholder="请输入支付密码" />
      </view>
      <view class="btns">
        <van-button color="#4b80af" block @click="login" :loading="loading">
          确认提现
        </van-button>
      </view>
    </view>
    <van-popup
      v-model="show"
      round
      position="bottom"
      :style="{ height: '50%' }"
    >
      <van-picker
        :style="{ height: '100%' }"
        title="选择提现方式"
        show-toolbar
        :columns="columns"
        @confirm="onConfirm"
        @cancel="show = false"
      />
    </van-popup>
  </view>
</template>

<script>
export default {
  data() {
    return {
      columns: [
        { text: "提现到USDT钱包", value: 2 },
        { text: "提现到银行卡", value: 1 },
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
      this.title = e.text;
      this.type = e.value;
      this.show = false;
    },
    login() {
      if (!this.amount) {
        return this.$base.show("请输入提现金额~");
      } else if (!this.amount > this.infos.balance) {
        return this.$base.show("输入的金额不可大于可提现的金额~");
      } else if (!this.pwd || this.pwd.length < 6) {
        return this.$base.show("请输入支付密码~");
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
    margin-top: 20upx;
  }
  .card-num {
    width: 100%;
    height: 80upx;
    line-height: 80upx;
    padding-left: 15upx;
    background-color: #fff;
    margin-top: 20upx;
    box-sizing: border-box;
    label {
      margin-left: 20upx;
      width: calc(100% - 180upx);
    }
    .flex {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .icon {
      transform: rotate(90deg);
      padding-top: 30upx;
    }
  }
  .content {
    margin-top: 20upx;
    background-color: #fff;
    padding: 15upx;
    font-size: 24upx;
    .money {
      margin-top: 30upx;
      height: 50upx;
      display: flex;
      align-items: center;
      label {
        width: 80upx;
        font-size: 40upx;
        font-weight: 500;
        margin-left: 20upx;
      }
      input {
        font-size: 40upx;
      }
    }
    .text {
      font-size: 28upx;
      font-weight: 500;
      margin-top: 30upx;
    }
  }
  .pay {
    margin-top: 30upx;
    height: 60upx;
    display: flex;
    align-items: center;
    background-color: #fff;
    padding: 10upx;
    margin-bottom: 66upx;
    label {
      width: 180upx;
      font-weight: 500;
      font-size: 32upx;
      line-height: 48upx;
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
/deep/.van-cell {
  padding: 0;
}
/deep/.van-field__control {
  font-size: 32upx;
}
</style>
