<template>
  <el-popover popper-class="gt-password-popper" v-bind="popoverOptions">
    <template slot="reference">
      <el-input
        class="gt-password"
        v-bind="$attrs"
        v-on="$listeners"
        type="password"
        show-password
      >
      </el-input>
    </template>
    <CheckList
      :pagek="pagek"
      :values="checkValuesObj"
      title="密码必须包括"
      :options="checkList"
    />
  </el-popover>
</template>


<script>
import CheckList from "./CheckList.vue";

const popDefault = {
  placement: "right-start",
  width: 228,
  trigger: "focus"
};

export default {
  name: 'Password',
  components: {CheckList},
  props: {
    checkList: {
      type: Array,
      required: true
    },
    popover: {
      type: Array,
      required: false
    },
  },
  data: () => {
    return {
      pagek: new Date().getTime(),
      popoverOptions: {},
      checkValuesObj: {},
      passwordType: true
    }
  },
  mounted() {
    this.popoverOptions = {...this.popover, ...popDefault};
    this.update(this.checkList);
  },
  methods: {
    update(validList, clear = false) {
      // 每次update 前先清空 checkValuesObj
      Object.keys(this.checkValuesObj).forEach(v => {
        this.checkValuesObj[v] = "";
      });

      let tempObj = {};
      this.$nextTick(() => {
        this.pagek = new Date().getTime();
        tempObj =
          validList.length > 0 &&
          validList.reduce(
            (res = {}, key = "1") => Object.assign(res, {[key]: "success"}),
            {}
          );

        if (tempObj) {
          Object.keys(tempObj).forEach(v => {
            this.checkValuesObj[v] = tempObj[v];
          });
        }
        if (clear) {
          Object.keys(this.checkValuesObj).forEach(v => {
            this.checkValuesObj[v] = "";
          });
        }
      });
    }
  }
}


</script>

