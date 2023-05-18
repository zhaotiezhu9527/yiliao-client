<template>
  <view class="page">
    <van-nav-bar
      title="Account details"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="clip">
        <view class="item">
          <view class="title"> My assets </view>
          <view class="txt"> {{ items.totalAsset }} </view>
        </view>
        <view class="item">
          <view class="title">Accumulated income </view>
          <view class="txt"> {{ items.income }} </view>
        </view>
      </view>
      <view class="clip">
        <view class="item">
          <view class="title"> Available balance </view>
          <view class="txt"> {{ items.availBalance }} </view>
        </view>
        <view class="item">
          <view class="title"> Waiting for recycling </view>
          <view class="txt"> {{ items.recyclingBalance }} </view>
        </view>
      </view>
      <view class="clip">
        <view class="item">
          <view class="title"> Total deposits </view>
          <view class="txt"> {{ items.deposit }} </view>
        </view>
        <view class="item">
          <view class="title"> Total withdraw </view>
          <view class="txt"> {{ items.withdraw }} </view>
        </view>
      </view>
      <view class="row">
        <view class="list" @click="pathChange('/pages/record')">
          <view class="title van-ellipsis"> Deposits details </view>
          <view class="value">
            <van-icon name="arrow" color="#969799" size="30upx" />
          </view>
        </view>
        <view class="list" @click="pathChange('/pages/withdraw')">
          <view class="title van-ellipsis"> withdraw details </view>
          <view class="value">
            <van-icon name="arrow" color="#969799" size="30upx" />
          </view>
        </view>
      </view>
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
  onLoad() {
    uni.showLoading();
    this.$api.user_accountDetails().then((res) => {
      if (res.data.code == 0) {
        this.items = res.data.data;
      } else {
        this.$base.show(res.data.msg);
      }
    });
  },
  methods: {
    pathChange(url) {
      uni.navigateTo({
        url,
      });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  padding: 126upx 20upx 50upx;
}
.clip {
  margin: 0 20upx 20upx 20upx;
  width: calc(100% - 40upx);
  border-radius: 10upx;
  display: flex;
  align-items: center;
  justify-content: space-between;
  text-align: center;
  background-color: #dff2f0;
  padding: 40upx 0upx;
  .item {
    width: 50%;
    position: relative;
    &:nth-child(1)::after {
      content: "";
      position: absolute;
      top: 50%;
      right: 0;
      transform: translateY(-50%);
      background: #596785;
      height: 100%;
      width: 1upx;
    }
    .title {
      color: #404040;
      font-size: 28upx;
      padding-bottom: 30upx;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .txt {
      color: #e6a23c;
      font-size: 32upx;
      padding-bottom: 10upx;
    }
  }
}
.list {
  width: calc(100% - 40upx);
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-between;
  line-height: 110upx;
  padding: 0 16upx;
  margin: 0 20upx;
  background-color: #dff2f0;
  .title {
    display: flex;
    align-items: center;
    font-size: 28upx;
    width: 65%;
    padding-left: 30upx;
  }
  .value {
    width: 35%;
    text-align: right;
  }
}
</style>
