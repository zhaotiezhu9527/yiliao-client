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
      height="52px"
      titleStyle="color:#fff;font-weight:500;font-size:32rpx;"
    >
    </u-navbar>
    <view class="wrap">
      <scroll-view scroll-y class="scroll">
        <image
          class="image"
          mode="widthFix"
          src="../static/img/zhenshi_about_post.jpg"
        />
        <div class="text">
          安徽安科生物工程（集团）股份有限公司是以生物医药的研发、
          生产和销售为主营业务的高新技术企业，首批中国创业板上市公司。
          先后被认定为国家创新型（试点）企业、国家技术创新示范企业、
          安徽省制造业综合百强企业。设有国家认定的企业技术中心、
          肿瘤精准治疗技术及产品国家地方联合工程研究中心、
          安徽省生物工程实验室、基因工程制药安徽省重点实验室、
          安徽省肿瘤精准治疗产品创新中心等技术平台，是安徽省生物医药领军企业。
        </div>
      </scroll-view>
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
  height: calc(100vh - 210rpx + var(--status-bar-height));
}
.image {
  width: 100vw;
}
.text{
  font-size: 50rpx;
}
</style>
