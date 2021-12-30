<template>
  <div class="infinite-list-wrapper" style="overflow:auto">
    <ul
      class="list"
      v-infinite-scroll="load"
      infinite-scroll-disabled="disabled">
      <li v-for="i in count" class="list-item">{{ i }}</li>
    </ul>


    <p v-if="loading">加载中...</p>
  </div>
</template>

<script>
export default {
  name: "collect",
  data() {
    return {
      count: 10,
      loading: false
    }
  },
  computed: {
    disabled() {
      return this.loading
    }
  },
  methods: {
    load() {
      this.loading = true
      setTimeout(() => {
        this.count += 2
        this.loading = false
      }, 200)
    }
  }
}
</script>
