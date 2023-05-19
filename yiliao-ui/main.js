import Vue from "vue";
import App from "./App";

import UniRouteGuards from "uniapp-route-guards";
Vue.use(UniRouteGuards);
const guard = new UniRouteGuards();
// 自定义路由拦截白名单
const WHILE_LIST = [
  "/",
  "/pages/index",
  "/pages/investor",
  "/pages/about",
  "/pages/register",
  "/pages/login",
];

// 跳过路由白名单拦截
guard.beforeEach((to, from, next) => {
  let url = "";
  if (to.url) {
    url = to.url.split("?")[0];
  }
  let token = uni.getStorageSync("token");
  if (!WHILE_LIST.includes(url) && !token) {
    return next("/pages/login");
  }
  next();
});

guard.afterEach((to, from, next) => {});

import Vant from "vant";

Vue.use(Vant);

import * as base from "plugins/base.js";
import * as api from "plugins/api.js";
import * as store from "plugins/store.js";

Vue.prototype.$api = api;
Vue.prototype.$base = base;
Vue.prototype.$store = store;

Vue.config.productionTip = false;

App.mpType = "app";

const app = new Vue({
  ...App,
});
// #ifdef H5
app.$mount();
// #endif
