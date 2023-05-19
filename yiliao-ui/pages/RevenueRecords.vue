<template>
  <view class="page">
    <van-nav-bar
      title="收益记录"
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
          <th>项目名称</th>
          <th width="20%">收益时间</th>
          <th width="18%">收益金额</th>
          <th width="10%">状态</th>
        </tr>
        <tr>
          <td>每日签到，获得奖励2元</td>
          <td class="text-left">2023-05-18 12:28:10</td>
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
      border-right: 1px solid #bbb;
      border-bottom: 1px solid #bbb;
    }
  }
}
</style>
