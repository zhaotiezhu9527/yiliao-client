<template>
  <view class="page">
    <van-nav-bar
      title="Deposits details"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/accountDetails')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="row">
        <view class="list top">
          <view class="title van-ellipsis"> Amount </view>
          <view class="title"> status </view>
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
            <view class="title van-ellipsis"> {{ item.amount }} </view>
            <view
              class="title"
              :class="{
                success: item.status === 1,
                loading: item.status === 0,
                error: item.status === -1,
              }"
            >
              {{ item.statusStr }}
            </view>
            <view class="value"> {{ item.createTime }} </view>
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
            <view class="w50">order no</view>
            <view class="w50"> {{ items.orderNo || "-" }} </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">amount</view>
            <view class="w50"> {{ items.amount || "-" }} </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">status</view>
            <view
              class="w50"
              :class="{
                success: items.status === 1,
                loading: items.status === 0,
                error: items.status === -1,
              }"
            >
              {{ items.statusStr || "-" }}
            </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">time</view>
            <view class="w50"> {{ items.createTime || "-" }} </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">checkTime</view>
            <view class="w50"> {{ items.checkTime || "-" }} </view>
          </view>
          <view class="list white">
            <view class="w50">remake</view>
            <view class="w50"> {{ items.remake || "-" }} </view>
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
      this.$api.user_topUpRecord({ page, limit }).then((res) => {
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
  padding: 0 16upx;
  background-color: #41b7b8;
  &.white {
    background-color: #ffffff;
    .value {
      color: #333;
    }
  }
  .title {
    display: flex;
    align-items: center;
    font-size: 20upx;
    width: 25%;
    padding-left: 30upx;
    &:nth-child(2) {
      padding-left: 0;
      justify-content: center;
    }
  }
  .value {
    width: 50%;
    text-align: right;
    font-size: 20upx;
    color: #404040;
  }
  .w50 {
    width: 50%;
  }
}
.success {
  color: #5dff00;
}
.loading {
  color: #ffcd04;
}
.error {
  color: red;
}
</style>
