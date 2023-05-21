<template>
  <view class="page">
    <van-nav-bar placeholder :border="false" fixed safe-area-inset-top>
      <template #left><text class="headr_title">项目投资</text></template>
    </van-nav-bar>
    <view class="wrap">
      <view class="list" v-if="shopGoods.length">
        <view
          class="view"
          v-for="(item, index) in shopGoods"
          :key="index"
          @click="routePath(item)"
        >
          <image class="img" :src="item.img" mode="widthFix" />
          <view class="name">
            <text>{{ guaranteeCompanyFn(item.guaranteeCompany) }}</text>
            <text>{{ item.projectName }}</text>
          </view>
          <view class="rate">
            <view class="li">
              <view class="num"
                ><text>{{ item.incomeRate }}</text>
                %
              </view>
              <view class="con">日化利率</view>
            </view>
            <view class="li">
              <view class="num">
                <text>{{ item.limitTime }}</text>
                分钟
              </view>
              <view class="con">投资期限</view></view
            >
            <view class="li">
              <view class="num"
                >￥<text>{{ item.minAmount }}</text>
                元
              </view>
              <view class="con">起投金额</view></view
            >
          </view>
          <view class="investor">
            <view class="con">
              <text>项目规模：{{ item.projectAmount }}元</text>
              <text>每日还息：到齐还本</text>
            </view>
            <view class="btn">立即投资</view>
          </view>
          <view class="progress">
            <view class="txt">项目进度：</view>
            <van-progress
              class="prog"
              :percentage="item.schedule || 0"
              :show-pivot="false"
            />
            <view class="number">{{ item.schedule }}%</view>
          </view>
        </view>
        <van-empty description="暂无产品" v-if="!shopGoods.length" />
      </view>
      <!-- <van-list
        :immediate-check="false"
        v-model="loading"
        :finished="finished"
        loading-text="加载中..."
        finished-text="已经最底了"
        @load="load"
        v-if="totalCount"
      >
      </van-list> -->
      <van-empty description="暂无产品" v-else />
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      shopGoods: [],
      loading: false,
      finished: false,
      totalCount: false,
      page: 0,
    };
  },
  onShow() {
    this.page = 1;
    this.dataFn();
  },
  methods: {
    load() {
      this.page++;
      this.dataFn(this.page);
    },
    dataFn(page = 1, limit = 30) {
      this.loading = true;
      this.$api.project_list({ page, limit }).then(({ data }) => {
        this.loading = false;
        if (data.code == 0) {
          this.shopGoods = data.data;
          // const vim = data.page;
          // this.shopGoods = this.shopGoods.concat(vim.list);
          // this.totalCount = vim.totalCount ? true : false;
          // if (this.page >= vim.totalPage) {
          // this.finished = true;
          // }
        } else {
          this.$base.show(data.msg);
        }
      });
    },
    routePath(item) {
      uni.navigateTo({
        url: `/pages/info?id=${item.projectId}`,
      });
    },
    guaranteeCompanyFn(name) {
      return name.charAt(name.length - 1);
    },
  },
};
</script>

<style scoped lang="scss">
@import "../static/list.scss";
.page {
  background-color: #fafafa;
}
.van-empty {
  background-color: #fff;
}
</style>
