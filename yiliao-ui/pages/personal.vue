<template>
  <view class="page">
    <van-nav-bar title="Personal" :border="false" fixed safe-area-inset-top>
    </van-nav-bar>
    <view class="wrap">
      <view class="user">
        <!-- <image class="image" mode="widthFix" src="../static/images/1.jpg" /> -->
        <view class="name">{{ items.recommend }}</view>
        <view class="txt">{{ items.userPhone }}</view>
        <view class="txt">ID:{{ items.userId }}</view>
        <image
          v-if="!items.signInAbility"
          mode="widthFix"
          class="close"
          src="../static/images/d13.png"
        />
        <image
          v-else
          mode="widthFix"
          class="close"
          src="../static/images/d3.png"
          @click="sign"
        />
      </view>
      <view class="clip">
        <view class="item">
          <view class="title">
            Total assets（USDT）
            <image
              @click="padChange"
              class="look"
              mode="widthFix"
              src="../static/images/d11.png"
            />
          </view>
          <view class="txt"> {{ pwd ? "****" : items.balance }} </view>
        </view>
        <view class="item">
          <view class="title">Accumulated income</view>
          <view class="txt">
            {{ pwd ? "****" : items.accumulatedIncome }}
          </view>
        </view>
      </view>
      <view class="other">
        <view class="item" @click="change('/pages/promote')">
          <image
            class="image"
            mode="widthFix"
            src="../static/images/depositapp.png"
          />
          <view class="text">Deposit</view>
          <van-icon name="arrow" color="#ffffff" size="36upx" />
        </view>
        <view class="item" @click="change('/pages/addmoney')">
          <image
            class="image"
            mode="widthFix"
            src="../static/images/withdrawapp.png"
          />
          <view class="text">Withdraw</view>
          <van-icon name="arrow" color="#ffffff" size="36upx" />
        </view>
      </view>
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
        <van-button
          color="#5ec0b3"
          round
          block
          @click="signOut"
          :loading="loading"
        >
          sign out
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
          name: "Notice",
          path: "/pages/notice",
          icon: "d12",
        },
        {
          name: "Account details",
          path: "/pages/accountDetails",
          icon: "d10",
        },
        {
          name: "My investment",
          path: "/pages/investment",
          icon: "d9",
        },
        {
          name: "Interest statement",
          path: "/pages/interest",
          icon: "d8",
        },
        {
          name: "Fund flow",
          path: "/pages/fundRecord",
          icon: "d7",
        },
        {
          name: "Membership program",
          path: "/pages/memberPlan",
          icon: "d6",
        },
        {
          name: "Change password",
          path: "/pages/set",
          icon: "d5",
        },
        {
          name: "Withdraw wallet address",
          path: "/pages/usdtadd",
          icon: "d4",
        },
      ],
      loading: false,
      items: {},
      pwd: "",
    };
  },
  onTabItemTap(e) {
    this.$base.authorityPage(this);
  },
  onLoad() {
    this.pwd = uni.getStorageSync("pwd");
    this.$base.authorityPage(this).then(() => {
      uni.showLoading();
      this.$api.user_info().then((res) => {
        if (res.data.code == 0) {
          this.items = res.data.data;
          this.$base.storage("phone", this.items.userPhone);
          this.$base.storage("info", this.items);
        } else {
          this.$base.show(res.data.msg);
        }
      });
    });
  },
  methods: {
    padChange() {
      this.$base.storage("pwd", !uni.getStorageSync("pwd"));
      this.pwd = uni.getStorageSync("pwd");
    },
    PathRouter() {
      uni.navigateTo({
        url: "notice",
      });
    },
    sign() {
      this.$api.user_sign().then((res) => {
        if (res.data.code == 0) {
          this.items.signInAbility = false;
        }
        this.$base.show(res.data.msg);
      });
    },
    signOut() {
      this.loading = true;
      this.$api.user_logout().then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          this.$base.show(res.data.msg, "success");
          uni.removeStorage({
            key: "token",
            success() {
              uni.switchTab({
                url: "/pages/index",
              });
            },
          });
        }
      });
    },
    change(url) {
      if (url === "/pages/notice" || url === "/pages/memberPlan") {
        this.$base.storage("path", "/pages/personal");
      }
      uni.navigateTo({
        url,
      });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  padding-top: 126upx;
  .user {
    text-align: center;
    position: relative;
    padding-bottom: 40upx;
    .close {
      width: 45upx;
      position: absolute;
      top: 20upx;
      right: 30upx;
    }
    .image {
      width: 110upx;
      height: 110upx;
      border-radius: 50%;
      border: 1upx solid #5ec0b3;
      overflow: hidden;
    }
    .name,
    .txt {
      padding-top: 10upx;
      color: #333;
      font-size: 28upx;
    }
  }
  .row {
    margin: 0 20upx;
  }
  .list {
    display: flex;
    align-items: center;
    justify-content: space-between;
    line-height: 90upx;
    padding: 10upx 16upx;
    .title {
      display: flex;
      align-items: center;
      .txt {
        font-size: 24upx;
        width: 65%;
        padding-left: 30upx;
      }
      .icon {
        width: 38upx;
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
  .clip {
    margin: 0 20upx 20upx 20upx;
    width: calc(100% - 40upx);
    border-radius: 10upx;
    display: flex;
    align-items: center;
    justify-content: space-between;
    text-align: center;
    background-color: #5ec0b3;
    padding: 40upx 0upx;
    .item {
      width: 50%;
      position: relative;
      &:nth-child(1)::after {
        content: "";
        position: absolute;
        top: 50%;
        right: 0;
        transform: translateY(-50%);
        background: #596785;
        height: 100%;
        width: 1upx;
      }
      .title {
        color: #fff;
        font-size: 28upx;
        padding-bottom: 30upx;
        display: flex;
        align-items: center;
        justify-content: center;
      }
      .txt {
        color: #fff;
        font-size: 30upx;
        padding-bottom: 10upx;
      }
      .look {
        width: 30upx;
        margin-left: 10upx;
      }
    }
  }
  .other {
    width: calc(100% - 40upx);
    margin: 0 20upx 20upx;
    display: flex;
    align-items: center;
    justify-content: space-between;
    .item {
      width: calc(50% - 10upx);
      padding: 20upx 0;
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: #5ec0b3;
      border-radius: 10upx;
      .image {
        width: 120upx;
      }
      .text {
        font-size: 30upx;
        color: #fff;
        padding: 0 10upx;
      }
    }
  }
}
</style>
