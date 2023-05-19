import Vue from "vue";
import base from "./base.js";
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

//登录
export const user_login = (data) => {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/login",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
};