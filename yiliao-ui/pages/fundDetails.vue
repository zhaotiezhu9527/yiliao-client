<template>
  <view class="page">
    <van-nav-bar
      title="资金明细"
      left-arrow
      placeholder
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
      <van-list
        :immediate-check="false"
        v-model="loading"
        :finished="finished"
        loading-text="loading..."
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
        <view
          class="content"
          v-for="(item, index) in list"
          :key="index"
        >
          <view class="table-title">
            {{ item.remark }}
          </view>
          <view class="line"></view>
          <view class="table-money" :class="item.amount > 0 ? 'green-text' : 'red-text'">{{ item.amount }}</view>
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
    dataFn(page = 1, limit = 20) {
      this.$api.account_list({ page, limit }).then((res) => {
        if (res.data.code == 0) {
          const vim = res.data.page;
          this.list = this.list.concat(vim.list);
          console.log(this.list);
          this.isArray = vim.totalCount ? true : false;
          if (this.page >= vim.totalPage) {
            this.finished = true;
          }
        }
      });
    },
    // change(item) {
    //   uni.navigateTo({
    //     url: "/pages/detail?id=" + item.id,
    //   });
    // },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  .title{
    display: flex;
    text-align: center;
    font-size: 24upx;
    font-weight: 600;
    border-top: 1px solid #bbb;
    border-bottom: 1px solid #bbb;
    height: 60upx;
    align-items: center;
    .title-remark{
      flex: 1;
    }
    .title-amount{
      width: 20%;
    }
    .title-time{
      width: 20%;
    }
  }
  .content{
    display: flex;
    font-size: 24upx;
    height: 80upx;
    align-items: center;
    border-bottom: 1px solid #bbb;
  }
  .table-title{
    text-align: center;
    flex: 1;
  }
  .table-money {
    width: 20%;
    text-align: center;
  }
  .table-time {
    width: 19%;
    text-align: left;
    padding-left: 1%;
  }
}
</style>
