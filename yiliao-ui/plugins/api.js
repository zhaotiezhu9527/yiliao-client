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

//注册
export const user_register = (data) => {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/register",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
};

//退出登陆
export const user_logout = (data) => {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/logout",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
};

//用户数据详情
export const user_info = (data) => {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/info",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
};

//用户签到
export const user_sign = (data) => {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/sign",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
};

//资金明细
export const account_list = (data) => {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/account/list",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
};

//投资记录
export const invest_list = (data) => {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/invest/list",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
};

//注单详情
export const order_detail = (data) => {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "order/detail/" + data.orderNo,
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
};

//注单详情
export const user_bindBank = (data) => {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/bindBank",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
};