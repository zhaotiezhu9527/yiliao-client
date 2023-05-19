import Vue from "vue";
import App from "./App";
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
