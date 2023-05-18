<template>
  <van-tabbar v-model="indexs" @change="change">
    <van-tabbar-item v-for="(item, index) in list" :key="index">
      <view class="name">{{ item.name }}</view>
      <template #icon="props">
        <image
          class="image"
          mode="heightFix"
          :src="
            props.active
              ? `../static/images/${item.active}_active.png`
              : `../static/images/${item.active}.png`
          "
        ></image>
      </template>
    </van-tabbar-item>
  </van-tabbar>
</template>
<script>
export default {
  props: ["active"],
  watch: {
    active: {
      handler(newName, oldName) {
        this.indexs = newName;
      },
      immediate: true,
    },
  },
  data() {
    return {
      list: [
        {
          name: "Início",
          active: "home",
          path: "/pages/index",
        },
        {
          name: "Ofícios",
          active: "qian",
          path: "/pages/trades",
        },
        {
          name: "Pessoal",
          active: "user",
          path: "/pages/personal",
        },
        {
          name: "Contacto",
          active: "kefu",
          path: "/pages/contact",
        },
      ],
      indexs: 0,
    };
  },
  methods: {
    change(e) {
      uni.redirectTo({
        url: this.list[e].path,
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.van-tabbar-item--active,
.van-tabbar {
  background: #efefef;
  z-index: 1024;
}
.van-tabbar-item {
  color: #cfcfcf;
}
.van-tabbar-item--active {
  color: #073441;
}
.van-tabbar-item__text .name {
  font-size: 28upx;
}
.image {
  height: 40upx;
}
</style>
