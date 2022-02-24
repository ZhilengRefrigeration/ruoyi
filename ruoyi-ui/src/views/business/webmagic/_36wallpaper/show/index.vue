<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="图片类型" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="请输入"
          size="small"
          @change="handleQuery"
          style="width: 180px">
          <el-option
            v-for="index in typeList"
            :key="index"
            :label="index"
            :value="index"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <div class="bigDiv">
      <el-row :gutter="20" class="el-row">
        <el-col :span="4" v-for="data in wallpaperList">
          <div class="grid-content bg-purple">

            <div style="height: 7px;background-color: #ffba00"></div>

            <!--图片div-->
            <div style="">
              <el-image
                :src="data.pictureUrl"
                fit="fill"
                style="height: 160px"
                :preview-src-list="[data.pictureUrl]">
              </el-image>
            </div>

            <div style="height: 7px;background-color: #ffba00"></div>

            <!--标签div-->
            <div style="">
              <div v-for="label in data.labels" style="float: left;margin-left: 4px;margin-top: 4px">
                <el-tag>{{ label }}</el-tag>
              </div>
            </div>


          </div>
        </el-col>
      </el-row>

    </div>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>

import {getWallpaperList, getType} from '@/api/business/webmagic/_36wallpaper/wallpaper36'

export default {
  name: "wallpaper_36Show",

  data() {
    return {
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 12,
        condition: null,
        type: null,
      },

      //总数
      total: 0,

      //壁纸列表数据
      wallpaperList: {},

      //壁纸类别
      typeList: [],
    }
  },

  created() {
    this.init()

  },


  methods: {
    getList() {

      getWallpaperList(this.queryParams).then(response => {
        this.wallpaperList = response.data.records;
        this.total = response.data.total;
      });
    },


    init() {
      //获取类别
      getType().then(res => {
        this.typeList = res.data

        this.queryParams.type = this.typeList[0]
        this.getList();
      })
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.queryParams.type = this.typeList[0]
      this.handleQuery();
    },
  }


}
</script>

<style scoped>
.bg-purple {
  background: #666666;
}

.grid-content {
  border-radius: 10px;
  min-height: 36px;
  max-height: 350px;
  height: 310px;
  margin-bottom: 10px;
}

.bigDiv {
  margin: 15px;
}

.el-row {
  margin-bottom: 15px;
}

</style>
