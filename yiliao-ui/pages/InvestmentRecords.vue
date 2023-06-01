<template>
  <view class="page">
    <u-navbar
      placeholder
      title="投资记录"
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
      <view class="title">
        <view class="title-remark">项目名称</view>
        <view class="line"></view>
        <view class="title-amount">投资金额</view>
        <view class="line"></view>
        <view class="title-time">状态</view>
        <view class="line"></view>
        <view class="title-time">详情</view>
        <view class="line"></view>
        <view class="title-time">合同</view>
      </view>
      <u-list @scrolltolower="load" v-if="isArray" class="scroll">
        <u-list-item v-for="(item, index) in list" :key="index">
          <view class="content">
            <view class="table-title">
              {{ item.projectName }}
            </view>
            <view class="line"></view>
            <view class="table-money green-text">{{ item.amount }}</view>
            <view class="line"></view>
            <view class="table-time">{{
              item.status ? "已完成" : "待收益"
            }}</view>
            <view class="line"></view>
            <view class="table-time">
              <label
                class="blue-text"
                @click="goInvestmentDetails(item.orderNo)"
              >
                查看
              </label>
            </view>

            <view class="line"></view>
            <view class="table-time">
              <label class="grey-text" @click="goContract(item.orderNo)">
                查看
              </label>
            </view>
          </view>
        </u-list-item>
        <view class="loading" v-if="loading">加载中...</view>
        <view class="nomore" v-if="finished">没有更多了</view>
      </u-list>
      <u-empty class="empty" text="暂无数据" v-else />
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
      isArray: false,
      page: 0,
    };
  },
  onLoad() {
    this.list = [];
    uni.showLoading();
    this.page = 1;
    this.dataFn();
  },
  methods: {
    goInvestmentDetails(num) {
      uni.navigateTo({
        url: "/pages/InvestmentDetails?orderNo=" + num,
      });
    },
    goContract(num) {
      uni.navigateTo({
        url: "/pages/contract?orderNo=" + num,
      });
    },
    load() {
      if (this.loading || this.finished) return false;
      this.page++;
      this.dataFn(this.page);
    },
    dataFn(page = 1, limit = 20) {
      this.loading = true;
      this.$api
        .invest_list({ page, limit })
        .then((res) => {
          if (res.data.code == 0) {
            const vim = res.data.page;
            this.list = this.list.concat(vim.list);
            this.isArray = vim.totalCount ? true : false;
            if (this.page >= vim.totalPage) {
              this.finished = true;
            }
          }
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
};
</script>

<style scoped lang="scss">
@import "../static/record.scss";
</style>
