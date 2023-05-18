<template>
  <view class="page">
    <van-nav-bar
      title="Fund flow record"
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
          <view class="value"> Type </view>
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
            :key="index"
          >
            <view class="value"> {{ item.statusStr }} </view>
            <view class="value"> {{ item.amount }} </view>
            <view class="value"> {{ item.createTime }} </view>
          </view>
        </van-list>
        <van-empty description="No more" v-else />
      </view>
    </view>
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
    dataFn(page = 1, limit = 30) {
      this.$api.user_fundRecord({ page, limit }).then((res) => {
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
.wrap {
  padding: 106upx 0upx 50upx;
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
    width: calc(100% / 3);
    font-size: 20upx;
    text-align: center;
    &:nth-child(1) {
      text-align: left;
    }
    &:nth-child(3) {
      text-align: right;
    }
  }
}
</style>
