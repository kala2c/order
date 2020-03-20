<template>
  <div id="app">
    <Loading />
    <router-view/>
    <Dialog />
  </div>
</template>

<script>
import Dialog from './components/Dialog'
import Loading from './components/Loading'
export default {
  components: {
    Dialog,
    Loading
  }
}
</script>

<style lang="scss">
html, body {
  height: 100%;
}
#app {
  position: relative;
  height: 100%;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
