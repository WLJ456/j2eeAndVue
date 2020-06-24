module.exports = {
  assetsDir: 'assets',
  lintOnSave: false,
  devServer: {
    open: true,
    port: 8088,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
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
