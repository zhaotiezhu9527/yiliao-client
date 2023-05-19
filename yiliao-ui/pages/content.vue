<template>
  <view class="page">
    <van-nav-bar
      left-arrow
      placeholder
      :border="false"
      fixed
      title="立即投资"
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/investor')"
    >
    </van-nav-bar>
    <view class="cardStyle">
      <view class="card">
        <view class="item">
          <view>账户可用余额 (元)</view>
          <view>￥0元</view>
        </view>
        <view class="item">
          <view>项目可投金额 (元)</view>
          <view>￥667083444200元</view>
        </view>
      </view>
    </view>
    <view class="money">
      <view class="li">
        <view class="name">起投金额</view>
        <view class="txt">￥<text>300000</text>元</view>
      </view>
      <view class="li">
        <view class="name">投资金额</view>
        <view class="txt images">
          <image class="img" src="../static/img/jian.png" mode="widthFix" />
          <van-field placeholder="请输入金额" />
          <image class="img" src="../static/img/jia.png" mode="widthFix" />
        </view>
      </view>
      <view class="li end">
        <view class="btns">
          <van-button color="#f34133"> 一键全投 </van-button>
        </view>
      </view>
    </view>
    <view class="pass">
      最低起投<text>300000</text>元，加一次为<text>300000</text>元,<text
        class="other"
        >一键全投</text
      >即将账户可用余额<text>300000</text>元的倍数进行投资。
    </view>
    <view class="input">
      <van-field label="支付密码" placeholder="请输入支付密码" />
    </view>
    <view class="btn">
      <van-button color="#4b80af" block> 立即投资 </van-button>
    </view>
  </view>
</template>
<script>
export default {
  data() {
    return {};
  },
  onLoad() {},
  methods: {
    change() {
      uni.navigateTo({
        url: "/pages/content",
      });
    },
  },
};
</script>
<style scoped lang="scss">
view,
text {
  font-weight: 500;
}
.cardStyle {
  padding: 40upx 30upx;
  .card {
    background-color: #4c80b0;
    border-radius: 10upx;
    padding: 40upx;
    box-sizing: border-box;
    display: flex;
    color: #fff;
    justify-content: center;
    .item {
      width: 50%;
      text-align: center;
      view:nth-child(1) {
        font-size: 26upx;
      }
      view:nth-child(2) {
        padding-top: 40upx;
        font-size: 32upx;
      }
    }
  }
}
.money {
  padding: 0 30upx;
  .li {
    display: flex;
    justify-content: space-between;
    padding-bottom: 20upx;
    font-size: 32upx;
    &:nth-child(1) {
      border-bottom: 2upx solid #d3d3d3;
    }
    &:nth-child(2) {
      align-items: center;
    }
    text {
      color: #4c80b0;
    }
    &.end {
      padding-top: 20upx;
      width: 100%;
      display: flex;
      justify-content: flex-end;
    }
    .images {
      width: 60%;
      display: flex;
      align-items: center;
      justify-content: center;
      border-bottom: 2upx solid #d3d3d3;
      .img {
        width: 80upx;
        height: 80upx;
      }
      /deep/.van-cell::after {
        border: 0;
      }
      /deep/.van-field__label {
        span {
          font-weight: 500;
        }
      }
      /deep/.van-field__control {
        font-size: 32upx;
        color: #000;
        text-align: right;
        font-weight: 500;
      }
    }
    .btns {
      .van-button {
        padding: 0 60upx;
        border-radius: 10upx;
      }
    }
  }
}
.pass {
  font-size: 28upx;
  padding: 50upx 0 20upx;
  margin: 0 30upx;
  box-sizing: border-box;
  border-bottom: 2upx solid #d3d3d3;
  color: #ababab;
  text {
    color: #4c80b0;
    &.other {
      color: #000;
    }
  }
}
.input {
  /deep/.van-field__label {
    span {
      font-size: 32upx;
      color: #000;
      font-weight: 500;
    }
  }
  /deep/.van-field__control {
    font-size: 32upx;
    color: #000;
    text-align: right;
    font-weight: 500;
  }
}
.btn {
  padding: 10upx 30upx;
  width: 100%;
  box-sizing: border-box;
  .van-button {
    border-radius: 10upx;
  }
}
</style>
