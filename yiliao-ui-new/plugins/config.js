export const config = {
  requestRemoteIp: () => {
    if (config.RemoteIpInited) return Promise.resolve();

    return new Promise((resolve, reject) => {
      if (process.env.NODE_ENV !== "development") {
        resolve({ url: "https://www.anke8811.com/lCY45gucOU2CMttF/" });
      } else {
        let all = ["https://www.anke8811.com/lCY45gucOU2CMttF/system/config"];
        all.forEach((e) => {
          uni.$u.http
            .get(e)
            .then(({ data }) => {
              resolve({
                url: "https://www.anke8811.com/lCY45gucOU2CMttF/",
                data,
              });
            })
            .catch((error) => {
              reject(error);
            });
        });
      }
    });
  },
};
