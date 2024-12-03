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
            江苏万邦生化医药集团有限责任公司（简称万邦医药）系上海复星医药（集团）股份有限公司（简称复星医药，股票代码：600196-SH，02196-HK）核心成员企业。公司前身为成立于1981年的徐州生物化学制药厂；1998年改制为徐州万邦生化制药有限公司；2001年变更为江苏万邦生化医药股份有限公司；2004年加入复星医药；2017年升级为江苏万邦生化医药集团有限责任公司。
          </view>
          <view class="content">
            万邦医药建有国内一流的原核和真核细胞基因工程药物研发和生产基地，重组人胰岛素及类似物、重组人促红细胞生成素等重组蛋白药物的研发国内领先；在小分子高难度仿制药方面亦处于行业领先地位。公司年投入约占当年销售收入10％的资金用于新品研发；拥有近400人的专职研发队伍，其中50%以上为硕士博士等高层次人员，年均申请国家专利五十余项，每年有数个新产品上市。
          </view>
          <view class="content">
            万邦医药现控股管理11家成员企业，在全国建有6个生产基地，总占地面积近千亩。公司积极推进质量体系与国际接轨，目前公司共有10余条生产线通过国外药品监督管理机构的检查认证，其中1条无菌注射剂生产线通过欧盟和美国FDA现场检查，1条口服固体制剂生产线通过美国FDA现场检查。
          </view>
		  <view class="content">
        万邦医药经营的药品涵盖化学原料药及其制剂、生化药品与生物制品、中成药等。公司专注于高血糖、高血压、高血脂、高尿酸、抗肿瘤等治疗领域药品的研发、生产与销售。动物源胰岛素、优立通®（非布司他片）、怡宝（EPO）、万苏平®（格列美脲片）、可元®（羟苯磺酸钙胶囊）、肝素钠等产品市场占有率国内名列前茅；可伊®（新复方芦荟胶囊）、可胜®（西黄胶囊）、消渴康颗粒、盾叶冠心宁片为独家品种。目前，公司共有十多个产品年销售额过亿元。
		  </view>
		  <view class="content">
        万邦医药自成立以来始终坚持诚信经营，实现了连续三十几年盈利、连续三十几年正增长的良好业绩。公司营销网络遍布全国，组建了处方药、OTC、普品等多个自营营销事业部；市场、商务、行政支持职能齐全；患者教育、客户服务细致入微；海外业务稳步发展；互联网业务突飞猛进；营销队伍超过3000人。
		  </view>
		  <view class="content">
        公司一直秉承“修身、齐家、立业、助天下”的核心价值观，并积极在企业内部营造“部队、学校、家庭”的企业文化氛围。公司大力引进和培养人才，倾听员工心声、关注员工诉求，为员工提供学习、成长和发展的空间与机会；致力于让所有员工在万邦医药的平台上，有事业可为、有发展可期、收入丰厚，并享受到工作的乐趣和家一般的温暖！
		  </view>
		  <view class="content">
        万邦医药还积极履行企业社会责任，大力推进EHS体系建设，公益投入持续增长（年均投入数百万元），“邦计划”、“万邦糖友关爱之家”、“万邦奖学金”在助医、助学、社区关怀等方面持续发力，收获了良好的社会口碑。
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
