<template>
  <div class="app-container">
    <el-table
      :data="list"
      style="width: 100%"
      row-key="id"
      border
      lazy
      :load="load"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column
        prop="title"
        label="名称"
        width="150">
      </el-table-column>
      <el-table-column
        prop="createTime"
        width="200"
        label="创建时间">
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">

          <el-button @click="open(scope.row)" type="text" size="mini">添加</el-button>

          <el-button type="text" size="mini">修改</el-button>
          <el-button type="text" size="mini" @click="delSubject(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="el-toolbar">
      <div class="el-toolbar-body" style="justify-content: flex-start;">
        <el-button type="text" @click="exportData"><i class="fa fa-plus"/> 导出</el-button>
        <el-button type="text" @click="importData"><i class="fa fa-plus"/> 导入</el-button>
      </div>
    </div>

    <el-dialog title="导入" :visible.sync="dialogImportVisible" width="480px">
      <el-form label-position="right" label-width="170px">
        <el-form-item label="文件">
          <el-upload
            :multiple="false"
            :on-success="onUploadSuccess"
            :action="'http://localhost:8080/classroom-service-vod/admin/vod/subject/importData'"
            class="upload-demo">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传xls文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogImportVisible = false">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import subjectApi from '@/api/classroom/vod/subject'

export default {
  data() {
    return {
      dialogImportVisible: false,
      list: [], //数据字典列表数组
    }
  },
  created() {
    this.getSubList(0)
  },
  methods: {
    importData() {
      this.dialogImportVisible = true
    },
    onUploadSuccess(response, file) {
      this.$message.info('上传成功')
      this.dialogImportVisible = false
      this.getSubList(0)
    },

    //导出
    exportData() {
      window.open("http://localhost:8080/classroom-service-vod/admin/vod/subject/exportData")
    },
    //数据字典列表
    getSubList(id) {
      subjectApi.getChildList(id)
        .then(response => {
          this.list = response.data
        })
    },
    load(tree, treeNode, resolve) {
      subjectApi.getChildList(tree.id).then(response => {
        resolve(response.data)
      })
    },

    addSubject(data, params) {


      subjectApi.addSubject(data.id, params).then(res => {
        this.getSubList(0)
      })
    },

    open(data) {
      this.$prompt('请输入名称', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({value}) => {

        let params = {
          subjectName: value
        }
        this.addSubject(data, params)

        this.$message({
          type: 'success',
          message: "添加成功"
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },

    delSubject(id) {
      subjectApi.delSubject(id).then(res =>{
        this.$modal.notifySuccess("删除成功")
        this.list=[]
        this.getSubList(0)
      })
    },

  }
}
</script>
