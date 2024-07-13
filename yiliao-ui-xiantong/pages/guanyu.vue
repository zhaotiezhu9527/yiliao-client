<template>
  <view class="page">
    <u-navbar
      placeholder
      title="公司简介"
      :border="false"
      autoBack
      fixed
      leftIconSize="0"
      safe-area-inset-top
      bgColor="#4b80af"
      height="100rpx"
      titleStyle="color:#fff;font-weight:500;font-size:32rpx;"
    >
    </u-navbar>
    <view class="wrap">
        <!-- <view class="title">中核简介</view>
        <view class="head">COMPANY PROFILE</view> -->
<!--        <image-->
<!--          class="image-title"-->
<!--          mode="widthFix"-->
<!--          src="../static/img/about_title.jpg"-->
<!--        />-->
        <image
          class="image"
          mode="widthFix"
          src="../static/img/about.jpg"
        />
        <view>
          <view class="content">
            先通医药是中国创新放射性药物领域的领先企业，专注于放射性药物的研发创新及落地应用以改善患者的健康水平。
          </view>
          <view class="content">
            先通医药于2014年正式开展新一代放射性药物的研发，总部位于北京，在上海设有研发中心，在江苏、广东、四川建设有现代化放射性药物智能生产基地；同时，在美国设有分支机构，并与十几家跨国药企达成战略合作。
          </view>
          <view class="content">
            全球放射性药物主要应用于在神经、心血管、肿瘤三大疾病领域，先通医药已实现全面布局，并拥有20+精准诊断和靶向治疗放射性药物研发管线。凭借自身的研发优势，先通医药获得了投资机构的青睐，2020年4月至今，已成功募资超20亿元人民币，未来不久将登陆主要资本市场，成为中国放射性药物领域的创新力量。
          </view>
        </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {};
  },
  onLoad() {},
  methods: {},
};
</script>

<style scoped lang="scss">
.scroll {
  height: calc(100vh - 200rpx + var(--status-bar-height));
}
.image-title {
  width: 80%;
  margin: 5% 10%;
}
.image {
  width: 100vw;
}
.text {
  font-size: 50rpx;
}
.title{
  width: 100%;
  text-align: center;
  color: #ff6c00;
  font-size: 36rpx;
  margin: 20rpx 0;
}
.head{
  width: 100%;
  text-align: center;
  color: #999;
  font-size: 28rpx;
  margin: 20rpx 0;
}
.content{
  color: rgb(127,127,127);
  margin: 40rpx auto;
  width: 90%;
  text-indent: 80rpx;
}
</style>
