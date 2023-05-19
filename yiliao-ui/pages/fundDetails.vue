<template>
  <view class="page">
    <van-nav-bar
      title="资金明细"
      left-arrow
      placeholder
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
    </van-nav-bar>
    <view class="wrap">
      <table class="table-data">
        <tr>
          <th>摘要</th>
          <th class="table-money">金额</th>
          <th>时间</th>
        </tr>
        <tr>
          <td>每日签到，获得奖励2元</td>
          <td class="table-money">
            <!-- <label class="green-text">+2</label> -->
            <label class="red-text">-2</label>
          </td>
          <td class="table-time">2023-05-18 12:28:10</td>
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
    th,
    td {
      border-right: 1px solid #eee;
      border-bottom: 1px solid #eee;
    }
    .table-money {
      width: 20%;
      .green-text {
        color: green;
      }
      .red-text {
        color: red;
      }
    }
    .table-time {
      width: 20%;
      text-align: left;
      padding: 16upx 4upx;
    }
  }
}
</style>
