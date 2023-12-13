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
        <view class="title">东诚简介</view>
        <view class="head">COMPANY PROFILE</view>
        <image
          class="image"
          mode="widthFix"
          src="../static/img/about.jpg"
        />
        <view>
          <view class="content">
            烟台东诚药业集团股份有限公司成立于1998年，2012年5月在深交所上市（股票代码002675）。历经20余年的发展，东诚药业现已成为一家覆盖原料药、制剂、核医疗、大健康四大领域，融药品研发、生产、销售于一体的大型制药企业集团。
          </view>
          <view class="content">
            作为肝素行业领导者，公司拥有垂直一体化的肝素全产业链，2022年肝素原料药出口国内排名第一、全球前三。公司是最大的硫酸软骨素生产商，业务规模常年稳居全球第一。
          </view>
          <view class="content">
            公司制剂产品管线丰富，拥有粉针剂、片剂、胶囊剂、颗粒剂、滴丸剂等多种剂型生产线，产品覆盖抗凝、心血管、抗肿瘤、泌尿、骨科及抗感染等多个治疗领域。公司立足肝素原料药优势，向下游制剂延伸，目前已储备了多种低分子肝素制剂品种，为未来持续打造抗凝细分领域知名品牌奠定了基础。
          </view>
          <view class="content">
            公司是中国核医疗领先企业之一，已建成国内领先的放射性药品生产配送网络；在售产品已实现“筛查—诊断—治疗”全覆盖；在研的多个创新品种处于临床前和临床试验阶段。公司目前已初步形成了稳定的核素供应平台、药物孵化平台、转化服务平台、生产配送平台、诊疗营销平台五大平台，基本完成了从原料供应、研发、临床转化、生产、销售的核医疗全产业链布局，构建了完整的东诚药业核医疗生态圈。
          </view>
          <view class="content">
            2022年，公司荣获国家企业技术中心、工信部中国医药工业百强榜第72名、工信部专精特新“小巨人”企业、山东省制造业单项冠军企业、中国新经济企业500强、中国化药企业百强（第52位）、山东民营企业创新100强、山东民营企业现代海洋行业领军10强等荣誉称号。公司是国家高新技术企业，现有省企业技术中心、省工程技术研究中心、省工程研究中心、省工程技术协同创新中心等创新平台。
          </view>
          <view class="content">
            在“立足东方，守望健康”使命指引下，东诚药业将深化资源整合、优化产业结构、不断提升企业创新能力与核心竞争力，向着“成为持续卓越的医药企业”不断迈进。
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
