<template>
  <view class="page">
    <van-nav-bar title="个人中心" :border="false" fixed safe-area-inset-top>
    </van-nav-bar>
    <view class="wrap">
      <!-- 头部 -->
      <view class="head">
        <view class="head-text">
          <label>我的账户：test08</label>
          <label>用户登记：普通会员</label>
        </view>
        <view class="head-money">
          319022
        </view>
        <view class="head-integral">
          账户余额（元）账户积分（0）
        </view>
      </view>
      <view class="money">
        <view class="interest">
          <view>0</view>
          <view>待收利息（元）</view>
        </view>
        <view class="principal">
          <view>0</view>
          <view>待收本金（元）</view>
        </view>
      </view>
      <!-- 按钮 -->
      <view class="button-box">
        <van-button class="button-class">充值</van-button>
        <van-button class="button-class" @click="goWithdraw">提现</van-button>
      </view>
      <!-- 列表 -->
      <view class="list">
        <view class="list-item">
          <image class="icon-img" src="../static/img/okx_app_icon.png" />
          <label>欧易安卓下载</label>
          <view class="icon"></view>
        </view>
        <view class="list-item">
          <image class="icon-img" src="../static/img/bian_app_icon.png" />
          <label>币安安卓下载</label>
          <view class="icon"></view>
        </view>
        <view class="list-item">
          <image class="icon-img" src="../static/img/icon_app_huobi.png" />
          <label>火币APP下载</label>
          <view class="icon"></view>
        </view>
        <view class="list-item">
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
          <image class="icon-img" src="../static/img/mine_func_chongzhi.png" />
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
        <view class="list-item" @click="goBindBank">
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
      <van-button class="logout">退出登录</van-button>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      list: [
        {
          name: "Notice",
          path: "/pages/notice",
          icon: "d12",
        },
        {
          name: "Account details",
          path: "/pages/accountDetails",
          icon: "d10",
        },
        {
          name: "My investment",
          path: "/pages/investment",
          icon: "d9",
        },
        {
          name: "Interest statement",
          path: "/pages/interest",
          icon: "d8",
        },
        {
          name: "Fund flow",
          path: "/pages/fundRecord",
          icon: "d7",
        },
        {
          name: "Membership program",
          path: "/pages/memberPlan",
          icon: "d6",
        },
        {
          name: "Change password",
          path: "/pages/set",
          icon: "d5",
        },
        {
          name: "Withdraw wallet address",
          path: "/pages/usdtadd",
          icon: "d4",
        },
      ],
      loading: false,
      items: {},
      pwd: "",
    };
  },
  onTabItemTap(e) {
  },
  onLoad() {
    
  },
  methods: {
    goFundDetails() {
      uni.navigateTo({
        url: "/pages/fundDetails",
      });
    },
    goInvestmentRecords(){
      uni.navigateTo({
        url: "/pages/InvestmentRecords",
      });
    },
    goRevenueRecords(){
      uni.navigateTo({
        url: "/pages/RevenueRecords",
      });
    },
    goRechargeRecord(){
      uni.navigateTo({
        url: "/pages/RechargeRecord",
      });
    },
    goWithdrawalRecords(){
      uni.navigateTo({
        url: "/pages/WithdrawalRecords",
      });
    },
    goRealName(){
      uni.navigateTo({
        url: "/pages/RealName",
      });
    },
    goBindUSDT(){
      uni.navigateTo({
        url: "/pages/BindUSDT",
      });
    },
    goBindBank(){
      uni.navigateTo({
        url: "/pages/BindBank",
      });
    },
    goAccountSafe(){
      uni.navigateTo({
        url: "/pages/AccountSafe",
      });
    },
    goWithdraw(){
      uni.navigateTo({
        url: "/pages/withdraw",
      });
    }
  },
};
</script>

<style scoped lang="scss">
.wrap {
  padding-top: 104upx;
  background-color: #f8f8f9;
  padding-bottom: 40upx;
  .head{
    height: 258upx;
    background-image: linear-gradient(#759dd6, #5d80b9);
    color: #fff;
    .head-text{
      padding: 16upx;
      font-size: 26upx;
      label{
        margin-right: 20upx;
      }
    }
    .head-money{
      width: 100%;
      font-size: 60upx;
      line-height: 60upx;
      text-align: center;
      font-weight: 600;
      margin-top: 5px;
    }
    .head-integral{
      width: 100%;
      text-align: center;
      margin-top: 20px;
    }
  }
  .money{
    width: 100%;
    height: 90upx;
    background-color: #5874a9;
    font-size: 24upx;
    color: #fff;
    align-items: center;
    vertical-align: middle;
    display: flex;
    .interest{
      border-right: 1px solid #ccc;
      flex: 1;
      text-align: center;
    }
    .principal{
      flex: 1;
      text-align: center;
    }
  }
  .button-box{
    width: 100%;
    margin: auto;
    margin-top: 10upx;
    .button-class{
      background-color: #5780ab;
      color: #fff;
      font-weight: blod;
      font-size: 28upx;
      border-radius: 15upx;
      width: 48%;
      height: 90upx;
      line-height: 90upx;
      margin: 0 1%;
    }
  }
  .list{
    width: 92%;
    margin: auto;
    background-color: #fff;
    border-radius: 20upx;
    margin-top: 24upx;
    color: #666;
    .list-item{
      display: flex;
      align-items: center;
      vertical-align: middle;
      font-size: 24upx;
      font-weight: 600;
      height: 116upx;
      margin: 0 30upx;
      border-bottom: 1px solid #eee;
      &:last-child{
        border: none;
      }
      .icon-img{
        width: 40upx;
        height: 40upx;
        margin-right: 20upx;
      }
      label{
        flex: 1;
      }
      .icon{
        width: 10px;
        height: 10px;
        position: relative;
        &:after{
          content: '';
          width: 8px;
          height: 8px;
          position: absolute;
          right: 5px;
          bottom: 5px;
          border-left: 2px solid #666;
          border-bottom: 2px solid #666;
          transform: translate(0,50%) rotate(-135deg);
          -webkit-transform: translate(0,50%) rotate(-135deg);
        }
      }
    }
  }
  .logout{
    display: block;
    width: 92%;
    height: 84upx;
    margin: 30upx auto;
    border-radius: 40upx;
    color: #fff;
    background-color: #e15241;
  }
}
</style>
