<template>
  <view class="page">
    <van-nav-bar
      placeholder
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
    >
      <template #left><text class="title">安科生物</text></template>
    </van-nav-bar>
    <view class="wrap">
      <view class="banner">
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
          <van-swipe-item>
            <image src="../static/img/banner_13.jpg" mode="heightFix" />
          </van-swipe-item>
          <van-swipe-item>
            <image src="../static/img/banner_14.jpg" mode="heightFix" />
          </van-swipe-item>
        </van-swipe>
      </view>
      <view class="content">
        <view
          class="item"
          v-for="(item, index) in list"
          :key="index"
          @click="change(item)"
        >
          <image class="img" :src="item.img" />
          <text class="txt">{{ item.name }}</text>
        </view>
      </view>
      <view class="notice">
        <van-notice-bar
          color="#ffffff"
          background="#e15241"
          left-icon="volume-o"
          scrollable
          text="安科生物欢迎您！"
        />
      </view>
      <view class="list">
        <view
          class="view"
          v-for="(item, index) in 10"
          :key="index"
          @click="routePath"
        >
          <image class="img" src="../static/img/txt.jpg" mode="widthFix" />
          <view class="name">
            <text>保</text>
            <text>安苏萌·注射用人生长激素</text>
          </view>
          <view class="rate">
            <view class="li">
              <view class="num"><text>5.88</text>%</view>
              <view class="con">日化利率</view>
            </view>
            <view class="li">
              <view class="num"><text>15</text>分钟</view>
              <view class="con">投资期限</view></view
            >
            <view class="li">
              <view class="num">￥<text>1000.00</text>元</view>
              <view class="con">起投金额</view></view
            >
          </view>
          <view class="investor">
            <view class="con">
              <text>项目规模：20000万元</text>
              <text>每日还息：到齐还本</text>
            </view>
            <view class="btn">立即投资</view>
          </view>
          <view class="progress">
            <view class="txt">项目进度：</view>
            <van-progress class="prog" percentage="50" :show-pivot="false" />
            <view class="number">21.05%</view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import img0 from "../static/img/func_icon_touzi.png";
import img1 from "../static/img/func_icon_guanyu.png";
import img2 from "../static/img/func_icon_jisuan.png";
import img3 from "../static/img/func_icon_qiandao.png";
import img4 from "../static/img/func_icon_chongzhi.png";
import img5 from "../static/img/func_icon_tixian.png";
import img6 from "../static/img/func_icon_zhuce.png";
import img7 from "../static/img/func_icon_kefu.png";
export default {
  data() {
    return {
      list: [
        { name: "投资项目", img: img0, path: "/pages/investor" },
        { name: "关于我们", img: img1, path: "/pages/about" },
        { name: "计算器", img: img2 },
        { name: "每日签到", img: img3 },
        { name: "我要充值USDT", img: img4 },
        { name: "我要提现", img: img5 },
        { name: "免费注册", img: img6, path: "/pages/register" },
        { name: "在线客服", img: img7 },
      ],
    };
  },
  async onLoad() {
    await this.$onLaunched;
  },
  methods: {
    change({ name, path }) {
      if (["投资项目", "关于我们"].includes(name)) {
        uni.switchTab({
          url: path,
        });
      } else if (name === "计算器") {
        this.$base.show("敬请期待！！！");
      } else if (name === "免费注册") {
        uni.navigateTo({
          url: path,
        });
      }
    },
    routePath() {
      uni.navigateTo({
        url: "/pages/info",
      });
    },
  },
};
</script>

<style scoped lang="scss">
@import "../static/list.scss";
.page {
  background-color: #fafafa;
}
.title {
  font-size: 32upx;
  color: #fff;
}
.banner {
  height: 280upx;
  background: #4b80af;
  width: 100vw;
  border-bottom-left-radius: 50upx;
  border-bottom-right-radius: 50upx;
  padding: 0 30upx;
  box-sizing: border-box;
}
.my-swipe {
  padding-top: 20upx;
  .van-swipe-item {
    width: 100%;
    overflow: hidden;
    height: 350upx;
    text-align: center;
    border-radius: 20upx;
    box-sizing: border-box;
  }
  image {
    border-radius: 20upx;
  }
}
.content {
  padding: 100upx 0 0;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  .img {
    width: 70upx;
    height: 70upx;
  }
  .item {
    padding-top: 40upx;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    width: 25%;
  }
  .txt {
    padding-top: 10upx;
    font-size: 24upx;
  }
}
.notice {
  margin: 30upx 30upx 0;
  border-radius: 10upx;
  overflow: hidden;
}
</style>
