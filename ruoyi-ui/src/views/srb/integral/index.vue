<template>

  <div class="app-container">
    <el-table :data="list" border stripe>

      <el-table-column label="借款额度(￥)" align="center" prop="borrowAmount" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{ scope.row.borrowAmount }}￥</span>
        </template>
      </el-table-column>
      <el-table-column label="积分区间开始" align="center" prop="integralStart" :show-overflow-tooltip="true"/>
      <el-table-column label="积分区间结束" align="center" prop="integralEnd" :show-overflow-tooltip="true"/>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

          <router-link
            :to="{
                path: '/srb/integral/form/',
                query: { id: scope.row.id },
              }"

            v-hasPermi="['srb:integralGrade:update']"
            style="margin-right: 5px"
          >
            <el-button
              size="small"
              type="primary"
              icon="el-icon-edit"
            ></el-button>
          </router-link>


          <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定删除吗？"
            @confirm="removeById(scope.row.id)"
          >
            <el-button
              type="info"
              size="small"
              slot="reference"
              icon="el-icon-delete"
              v-hasPermi="['srb:integralGrade:remove']"
            >
            </el-button>
          </el-popconfirm>


        </template>
      </el-table-column>
    </el-table>


  </div>

</template>

<script>

import {getList, removeById} from "@/api/srb/core/integral";

export default {
  name: "IntegralIndex",

  data() {
    return {

      list: [],

    }
  },

  created() {
    this.fetchData()
  },

  methods: {
    //获取数据
    fetchData() {
      const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: "#666666"
      });

      getList().then(res => {
        loading.close();
        this.list = res.data
      }).catch(err => {
      })
      loading.close();
    },

    //删除
    removeById(id) {
      removeById(id).then(() => {
        this.$modal.notifySuccess("删除成功");
        this.fetchData()
      }).catch(() => {
      });
    },
  },


}
</script>

<style scoped>

</style>
