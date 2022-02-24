<template>
  <div>
    <div class="bigDiv">
      <el-row :gutter="20" class="el-row" >
        <el-col :span="4" v-for="data in wallpaperList">
          <div class="grid-content bg-purple">
            <div style="">
              <el-image
                style="height: 108px;width: 192px"
                :src="data.pictureUrl"
                :preview-src-list="[data.pictureUrl]">
              </el-image>
            </div>

            <div>

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

import {getWallpaperList} from '@/api/business/webmagic/_36wallpaper/wallpaper36'

export default {
  name: "wallpaper_36Show",

  data() {
    return {
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 12,
        condition: null,
      },

      //总数
      total:0,

      //壁纸列表数据
      wallpaperList:{},
    }
  },

  created() {
    this.getList();
  },


  methods:{
    getList() {
      getWallpaperList(this.queryParams).then(response => {
        this.wallpaperList = response.data.records;
        this.total = response.data.total;
      });
    },
  }


}
</script>

<style scoped>
.bg-purple {
  background: #d3dce6;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  max-height: 350px;
  height: 320px;
  margin-bottom: 10px;
}

.bigDiv{
  margin: 15px;
}

.el-row{
  margin-bottom: 15px;
}

</style>
