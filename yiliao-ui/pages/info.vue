<template>
  <view class="pages">
    <van-nav-bar
      title="Project"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/trades')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="header">
        <image mode="widthFix" class="icon" src="../static/images/xiazai.png" />
        <view class="text">{{ items.name }}</view>
      </view>
      <view class="count">
        <view class="li">
          <view class="num">{{ items.minAmount }}</view>
          <view class="txt">Minimum amount</view>
        </view>
        <view class="li">
          <view class="num">{{ (items.interest * 100) | ceil }}%</view>
          <view class="txt">Period rate</view>
        </view>
        <view class="li">
          <view class="num"> {{ items.period }} {{ items.unit | time }} </view>
          <view class="txt">Release cycle</view>
        </view>
      </view>
      <view class="msg">
        <view class="nocontent">
          <view class="text">
            Minimum Daily Earnings:{{ items.minAmount }}*{{
              (items.interest * 100) | ceil
            }}%={{ (items.interest * items.minAmount) | ceil }} USDT
          </view>
          <view class="text"> Garantes：BambuCrypro(BC) </view>
        </view>
      </view>
      <view class="progress">
        <view class="txt">Project progress:</view>
        <van-progress
          :show-pivot="false"
          stroke-width="14upx"
          color="linear-gradient(90deg,#4b80af,#aedfd9)"
          :percentage="items.schedule"
          track-color="#ffffff"
        />
        <view class="txt">{{ items.schedule }}%</view>
      </view>
      <view class="msg">
        <view class="name"> Mode of repayment: </view>
        <view class="content">
          <view class="notext">
            {{ items.content }}
          </view>
        </view>
      </view>
      <view class="msg">
        <view class="name"> Project details </view>
        <view class="content">
          <view class="notext">
            {{ items.info }}
          </view>
        </view>
      </view>
      <view class="msg">
        <view class="name">Trading statistics </view>
        <view class="content">
          <view class="text">Project amount:$ {{ items.totalAmount }}</view>
          <view class="text">
            Mode of repayment:Return principal and profit at the end of the
            project
          </view>
          <view class="text"
            >The investment amount:{{ items.minAmount }} ~
            {{ items.maxAmount }}$</view
          >
          <view class="text"
            >Release cycle: {{ items.period }} {{ items.unit | time }}</view
          >
          <view class="text" v-if="time === 1">
            Estimated income：{{ (items.interest * 100) | ceil }}%*{{
              items.period
            }}={{ (items.interest * 100 * items.period) | ceil }}%
          </view>
          <view class="text">
            Income description: After the investment, the profit and principal
            will be returned to the member account
          </view>
          <view class="text">Purchase limit:{{ items.buyLimit }}</view>
        </view>
      </view>
      <view class="btns">
        <van-button color="#4b80af" round block @click="link">
          Investimento agora
        </van-button>
      </view>
    </view>
  </view>
</template>
<script>
export default {
  data() {
    return {
      items: {
        schedule: 0,
      },
    };
  },
  filters: {
    ceil(value) {
      return value.toFixed(2);
    },
    time(value) {
      let type = "";
      switch (value) {
        case 1:
          type = "Day";
          break;
        case 2:
          type = "Hour";
          break;
        case 3:
          type = "Minute";
          break;
      }
      return type;
    },
  },
  methods: {
    link() {
      uni.navigateTo({
        url: "/pages/buy?id=" + this.$route.query.id,
      });
    },
  },
  onShow() {
    uni.showLoading();
    this.$api.product_info(this.$route.query.id).then((res) => {
      if (res.data.code == 0) {
        this.items = res.data.data;
      } else {
        this.$base.show(res.data.msg);
      }
    });
  },
};
</script>
<style scoped lang="scss">
.wrap {
  padding-bottom: 40upx;
}
.btns {
  padding: 40upx 40upx 0;
  .van-button__content span {
    font-size: 32upx;
  }
}
.header {
  padding: 32upx 28upx;
  display: flex;
  align-items: center;
  .icon {
    width: 38upx;
  }
  .text {
    padding-left: 20upx;
    font-size: 32upx;
  }
}
.msg {
  padding-top: 10upx;
  .name {
    font-size: 24upx;
    background: linear-gradient(90deg, #7fe0d2, #aedfd9);
    padding: 0 28upx 0 28upx;
    color: #fff;
    line-height: 80upx;
  }
  .nocontent,
  .content {
    padding: 28upx;
    .text,
    .notext {
      font-size: 24upx;
      color: #404040;
      line-height: 1.5;
    }
    .text {
      line-height: 72upx;
    }
  }
  .nocontent {
    padding: 0 28upx;
  }
}
.progress {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 30upx 28upx;
  .txt {
    font-size: 24upx;
  }
  .van-progress {
    width: 300upx;
  }
}
.count {
  padding: 35upx 0;
  margin: 0 28upx;
  width: calc(100% - 48upx);
  box-shadow: 0 10upx 40upx 0 rgba(54, 54, 54, 0.18);
  display: flex;
  align-items: center;
  flex-wrap: nowrap;
  text-align: center;
  border-radius: 10upx;
  .li {
    width: calc(100% / 3);
  }
  .num {
    padding-bottom: 5upx;
    font-size: 36upx;
  }
  .txt {
    font-size: 24upx;
    padding-top: 10upx;
  }
}
</style>
