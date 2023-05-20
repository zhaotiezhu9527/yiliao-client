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
        <tr v-for="(item,index) in list" :key="index">
          <td>{{ item.remark }}</td>
          <td class="table-money">
            <!-- <label class="green-text">+2</label> -->
            <label :class="item.amount < 0 ? 'red-text' : 'green-text'">{{ item.amount }}</label>
          </td>
          <td class="table-time">{{ item.optTime}}</td>
        </tr>
      </table>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      list:[],//列表数据
    };
  },
  onShow(){
    this.getData()
  },
  methods: {
    //获取数据
    getData() {
      this.$api.account_list().then((res) => {
        if (res.data.code == 0) {
          this.list = res.data.page.list
        } 
      });
    },
  },
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
