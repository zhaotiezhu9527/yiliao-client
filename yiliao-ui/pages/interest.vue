<template>
  <view class="page">
    <van-nav-bar
      title="Interest statement"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="clip">
        <view class="item">
          <view class="title"> Received interest </view>
          <view class="txt"> {{ items.receivedInterest }} </view>
        </view>
        <view class="item">
          <view class="title">Interest to be collected </view>
          <view class="txt"> {{ items.interestToBeCollected }} </view>
        </view>
      </view>
      <view class="row">
        <van-list
          :immediate-check="false"
          v-model="loading"
          :finished="finished"
          loading-text="loading..."
          finished-text="No more"
          @load="load"
          v-if="isArray"
        >
          <view class="list top">
            <view class="value"> Product name </view>
            <view class="value"> Amount </view>
            <view class="value"> Invested time </view>
          </view>
          <view
            class="list white van-hairline--bottom"
            v-for="(item, index) in list"
            @click="change(item)"
            :key="index"
          >
            <view
              class="value"
              :class="{
                success: item.status === 1,
                loading: item.status === 0,
              }"
            >
              {{ item.productName }}
            </view>
            <view class="value"> {{ item.amount }} </view>
            <view class="value"> {{ item.investedTime }} </view>
          </view>
        </van-list>
        <van-empty description="No more" v-else />
      </view>
    </view>
    <van-dialog v-model="show" :showConfirmButton="false">
      <view class="dialog">
        <view class="header">
          <van-icon name="cross" size="30upx" @click="show = false" />
        </view>
        <view class="content">
          <view class="list white van-hairline--bottom">
            <view class="w50">Número de encomenda</view>
            <view class="w50"> {{ items.orderNo || "-" }} </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">Nome</view>
            <view class="w50"> {{ items.productName || "-" }} </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">Montante</view>
            <view class="w50">
              {{ items.amount || "-" }}
            </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">Ciclo de lançamento</view>
            <view class="w50">
              {{ items.period }} {{ items.unit | time }}
            </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">Lucro</view>
            <view class="w50">
              {{ items.interest }}% ({{ items.income || "-" }})
            </view>
          </view>
          <view class="list white">
            <view class="w50">Benefício total</view>
            <view class="w50">
              {{ items.totalInterest }}% ({{ items.totalIncome || "-" }})
            </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">Estado</view>
            <view
              class="w50"
              :class="{
                success: items.status === 1,
                loading: items.status === 0,
              }"
            >
              {{ items.statusStr || "-" }}
            </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">Tempo de criação</view>
            <view class="w50"> {{ items.settleTime || "-" }} </view>
          </view>
          <view class="list white van-hairline--bottom">
            <view class="w50">Data de expiração</view>
            <view class="w50"> {{ items.investedTime || "-" }} </view>
          </view>
        </view>
        <view class="btns">
          <van-button
            color="linear-gradient(90deg,#ff6034,#ee0a24)"
            round
            block
            @click="show = false"
          >
            confirm
          </van-button>
        </view>
      </view>
    </van-dialog>
  </view>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      finished: false,
      list: [],
      isArray: true,
      page: 0,
      show: false,
      items: {},
    };
  },
  onLoad() {
    uni.showLoading();
    this.page = 1;
    this.dataFn();
  },
  filters: {
    time(value) {
      let type = "";
      switch (value) {
        case 1:
          type = "Dia";
          break;
        case 2:
          type = "Horas";
          break;
        case 3:
          type = "Ata";
          break;
      }
      return type;
    },
  },
  methods: {
    load() {
      this.page++;
      this.dataFn(this.page);
    },
    change(item) {
      this.items = item;
      this.show = true;
    },
    dataFn(page = 1, limit = 30) {
      this.$api.user_interest({ page, limit }).then((res) => {
        if (res.data.code == 0) {
          this.items = res.data;
          const vim = res.data.page;
          this.list = this.list.concat(vim.list);
          this.isArray = vim.totalCount ? true : false;
          if (this.page >= vim.totalPage) {
            this.finished = true;
          }
        }
      });
    },
  },
};
</script>

<style scoped lang="scss">
.dialog {
  .header {
    padding: 20upx 20upx 0;
    display: flex;
    justify-content: flex-end;
  }
  .content {
    padding: 20upx 0;
    font-size: 24upx;
    .w50 {
      line-height: 90upx;
      height: 90upx;
      padding: 0 20upx;
      text-align: left;
      &:nth-child(2) {
        text-align: right;
      }
    }
  }
  .btns {
    padding: 30upx 60upx;
    .van-button {
      height: 72upx;
    }
    .van-button__content span {
      font-size: 30upx;
    }
  }
}
.wrap {
  padding: 106upx 0upx 50upx;
}
.clip {
  margin: 0 10upx 10upx 10upx;
  width: calc(100% - 20upx);
  border-radius: 6upx;
  display: flex;
  align-items: center;
  justify-content: space-between;
  text-align: center;
  background-color: #dff2f0;
  padding: 30upx 0upx;
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
      color: #404040;
      font-size: 24upx;
      padding-bottom: 30upx;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .txt {
      color: #404040;
      font-size: 38upx;
    }
  }
}
.row {
  position: relative;
  .top {
    position: sticky;
    top: 92upx;
    z-index: 1024;
  }
}
.list {
  width: 100%;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-between;
  line-height: 110upx;
  padding: 0 25upx;
  background-color: #41b7b8;
  &.white {
    background-color: #ffffff;
    .value {
      color: #333;
    }
  }
  .value {
    color: #404040;
    width: 20%;
    font-size: 20upx;
    text-align: center;
    &:nth-child(1) {
      text-align: left;
      width: 45%;
    }
    &:nth-child(3) {
      text-align: right;
      width: 35%;
    }
  }
}
.success {
  color: #5dff00 !important;
}
.loading {
  color: #ffcd04 !important;
}
</style>
