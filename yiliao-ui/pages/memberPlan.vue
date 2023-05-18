<template>
  <view class="page">
    <van-nav-bar
      title="Member plan"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="pathChange"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="clip">
        <view class="item">
          <view class="title"> Total team investment </view>
          <view class="txt"> ${{ items.totalTeamInvestment }} </view>
        </view>
        <view class="item">
          <view class="title"> Total Team People </view>
          <view class="txt"> {{ items.totalTeamPeople }} </view>
        </view>
        <view class="memberplantf">
          Total commission fees received:
          <text class="number">${{ items.totalCommission }}</text>
          <van-icon
            @click="show = true"
            color="#404040"
            size="35upx"
            name="question-o"
          />
        </view>
      </view>
      <view class="row">
        <view class="list">
          <view class="title van-ellipsis">
            Invite Code: {{ items.inviteCode }}</view
          >
          <view class="value copy" @click="copyChange(items.inviteCode)">
            copy
          </view>
        </view>
        <view class="list">
          <view class="title van-ellipsis">
            Invite Link: {{ items.inviteUrl }}</view
          >
          <view class="value copy" @click="copyChange(items.inviteUrl)">
            copy
          </view>
        </view>
      </view>
      <view class="view">
        <view class="list van-hairline--bottom">
          <view class="value">Member list</view>
          <view class="value"> Level </view>
          <view class="value"> Commission fees </view>
        </view>
        <template v-if="items.members.length">
          <view
            v-for="(item, index) in items.members"
            class="list van-hairline--bottom"
            :key="index"
          >
            <view class="value"> {{ item.userPhone }}</view>
            <view class="value">{{ item.userLevel }} </view>
            <view class="value">{{ item.commission }} </view>
          </view>
        </template>
        <van-empty description="no more" v-else />
      </view>
    </view>
    <van-popup v-model="show" position="top" round>
      <view class="popup">
        Each user can invite 2 levels of users. There is no limit to the number
        of people. Direct invitation is the first level, and the second level
        invitation is the second level. The first level enjoys a 2% rebate of
        the investment amount of each invited user, and the second level enjoys
        a 1% rebate of the investment amount of each invited user. For example,
        if the level 1 user you invited has invested 10000 USDT, you can get
        10000*2%=200 USDT. If the level 2 user you invited has invested 10000
        USDT, you can get 10000*1%=100 USDT
        <view class="txt">
          PS：Only when the investment amount of the invitee exceeds 50 USDT,
          his inviter can get the reward
        </view>
      </view>
    </van-popup>
  </view>
</template>

<script>
export default {
  data() {
    return {
      show: false,
      items: {
        members: [],
      },
    };
  },
  onLoad() {
    uni.showLoading();
    this.$api.user_team_detail().then((res) => {
      if (res.data.code == 0) {
        this.items = res.data.data;
        this.items.inviteUrl = `${this.items.inviteUrl}/#/pages/register?inviteCode=${this.items.inviteCode}`;
      }
    });
  },
  methods: {
    pathChange() {
      this.$base.BackPage(uni.getStorageSync("path") || "");
    },
    r(value) {
      uni.setClipboardData({
        data: value,
        success: () => {
          uni.hideToast();
          this.$base.show("Copy Success!", "success");
        },
      });
    },
  },
};
</script>

<style scoped lang="scss">
.popup {
  padding: 40upx;
  font-size: 28upx;
  line-height: 1.5;
}
.wrap {
  padding: 126upx 0upx 50upx;
}
.clip {
  margin: 0 40upx 20upx;
  width: calc(100% - 80upx);
  border-radius: 10upx;
  display: flex;
  align-items: center;
  justify-content: space-between;
  text-align: center;
  flex-wrap: wrap;
  background-color: #dff2f0;
  padding: 40upx 0upx;
  .item {
    width: 50%;
    position: relative;
    .title {
      color: #404040;
      font-size: 24upx;
      padding-bottom: 30upx;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .txt {
      color: #404040;
      font-size: 30upx;
      padding-bottom: 10upx;
    }
  }
  .memberplantf {
    font-size: 24upx;
    padding: 20upx 0 0 50upx;
    color: #404040;
    .number {
      font-size: 30upx;
      display: inline-block;
      padding-right: 6upx;
    }
  }
}
.row {
  .list {
    width: 100%;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: space-between;
    line-height: 110upx;
    padding: 0 16upx;
    margin-bottom: 6upx;
    background-color: #dff2f0;
    .title {
      display: flex;
      align-items: center;
      font-size: 28upx;
      width: 70%;
      padding-left: 10upx;
    }
    .value {
      width: 30%;
      text-align: right;
      font-size: 30upx;
      color: #4b80af;
      font-weight: 700;
    }
  }
}
.view {
  padding-top: 20upx;
  .list {
    width: 100%;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: space-between;
    line-height: 110upx;
    padding: 0 30upx;
    &:nth-child(1)::after {
      border-color: #404040;
    }
    .title {
      display: flex;
      align-items: center;
      font-size: 24upx;
      width: 70%;
      padding-left: 30upx;
    }
    .value {
      width: 30%;
      &:nth-child(1) {
        width: 40%;
        text-align: left;
      }
      &:nth-child(2) {
        text-align: center;
      }
      text-align: right;
      font-size: 20upx;
      color: #333;
    }
  }
}
</style>
