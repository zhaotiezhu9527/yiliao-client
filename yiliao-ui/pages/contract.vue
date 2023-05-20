<template>
  <view class="page">
    <van-nav-bar
      title="投资合同"
      left-arrow
      placeholder
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/InvestmentRecords')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="title">{{detailsData.ourCompany}}投资合同书</view>
      <view class="number">合同编号：{{detailsData.orderNo}}</view>
      <view class="name">
        甲方（投资方）：
        <label>{{detailsData.realName}}</label>
      </view>
      <view class="name">
        乙方（管理方）：
        <label>{{detailsData.realName}}</label>
      </view>
      <view class="name">
        丙方（担保方）：
        <label>{{detailsData.realName}}</label>
      </view>
      <view class="text"
        >甲乙丙双方经友好协商，本着平等自愿、诚实信用的原则，就甲方使用乙方提供的本网站所有服务的</view
      >
      <view class="name">有关事项达成如下协议：</view>
      <view class="name">一、理财投资明细</view>
      <table class="table-data">
        <tr>
          <td>产品名称</td>
          <td width="50%">{{detailsData.realName}}</td>
        </tr>
        <tr>
          <td>投资人姓名</td>
          <td>{{detailsData.realName}}</td>
        </tr>
        <tr>
          <td>投资人身份证</td>
          <td width="50%">{{detailsData.realName}}</td>
        </tr>
        <tr>
          <td>投入本金数额（下称“甲方投资本金”）</td>
          <td>{{detailsData.realName}}元</td>
        </tr>
        <tr>
          <td>协议期（分钟）</td>
          <td width="50%">{{detailsData.realName}}分钟</td>
        </tr>
        <tr>
          <td>预期收益率</td>
          <td>{{detailsData.realName}}%</td>
        </tr>
        <tr>
          <td>起息日</td>
          <td width="50%">{{detailsData.realName}}</td>
        </tr>
        <tr>
          <td>到期日</td>
          <td>{{detailsData.realName}}</td>
        </tr>
        <tr>
          <td>应收本息（元）</td>
          <td width="50%">{{detailsData.realName}}元</td>
        </tr>
        <tr>
          <td>还款方式</td>
          <td>每日反息，到期还本</td>
        </tr>
      </table>
      <view class="date">
        <view class="date-left">
          <view class="marginBottom30">甲方：{{detailsData.realName}}</view>
          <view>{{detailsData.realName}}</view>
        </view>
        <view class="date-right">
          <view class="marginBottom30"
            >乙方：{{detailsData.realName}}</view
          >
          <view>{{detailsData.realName}}</view>
          <canvas
            cancas-id="firstCanvas"
            id="firstCanvas"
            :style="{ width: canvasW + 'px', height: canvasH + 'px' }"
          ></canvas>
        </view>
      </view>
      <view class="about">
        <view>丙方：{{detailsData.realName}}</view>
        <canvas
          cancas-id="secondCanvas"
          id="secondCanvas"
          :style="{ width: canvasW + 'px', height: canvasH + 'px' }"
        ></canvas>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      canvasW: 100,
      canvasH: 100,
      detailsData: {
        amount:'',//投资金额
        forecastReturnAmount:'',//预期收益
        orderTime:'',//投资时间
        returnTime:'',//到期时间
        forecastReturnTime:'',//到期时间，应收时间
        status: 0,//状态，0未结算 1已完成
        IdCardNo: '',//身份证
        ourCompany: '',//乙方名称
        projectName: '',//产品名称
        guaranteeCompany: '',//丙方名称
        limitTime: '',//协议分钟
        incomeRate: '',//预期收益
        orderNo: '',//合同编号
      },//注单详情数据
    };
  },
  onShow() {},
  onLoad(option){
    this.getData(option.orderNo)
  },
  mounted() {
    this.$nextTick(() => {
      this.createSeal("firstCanvas", "安徽安科生物股份有限公司", "安科生物");
      this.createSeal("secondCanvas", "中国人民财产保险股份有限公司", "");
    });
  },
  methods: {
    //画章
    createSeal(id, company, name) {
      const canvas = document.getElementById(id).childNodes[0];
      var context = canvas.getContext("2d");

      // 绘制印章边框
      var width = 50;
      var height = 50;
      context.lineWidth = 1;
      context.strokeStyle = "#f00";
      context.beginPath();
      context.arc(width, height, 45, 0, Math.PI * 2);
      context.stroke();

      //画五角星
      this.create5star(context, width, height, 10, "#f00", 0);

      // 绘制印章名称
      context.font = "10px Helvetica";
      context.textBaseline = "middle"; //设置文本的垂直对齐方式
      context.textAlign = "center"; //设置文本的水平对对齐方式
      context.lineWidth = 1;
      context.fillStyle = "#f00";
      context.fillText(name, width, height + 25);

      // 绘制印章单位
      context.translate(50, 50); // 平移到此位置,
      context.font = "10px Helvetica";
      var count = company.length; // 字数
      var angle = (4 * Math.PI) / (3 * (count - 1)); // 字间角度
      var chars = company.split("");
      var c;
      for (var i = 0; i < count; i++) {
        c = chars[i]; // 需要绘制的字符
        if (i == 0) context.rotate((5 * Math.PI) / 6);
        else context.rotate(angle);
        context.save();
        context.translate(50, 0); // 平移到此位置,此时字和x轴垂直
        context.rotate(Math.PI / 2); // 旋转90度,让字平行于x轴
        context.fillText(c, 0, 15); // 此点为字的中心点
        context.restore();
      }
    },
    create5star(context, sx, sy, radius, color, rotato) {
      context.save();
      context.fillStyle = color;
      context.translate(sx, sy); //移动坐标原点
      context.rotate(Math.PI + rotato); //旋转
      context.beginPath(); //创建路径
      var x = Math.sin(0);
      var y = Math.cos(0);
      var dig = (Math.PI / 5) * 4;
      for (var i = 0; i < 5; i++) {
        //画五角星的五条边
        var x = Math.sin(i * dig);
        var y = Math.cos(i * dig);
        context.lineTo(x * radius, y * radius);
      }
      context.closePath();
      context.stroke();
      context.fill();
      context.restore();
    },
    getData(orderNo){
      this.$api.order_detail({
        orderNo: orderNo
      }).then((res) => {
        if (res.data.code == 0) {
          this.detailsData = res.data.data
        } 
      });
    }
  },
};
</script>

<style scoped lang="scss">
.wrap {
  .title {
    font-size: 30upx;
    font-weight: 500;
    width: 100%;
    text-align: center;
    margin-top: 15upx;
  }
  .number {
    margin-top: 15upx;
    text-align: right;
    font-size: 28upx;
  }
  .name {
    margin-left: 15upx;
    font-weight: 600;
    font-size: 28upx;
    line-height: 60upx;
    label {
      font-weight: 400;
    }
  }
  .text {
    margin: 0 15upx;
    font-size: 28upx;
  }
  .table-data {
    width: 100%;
    border-collapse: collapse;
    font-size: 24upx;
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
      height: 56upx;
    }
    th,
    td {
      border-right: 1px solid #bbb;
      border-bottom: 1px solid #bbb;
    }
  }
  .date {
    display: flex;
    margin-top: 70upx;
    font-size: 28upx;
    align-items: center;
    .date-left {
      flex: 1;
    }
    .date-right {
      flex: 1;
      position: relative;
      #firstCanvas {
        position: absolute;
        top: -30upx;
        right: 50upx;
      }
    }
  }
  .about {
    position: relative;
    margin-top: 50upx;
    #secondCanvas {
      position: absolute;
      top: -50upx;
      left: 40upx;
    }
  }
}
</style>
