<template>
  <view class="page">
    <van-nav-bar
      title="Change password"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="row">
        <view
          class="list van-hairline--bottom"
          v-for="(item, index) in list"
          :key="index"
          @click="change(item.path)"
        >
          <view class="title van-ellipsis">
            <image
              class="icon"
              mode="widthFix"
              :src="`../static/images/${item.icon}.png`"
            />
            <view class="txt">{{ item.name }}</view>
          </view>
          <view class="value">
            <van-icon name="arrow" color="#969799" size="30upx" />
          </view>
        </view>
      </view>
      <view class="btns">
        <van-button color="#5ec0b3" round block @click="signOut">
          Exit current account
        </van-button>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      list: [
        {
          name: "Change login password",
          path: "/pages/loginPwd",
          icon: "d2",
        },
        {
          name: "Reset transaction password",
          path: "/pages/resetPayPwd",
          icon: "d1",
        },
      ],
    };
  },
  onLoad() {},
  methods: {
    signOut() {
      this.loading = true;
      this.$api.user_logout().then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          uni.removeStorageSync("token");
          uni.switchTab({
            url: "/pages/index",
          });
        }
        this.$base.show(res.data.msg, "success");
      });
    },
    change(url) {
      uni.navigateTo({
        url,
      });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  .row {
    margin: 0 20upx;
  }
  .list {
    display: flex;
    align-items: center;
    justify-content: space-between;
    line-height: 110upx;
    padding: 0 16upx;
    .title {
      display: flex;
      align-items: center;
      .txt {
        font-size: 28upx;
        width: 65%;
        padding-left: 30upx;
      }
      .icon {
        width: 25upx;
      }
    }
    .value {
      width: 35%;
      text-align: right;
    }
  }
  .btns {
    padding: 60upx 0 140upx 0;
    width: 80%;
    margin: 0 auto;
    .van-button {
      height: 80upx;
      font-size: 30upx;
    }
  }
}
</style>
