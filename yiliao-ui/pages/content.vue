<template>
  <view class="page">
    <van-nav-bar
      placeholder
      :border="false"
      fixed
      safe-area-inset-top
      title="立即投资"
      @click-left="$base.BackPage(`/pages/info?id=${form.projectId}`)"
    >
      <template #left>
        <van-icon name="arrow-left" size="18" />
      </template>
    </van-nav-bar>
    <view class="cardStyle">
      <view class="card">
        <view class="item">
          <view>账户可用余额 (元)</view>
          <view>￥{{ infos.balance }}元</view>
        </view>
        <view class="item">
          <view>项目可投金额 (元)</view>
          <view>{{ items.projectAmount }}元</view>
        </view>
      </view>
    </view>
    <view class="amount">
      <view class="li">
        <view class="name">起投金额</view>
        <view class="txt"
          >￥<text>{{ items.minAmount }}</text
          >元</view
        >
      </view>
      <view class="li">
        <view class="name">投资金额</view>
        <view class="txt images">
          <image
            class="img"
            @click="subtract()"
            src="../static/img/jian.png"
            mode="widthFix"
          />
          <van-field
            v-model="form.amount"
            type="number"
            placeholder="请输入金额"
          />
          <image
            class="img"
            @click="add()"
            src="../static/img/jia.png"
            mode="widthFix"
          />
        </view>
      </view>
      <view class="li end">
        <view class="btns">
          <van-button color="#f34133" @click="fullthrow"> 一键全投 </van-button>
        </view>
      </view>
    </view>
    <view class="pwd">
      最低起投<text>{{ items.minAmount }}</text
      >元，加一次为<text>{{ items.minAmount }}</text
      >元,<text class="other">一键全投</text>即将账户可用余额<text>
        {{ infos.balance }} </text
      >元的倍数进行投资。
    </view>
    <view class="input">
      <van-field
        label="支付密码"
        v-model="form.pwd"
        :maxlength="6"
        type="number"
        placeholder="请输入支付密码"
      />
    </view>
    <view class="btn">
      <van-button color="#4b80af" block @click="investor">
        立即投资
      </van-button>
    </view>
  </view>
</template>
<script>
export default {
  data() {
    return {
      items: {},
      infos: {},
      form: {
        amount: "",
        pwd: "",
        projectId: 0,
      },
    };
  },
  onLoad(e) {
    this.form.projectId = e.id;
    // 获取产品详情
    this.dataFn(e.id);
  },
  methods: {
    change() {
      uni.navigateTo({
        url: "/pages/content",
      });
    },
    dataFn(id) {
      // 投资提交完成，则再一次获取信息
      this.$api.user_info().then(({ data }) => {
        if (data.code == 0) {
          this.infos = data.data;
        }
      });
      // 获取产品详情
      this.$api.project_info(id).then(({ data }) => {
        if (data.code == 0) {
          this.items = data.data;
        }
      });
    },
    // 立即投资
    investor() {
      if (!this.form.amount) {
        this.$base.show("请输入金额");
        return false;
      } else if (Number(this.form.amount) < Number(this.items.minAmount)) {
        this.$base.show("金额必须大于起投金额");
        return false;
      } else if (Number(this.form.amount) > Number(this.items.projectAmount)) {
        this.$base.show("金额不可大于项目可投金额");
        return false;
      } else if (!this.form.pwd && this.form.pwd + "".length < 6) {
        this.$base.show("请输入正确支付密码");
        return false;
      }
      this.$api.order_execute(this.form).then(({ data }) => {
        if (data.code == 0) {
          this.$base.show("投资成功~");
          this.form = {
            amount: "",
            pwd: "",
            projectId: this.items.projectId,
          };
          setTimeout(() => {
            this.dataFn(this.items.projectId);
          }, 2000);
        }
      });
    },
    // 一键全投
    fullthrow() {
      this.form.amount = Number(this.infos.balance);
    },
    // 加减
    add() {
      if (this.items.minAmount >= this.infos.balance) {
        this.fullthrow();
        return false;
      } else if (this.form.amount < this.items.minAmount) {
        this.form.amount = this.items.minAmount;
        return false;
      }
      if (this.form.amount > Number(this.items.projectAmount)) {
        this.form.amount = Number(this.items.projectAmount);
      } else if (this.form.amount >= Number(this.infos.balance)) {
        this.form.amount = Number(this.infos.balance);
      } else {
        this.form.amount =
          Number(this.form.amount) + Number(this.items.minAmount);
      }
    },
    subtract() {
      if (this.items.minAmount >= this.infos.balance) {
        this.fullthrow();
        return false;
      } else if (this.form.amount <= this.items.minAmount) {
        this.form.amount = this.items.minAmount;
        return false;
      }
      this.form.amount -= this.items.minAmount;
    },
  },
};
</script>
<style scoped lang="scss">
view,
text {
  font-weight: 500;
}
.cardStyle {
  padding: 40upx 30upx;
  .card {
    background-color: #4c80b0;
    border-radius: 10upx;
    padding: 40upx;
    box-sizing: border-box;
    display: flex;
    color: #fff;
    justify-content: center;
    .item {
      width: 50%;
      text-align: center;
      view:nth-child(1) {
        font-size: 26upx;
      }
      view:nth-child(2) {
        padding-top: 40upx;
        font-size: 32upx;
      }
    }
  }
}
.amount {
  padding: 0 30upx;
  .li {
    display: flex;
    justify-content: space-between;
    padding-bottom: 20upx;
    font-size: 32upx;
    &:nth-child(1) {
      border-bottom: 2upx solid #d3d3d3;
    }
    &:nth-child(2) {
      align-items: center;
    }
    text {
      color: #4c80b0;
    }
    &.end {
      padding-top: 20upx;
      width: 100%;
      display: flex;
      justify-content: flex-end;
      .van-button__content span {
        font-size: 32upx;
      }
    }
    .images {
      width: 60%;
      display: flex;
      align-items: center;
      justify-content: center;
      border-bottom: 2upx solid #d3d3d3;
      .img {
        width: 64upx;
        height: 64upx;
      }
      /deep/.van-cell::after {
        border: 0;
      }
      /deep/.van-field__label {
        span {
          font-weight: 500;
        }
      }
      /deep/.van-field__control {
        font-size: 32upx;
        color: #000;
        text-align: right;
        font-weight: 500;
      }
    }
    .btns {
      .van-button {
        padding: 0 60upx;
        border-radius: 10upx;
      }
    }
  }
}
.pwd {
  font-size: 28upx;
  padding: 50upx 0 20upx;
  margin: 0 30upx;
  box-sizing: border-box;
  border-bottom: 2upx solid #d3d3d3;
  color: #ababab;
  text {
    color: #4c80b0;
    &.other {
      color: #000;
    }
  }
}
.input {
  /deep/.van-field__label {
    span {
      font-size: 32upx;
      color: #000;
      font-weight: 500;
    }
  }
  /deep/.van-field__control {
    font-size: 32upx;
    color: #000;
    text-align: right;
    font-weight: 500;
  }
}
.btn {
  padding: 10upx 30upx;
  width: 100%;
  box-sizing: border-box;
  .van-button {
    border-radius: 10upx;
  }
  .van-button__content span {
    font-size: 32upx;
  }
}
</style>
