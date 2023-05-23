<template>
  <view class="page">
    <van-nav-bar
      placeholder
      :border="false"
      fixed
      title="投资记录"
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
      <template #left>
        <van-icon name="arrow-left" size="18" />
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
        <view class="content" v-for="(item, index) in list" :key="index">
          <view class="table-title">
            {{ item.projectName }}
          </view>
          <view class="line"></view>
          <view class="table-money green-text">{{ item.amount }}</view>
          <view class="line"></view>
          <view class="table-time">{{
            item.status ? "已完成" : "未结算"
          }}</view>
          <view class="line"></view>
          <view class="table-time">
            <label class="blue-text" @click="goInvestmentDetails(item.orderNo)">
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
      this.page++;
      this.dataFn(this.page);
    },
    dataFn(page = 1, limit = 20) {
      this.$api.invest_list({ page, limit }).then((res) => {
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
</style>
