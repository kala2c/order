<template>
  <!-- muse-ui的弹窗 -->
  <mu-dialog :title="dialogTitle" width="320" :open.sync="dialogShow">
    {{dialogMessage}}
    <mu-button slot="actions" flat color="primary" @click="closeDialog">关闭</mu-button>
  </mu-dialog>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  // data () {
  //   return {
  //     dialogMessage: '警告'
  //   }
  // },
  computed: {
    ...mapGetters(['dialogTitle', 'dialogShow', 'dialogMessage'])
  },
  methods: {
    ...mapActions(['dialog/close']),
    closeDialog () {
      // console.log(this)
      console.log(this['dialog/close']())
    }
  }
}
</script>

<style>

</style>
