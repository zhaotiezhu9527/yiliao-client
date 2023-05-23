<template>
  <view class="page">
    <van-nav-bar
      :border="false"
      placeholder
      fixed
      title="实名认证"
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/personal')"
    >
      <template #left>
        <van-icon name="arrow-left" size="18" />
      </template>
    </van-nav-bar>
    <view class="wrap">
      <view class="from-input">
        <label>姓名</label>
        <input
          type="text"
          :disabled="!bindStatus"
          v-model="realName"
          class="input-text"
          placeholder="请输入真实姓名"
        />
      </view>
      <view class="from-input">
        <label>身份证号码</label>
        <input
          type="text"
          :disabled="!bindStatus"
          v-model="idCardNo"
          class="input-text"
          placeholder="请输入身份证号码"
        />
      </view>
      <van-button
        v-if="bindStatus"
        class="btn-class"
        block
        @click="changeBind"
        :loading="loading"
      >
        实名认证
      </van-button>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      idCardNo: "", //身份证号
      realName: "", //真实姓名
      bindStatus: false, //是否实名
    };
  },
  onShow() {
    this.getInfo();
  },
  methods: {
    //用户列表数据
    getInfo() {
      this.$api.user_info().then((res) => {
        if (res.data.code == 0) {
          if (res.data.data.idCard === null) {
            this.bindStatus = true;
          } else {
            this.bindStatus = false;
          }
          this.idCardNo = res.data.data.idCard;
          this.realName = res.data.data.realName;
        }
      });
    },
    // 实名认证
    changeBind() {
      if (!this.realName) {
        return this.$base.show("请输入真实姓名~");
      } else if (!this.idCardNo) {
        return this.$base.show("请输入身份证号码~");
      }
      this.loading = true;
      this.$api
        .user_realName({
          realName: this.realName,
          idCardNo: this.idCardNo,
        })
        .then((res) => {
          this.loading = false;
          if (res.data.code == 0) {
            this.getInfo();
          }
        });
    },
  },
};
</script>

<style scoped lang="scss">
.wrap {
  .from-input {
    margin: 40upx;
    display: flex;
    height: 90upx;
    label {
      width: 200upx;
      margin-right: 20upx;
    }
    .input-text {
      flex: 1;
      text-align: right;
    }
  }
}
</style>
