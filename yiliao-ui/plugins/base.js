import { metadata, PhoneNumberUtil } from "google-libphonenumber";
import Vue from "vue";
import store from "plugins/store.js";
Vue.prototype.$store = store;
const phone = (value) => {
  try {
    const phoneUtil = PhoneNumberUtil.getInstance();
    let isValite = false;
    let number;
    let keys = [];
    const CODEMAP = metadata.countryCodeToRegionCodeMap;
    Object.keys(CODEMAP).forEach((item) => {
      if (item == store.code) {
        keys = CODEMAP[item];
      }
    });
    isValite = keys.some((e) => {
      number = phoneUtil.parseAndKeepRawInput(value, e);
      return phoneUtil.isValidNumber(number);
    });
    return isValite;
  } catch (error) {
    return false;
  }
};

//设置缓存内容
function storage(name, value) {
  uni.setStorage({
    key: name,
    data: value,
  });
}
//通用提示
function show(title, icon = "none") {
  uni.showToast({
    title: title,
    duration: 2000,
    icon: icon,
  });
}
//loading
function loading(title) {
  uni.showLoading({
    title: title,
  });
}
// 西班牙
const PATH_URL =
  process.env.NODE_ENV === "development" ? "http://47.243.237.139:8522/" : "https://api.bambu.cc/";

function upload(params) {
  let header = {
    token: uni.getStorageSync("token"),
  };
  return new Promise((resolve, reject) => {
    uni.uploadFile({
      url: PATH_URL + "api_common/upload/uploading",
      filePath: params.filePath,
      name: params.name,
      header: header,
      formData: params.formData,
      success: (uploadFileRes) => {
        resolve(uploadFileRes);
      },
    });
  });
}
//请求
function request(params) {
  let str = params.method.toUpperCase();
  if (str == "POST") {
    params.header = {
      lang: "en_US",
      "Content-Type":
        params["Content-Type"] === undefined
          ? "application/x-www-form-urlencoded;charset=UTF-8"
          : params["Content-Type"],
      token: uni.getStorageSync("token"),
    };
  } else {
    params.header = {
      lang: "en_US",
      token: uni.getStorageSync("token"),
    };
  }
  uni.showLoading({
  	title: "loading",
  	mask: true
  })
  return new Promise((resolve, reject) => {
    uni.request({
      url: PATH_URL + params.url,
      data: params.data,
      method: str,
      header: params.header,
      success: (res) => {
        uni.hideLoading();
        if (res.data.code != 0) {
          uni.showToast({
            title: res.data.msg,
            duration: 2000,
            icon: "none",
          });
        }
        if (res.data.code == -2) {
          uni.redirectTo({
            url: "/pages/login",
          });
          uni.removeStorageSync("token");
          uni.removeStorageSync("phone");
          uni.removeStorageSync("info");
          uni.removeStorageSync("areaCode");
        }
        resolve(res);
      },
      fail: () => {
        show("Network exceptions Exist.");
      },
    });
  });
}
//返回上一页
const BackPage = (url) => {
  if (
    url === "/pages/index" ||
    url === "/pages/trades" ||
    url === "/pages/personal" ||
    url === "/pages/contact" ||
    url === "/"
  ) {
    uni.switchTab({
      url,
    });
    return false;
  }
  if (url && url !== "/pages/index") {
    uni.redirectTo({ url });
    return false;
  }
  if (getCurrentPages().length < 2 && "undefined" !== typeof __wxConfig) {
    let path = "/" + __wxConfig.pages[0];
    return uni.redirectTo({ url: path });
  }
  uni.switchTab({
    url: "/pages/index",
  });
};

const authorityPage = (that) => {
  return new Promise((resolve, reject) => {
    const WHILE_LIST = [
      "/",
      "/pages/index",
      "/pages/trades",
      "/pages/about",
      "/pages/contact",
      "/pages/register",
      "/pages/setloginpwd",
      "/pages/info",
    ];
    let path = that.$route.path;
    let token = uni.getStorageSync("token");
    if (!WHILE_LIST.includes(path) && !token) {
      uni.redirectTo({
        url: "/pages/login",
      });
    } else {
      resolve();
    }
  });
};

const gettimeFn = (value) => {
  return value < 10 ? "0" + value : value;
};

const getdateTimeFn = (time) => {
  let data = {};
  let defaultTimeStamp = time;
  let dayA = defaultTimeStamp % (24 * 3600);
  let hourA = dayA % 3600;
  let minuteA = hourA % 60;
  let day = Math.floor(defaultTimeStamp / (24 * 3600));
  let hour = Math.floor(dayA / 3600);
  let minute = Math.floor(hourA / 60);
  let second = Math.floor(minuteA);
  data = {
    day: gettimeFn(day),
    hour: gettimeFn(hour),
    minute: gettimeFn(minute),
    second: gettimeFn(second),
  };
  return data;
};

module.exports.gettimeFn = gettimeFn;
module.exports.getdateTimeFn = getdateTimeFn;
module.exports.phone = phone;
module.exports.authorityPage = authorityPage;
module.exports.BackPage = BackPage;
module.exports.upload = upload;
module.exports.loading = loading;
module.exports.show = show;
module.exports.storage = storage;
module.exports.request = request;
