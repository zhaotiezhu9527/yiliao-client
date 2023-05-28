<template>
  <view class="page">
    <u-navbar
      placeholder
      title="投资详情"
      :border="false"
      autoBack
      fixed
      safe-area-inset-top
      bgColor="#4b80af"
      leftIconColor="#fff"
      leftIconSize="32"
      height="52px"
      titleStyle="color:#fff;font-weight:500;font-size:32rpx;"
    >
    </u-navbar>
    <view class="wrap">
      <view class="details-text">
        <view> 投资金额：{{ detailsData.amount }}元 </view>
        <view> 预期收益：{{ detailsData.forecastReturnAmount }}元 </view>
        <view> 投资时间：{{ detailsData.orderTime }} </view>
        <view> 到期时间：{{ detailsData.forecastReturnTime }} </view>
        <view> 收益方式：每日返息，到期还本 </view>
      </view>
      <table class="table-data">
        <tr>
          <th width="15%">应收本金</th>
          <th width="15%">应收利息</th>
          <th>应收时间</th>
          <th>收益时间</th>
          <th width="15%">应收总额</th>
          <th width="13%">已支付</th>
          <th width="10%">状态</th>
        </tr>
        <tr>
          <td>{{ detailsData.amount }}</td>
          <td>{{ detailsData.forecastReturnAmount }}</td>
          <td class="text-left">{{ detailsData.forecastReturnTime }}</td>
          <td class="text-left">{{ detailsData.returnTime }}</td>
          <td>{{ detailsData.amount + detailsData.forecastReturnAmount }}</td>
          <td>
            {{
              detailsData.status === 1
                ? detailsData.amount + detailsData.forecastReturnAmount
                : 0
            }}
          </td>
          <td class="text-left">
            {{ detailsData.status === 1 ? "已完成" : "待收益" }}
          </td>
        </tr>
      </table>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      detailsData: {
        amount: "", //投资金额
        forecastReturnAmount: "", //预期收益
        orderTime: "", //投资时间
        returnTime: "", //到期时间
        forecastReturnTime: "", //到期时间，应收时间
        status: 0, //状态，0待收益 1已完成
      }, //注单详情数据
    };
  },
  onLoad(option) {
    this.getData(option.orderNo);
  },
  methods: {
    getData(orderNo) {
      this.$api
        .order_detail({
          orderNo: orderNo,
        })
        .then((res) => {
          if (res.data.code == 0) {
            this.detailsData = res.data.data;
          }
        });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  .details-text {
    margin-top: 30rpx;
    margin-left: 20rpx;
    line-height: 70rpx;
    font-size: 28rpx;
  }
  .text {
    margin-left: 20rpx;
  }
  .table-data {
    width: 100%;
    border-collapse: collapse;
    font-size: 24rpx;
    text-align: center;
    border-top: 1px solid #bbb;
    th {
      height: 50rpx;
      text-align: center;
      background-color: #f0f0f0;
    }
    th,
    td {
      border-right: 1px solid #bbb;
      border-bottom: 1px solid #bbb;
    }
    .text-left {
      text-align: left;
    }
  }
}
</style>
