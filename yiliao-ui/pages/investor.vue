<template>
  <view class="page">
    <van-nav-bar
      placeholder
      title="项目投资"
      :border="false"
      fixed
      safe-area-inset-top
    >
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
              :percentage="scheduleFn(item.schedule)"
              :show-pivot="false"
            />
            <view class="number">{{ item.schedule }}%</view>
          </view>
        </view>
        <van-empty description="暂无产品" v-if="!shopGoods.length" />
      </view>
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
    };
  },
  onShow() {
    this.dataFn();
  },
  methods: {
    dataFn() {
      this.loading = true;
      this.$api.project_list().then(({ data }) => {
        this.loading = false;
        if (data.code == 0) {
          this.shopGoods = data.data;
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
.page {
  background-color: #fafafa;
}
.van-empty {
  background-color: #fff;
}
</style>
