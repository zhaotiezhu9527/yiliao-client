<template>
  <view class="page">
    <van-nav-bar
      title="Descarregar"
      left-arrow
      :border="false"
      fixed
      safe-area-inset-top
      @click-left="$base.BackPage('/pages/index')"
    >
    </van-nav-bar>
    <view class="wrap">
      <view class="logo">
        <!-- <image
          class="image"
          mode="widthFix"
          src="../static/images/bbzhomes111.png"
        /> -->
      </view>
      <view class="header">BambuCrypto</view>
      <view class="ul">
        <view class="li" @click="active = 1" :class="{ active: active === 1 }">
          Android
        </view>
        <view class="li" @click="active = 2" :class="{ active: active === 2 }">
          IOS
        </view>
      </view>
      <view class="tips">
        <view class="item">
          <view class="span"> Version：1.0.2</view>
          <view class="span">Size：4.10MB</view>
        </view>
        <view class="item">Tempo de actualização ：2022-01-19</view>
        <image class="image" mode="widthFix" src="../static/images/test.png" />
      </view>
      <view class="btns van-hairline--bottom">
        <van-button color="#5ec0b3" round block> Instalar </van-button>
      </view>
      <view class="msg border van-hairline--bottom">
        <view class="title">Versão histórica</view>
        <view class="row">
          <view class="item">
            <view class="span">1.0.0 (build 2)</view>
          </view>
          <view class="item">
            <view class="span">2022-03-06</view>
          </view>
        </view>
      </view>
      <view class="msg">
        <view class="title">Declaração de exoneração de responsabilidade</view>
        <view class="content">
          Este site é apenas para os programadores descarregarem e instalarem o
          APP, o alojamento, o conteúdo do APP e os assuntos relacionados com o
          seu relacionado com o seu funcionamento pelos criadores do APP, não
          tem nada a ver com este site. o APP, não tem nada a ver com este site.
        </view>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      active: 1,
    };
  },
  onLoad() {},
};
</script>

<style scoped lang="scss">
.wrap {
  padding: 122upx 40upx 100upx;
  .tips {
    text-align: center;
    padding-top: 40upx;
    .item {
      display: flex;
      align-items: center;
      justify-content: center;
      padding-bottom: 20upx;
      color: #788090;
      .span {
        border-right: 1upx solid #dee2ec;
        &:nth-child(2) {
          border: 0;
        }
        padding: 0 15upx;
        color: #788090;
      }
    }
    .image {
      width: 240upx;
      height: 240upx;
    }
  }
  .logo {
    padding: 20upx 0 60upx 0;
    text-align: center;
    .image {
      width: 200upx;
      height: 200upx;
    }
  }
  .header {
    color: #333;
    font-size: 48upx;
    margin-bottom: 20upx;
    text-align: center;
  }
  .ul {
    display: flex;
    align-items: center;
    justify-content: center;
    .li {
      width: 336upx;
      border-radius: 10upx;
      color: #ffffff;
      background: lightgray;
      padding: 20upx 0;
      font-size: 34upx;
      text-align: center;
      margin: 0 10upx;
      &.active {
        background: #5ec0b3;
      }
    }
  }
  .btns {
    padding: 60upx 0 140upx 0;
    .van-button {
      margin: 0 auto;
      width: 70%;
      height: 80upx;
      font-size: 36upx;
    }
  }
  .msg {
    padding: 60upx 20upx 20upx;
    &.border {
      padding-bottom: 100upx;
    }
    .title {
      padding: 40upx 0;
      color: #000;
      font-size: 40upx;
      font-weight: 700;
    }
    .row {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 10upx 20upx;
      &:nth-child(even) {
        background: #f2f6f8;
      }
      .item {
        height: 60upx;
        line-height: 60upx;
        font-size: 32upx;
      }
    }
    .content {
      color: #969b9d;
      font-size: 30upx;
      line-height: 1.5;
    }
  }
}
</style>
