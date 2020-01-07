// vue.config.js
module.exports = {
    devServer: {
        proxy: {
            '': {
                target: 'http://202.120.40.8:30859', //对应自己的接口
                disableHostCheck: true,
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}