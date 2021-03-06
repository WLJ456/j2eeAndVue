module.exports = {
  assetsDir: 'assets',
  lintOnSave: false,
  publicPath:'/',

  devServer: {
    open: true,
    port: 80,
    proxy: {
      '/api': {
       target: 'http://49.233.69.229:8080/',
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      },
      '/code': {
        target: 'http://apis.haoservice.com',
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/code': ''
        }
      }
    }
  }
}
