import Vue from "vue";
import base from "./base.js";
Vue.prototype.$base = base;
//统一API资源管理

//产品列表
function product_list(data) {
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
}

//产品详情
function product_info(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "product/info/" + data,
        method: "get",
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//购买产品
function product_buy(data) {
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
}

//通知列表
function user_notice(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/notice",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//通知详情
function user_notice_info(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/notice/" + data,
        method: "get",
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//登录(密码)
function user_login(data) {
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
}

//注册
function user_register(data) {
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
}

//短信发送接口
function sms_send(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "sms/send",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//退出
function user_logout(data) {
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
}

//签到
function user_sign(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/sign",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//用户信息
function user_info(data) {
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
}

//改提现密码(短信)
function user_update_withdraw_pwd(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/update/withdraw/password/sms",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//重置密码
function user_reset_password(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/reset/password",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//修改密码
function user_update_password(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/update/password",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//绑定提币地址
function user_bindAddr(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/bindAddr",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//资金流动列表
function user_fundRecord(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/fundRecord",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//获取绑定的提币地址
function user_getBindAddr(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/getBindAddr",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//获取用户充币地址
function user_getTopupAddr(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/getTopupAddr",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//收益列表
function user_interest(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/interest",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//我的投资列表
function user_myInvestment(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/myInvestment",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//团队详情
function user_team_detail(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/team/detail",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//充币列表
function user_topUpRecord(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/topUpRecord",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//提币
function user_withdraw(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/withdraw",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//提币列表
function user_withdrawRecord(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/withdrawRecord",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//获取用户帐号详情
function user_accountDetails(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/accountDetails",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//公告
function sys_config(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "sys/config",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//用户余额
function user_balance(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/balance",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//充币
function user_topup(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "user/topup",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//投资滚动列表
function sys_bonus_list(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "sys/bonus/list",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//首页弹窗公告
function sys_homeNotice(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "sys/homeNotice",
        method: "get",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

//转盘执行
function turntable_execute(data) {
  return new Promise((resolve, reject) => {
    base
      .request({
        url: "turntable/execute",
        method: "post",
        data: data,
      })
      .then((res) => {
        resolve(res);
      });
  });
}

module.exports.turntable_execute = turntable_execute;
module.exports.sys_homeNotice = sys_homeNotice;
module.exports.sys_bonus_list = sys_bonus_list;
module.exports.user_topup = user_topup;
module.exports.user_balance = user_balance;
module.exports.sys_config = sys_config;
module.exports.user_accountDetails = user_accountDetails;
module.exports.user_withdrawRecord = user_withdrawRecord;
module.exports.user_withdraw = user_withdraw;
module.exports.user_topUpRecord = user_topUpRecord;
module.exports.user_team_detail = user_team_detail;
module.exports.user_fundRecord = user_fundRecord;
module.exports.user_myInvestment = user_myInvestment;
module.exports.user_interest = user_interest;
module.exports.user_getTopupAddr = user_getTopupAddr;
module.exports.user_getBindAddr = user_getBindAddr;
module.exports.user_bindAddr = user_bindAddr;
module.exports.product_buy = product_buy;
module.exports.user_update_password = user_update_password;
module.exports.user_reset_password = user_reset_password;
module.exports.user_update_withdraw_pwd = user_update_withdraw_pwd;
module.exports.user_info = user_info;
module.exports.user_sign = user_sign;
module.exports.user_logout = user_logout;
module.exports.user_register = user_register;
module.exports.user_register = user_register;
module.exports.sms_send = sms_send;
module.exports.user_login = user_login;
module.exports.product_list = product_list;
module.exports.product_info = product_info;
module.exports.user_notice = user_notice;
module.exports.user_notice_info = user_notice_info;
