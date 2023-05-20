<template>
  <view class="page">
    <van-nav-bar
      title="绑定USDT地址"
      left-arrow
      :border="false"
      fixed
      placeholder
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
        <view v-if="bindStatus">
          <view class="bind-title">我的USDT钱包地址</view>
          <view class="bind-text">{{ addr }}</view>
        </view>
        <view v-else>
          <view class="usdt-title">请输入USDT钱包地址</view>
          <view class="from-input">
            <input
              type="text"
              class="input-text"
              v-model="addr"
              placeholder="请输入USDT钱包地址"
            />
          </view>
          <van-button class="btn-class" block @click="changeBind" :loading="loading">
            提交绑定
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
      addr:'',//usdt地址
      bindStatus:'',//是否绑定地址
    };
  },
  onShow(){
    this.getInfo()
  },
  methods: {
    // 绑定银行卡
    changeBind(){
      if (!this.addr) {
        return this.$base.show("请输入USDT钱包地址~");
      }
      this.loading = true
      this.$api.user_bindUSDT(
        {
          addr: this.addr,
        }
      ).then((res) => {
        this.loading = false
        if (res.data.code == 0) {
          this.getInfo()
        }
      });
    },
    //用户列表数据
    getInfo() {
      this.$api.user_info().then((res) => {
        if (res.data.code == 0) {
          this.bindStatus = res.data.data.walletAddr;
          this.addr = res.data.data.walletAddr;
        }
      });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  .bind-title{
    margin: 40upx 0;
    font-size: 36upx;
    text-align: center;
    font-weight: 600;
  }
  .bind-text{
    text-align: center;
    color: #577fab;
    font-size: 28upx;
    font-weight: 600;
  }
  .usdt-title {
    margin: 60upx 50upx;
    color: #577fab;
    font-weight: 600;
    font-size: 40upx;
  }
  .from-input {
    margin: 40upx;
    height: 90upx;
    .input-text {
      height: 64upx;
      border-bottom: 1px solid #bbb;
      font-size: 24upx;
      font-weight: 600;
    }
  }
}
</style>
