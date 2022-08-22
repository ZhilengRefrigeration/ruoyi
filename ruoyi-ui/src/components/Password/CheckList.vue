<template>
  <div class="check-list" :key="pagek">
    <div class="check-list__title">{{ title }}</div>
    <div v-for="{ key, label } in options" :key="key">
      <div class="check-list-item">
        <span class="check-list-item__prefix">
          <i
            v-if="isEqual(key, 'success')"
            class="iconfont el-icon-success"
          ></i>
          <i
            v-else-if="isEqual(key, 'error')"
            class="iconfont el-icon-error"
          ></i>
          <i
            v-else-if="isEqual(key, 'warning')"
            class="iconfont el-icon-warning"
          ></i>
          <i v-else-if="isEqual(key, 'info')" class="el-icon-info"></i>
          <i v-else class="iconfont el-icon-pending"></i>
        </span>
        <div class="check-list-label">{{ label }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CheckList',
  props: {
    pagek: {
      default: new Date().getTime()
    },
    values: {
      type: Object,
      default: () => ({})
    },
    options: {
      type: Array,
      default: () => []
    },
    title: {
      type: String,
      default: ""
    }
  },
  mounted() {
  },
  data: ()=>{
    return{
      status : ["pending", "success", "error", "warning", "info"],
    }
  },
  methods: {
    defaultStatus() {
      return this.options.reduce(
        (res, {key}) => Object.assign(res, {[key]: this.status[0]}),
        {}
      );
    },
    statusMap() {
      return Object.assign({}, this.defaultStatus(), this.values);
    },
    isEqual(key, statusCode){
      return this.statusMap()[key] == statusCode;
    }
  }
}
</script>

<style lang="scss" scoped>
// 胶囊状态颜色
.el-icon-success {
  color: #40b828;
}

.el-icon-error {
  color: #f0553a;
}

.el-icon-warning {
  color: #fc971c;
}

.el-icon-info {
  color: #878e99;
}

.el-icon-pending {
  &::before {
    content: "";
    display: inline-flex;
    width: 12px;
    height: 12px;
    border: 2px solid rgba(#000, 0.15);
    border-radius: 50%;
    // vertical-align: baseline;
    // margin-bottom: -2px;
  }
}

.check-list {
  font-size: 13px;
}

.check-list__title {
  line-height: 20px;
  color: #fc971c;
  margin-bottom: 12px;
}

.check-list-item {
  line-height: 20px;
  margin-bottom: 8px;
}

.check-list-item__prefix {
  float: left;
  font-size: 16px;
}

.check-list-label {
  margin-left: 16px + 12px;
}
</style>

