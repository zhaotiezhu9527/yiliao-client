<template>
  <view class="page">
    <van-nav-bar
      title="提现记录"
      placeholder
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
      <table class="table-data">
        <tr>
          <th>提现金额</th>
          <th width="40%">充值时间</th>
          <th>状态</th>
        </tr>
        <tr>
          <td>30000</td>
          <td>2023-05-18 12:28:10</td>
          <td>提现成功</td>
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
  .table-data {
    width: 100%;
    border-collapse: collapse;
    font-size: 24upx;
    text-align: center;
    th {
      height: 50upx;
      text-align: center;
    }
    td {
      height: 50upx;
    }
    th,
    td {
      border-right: 1px solid #bbb;
      border-bottom: 1px solid #bbb;
    }
  }
}
</style>
