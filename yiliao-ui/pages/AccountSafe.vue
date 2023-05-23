<template>
  <view class="page">
    <van-nav-bar
      placeholder
      :border="false"
      fixed
      title="账户安全"
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
      <template #left>
        <van-icon name="arrow-left" size="18" />
      </template>
    </van-nav-bar>
    <view class="wrap">
      <view class="content">
        <view class="list">
          <view class="image">
            <image class="icon-img2" src="../static/img/phone.png" />
          </view>
          <label>用户名</label>
          <view class="right-text">{{ infos.userName }}</view>
        </view>
        <view class="list margintop10 borderBottom" @click="goRealName">
          <view class="image">
            <image class="icon-img" src="../static/img/admin.png" />
          </view>
          <label>实名认证</label>
          <view class="right-text">{{
            infos.idCard ? "已认证" : "未认证"
          }}</view>
        </view>
        <view class="list borderBottom" @click="goChangeLoginPassword">
          <view class="image">
            <image class="icon-img" src="../static/img/pass.png" />
          </view>
          <label>修改登录密码</label>
        </view>
        <view class="list" @click="goModifyPaymentPassword">
          <view class="image">
            <image class="icon-img2" src="../static/img/key.png" />
          </view>
          <label>修改支付密码</label>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      infos: {},
    };
  },
  async onShow() {
    await this.$onLaunched;
    this.infos = uni.getStorageSync("infos");
  },
  methods: {
    goRealName() {
      uni.navigateTo({
        url: "/pages/RealName",
      });
    },
    goModifyPaymentPassword() {
      uni.navigateTo({
        url: "/pages/ModifyPaymentPassword",
      });
    },
    goChangeLoginPassword() {
      uni.navigateTo({
        url: "/pages/ChangeLoginPassword",
      });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  background-color: #f7f8fa;
  height: 100vh;
  .content {
    padding-top: 14upx;
    color: #557fab;
    .list {
      display: flex;
      padding: 20upx;
      background-color: #fff;
      font-size: 28upx;
      height: 52upx;
      align-items: center;
      .image {
        margin-right: 20upx;
      }
      .image,
      .icon-img {
        width: 40upx;
        height: 40upx;
      }
      .icon-img2 {
        width: 36upx;
        height: 36upx;
      }
      label {
        flex: 1;
      }
      .right-text {
        text-align: right;
      }
    }
  }
}
</style>
