<template>
  <div>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'work',
  components: {

  },
  data() {
    return {

    }
  },
  computed: {
    ...mapGetters({
      codeCombo: 'getCodeCombo',
      permissionList: 'getPermission',
      userInfo: 'getUserInfo',
    }),
  },
  activated() {

  },
  created() {

  },
  methods: {

  },
}
</script>

<style lang="scss" scoped>

</style>
