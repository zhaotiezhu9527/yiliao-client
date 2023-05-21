<template>
  <view class="page">
    <van-nav-bar
      placeholder
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
      <template #left>
        <van-icon name="arrow-left" size="18" />
        <text class="headr_title">资金明细</text>
      </template>
    </van-nav-bar>
    <view class="wrap">
      <van-list
        :immediate-check="false"
        v-model="loading"
        :finished="finished"
        loading-text="加载中..."
        finished-text="没有更多了"
        @load="load"
        v-if="isArray"
      >
        <!-- @click="change(item)" -->
        <view class="title">
          <view class="title-remark">摘要</view>
          <view class="line"></view>
          <view class="title-amount">金额</view>
          <view class="line"></view>
          <view class="title-time">时间</view>
        </view>
        <view class="content" v-for="(item, index) in list" :key="index">
          <view class="table-title">
            {{ item.remark }}
          </view>
          <view class="line"></view>
          <view
            class="table-money"
            :class="item.amount > 0 ? 'green-text' : 'red-text'"
            >{{ item.amount }}</view
          >
          <view class="line"></view>
          <view class="table-time">{{ item.optTime }}</view>
        </view>
      </van-list>
      <van-empty description="没有更多了" v-else />
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      list: [], //列表数据
      loading: false,
      finished: false,
      isArray: true,
      page: 0,
    };
  },
  onShow() {
    uni.showLoading();
    this.page = 1;
    this.dataFn();
  },
  methods: {
    load() {
      this.page++;
      this.dataFn(this.page);
    },
    dataFn(page = 1, limit = 20) {
      this.$api.account_list({ page, limit }).then((res) => {
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
@import "../static/record.scss";
.table-time {
  width: 20% !important;
  text-align: left !important;
  padding-left: 1%;
  box-sizing: border-box;
}
.title-time {
  width: 20% !important;
}
</style>
