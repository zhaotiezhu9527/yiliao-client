<template>
  <view class="page">
    <van-nav-bar
      left-arrow
      placeholder
      :border="false"
      fixed
      title="投资详情"
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/investor')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="list">
        <view class="view">
          <image class="img" src="../static/img/txt.jpg" mode="widthFix" />
          <view class="name">
            <text>安苏萌·注射用人生长激素</text>
          </view>
          <view class="rate">
            <view class="li">
              <view class="con">项目规模</view>
              <view class="num">￥<text>66806000</text>万元</view>
            </view>
            <view class="li">
              <view class="con">每份分红</view>
              <view class="num"><text>1902000</text>元起</view>
            </view>
            <view class="li">
              <view class="con">投资周期</view>
              <view class="num"><text>15</text>分钟</view>
            </view>
          </view>
          <view class="investor">
            <view class="con">
              <text>分红方式：每日返息，到期还本</text>
              <text>起投金额：300000元</text>
              <text>担保机构：中国人民财产保险股份有限公司</text>
              <text>投资零风险：本金保障</text>
            </view>
          </view>
          <view class="progress">
            <view class="number">21.05%</view>
            <van-progress
              class="prog"
              percentage="50"
              stroke-width="30"
              :show-pivot="false"
              track-color="#cccccc"
              color="#2094f3"
            />
          </view>
        </view>
      </view>
      <view class="tabs">
        <view class="name">投资详情</view>
        <van-row class="row">
          <van-col class="col" span="8">项目名称</van-col>
          <van-col class="col" span="16">安达芬·重组人干扰素a2b栓</van-col>
        </van-row>
        <van-row class="row">
          <van-col class="col" span="8">项目金额:</van-col>
          <van-col class="col" span="16"
            ><text>66806000</text>万元人民币</van-col
          >
        </van-row>
        <van-row class="row">
          <van-col class="col" span="8">每天分红:</van-col>
          <van-col class="col" span="16"
            ><text>按每日6.34%的收益 (保本保息)</text></van-col
          >
        </van-row>
        <van-row class="row">
          <van-col class="col" span="8">投资金额:</van-col>
          <van-col class="col" span="16"><text>最低起投300000元</text></van-col>
        </van-row>
        <van-row class="row">
          <van-col class="col" span="8">项目期限:</van-col>
          <van-col class="col" span="16"
            ><text>分期定投，每期15分钟</text></van-col
          >
        </van-row>
        <van-row class="row">
          <van-col class="col" span="8">收益计算:</van-col>
          <van-col class="col" span="16"
            ><text>每天保底分红 利率*本金</text></van-col
          >
        </van-row>
        <van-row class="row">
          <van-col class="col" span="8">还款方式:</van-col>
          <van-col class="col" span="16"
            >每日返息，到期还本，节假日照常收益</van-col
          >
        </van-row>
        <van-row class="row">
          <van-col class="col" span="8">结算时间:</van-col>
          <van-col class="col" span="16"
            >当天投资，当天到期自动结算收益(例如在15:00成功投资，则在投资期限到期收到分红和本金)，到期系统会把兰日分红和产品本金一起返还到您的账户当中。
          </van-col>
        </van-row>
        <van-row class="row">
          <van-col class="col" span="8">可投金额:</van-col>
          <van-col class="col" span="16"
            >投资期间只要产品未投满，投资者均可自由投资。</van-col
          >
        </van-row>
        <van-row class="row">
          <van-col class="col" span="8">安全保障:</van-col>
          <van-col class="col" span="16"
            >中国人民财产保险股份有限公司对平台上的每一笔投资提供<text>100%本金保障</text>平台设立风险备用金，对本金承诺全额垫付;
          </van-col>
        </van-row>
        <van-row class="row">
          <van-col class="col" span="8">项目概述:</van-col>
          <van-col class="col" span="16"
            ><text
              >本项目筹集资金66806000万元人民币，所筹集资金用于该项目直投运作作为投资者分红固定且无任何风险，集为投资份红围庆昌香括保风曾，所承担。
            </text></van-col
          >
        </van-row>
      </view>
    </view>
    <view class="btn">
      <van-button color="#4b80af" block @click="change"> 马上投资 </van-button>
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
    // this.$api.user_notice({ id: e.id }).then(({ data }) => {
    //   if (data.code === 0) {
    //     this.items = data.data;
    //   }
    // });
  },
  methods: {
    change() {
      uni.navigateTo({
        url: `/pages/content?id=${this.items.id}`,
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
    padding: 20upx;
    box-sizing: border-box;
    background-color: #fff;
  }
  .name {
    display: flex;
    align-items: center;
    padding-bottom: 16upx;
    text {
      font-size: 32upx;
    }
  }
  .rate {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 40upx 0;
    background-color: #f4f4f4;
    .li {
      width: calc(100% / 3);
      text-align: center;
    }
    .con,
    .num {
      font-size: 24upx;
    }
    .num {
      padding-top: 20upx;
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
        font-size: 26upx;
        padding-bottom: 25upx;
        font-weight: 500;
        &:nth-child(1) {
          padding-top: 25upx;
        }
      }
    }
  }
  .progress {
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: relative;

    .number {
      position: absolute;
      color: #fff;
      font-size: 26upx;
      width: 120upx;
      text-align: right;
      z-index: 2;
    }
    .prog {
      z-index: 1;
      width: 100%;
    }
  }
}
.tabs {
  padding: 40upx 20upx 120upx;
  padding-bottom: calc(120upx + constant(safe-area-inset-bottom));
  padding-bottom: env(120upx + constant(safe-area-inset-bottom));
  .name {
    text-align: center;
    color: #4c7eaf;
    font-size: 32upx;
  }
  .row {
    display: flex;
    &:nth-child(2) {
      margin-top: 30upx;
      .col {
        border-top: 2upx solid #000;
      }
    }
  }
  .col {
    border: 2upx solid #000;
    padding: 15upx 30upx;
    font-size: 24upx;
    line-height: 1.5;
    border-top: 0;
    &:nth-child(1) {
      border-right: 0;
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}
.btn {
  padding: 10upx 20upx;
  padding-bottom: calc(10upx + constant(safe-area-inset-bottom));
  padding-bottom: env(10upx + constant(safe-area-inset-bottom));
  background-color: #fff;
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  box-sizing: border-box;
  .van-button {
    border-radius: 10upx;
  }
  .van-button__content span {
    font-size: 32upx;
  }
}
</style>
