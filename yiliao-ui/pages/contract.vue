<template>
  <view class="page">
    <u-navbar
      placeholder
      title="投资合同"
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
      <view class="title">{{ detailsData.ourCompany }}投资合同书</view>
      <view class="number">合同编号：{{ detailsData.orderNo }}</view>
      <view class="name">
        甲方（投资方）：
        <label>{{ detailsData.realName }}</label>
      </view>
      <view class="name">
        乙方（管理方）：
        <label>{{ detailsData.ourCompany }}</label>
      </view>
      <view class="name">
        丙方（担保方）：
        <label>{{ detailsData.guaranteeCompany }}</label>
      </view>
      <view class="text"
        >甲乙丙双方经友好协商，本着平等自愿、诚实信用的原则，就甲方使用乙方提供的本网站所有服务的</view
      >
      <view class="name">有关事项达成如下协议：</view>
      <view class="name">一、理财投资明细</view>
      <table class="table-data">
        <tr>
          <td>产品名称</td>
          <td width="50%">{{ detailsData.projectName }}</td>
        </tr>
        <tr>
          <td>投资人姓名</td>
          <td>{{ detailsData.realName }}</td>
        </tr>
        <tr>
          <td>投资人身份证</td>
          <td width="50%">{{ detailsData.IdCardNo }}</td>
        </tr>
        <tr>
          <td>投入本金数额（下称“甲方投资本金”）</td>
          <td>{{ detailsData.amount }}元</td>
        </tr>
        <tr>
          <td>协议期（分钟）</td>
          <td width="50%">{{ detailsData.limitTime }}分钟</td>
        </tr>
        <tr>
          <td>预期收益率</td>
          <td>{{ detailsData.incomeRate }}%</td>
        </tr>
        <tr>
          <td>起息日</td>
          <td width="50%">{{ detailsData.orderTime }}</td>
        </tr>
        <tr>
          <td>到期日</td>
          <td>{{ detailsData.returnTime }}</td>
        </tr>
        <tr>
          <td>应收本息（元）</td>
          <td width="50%">
            {{ detailsData.amount + detailsData.forecastReturnAmount }}元
          </td>
        </tr>
        <tr>
          <td>还款方式</td>
          <td>每日反息，到期还本</td>
        </tr>
      </table>
      <view class="date">
        <view class="date-left">
          <view class="marginBottom30">甲方：{{ detailsData.realName }}</view>
          <view>{{ detailsData.orderTime }}</view>
        </view>
        <view class="date-right">
          <view class="marginBottom30">乙方：{{ detailsData.ourCompany }}</view>
          <view>{{ detailsData.orderTime }}</view>
          <canvas
            canvas-id="firstCanvas"
            id="firstCanvas"
            :style="{ width: canvasW + 'px', height: canvasH + 'px' }"
          ></canvas>
        </view>
      </view>
      <view class="about">
        <view>丙方：{{ detailsData.guaranteeCompany }}</view>

        <canvas
          canvas-id="secondCanvas"
          id="secondCanvas"
          :style="{ width: canvasW + 'px', height: canvasH + 'px' }"
        ></canvas>
      </view>
    </view>
  </view>
</template>

<script>
import { CanvasYinzhang } from "../plugins/util";
export default {
  data() {
    return {
      canvasW: 100,
      canvasH: 100,
      detailsData: {
        amount: "", //投资金额
        forecastReturnAmount: "", //预期收益
        orderTime: "", //投资时间
        returnTime: "", //到期时间
        forecastReturnTime: "", //到期时间，应收时间
        status: 0, //状态，0待收益 1已完成
        IdCardNo: "", //身份证
        ourCompany: "", //乙方名称
        projectName: "", //产品名称
        guaranteeCompany: "", //丙方名称
        limitTime: "", //协议分钟
        incomeRate: "", //预期收益
        orderNo: "", //合同编号
        abbreviation: "", //公司名称简称
      }, //注单详情数据
    };
  },
  onShow() {},
  onLoad(option) {
    this.getData(option.orderNo);
  },
  mounted() {},
  methods: {
    //画章
    getData(orderNo) {
      this.$api
        .order_detail({
          orderNo: orderNo,
        })
        .then((res) => {
          if (res.data.code == 0) {
            this.detailsData = res.data.data;
            this.$nextTick(() => {
              // 画印章
              CanvasYinzhang(
                "firstCanvas",
                this.detailsData.ourCompany,
                this.detailsData.abbreviation
              );
              CanvasYinzhang(
                "secondCanvas",
                this.detailsData.guaranteeCompany,
                ""
              );
            });
          }
        });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  .title {
    font-size: 30rpx;
    font-weight: 500;
    width: 100%;
    text-align: center;
    margin-top: 15rpx;
  }
  .number {
    margin-top: 15rpx;
    text-align: right;
    font-size: 28rpx;
  }
  .name {
    margin-left: 15rpx;
    font-weight: 600;
    font-size: 28rpx;
    line-height: 60rpx;
    label {
      font-weight: 400;
    }
  }
  .text {
    margin: 0 15rpx;
    font-size: 28rpx;
  }
  .table-data {
    width: 100%;
    border-collapse: collapse;
    font-size: 24rpx;
    text-align: center;
    border-top: 1px solid #bbb;
    border-left: 1px solid #bbb;
    font-weight: 600;
    tr {
      &:nth-child(odd) {
        background-color: #eee;
      }
    }
    td {
      height: 56rpx;
    }
    th,
    td {
      border-right: 1px solid #bbb;
      border-bottom: 1px solid #bbb;
    }
  }
  .date {
    display: flex;
    margin-top: 70rpx;
    font-size: 28rpx;
    align-items: center;
    .date-left {
      flex: 1;
    }
    .date-right {
      flex: 1;
      position: relative;
      #firstCanvas {
        position: absolute;
        top: -30rpx;
        right: 50rpx;
      }
    }
  }
  .about {
    position: relative;
    margin-top: 50rpx;
    #secondCanvas {
      position: absolute;
      top: -50rpx;
      left: 40rpx;
    }
  }
}
</style>
