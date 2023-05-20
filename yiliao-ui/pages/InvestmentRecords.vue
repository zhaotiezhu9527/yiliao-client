<template>
  <view class="page">
    <van-nav-bar
      title="投资记录"
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
          <th>项目名称</th>
          <th class="table-money">投资金额</th>
          <th>状态</th>
          <th>详情</th>
          <th>合同</th>
        </tr>
        <tr v-for="(item,index) in list" :key="index">
          <td>{{item.projectName}}</td>
          <td class="table-money">
            <label class="green-text">{{ item.amount }}</label>
          </td>
          <td class="table-time">{{ item.status === 1 ? '已完成' : '未结算' }}</td>
          <td class="table-btn">
            <label class="blue-text" @click="goInvestmentDetails(item.orderNo)">查看</label>
          </td>
          <td class="table-btn">
            <label class="grey-text" @click="goContract(item.orderNo)">查看</label>
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
      list: [],//列表数据
    };
  },
  onShow(){
    this.getData()
  },
  methods: {
    goInvestmentDetails(num) {
      uni.navigateTo({
        url: "/pages/InvestmentDetails?orderNo=" + num,
      });
    },
    goContract(num) {
      uni.navigateTo({
        url: "/pages/contract?orderNo=" + num,
      });
    },
    //获取数据
    getData() {
      this.$api.invest_list().then((res) => {
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
      width: 18%;
      .green-text {
        color: green;
      }
      .red-text {
        color: red;
      }
    }
    .table-time {
      width: 13%;
      padding: 16upx 4upx;
    }
    .table-btn {
      label {
        font-size: 12px;
        padding: 5px 5px;
        color: #fff;
        border-radius: 5px;
      }
      .grey-text {
        background-color: #567da8;
      }
      .blue-text {
        background-color: #4994ec;
      }
    }
  }
}
</style>
