<template>
  <view class="page">
    <u-navbar
      placeholder
      :border="false"
      autoBack
      :title="config.jiancheng"
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
        <view class="banner">
          <u-swiper :list="list2" class="my-swipe" indicator> </u-swiper>
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
          <u-notice-bar
            color="#ffffff"
            bgColor="#e15241"
            scrollable
            fontSize="28rpx"
            :text="config.home_notice || ''"
          />
        </view>
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
              ></u-line-progress>
              <view class="number">{{ item.schedule }}%</view>
            </view>
          </view>
          <u-empty class="empty2" text="暂无数据" v-if="!shopGoods.length" />
        </view>
      </scroll-view>
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
import banner1 from "../static/img/banner_13.jpg";
import banner2 from "../static/img/banner_14.jpg";
export default {
  data() {
    return {
      list: [
        { name: "投资项目", img: img0, path: "/pages/investor" },
        { name: "关于我们", img: img1, path: "/pages/about" },
        { name: "计算器", img: img2 },
        { name: "每日签到", img: img3 },
        {
          name: "我要充值USDT",
          img: img4,
          path: "/pages/preview",
        },
        { name: "我要提现", img: img5, path: "/pages/withdraw" },
        { name: "免费注册", img: img6, path: "/pages/register" },
        {
          name: "在线客服",
          img: img7,
          path: "/pages/preview",
        },
      ],
      list2: [banner1, banner2],
      shopGoods: [],
      config: {},
      infos: {},
    };
  },
  async onLoad() {
    await this.$onLaunched;
    this.config = uni.getStorageSync("system_config");
    this.infos = uni.getStorageSync("infos");
  },
  onShow() {
    // 获取产品列表
    this.$api.project_list().then(({ data }) => {
      if (data.code == 0) {
        this.shopGoods = data.data;
      }
    });
  },
  methods: {
    change({ name, path, url }) {
      if (["投资项目", "关于我们"].includes(name)) {
        uni.switchTab({
          url: path,
        });
      } else if (name === "计算器") {
        this.$base.show("敬请期待！");
      } else if (name === "免费注册") {
        uni.navigateTo({
          url: path,
        });
      } else if (name === "每日签到") {
        this.$api.user_sign().then(({ data }) => {
          if (data.code == 0) {
            this.$base.show(data.msg + "~");
          }
        });
      } else if (["我要充值USDT"].includes(name)) {
        uni.navigateTo({
          url: "/pages/onlineService",
        });
      } else if (["在线客服"].includes(name)) {
        uni.navigateTo({
          url: "/pages/onlineService",
        });
      } else if (["我要提现"].includes(name)) {
        if (!this.infos.bankCardNum && !this.infos.walletAddr) {
          return this.$base.show("请先绑定一种提款方式~");
        } else {
          uni.navigateTo({
            url: "/pages/withdraw",
          });
        }
      }
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
.list {
  background-color: #fff;
}
.scroll {
  height: calc(100vh - 210rpx + var(--status-bar-height));
}
.page {
  background-color: #fafafa;
}
.banner {
  height: 280rpx;
  background: #4b80af;
  width: 100vw;
  border-bottom-left-radius: 50rpx;
  border-bottom-right-radius: 50rpx;
  padding: 20rpx 30rpx 0;
  box-sizing: border-box;
}
/deep/.u-swiper,
/deep/.u-swiper__wrapper,
.my-swipe {
  height: 350rpx !important;
  border-radius: 20rpx !important;
  .u-swiper__wrapper__item__wrapper__image {
    height: 100% !important;
    border-radius: 20rpx !important;
  }
}
.content {
  padding: 100rpx 0 0;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  .img {
    width: 70rpx;
    height: 70rpx;
  }
  .item {
    padding-top: 40rpx;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    width: 25%;
  }
  .txt {
    padding-top: 10rpx;
    font-size: 24rpx;
  }
}
.notice {
  margin: 30rpx 30rpx 0;
  border-radius: 10rpx;
  overflow: hidden;
  /deep/.uicon-volume {
    font-size: 32rpx !important;
    line-height: 32rpx !important;
  }
}
.empty2 {
  padding-top: 40rpx;
  background-color: #fff;
}
</style>
