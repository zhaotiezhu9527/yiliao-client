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
        <image
          class="image-title"
          mode="widthFix"
          src="../static/img/about_title.jpg"
        />
        <image
          class="image"
          mode="widthFix"
          src="../static/img/about.jpg"
        />
        <view>
          <view class="content">
            中国同辐股份有限公司是中国核工业集团有限公司控股子公司，于2011年在原中国同位素有限公司基础上重组设立，是中核集团面向人民生命健康科技进军的主力和先进核科技成果转化的主通道。肩负建设“核工业强国”和“健康中国”双重责任，公司聚焦核医疗健康及辐照应用两大产业方向，致力于提供核医学整体解决方案、放疗整体解决方案以及辐射技术推广应用，2018年7月6日在香港联合交易所成功上市。
          </view>
          <view class="content">
            在放射性药品方面，中国同辐作为我国放射性药品及其应用服务的开创者，是中国规模最大的诊断及治疗用放射性药品供应与服务商，市场占有率高达70%，并专注肿瘤、心血管疾病及神经退形性疾病、脏器功能等诊疗药物和试剂的研发与生产。
          </view>
          <view class="content">
            在医学诊断方面，中国同辐以同位素技术应用为根基精准发力，在已开发的细分领域处于市场领导地位，并积极拓展相关业务，为用户提供更加全面的诊断服务，呼气诊断产品国内市场占有率达76％，放免产品国内市场份额第一，可提供近2000余项医学检测服务。
          </view>
          <view class="content">
            在核医疗装备方面，中国同辐坚持“高起点、高质量”的发展策略，专注于螺旋断层放疗系统和放射外科手术机器人等高端放疗装备的研发生产，不断引进、消化、吸收先进技术，逐步成为国际一流的高端核医疗装备制造商与服务商，助力“中国智造”。
          </view>
          <view class="content">
            在放射源方面，中国同辐是我国最早开展的放射源生产供应商，可提供航空航天、工业探伤等诸多领域的放射源及应用服务，市场占有率超过70%，自主研发的国产高比活度工业辐照用钴60放射源打破国际垄断并实现出口，是中国第一、世界第三大钴源供应商，连续三十余年为多种堆型提供启动中子源，助力中国核电事业发展。
          </view>
          <view class="content">
            在辐照应用方面，中国同辐以市场需求为导向，形成了辐照应用整体解决方案和全产业链服务能力，广泛服务于医疗器械、食品及药品的消毒灭菌及高分子材料改性，大型辐照装置设计建设市场占有率超过80%，并已出口到东南亚等国家。
          </view>
          <view class="content">
            在核素制造方面，中国同辐建立了工业用钴60和医用高比活度钴60的规模化生产能力，具备碘131、锶89、无载体镥177等反应堆核素、铜64、锆89、碘123、钯103等加速器核素的批量供应能力。
          </view>
          <view class="content">
            在进出口贸易方面，中国同辐立足国内国际两个市场，建立了国内最全面的国际核技术产品引进渠道，贸易平台已发展成为国际先进放射性药物进入中国市场的最优选择，积极推进“贸易先行、产业跟进”战略，核技术应用产品与技术已出口至全球50多个国家和地区。
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
