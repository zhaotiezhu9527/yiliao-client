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
            齐鲁制药以维护人类健康为宗旨，以满足社会需求为己任，以先进的科学技术为依托，致力于研制开发治疗常见病、多发病及其它多种严重危害人类健康疾病的药物。
          </view>
          <view class="content">
            公司总部位于山东省济南市，是中国大型综合性现代制药企业，专业从事治疗肿瘤、心脑血管、抗感染、精神系统、神经系统、眼科疾病的制剂及其原料药的研制、生产与销售。
          </view>
          <view class="content">
            公司始终坚持创新发展战略，以市场需求为核心，以产品创新为先导，广泛拓展国内外科研开发合作，注重人才的引进与培养，建有一支高素质的科研队伍，具备专业而高效的研发能力，已先后研制成功了近百个新药，为公司未来的发展建立了合理的在研产品线，多个项目获得国家科技进步二等奖，并创造了良好的社会效益。
          </view>
		  <view class="content">
		    公司建有制剂、化学合成、生物技术、抗生素发酵等十一大生产基地以及现代化的生产车间，全自动生产线和其他主要生产设备及检测仪器均购自于世界主要专业制药设备制造商，抗生素类原料药、头孢类原料药、抗肿瘤类原料药等数个产品已具有国内乃至世界领先的生产能力和生产水平。
		  </view>
		  <view class="content">
		    公司建设了严格规范的质量控制机构和完善的质量保证体系，是首批国家食品药品监督管理局GMP认证企业。其中非无菌原料药（发酵、化学合成）、无菌原料药及多种制剂通过了美国、欧盟、澳大利亚、英国、南非以及其它国家和地区药品监管机构的认证。
		  </view>
		  <view class="content">
		    公司的产品结构科学完整，已上市产品达300余种，具有治疗领域广、产品系列化特点。凭借过硬的产品质量、全心全意为客户的服务宗旨，在医药领域树立了齐鲁品牌，拥有遍布全国各大省市，远销北美、欧盟、日本、澳大利亚等地的全球销售网络，以卓越的服务向中国和世界呈现着完全可以信赖的真诚。
		  </view>
		  <view class="content">
		    “大医精诚，家国天下”， 公司凝练出独具齐鲁特色的企业文化，实现了员工与企业的共同成长。在快速发展的同时，切实履行各项社会责任，扶危救困，奉献爱心，以辉煌的成就、傲人的气魄展示了中国民族医药企业的发展与腾飞。
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
