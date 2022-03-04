<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="查询条件" prop="condition">
        <el-input
          v-model="queryParams.condition"
          placeholder="请输入查询条件"
          clearable
          maxlength="10"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          :picker-options="pickerOptions"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          @change="dateQuery"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <router-link :to="'/business/english/article/add'" style="margin-left: 10px">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            v-hasPermi="['english:article:add']"
          >新增
          </el-button>
        </router-link>
      </el-form-item>
    </el-form>


    <div v-loading="loading">
      <el-timeline v-for="article in articleList">
        <el-timeline-item :timestamp=article.createTime placement="top">

          <el-button type="text"
                     @click="toEdit(article)"
                     v-hasPermi="['english:article:edit']"
                     icon="el-icon-edit">编辑
          </el-button>
          <el-divider direction="vertical"></el-divider>
          <el-popconfirm
            title="确定删除吗？"
            @confirm="remove(article.id)"
          >
            <el-button type="text"
                       slot="reference"
                       v-hasPermi="['english:article:remove']"
                       icon="el-icon-delete">删除
            </el-button>
          </el-popconfirm>


          <el-card>
            <router-link
              :to="{
                path: '/business/english/article/details',
                query: { id: article.id },
              }"
            >
              <span class="text-line-h3">{{ article.titleChinese }}</span>
            </router-link>
            <el-divider content-position="center">作者：{{ article.createUser }}</el-divider>
            <span class="text-line">{{ article.contentChinese }}</span>
          </el-card>
        </el-timeline-item>
      </el-timeline>
      <el-empty description="暂无内容" v-if="articleList.length===0"></el-empty>
    </div>


    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="listArticle"
    />

    <el-dialog
      title="修改文章"
      :visible.sync="dialogVisible"
      width="50%"
      @close="close"
    >

      <div>
        <el-row :gutter="15">
          <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
            <el-col :span="24">
              <el-form-item label="标题" prop="titleChinese">
                <el-input v-model="formData.titleChinese" placeholder="请输入标题" :maxlength="100" clearable
                          prefix-icon='el-icon-eleme' :style="{width: '100%'}"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="内容" prop="contentChinese">
                <el-input v-model="formData.contentChinese" type="textarea" placeholder="请输入内容" :maxlength="1000"
                          :autosize="{minRows: 8, maxRows: 8}"
                          style="width: 100%;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">

              <el-form-item label="上传封面" prop="pictureUrl" required>
                <el-upload ref="pictureUrl"
                           action="#" :http-request="requestUpload"
                           :file-list="fileList"
                           :limit="1"
                           :before-remove="removeImg"
                           :before-upload="picture_rulBeforeUpload"
                           list-type="picture-card" accept="image/*"
                           name="pictureUrl">
                  <i class="el-icon-plus"></i>
                </el-upload>
              </el-form-item>
            </el-col>

          </el-form>
        </el-row>
      </div>


      <span slot="footer" class="dialog-footer">
    <el-button @click="close">取 消</el-button>
     <el-button @click="resetForm">重置</el-button>
    <el-button type="primary" @click="editSubmit">确 定</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<script>
import {listArticle, updateArticle, delArticle, removeImg, uploadImg} from "@/api/business/english/article";
import {pickerOptions} from "@/layout/mixin/PickerOptions";

export default {
  name: "EnglishArticle",
  mixins: [pickerOptions],
  data() {
    return {
      //文章list
      articleList: [],

      fileList: [],

      dialogVisible: false,

      //查询条件
      queryParams: {
        pageNum: 1,
        pageSize: 3,
        condition: null,
        createTime: null,
      },

      // 查看次数时间范围
      daterangeCreateTime: [],

      // 总条数
      total: 0,

      loading: false,

      formData: {
        titleChinese: undefined,
        contentChinese: undefined,
        pictureUrl: null,
      },
      rules: {
        titleChinese: [{
          required: true,
          message: '请输入标题',
          trigger: 'blur'
        }],
        contentChinese: [{
          required: true,
          message: '请输入内容',
          trigger: 'blur'
        }],
      },

      //需要上传的文件
      file: {},

    }
  },

  created() {
    this.listArticle()

  },

  methods: {

    listArticle() {
      this.loading = true

      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.createTime = this.daterangeCreateTime[0];
        this.queryParams.endCreateTime = this.daterangeCreateTime[1];
      }

      listArticle(this.queryParams).then(res => {
        this.articleList = res.data.records
        this.total = res.data.total
        this.loading = false
      }).catch(err => {
        this.loading = false
      })

    },

    //修改提交
    editSubmit() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return

        this.$modal.loading("请稍候...");

        updateArticle(this.formData).then(res => {
          this.$modal.notifySuccess("修改成功");
          this.$modal.closeLoading()
        }).catch(err => {
          this.$modal.closeLoading()
        })

        this.dialogVisible = false;
      })
    },
    resetForm() {
      this.formData = {}

    },


    //打开编辑弹窗
    toEdit(article) {
      this.fileList = []
      this.dialogVisible = true;

      this.fileList.push({
        url: article.pictureUrl
      })

      this.formData = article
    },

    //关闭弹窗
    close() {
      this.fileList = []
      this.oldImgUrl = null
      this.dialogVisible = false;
      this.formData = {}
    },

    // 覆盖默认的上传行为
    requestUpload() {
    },

    //删除单条记录
    remove(id) {
      this.loading = true
      delArticle(id).then(res => {
        this.$modal.notifySuccess("删除成功");
        this.listArticle()
        this.loading = false
      }).catch(err => {
        this.loading = false
      })
    },

    //删除图片
    removeImg() {
      if (this.formData.pictureUrl) {
        let pictureUrl = {"url": this.formData.pictureUrl};
        removeImg(pictureUrl).then(res => {
        })

        this.formData.pictureUrl = null;
      }
    },

    picture_rulBeforeUpload(file) {
      this.$modal.loading("请稍候...");
      let isRightSize = file.size / 1024 / 1024 < 10
      if (!isRightSize) {
        this.$message.error('文件大小超过 10MB')
      }
      let isAccept = new RegExp('image/*').test(file.type)
      if (!isAccept) {
        this.$message.error('应该选择image/*类型的文件')
      }

      let formData = new FormData();
      formData.append("file", file);
      uploadImg(formData).then(res => {
        this.formData.pictureUrl = res.data.url
        this.$modal.closeLoading()
      }).catch(err => {
        this.$modal.closeLoading()
      })

      return isRightSize && isAccept
    },


    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.queryParams.createTime = null
      this.queryParams.endCreateTime = null

      this.resetForm("queryForm");
      this.handleQuery();
    },

    dateQuery() {
      //清空时间参数
      this.queryParams.createTime = null
      this.queryParams.endCreateTime = null

      this.handleQuery();
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.listArticle();
    },
  }


}
</script>

<style scoped>
.text-line {
  height: 25px;
  display: inline-block;
  /*下面是必需的*/
  width: 100%;
  color: #0157;
  white-space: nowrap; /*把文本强制显示在一行*/
  overflow: hidden; /*隐藏超出部分的文字*/
  text-overflow: ellipsis; /*超出显示省略号*/
}

.text-line-h3 {
  display: inline-block;
  /*下面是必需的*/
  width: 30%;
  color: #000;
  font-size: 15px;
  white-space: nowrap; /*把文本强制显示在一行*/
  overflow: hidden; /*隐藏超出部分的文字*/
  text-overflow: ellipsis; /*超出显示省略号*/
}
</style>
