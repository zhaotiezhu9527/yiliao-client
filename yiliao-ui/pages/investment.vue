<template>
  <view class="page">
    <van-nav-bar
      title="My investment"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="row">
        <view class="list top">
          <view class="value"> Product name </view>
          <view class="value"> Amount </view>
          <view class="value"> Invested time </view>
        </view>
        <van-list
          :immediate-check="false"
          v-model="loading"
          :finished="finished"
          loading-text="loading..."
          finished-text="No more"
          @load="load"
          v-if="isArray"
        >
          <view
            class="list white van-hairline--bottom"
            v-for="(item, index) in list"
            @click="change(item)"
            :key="index"
          >
            <view
              class="value van-ellipsis"
              :class="{
                success: item.status === 1,
                loading: item.status === 0,
              }"
            >
              {{ item.productName }}
            </view>
            <view class="value"> {{ item.amount }} </view>
            <view class="value"> {{ item.investedTime }} </view>
          </view>
        </van-list>
        <van-empty description="No more" v-else />
      </view>
    </view>
    <van-dialog v-model="show" :showConfirmButton="false">
      <view class="dialog">
        <view class="header">
          <van-icon name="cross" size="30upx" @click="show = false" />
        </view>
        <view class="content">
          <view class="list white van-hairline--bottom">
            <view class="w50">order number</view>
            <view class="w50"> {{ items.orderNo || "-" }} </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">Name</view>
            <view class="w50"> {{ items.productName || "-" }} </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">amount</view>
            <view class="w50">
              {{ items.amount || "-" }}
            </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">release cycle</view>
            <view class="w50">
              {{ items.period }} {{ items.unit | time }}
            </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">income</view>
            <view class="w50">
              {{ items.interest }}% ({{ items.income || "-" }})
            </view>
          </view>
          <view class="list white">
            <view class="w50">total income</view>
            <view class="w50">
              {{ items.totalInterest }}% ({{ items.totalIncome || "-" }})
            </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">status</view>
            <view
              class="w50"
              :class="{
                success: items.status === 1,
                loading: items.status === 0,
              }"
            >
              {{ items.statusStr || "-" }}
            </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">time</view>
            <view class="w50"> {{ items.settleTime || "-" }} </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">Expiration date</view>
            <view class="w50"> {{ items.investedTime || "-" }} </view>
          </view>
        </view>
        <view class="btns">
          <van-button
            color="linear-gradient(90deg,#ff6034,#ee0a24)"
            round
            block
            @click="show = false"
          >
            confirm
          </van-button>
        </view>
      </view>
    </van-dialog>
  </view>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      finished: false,
      list: [],
      isArray: true,
      show: false,
      page: 0,
      show: false,
      items: {},
    };
  },
  onLoad() {
    uni.showLoading();
    this.page = 1;
    this.dataFn();
  },
  filters: {
    time(value) {
      let type = "";
      switch (value) {
        case 1:
          type = "Day";
          break;
        case 2:
          type = "Hours";
          break;
        case 3:
          type = "Minutes";
          break;
      }
      return type;
    },
  },
  methods: {
    load() {
      this.page++;
      this.dataFn(this.page);
    },
    change(item) {
      this.items = item;
      this.show = true;
    },
    dataFn(page = 1, limit = 30) {
      this.$api.user_myInvestment({ page, limit }).then((res) => {
        if (res.data.code == 0) {
          const vim = res.data.page;
          this.list = this.list.concat(vim.list);
          this.isArray = vim.totalCount ? true : false;
          if (this.page >= vim.totalPage) {
            this.finished = true;
          }
        }
      });
    },
  },
};
</script>

<style scoped lang="scss">
.dialog {
  .header {
    padding: 20upx 20upx 0;
    display: flex;
    justify-content: flex-end;
  }
  .content {
    padding: 20upx 0;
    font-size: 24upx;
    .w50 {
      line-height: 90upx;
      height: 90upx;
      padding: 0 20upx;
      text-align: left;
      &:nth-child(2) {
        text-align: right;
      }
    }
  }
  .btns {
    padding: 30upx 60upx;
    .van-button {
      height: 72upx;
    }
    .van-button__content span {
      font-size: 30upx;
    }
  }
}
.wrap {
  padding: 106upx 0upx 50upx;
}
.row {
  position: relative;
  .top {
    position: sticky;
    top: 92upx;
    z-index: 1024;
  }
}
.list {
  width: 100%;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-between;
  line-height: 110upx;
  padding: 0 25upx;
  background-color: #41b7b8;
  &.white {
    background-color: #ffffff;
    .value {
      color: #333;
    }
  }
  .value {
    color: #404040;
    width: 20%;
    font-size: 20upx;
    text-align: center;
    &:nth-child(1) {
      text-align: left;
      width: 45%;
    }
    &:nth-child(3) {
      text-align: right;
      width: 35%;
    }
  }
}
.success {
  color: #5dff00 !important;
}
.loading {
  color: #ffcd04 !important;
}
</style>
