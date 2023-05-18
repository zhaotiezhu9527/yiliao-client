<template>
  <view class="page">
    <van-nav-bar
      title="login"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/index')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="from">
        <view class="input">
          <van-field v-model="userPhone" type="tel" placeholder="Phone">
            <template slot="label">
              <image :src="icon" mode="widthFix" class="image" />
              <view class="lable" @click="show = true"> + {{ areaCode }} </view>
              <van-icon
                @click="show = true"
                style="padding-left: 10upx"
                name="arrow-down"
              />
            </template>
          </van-field>
        </view>
        <view class="input">
          <van-field
            v-model="password"
            type="password"
            label="Password"
            placeholder="Password"
          />
        </view>
        <view class="btns">
          <van-button color="#4b80af" block @click="login" :loading="loading">
            Login
          </van-button>
          <van-button color="#e6a23c" block @click="register">
            Register
          </van-button>
          <!-- <van-button color="#073441" block @click="pawClcik">
            Reset login password
          </van-button> -->
        </view>
      </view>
    </view>
    <van-popup v-model="show" position="bottom">
      <van-picker
        title="Nation"
        show-toolbar
        :columns="columns"
        value-key="name"
        @cancel="show = false"
        @confirm="onConfirm"
        confirm-button-text="Confirm"
        cancel-button-text="Cancel"
      >
        <template #option="item">
          <view class="item">
            <view class="content">
              <image mode="widthFix" class="icon" :src="item.img" />
              <view class="code">{{ item.name }}</view>
            </view>
            <view class="code">+{{ item.code }}</view>
          </view>
        </template>
      </van-picker>
    </van-popup>
  </view>
</template>

<script>
export default {
  data() {
    return {
      password: "",
      userPhone: "",
      areaCode: "",
      icon: "",
      show: false,
      columns: [],
      loading: false,
    };
  },
  onLoad() {
    let date = null;
    date = setInterval(() => {
      uni.getStorage({
        key: "config",
        success: (val) => {
          if (val.data.contrys) {
            clearInterval(date);
            this.columns = val.data.contrys;
            if (this.columns.length) {
              this.areaCode = this.columns[0].code;
              this.icon = this.columns[0].img;
            }
          }
        },
      });
    }, 500);
  },
  methods: {
    onConfirm(value, index) {
      this.areaCode = value.code;
      this.$store.code = value.code;
      this.icon = value.img;
      this.show = false;
    },
    register() {
      uni.navigateTo({
        url: "/pages/register",
      });
    },
    pawClcik() {
      uni.navigateTo({
        url: "/pages/setloginpwd",
      });
    },
    login() {
      this.$store.code = this.areaCode;
      if (this.areaCode === "") {
        return this.$base.show("Please select a country");
      } else if (!this.$base.phone(this.userPhone)) {
        return this.$base.show("Please enter correctly Phone");
      } else if (this.password === "") {
        return this.$base.show("Please enter Password");
      }
      const DATA_OBJ = {
        password: this.password,
        userPhone: this.userPhone,
        areaCode: this.areaCode,
      };
      this.loading = true;
      this.$api.user_login(DATA_OBJ).then((res) => {
        this.loading = false;
        if (res.data.code == 0) {
          this.$base.storage("token", res.data.token);
          this.$base.storage("areaCode", this.areaCode);
          uni.switchTab({ url: "/pages/personal" });
        }
      });
    },
  },
};
</script>

<style scoped lang="scss">
/deep/.van-field__label {
  display: flex;
  align-items: center;
  color: #fff;
  .image {
    width: 50upx;
    padding-right: 10upx;
  }
}
/deep/.van-field__right-icon {
  color: #fff;
  font-size: 32upx;
}
.item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 40upx;
  width: 100%;
  box-sizing: border-box;
  .content {
    display: flex;
    align-items: center;
    justify-content: center;
    .code {
      padding-left: 20upx;
    }
  }
  .icon {
    width: 60upx;
  }
  .code {
    font-size: 28upx;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}
.btns {
  padding: 40upx 40upx 0;
  .van-button {
    border-radius: 10upx;
    margin-bottom: 20upx;
  }
  .van-button__content span {
    font-size: 30upx;
  }
}
.from {
  padding: 40upx 20upx;
  width: 100%;
  box-sizing: border-box;
  .input {
    padding-top: 20upx;
  }
  .van-cell {
    background: #4b80af;
  }
  /deep/.van-field__label {
    span {
      color: #fff;
    }
  }
  /deep/.van-field__control {
    color: #fff;
  }
  .image {
    height: 60upx;
  }
}
</style>
