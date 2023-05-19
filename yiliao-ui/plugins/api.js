import Vue from "vue";
import * as base from "plugins/base.js";
Vue.prototype.$base = base;
//统一API资源管理

//产品列表
export const product_list = (data) => {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "product/list",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
};

//购买产品
export const product_buy = (data) => {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "product/buy",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
};
