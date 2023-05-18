<template>
  <view class="pages">
    <van-nav-bar
      title="Buy"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="pathChange"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="number">
        <view class="txt">Available balance of account:</view>
        <view class="num">{{ info.balance }}</view>
      </view>
      <view class="row">
        <view class="list">
          <view class="title">Minimum amount: </view>
          <view class="value main"> {{ items.minAmount }}</view>
        </view>

        <view class="list">
          <view class="title">Maximum investment:</view>
          <view class="value"> {{ items.maxAmount }}</view>
        </view>

        <view class="list">
          <view class="title">lnterest settlement time:</view>
          <view class="value">
            <text class="txt"> {{ items.period }} {{ items.unit | time }}</text>
            Automatic interest
          </view>
        </view>

        <view class="list">
          <view class="title">Earnings deadline: </view>
          <view class="value">
            Daily dividend {{ items.interest * 100 }}%,
            {{ items.unit | time }}
          </view>
        </view>

        <view class="list">
          <view class="title">Purchase limit:  </view>
          <view class="value"> {{ items.buyLimit }}</view>
        </view>
      </view>
      <view class="other">
        <view class="value w20">Investment amount</view>
        <view class="value w80 van-ellipsis">
          <van-field
            v-model="form.amount"
            type="number"
            placeholder="Please input the investment amount"
          />
        </view>
      </view>
      <view class="btns">
        <van-button color="#5ec0b3" round block @click="link">
          Invista agora
        </van-button>
      </view>
    </view>
  </view>
</template>
<script>
export default {
  data() {
    return {
      items: {},
      info: { balance: "" },
      form: {
        amount: "",
        productId: "",
      },
    };
  },
  onShow() {
    uni.showLoading();
    this.$api.user_balance().then((res) => {
      if (res.data.code == 0) {
        this.info.balance = res.data.balance;
      } else {
        this.$base.show(res.data.msg);
      }
    });
    this.form.productId = this.$route.query.id;
    this.$api.product_info(this.$route.query.id).then((res) => {
      if (res.data.code == 0) {
        this.items = res.data.data;
      } else {
        this.$base.show(res.data.msg);
      }
    });
  },
  filters: {
    time(value) {
      let type = "";
      switch (value) {
        case 1:
          type = "Día";
          break;
        case 2:
          type = "Horas";
          break;
        case 3:
          type = "Minuto";
          break;
      }
      return type;
    },
  },
  methods: {
    pathChange() {
      this.$base.BackPage("/pages/info?id=" + this.$route.query.id);
    },
    link() {
      if (this.form.productId === "") {
        return this.$base.show("Please enter the product ID");
      } else if (this.form.amount === "") {
        return this.$base.show("Please input the investment amount");
      }
      this.$api.product_buy(this.form).then((res) => {
        if (res.data.code == 0) {
          this.form.amount = "";
          this.$base.show(res.data.msg, "success");
        } else {
          this.$base.show(res.data.msg);
        }
      });
    },
  },
};
</script>
<style scoped lang="scss">
/deep/.van-cell {
  background: transparent;
}
.number {
  padding: 40upx 30upx;
  background: #e4f3f1;
  margin: 28upx;
  border-radius: 15upx;
  .txt {
    font-size: 28upx;
    color: #073441;
  }
  .num {
    font-size: 50upx;
    color: #073441;
    padding-top: 20upx;
    font-weight: 600;
  }
}
.other {
  width: 100%;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  line-height: 40upx;
  padding: 10upx 20upx;
  background: #e4f3f1;
  .title {
    font-size: 28upx;
    width: 20%;
  }
  .value {
    width: 50%;
    font-size: 28upx;
    color: #404040;
    &.w20 {
      width: 20%;
    }
    &.w80 {
      width: 80%;
    }
  }
}
.row {
  padding: 40upx 28upx 30upx;
}
.list {
  width: 100%;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-between;
  line-height: 110upx;
  background-color: #ffffff;
  .title {
    display: flex;
    align-items: center;
    font-size: 26upx;
    width: 50%;
    color: #404040;
  }
  .value {
    width: 50%;
    text-align: right;
    font-size: 26upx;
    color: #404040;
    &.main {
      color: #2ba589;
      font-size: 50upx;
    }
    .txt {
      color: #e6a23c;
    }
  }
}
.btns {
  padding: 40upx 40upx 0;
  .van-button__content span {
    font-size: 30upx;
  }
}
</style>
