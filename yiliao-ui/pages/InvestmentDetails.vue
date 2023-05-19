<template>
  <view class="page">
    <van-nav-bar
      title="投资详情"
      left-arrow
      placeholder
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/InvestmentRecords')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="details-text">
        <view> 投资金额：300000元 </view>
        <view> 预期收益：19020元 </view>
        <view> 投资时间：2023-05-17 22:31:40 </view>
        <view> 到期时间：2023-05-17 </view>
        <view> 收益方式：每日返息，到期还本 </view>
      </view>
      <table class="table-data">
        <tr>
          <th width="15%">应收本金</th>
          <th width="15%">应收利息</th>
          <th>应收时间</th>
          <th>收益时间</th>
          <th width="15%">应收总额</th>
          <th>已支付</th>
          <th width="10%">状态</th>
        </tr>
        <tr>
          <td>300000</td>
          <td>19020</td>
          <td class="text-left">2023-05-18 12:28:10</td>
          <td class="text-left">2023-05-18 12:28:10</td>
          <td>319020</td>
          <td>319020</td>
          <td class="text-left">已完成</td>
        </tr>
      </table>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {},
};
</script>

<style scoped lang="scss">
.wrap {
  .details-text {
    margin-top: 30upx;
    margin-left: 20upx;
    line-height: 70upx;
    font-size: 28upx;
  }
  .text {
    margin-left: 20upx;
  }
  .table-data {
    width: 100%;
    border-collapse: collapse;
    font-size: 24upx;
    text-align: center;
    border-top: 1px solid #bbb;
    th {
      height: 50upx;
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
