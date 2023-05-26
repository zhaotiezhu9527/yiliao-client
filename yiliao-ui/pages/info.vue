<template>
  <view class="page">
    <u-navbar
      placeholder
      title="投资详情"
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
      <view class="list">
        <view class="view">
          <image class="img" :src="items.img" mode="widthFix" />
          <view class="name">
            <text>{{ items.projectName }}</text>
          </view>
          <view class="rate">
            <view class="li">
              <view class="con">项目规模</view>
              <view class="num">
                ￥<text>{{ items.projectAmount }} </text>
                元
              </view>
            </view>
            <view class="li">
              <view class="con">每份分红</view>
              <view class="num"
                ><text>{{ (items.minAmount * (items.incomeRate/100)).toFixed(0) }}</text
                >元起</view
              >
            </view>
            <view class="li">
              <view class="con">投资周期</view>
              <view class="num"
                ><text>{{ items.limitTime }}</text
                >分钟</view
              >
            </view>
          </view>
          <view class="investor">
            <view class="con">
              <text>分红方式：每日返息，到期还本</text>
              <text>起投金额：{{ items.minAmount }}元</text>
              <text>担保机构：{{ items.guaranteeCompany }}</text>
              <text>投资零风险：本金保障</text>
            </view>
          </view>
          <view class="progress">
            <view class="number">{{ items.schedule }}%</view>
            <u-line-progress
              height="40"
              :percentage="scheduleFn(items.schedule)"
              :showText="false"
              activeColor="#2196f3"
            ></u-line-progress>
          </view>
        </view>
      </view>
      <view class="tabs">
        <view class="name">投资详情</view>
        <u-row class="row">
          <u-col class="col" span="4">项目名称</u-col>
          <u-col class="col" span="8">{{ items.projectName }}</u-col>
        </u-row>
        <u-row class="row">
          <u-col class="col" span="4">项目金额:</u-col>
          <u-col class="col" span="8"
            ><text>{{ items.projectAmount }}</text
            >元人民币</u-col
          >
        </u-row>
        <u-row class="row">
          <u-col class="col" span="4">每天分红:</u-col>
          <u-col class="col" span="8"
            ><text>按每日{{ items.incomeRate }}%的收益 (保本保息)</text></u-col
          >
        </u-row>
        <u-row class="row">
          <u-col class="col" span="4">投资金额:</u-col>
          <u-col class="col" span="8"
            ><text>最低起投{{ items.minAmount }}元</text></u-col
          >
        </u-row>
        <u-row class="row">
          <u-col class="col" span="4">项目期限:</u-col>
          <u-col class="col" span="8"
            ><text>分期定投，每期{{ items.limitTime }}分钟</text></u-col
          >
        </u-row>
        <u-row class="row">
          <u-col class="col" span="4">收益计算:</u-col>
          <u-col class="col" span="8"
            ><text>每天保底分红 利率*本金</text></u-col
          >
        </u-row>
        <u-row class="row">
          <u-col class="col" span="4">还款方式:</u-col>
          <u-col class="col" span="8">每日返息，到期还本，节假日照常收益</u-col>
        </u-row>
        <u-row class="row">
          <u-col class="col" span="4">结算时间:</u-col>
          <u-col class="col" span="8"
            >当天投资，当天到期自动结算收益(例如在15:00成功投资，则在投资期限到期收到分红和本金)，到期系统会把兰日分红和产品本金一起返还到您的账户当中。
          </u-col>
        </u-row>
        <u-row class="row">
          <u-col class="col" span="4">可投金额:</u-col>
          <u-col class="col" span="8"
            >投资期间只要产品未投满，投资者均可自由投资。</u-col
          >
        </u-row>
        <u-row class="row">
          <u-col class="col" span="4">安全保障:</u-col>
          <u-col class="col" span="8"
            >{{
              items.guaranteeCompany
            }}对平台上的每一笔投资提供<text>100%本金保障</text>平台设立风险备用金，对本金承诺全额垫付;
          </u-col>
        </u-row>
        <u-row class="row">
          <u-col class="col" span="4">项目概述:</u-col>
          <u-col class="col" span="8"
            ><text
              >本项目筹集资金{{
                items.projectAmount
              }}元人民币，所筹集资金用于该项目直投运作作为投资者分红固定且无任何风险，集为投资份红围庆昌香括保风曾，所承担。
            </text></u-col
          >
        </u-row>
      </view>
    </view>
    <view class="btn">
      <u-button color="#4b80af" class="btn-class" block @click="change">
        马上投资
      </u-button>
    </view>
  </view>
</template>
<script>
export default {
  data() {
    return {
      items: {},
    };
  },
  onLoad(e) {
    // 获取产品详情
    this.$api.project_info(e.id).then(({ data }) => {
      if (data.code == 0) {
        this.items = data.data;
      }
    });
  },
  methods: {
    scheduleFn(page) {
      return (Number(page) > 100 ? 100 : Number(page)) || 0;
    },
    change() {
      uni.navigateTo({
        url: `/pages/content?id=${this.items.projectId}`,
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
.list {
  .view {
    padding: 20rpx;
    box-sizing: border-box;
    background-color: #fff;
  }
  .name {
    display: flex;
    align-items: center;
    padding-bottom: 16rpx;
    text {
      font-size: 32rpx;
    }
  }
  .rate {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 40rpx 0;
    background-color: #f4f4f4;
    .li {
      width: calc(100% / 3);
      text-align: center;
    }
    .con,
    .num {
      font-size: 24rpx;
    }
    .num {
      padding-top: 20rpx;
    }
    .con {
      color: #a0a0a1;
    }
    text {
      color: #4c7eaf;
    }
  }
  .investor {
    display: flex;
    align-items: center;
    justify-content: space-between;
    .con {
      display: flex;
      justify-content: center;
      flex-direction: column;
      text {
        text-align: left;
        font-size: 26rpx;
        padding-bottom: 25rpx;
        font-weight: 500;
        &:nth-child(1) {
          padding-top: 25rpx;
        }
      }
    }
  }
  .progress {
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: relative;
    /deep/.u-line-progress,
    /deep/.u-line-progress__background {
      border-radius: 10rpx !important;
    }

    .number {
      position: absolute;
      color: #fff;
      font-size: 26rpx;
      width: 100rpx;
      text-align: right;
      z-index: 2;
    }
  }
}
.tabs {
  padding: 40rpx 20rpx 120rpx;
  padding-bottom: calc(120rpx + constant(safe-area-inset-bottom));
  padding-bottom: env(120rpx + constant(safe-area-inset-bottom));
  .name {
    text-align: center;
    color: #4c7eaf;
    font-size: 32rpx;
  }
  .row {
    display: flex;
    justify-content: inherit !important;
    align-items: inherit !important;
    &:nth-child(2) {
      margin-top: 30rpx;
      .col {
        border-top: 2rpx solid #000;
      }
    }
  }
  .col {
    border: 2rpx solid #000;
    padding: 15rpx 30rpx;
    font-size: 24rpx;
    line-height: 1.5;
    border-top: 0;
    &:nth-child(1) {
      border-right: 0;
      display: flex !important;
      align-items: center !important;
      justify-content: center !important;
    }
    &:nth-child(2) {
      padding: 14rpx 30rpx !important;
    }
  }
}
.btn {
  .btn-class {
    width: 100%;
  }
  padding: 10rpx 20rpx;
  padding-bottom: calc(10rpx + constant(safe-area-inset-bottom));
  padding-bottom: env(10rpx + constant(safe-area-inset-bottom));
  background-color: #fff;
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  box-sizing: border-box;
}
</style>
