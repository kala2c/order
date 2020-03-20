module.exports = {
  devServer: {
    port: 7000,
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8080',
        ws: false,
        changeOrigin: false
      }
    }
  }
}