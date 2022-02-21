<template>
  <div>
    <div class="bigDiv">
      <el-row :gutter="20" class="el-row">
        <el-col :span="4">
          <div class="grid-content bg-purple">

          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple"></div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="el-row">
        <el-col :span="4">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple"></div>
        </el-col>
      </el-row>

    </div>

<!--    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />-->

  </div>
</template>

<script>
export default {
  name: "wallpaper_36Show"
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
}

.bigDiv{
  margin: 15px;
}

.el-row{
  margin-bottom: 15px;
}

</style>
