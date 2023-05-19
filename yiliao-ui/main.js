import Vue from "vue";
import App from "./App";
import Vant from "vant";
import { router, RouterMount } from "./plugins/router";
Vue.use(router);

Vue.use(Vant);

import * as base from "plugins/base.js";
import * as api from "plugins/api.js";
import * as store from "plugins/store.js";

Vue.prototype.$onLaunched = new Promise((resolve) => {
  Vue.prototype.$isResolve = resolve;
});

Vue.prototype.$api = api;
Vue.prototype.$base = base;
Vue.prototype.$store = store;

Vue.config.productionTip = false;

App.mpType = "app";

const app = new Vue({
  ...App,
});
// #ifdef H5
RouterMount(app, router, "#app");
// #endif

// #ifdef H5
app.$mount();
// #endif
