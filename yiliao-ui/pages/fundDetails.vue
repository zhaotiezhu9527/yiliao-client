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
      <!-- <table class="table-data">
        <tr>
          <th>摘要</th>
          <th class="table-money">金额</th>
          <th>时间</th>
        </tr>
        <tr v-for="(item,index) in list" :key="index">
          <td>{{ item.remark }}</td>
          <td class="table-money">
            <label :class="item.amount < 0 ? 'red-text' : 'green-text'">{{ item.amount }}</label>
          </td>
          <td class="table-time">{{ item.optTime}}</td>
        </tr>
      </table> -->
      <van-list
        :immediate-check="false"
        v-model="loading"
        :finished="finished"
        loading-text="loading..."
        finished-text="No more"
        @load="load"
        v-if="isArray"
      >
      <!-- @click="change(item)" -->
        <view
          class="list van-hairline--bottom"
          v-for="(item, index) in list"
          :key="index"
        >
          <view class="title van-ellipsis">
            {{ item.title }}
          </view>
          <view class="value">{{ item.createTime }}</view>
        </view>
      </van-list>
      <van-empty description="No more" v-else />
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      list:[],//列表数据
      loading: false,
      finished: false,
      isArray: true,
      page: 0,
    };
  },
  onShow(){
    // this.getData()
  },
  onLoad() {
    uni.showLoading();
    this.page = 1;
    this.dataFn();
  },
  methods: {
    //获取数据
    // getData() {
    //   this.$api.account_list().then((res) => {
    //     if (res.data.code == 0) {
    //       this.list = res.data.page.list
    //     } 
    //   });
    // },
    load() {
      this.page++;
      this.dataFn(this.page);
    },
    dataFn(page = 1, limit = 30) {
      this.$api.account_list({ page, limit }).then((res) => {
        if (res.data.code == 0) {
          const vim = res.data.page;
          this.list = this.list.concat(vim.list);
          console.log(this.list)
          this.isArray = vim.totalCount ? true : false;
          if (this.page >= vim.totalPage) {
            this.finished = true;
          }
        }
      });
    },
    // change(item) {
    //   uni.navigateTo({
    //     url: "/pages/detail?id=" + item.id,
    //   });
    // },
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
.list {
  display: flex;
  align-items: center;
  justify-content: space-between;
  line-height: 110upx;
  .title {
    font-size: 30upx;
    width: 55%;
  }
  .value {
    width: 45%;
    font-size: 30upx;
    color: #969799;
    text-align: right;
  }
}
</style>
