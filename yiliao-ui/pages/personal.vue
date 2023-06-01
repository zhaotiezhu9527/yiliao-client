<template>
  <view class="page">
    <u-navbar
      placeholder
      title="个人中心"
      :border="false"
      autoBack
      fixed
      leftIconSize="0"
      safe-area-inset-top
      bgColor="#4b80af"
      height="52px"
      titleStyle="color:#fff;font-weight:500;font-size:32rpx;"
    >
    </u-navbar>
    <scroll-view scroll-y class="scroll">
      <view class="wrap">
        <!-- 头部 -->
        <view class="head">
          <view class="head-text">
            <label>我的账户：{{ userData.userName }}</label>
            <label>用户等级：{{ userData.userLevelName }}</label>
          </view>
          <view class="head-money">
            ¥ {{ userData.balance }}
          </view>
          <view class="head-integral"> 账户余额 ≈ ${{userData.usdtAmount}}</view>
        </view>
        <view class="money">
          <view class="interest">
            <view>{{ userData.waitReturnInterest }}</view>
            <view>待收利息（元）</view>
          </view>
          <view class="principal">
            <view>{{ userData.waitReturnPrincipal }}</view>
            <view>待收本金（元）</view>
          </view>
        </view>
        <!-- 按钮 -->
        <view class="button-box">
          <u-button class="button-class" @click="pathChange">充值</u-button>
          <u-button class="button-class" @click="goWithdraw">提现</u-button>
        </view>
        <!-- 列表 -->
        <view class="list">
          <view
            v-if="config.ouyi_download_url"
            class="list-item"
            @click="downloadChange(config.ouyi_download_url)"
          >
            <image class="icon-img" src="../static/img/okx_app_icon.png" />
            <label>欧易安卓下载</label>
            <view class="icon"></view>
          </view>
          <view
            v-if="config.bian_download_url"
            class="list-item"
            @click="downloadChange(config.bian_download_url)"
          >
            <image class="icon-img" src="../static/img/bian_app_icon.png" />
            <label>币安安卓下载</label>
            <view class="icon"></view>
          </view>
          <view
            v-if="config.huobi_download_url"
            class="list-item"
            @click="downloadChange(config.huobi_download_url)"
          >
            <image class="icon-img" src="../static/img/icon_app_huobi.png" />
            <label>火币APP下载</label>
            <view class="icon"></view>
          </view>
          <view class="list-item" @click="sign">
            <image class="icon-img" src="../static/img/mine_func_qiandao.png" />
            <label>每日签到</label>
            <view class="icon"></view>
          </view>
          <view class="list-item" @click="goFundDetails">
            <image class="icon-img" src="../static/img/mine_func_zijin.png" />
            <label>资金明细</label>
            <view class="icon"></view>
          </view>
          <view class="list-item" @click="goInvestmentRecords">
            <image class="icon-img" src="../static/img/mine_func_touzi.png" />
            <label>投资记录</label>
            <view class="icon"></view>
          </view>
          <view class="list-item" @click="goRevenueRecords">
            <image class="icon-img" src="../static/img/mine_func_shouyi.png" />
            <label>收益记录</label>
            <view class="icon"></view>
          </view>
          <view class="list-item" @click="goRechargeRecord">
            <image
              class="icon-img"
              src="../static/img/mine_func_chongzhi.png"
            />
            <label>充值记录</label>
            <view class="icon"></view>
          </view>
          <view class="list-item" @click="goWithdrawalRecords">
            <image class="icon-img" src="../static/img/mine_func_tixian.png" />
            <label>提现记录</label>
            <view class="icon"></view>
          </view>
        </view>
        <view class="list">
          <view class="list-item" @click="goAccountSafe">
            <image class="icon-img" src="../static/img/mine_func_anquan.png" />
            <label>账户安全</label>
            <view class="icon"></view>
          </view>
          <view
            class="list-item"
            @click="goBindBank(userData.bankName, userData.bankCardNum)"
          >
            <image class="icon-img" src="../static/img/mine_func_yinhang.png" />
            <label>银行卡绑定</label>
            <view class="icon"></view>
          </view>
          <view class="list-item" @click="goRealName">
            <image class="icon-img" src="../static/img/mine_func_shiming.png" />
            <label>实名认证</label>
            <view class="icon"></view>
          </view>
          <view class="list-item" @click="goBindUSDT">
            <image class="icon-img" src="../static/img/mine_func_usdt.png" />
            <label>USDT绑定地址</label>
            <view class="icon"></view>
          </view>
        </view>
        <!-- 退出登录 -->
        <u-button class="logout" @click="show = true">退出登录</u-button>
      </view>
    </scroll-view>
    <u-modal
      :show="show"
      title="退出登陆"
      @confirm="modalChange"
      @cancel="show = false"
      ref="uModal"
      :asyncClose="true"
      showCancelButton
      confirmColor="#4b80af"
    >
      <view class="content">你确定退出吗？</view>
    </u-modal>
  </view>
</template>

<script>
export default {
  data() {
    return {
      show: false,
      userData: {
        userName: "xxxxxx", //用户名
        userLevelName: "xxxxxx", //会员等级
        balance: 0, //余额
        integral: "", //积分
        waitReturnInterest: 0, //待收利息
        waitReturnPrincipal: 0, //待收本金
        bankName: "", //银行名称
        bankCardNum: "", //银行卡号
        usdtAmount: "",//usdt
      },
      loading: false,
      items: {},
      pwd: "",
      config: {},
    };
  },
  async onLoad() {
    await this.$onLaunched;
    this.config = uni.getStorageSync("system_config");
  },
  onShow() {
    this.getInfo();
  },
  methods: {
    pathChange() {
      uni.navigateTo({
        url: "/pages/onlineService",
      });
    },
    downloadChange(url) {
      // #ifdef APP-PLUS
      plus.runtime.openURL(url);
      // #endif
      // #ifdef H5
      window.open(url);
      // #endif
    },
    goFundDetails() {
      uni.navigateTo({
        url: "/pages/fundDetails",
      });
    },
    goInvestmentRecords() {
      uni.navigateTo({
        url: "/pages/InvestmentRecords",
      });
    },
    goRevenueRecords() {
      uni.navigateTo({
        url: "/pages/RevenueRecords",
      });
    },
    goRechargeRecord() {
      uni.navigateTo({
        url: "/pages/RechargeRecord",
      });
    },
    goWithdrawalRecords() {
      uni.navigateTo({
        url: "/pages/WithdrawalRecords",
      });
    },
    goRealName() {
      uni.navigateTo({
        url: "/pages/RealName",
      });
    },
    goBindUSDT() {
      uni.navigateTo({
        url: "/pages/BindUSDT",
      });
    },
    goBindBank() {
      uni.navigateTo({
        url: "/pages/BindBank",
      });
    },
    goAccountSafe() {
      uni.navigateTo({
        url: "/pages/AccountSafe",
      });
    },
    goWithdraw() {
      if (!this.userData.bankCardNum && !this.userData.walletAddr) {
        return this.$base.show("请先绑定一种提款方式~");
      } else {
        uni.navigateTo({
          url: "/pages/withdraw",
        });
      }
    },
    // 退出登陆
    modalChange() {
      this.$api.user_logout().then((res) => {
        if (res.data.code == 0) {
          uni.removeStorage({
            key: "token",
            success: function (res) {
              uni.redirectTo({ url: "/pages/login" });
            },
          });
        }
      });
    },
    //用户列表数据
    getInfo() {
      this.$api.user_info().then((res) => {
        if (res.data.code == 0) {
          this.userData = res.data.data;
          this.$base.storage("infos", res.data.data);
        }
      });
    },
    //每日签到
    sign() {
      this.$api.user_sign().then((res) => {
        if (res.data.code == 0) {
          this.$base.show(res.data.msg + "~");
          // 等待提示成功再进行加载
          setTimeout(() => {
            this.getInfo();
          }, 2000);
        }
      });
    },
  },
};
</script>

<style scoped lang="scss">
.scroll {
  height: calc(100vh - 210rpx + var(--status-bar-height));
}
.wrap {
  background-color: #f8f8f9;
  padding-bottom: 40rpx;
  .head {
    height: 258rpx;
    background-image: linear-gradient(#759dd6, #5d80b9);
    color: #fff;
    .head-text {
      padding: 16rpx;
      font-size: 26rpx;
      label {
        margin-right: 20rpx;
      }
    }
    .head-money {
      width: 100%;
      font-size: 60rpx;
      line-height: 60rpx;
      text-align: center;
      font-weight: 500;
      margin-top: 5px;
    }
    .head-integral {
      width: 100%;
      text-align: center;
      margin-top: 20px;
    }
  }
  .money {
    width: 100%;
    height: 90rpx;
    background-color: #5874a9;
    font-size: 24rpx;
    color: #fff;
    align-items: center;
    vertical-align: middle;
    display: flex;
    .interest {
      border-right: 1px solid #ccc;
      flex: 1;
      text-align: center;
    }
    .principal {
      flex: 1;
      text-align: center;
    }
  }
  .button-box {
    width: 100%;
    margin: auto;
    margin-top: 10rpx;
    display: flex;
    justify-content: space-between;
    align-items: center;
    .button-class {
      background-color: #5780ab;
      color: #fff;
      font-weight: blod;
      font-size: 28rpx;
      border-radius: 15rpx;
      width: 48%;
      height: 90rpx;
      line-height: 90rpx;
      margin: 0 1%;
    }
  }
  .list {
    width: 92%;
    margin: auto;
    background-color: #fff;
    border-radius: 20rpx;
    margin-top: 24rpx;
    color: #666;
    .list-item {
      display: flex;
      align-items: center;
      vertical-align: middle;
      font-size: 26rpx;
      font-weight: 500;
      height: 116rpx;
      margin: 0 30rpx;
      border-bottom: 1px solid #eee;
      &:last-child {
        border: none;
      }
      .icon-img {
        width: 40rpx;
        height: 40rpx;
        margin-right: 20rpx;
      }
      label {
        flex: 1;
      }
      .icon {
        width: 10px;
        height: 10px;
        position: relative;
        &:after {
          content: "";
          width: 8px;
          height: 8px;
          position: absolute;
          right: 5px;
          bottom: 5px;
          border-left: 2px solid #666;
          border-bottom: 2px solid #666;
          transform: translate(0, 50%) rotate(-135deg);
          -webkit-transform: translate(0, 50%) rotate(-135deg);
        }
      }
    }
  }
  .logout {
    display: block;
    width: 92%;
    height: 84rpx;
    text-align: center;
    line-height: 84rpx;
    margin: 30rpx auto;
    border-radius: 40rpx;
    color: #fff;
    background-color: #e15241;
  }
}
.content {
  padding: 40upx 0;
  text-align: center;
}
</style>
