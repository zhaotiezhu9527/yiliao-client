<template>
  <view class="page">
    <u-navbar
      placeholder
      title="项目投资"
      :border="false"
      autoBack
      fixed
      leftIconSize="0"
      safe-area-inset-top
      bgColor="#4b80af"
      height="52px"
      titleStyle="color:#fff;font-weight:500;font-size:32rpx;"
    >
    </u-navbar>
    <view class="wrap">
      <scroll-view scroll-y v-if="shopGoods.length" class="scroll">
        <view class="list">
          <view
            class="view"
            v-for="(item, index) in shopGoods"
            :key="index"
            @click="routePath(item)"
          >
            <image class="img" :src="item.img" mode="widthFix" />
            <view class="name">
              <text>保</text>
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
                <text>每日还息：到期还本</text>
              </view>
              <view class="btn">立即投资</view>
            </view>
            <view class="progress">
              <view class="txt">项目进度：</view>
              <u-line-progress
                :percentage="scheduleFn(item.schedule)"
                :showText="false"
                activeColor="#2196f3"
              />
              <view class="number">{{ item.schedule }}%</view>
            </view>
          </view>
        </view>
      </scroll-view>

      <u-empty class="empty" text="暂无数据" v-else />
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      shopGoods: [],
      loading: false,
    };
  },
  onShow() {
    this.dataFn();
  },
  methods: {
    dataFn() {
      this.loading = true;
      this.$api
        .project_list()
        .then(({ data }) => {
          if (data.code == 0) {
            this.shopGoods = data.data;
          } else {
            this.$base.show(data.msg);
          }
        })
        .finally(() => {
          this.loading = false;
        });
    },
    routePath(item) {
      this.$api.project_info(item.projectId).then(({ data }) => {
        if (data.code == 0) {
          uni.navigateTo({
            url: `/pages/info?id=${item.projectId}`,
          });
        }
      });
    },
    guaranteeCompanyFn(name) {
      return name ? name.charAt(name.length - 1) : "-";
    },
    scheduleFn(page) {
      return (Number(page) > 100 ? 100 : Number(page)) || 0;
    },
  },
};
</script>

<style scoped lang="scss">
@import "../static/list.scss";
.scroll {
  height: calc(100vh - 210rpx + var(--status-bar-height));
}
.page {
  background-color: #fafafa;
}
</style>
