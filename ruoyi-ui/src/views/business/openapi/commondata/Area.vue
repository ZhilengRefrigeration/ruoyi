<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6">

        <el-button size="mini"
                   type="primary"
                   icon="el-icon-arrow-left"
                   :loading="buttonLoading"
                   v-hasPermi="['openapi:area:rest']"
                   @click="getNewsArea()">联网获取最新区域编码
        </el-button>

        <el-tree
          ref="tree"
          :props="props"
          :data="dataList"
          lazy
          :load="loadNode"
          highlight-current
        >
        </el-tree>


      </el-col>

      <el-col :span="18">

      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getAreaByParentId, getProvinceArea, rest} from "@/api/business/openapi/common-data";

export default {
  name: "Area",
  data() {
    return {
      props: {
        label: 'name',
        children: 'districts',
        isLeaf: 'leaf'
      },

      dataList: [],

      buttonLoading: false,
    };
  },

  created() {
    this.getProvinceAreaList()
  },

  methods: {
    getProvinceAreaList() {
      getProvinceArea().then(res => {
        this.dataList = res.data
      })
    },


    loadNode(node, resolve) {

      if (node.level === 1 || node.level === 2 || node.level === 3) {
        getAreaByParentId(node.data.id).then(res => {
          //处理当前没有子节点时不显示箭头
          if (node.level === 3) {
            res.data.forEach(a => {

              a.leaf = true
            });
            setTimeout(() => {

              return resolve(res.data);
            }, 200);

          } else {
            setTimeout(() => {

              return resolve(res.data);
            }, 200);
          }
        })
      } else if (node.level === 4) {
        return resolve([])
      }
    },
    getNewsArea() {
      this.buttonLoading = true
      rest().then(res => {
        getProvinceArea().then(res => {
          this.dataList = res.data
          this.buttonLoading = false
        })

        this.$modal.notifySuccess("获取成功")
      })
    },
  },


}
</script>

<style scoped>

</style>
