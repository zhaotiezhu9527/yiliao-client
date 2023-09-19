const TransformPages = require("uni-read-pages");
const { webpack } = new TransformPages();
var JavaScriptObfuscator = require('webpack-obfuscator');  
module.exports = {
  configureWebpack: {
    plugins: [
      new webpack.DefinePlugin({
        ROUTES: webpack.DefinePlugin.runtimeValue(() => {
          const tfPages = new TransformPages({
            includes: ["path", "name", "aliasPath"],
          });
          return JSON.stringify(tfPages.routes);
        }, true),
      }),
    ],
  },
  configureWebpack: process.env.NODE_ENV === 'production' ? {  
    plugins: [  
        new JavaScriptObfuscator({  
            rotateStringArray: true,  
        }, [])  
        ]  
    } : {} 
};
