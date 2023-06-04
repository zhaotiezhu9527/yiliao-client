<template>
  <view class="page">
    <u-navbar
      placeholder
      title="银行卡绑定"
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
      <view v-if="bindStatus" class="card-box">
        <view class="card">
          <view class="card-name">{{ bankName }}</view>
          <view class="card-text">储蓄卡</view>
          <view class="card-num">{{ bankCardNum }}</view>
        </view>
      </view>
      <view v-else>
        <view class="usdt-title">添加银行卡</view>
        <view class="from-input">
          <input
            type="text"
            class="input-text"
            v-model="bankName"
            placeholder="请输入所属银行，如：中国工商银行"
          />
        </view>
        <view class="from-input">
          <input
            type="text"
            class="input-text"
            v-model="addr"
            placeholder="请输入支行信息，如：北京朝阳支行"
          />
        </view>
        <view class="from-input">
          <input
            type="text"
            v-model="bankCardNum"
            class="input-text"
            placeholder="请输入储蓄卡号"
          />
        </view>
        <u-button
          class="btn-class"
          block
          @click="changeBind"
          :loading="loading"
        >
          提交绑定
        </u-button>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      bankName: "", //银行名称
      bankCardNum: "", //银行卡号
      addr: "", //支行
      loading: false,
      bindStatus: "", //银行卡绑定状态
    };
  },
  onShow() {
    this.getInfo()
  },
  methods: {
    // 绑定银行卡
    changeBind() {
      if (!this.bankName) {
        return this.$base.show("请输入所属银行~");
      } else if (!this.bankCardNum) {
        return this.$base.show("请输入银行卡号~");
      } else if (!this.addr) {
        return this.$base.show("请输入支行信息~");
      }
      this.loading = true;
      this.$api
        .user_bindBank({
          addr: this.addr,
          bankName: this.bankName,
          cardNo: this.bankCardNum,
        })
        .then((res) => {
          if (res.data.code == 0) {
            this.getInfo();
          }
        })
        .finally(() => {
          this.loading = false;
        });
    },
    //用户列表数据
    getInfo() {
      this.$api.user_info().then((res) => {
        if (res.data.code == 0) {
          this.bindStatus = res.data.data.bankName;
          this.bankName = res.data.data.bankName;
          this.bankCardNum = res.data.data.bankCardNum;
        }
      });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  .usdt-title {
    margin: 60rpx 50rpx;
    color: #577fab;
    font-weight: 500;
    font-size: 40rpx;
  }
  .from-input {
    margin: 40rpx;
    height: 90rpx;
    .input-text {
      height: 64rpx;
      font-size: 28rpx;
    }
  }
  .card-box {
    margin: 40rpx 30rpx;
    .card {
      padding: 30rpx 20rpx;
      height: 320rpx;
      background-image: linear-gradient(#769ed6, #5375ae);
      color: #fff;
      .card-name {
        font-size: 28rpx;
      }
      .card-text {
        font-size: 12px;
        line-height: 160rpx;
      }
      .card-num {
        font-size: 50rpx;
        font-weight: 600;
      }
    }
  }
}
</style>
