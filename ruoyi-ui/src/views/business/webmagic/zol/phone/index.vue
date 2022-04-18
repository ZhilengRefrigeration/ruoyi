<template>
  <div class="app-container" v-loading="loading">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px" :rules="rules">
      <el-form-item label="查询条件" prop="condition">
        <el-input v-model="queryParams.condition"
                  placeholder="请输入手机名称、描述等"
                  maxlength="21"
                  size="small"
        ></el-input>
      </el-form-item>

      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          size="small"
          style="width: 320px"
          value-format="yyyy-MM-dd"
          format="yyyy 年 MM 月 dd 日"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions"
          @change="dateQuery"
        ></el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery()">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>

    </el-form>

    <div>
      <el-row :gutter="10">
        <el-col :span="4" v-for="(data,index) in formList" :key="data.id">
          <div class="grid-content bg-purple">
            <el-card class="" shadow="hover">
              <!--图片 -->
              <div style="width: 100%">
                <el-image
                  style="width: 220px; height: 165px"
                  :src="data.pictureUrl"
                  fit="fit"></el-image>
              </div>
              <el-tooltip class="item" effect="dark" :content="data.phoneName+data.description" placement="top">
                <!--描述 -->
                <div style="width: 100%" class="content">
                  <a :href="data.detailPage" target="_blank">
                    <span>{{ data.phoneName }}</span>
                    <span style="color: red">{{ data.description }}</span>
                  </a>
                </div>
              </el-tooltip>
              <!--参考价 -->
              <div class="content">
                <span style="color: #999093;font-size: 13px">参考价：</span>
                <span style="color: red;font-size: 15px">￥{{ data.price }}</span>
              </div>
              <!--评分 -->
              <div style="padding-bottom: 10px">
                <el-rate
                  style="float: left"
                  disabled
                  :max="5"
                  v-model.number="data.heat/2"> >
                </el-rate>
                <span style="float: left;color: red;font-size: 12px">{{ data.heat }}评分</span>
              </div>

            </el-card>
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
import {pickerOptions} from "@/layout/mixin/PickerOptions";
import {listZolPhone} from "@/api/business/webmagic/zol/zolPhone";
import {updateWord} from "@/api/business/english/word";

export default {
  name: "ZolPhone",
  mixins: [pickerOptions],
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 18,
        condition: null,
      },

      //检查查询范围
      daterangeCreateTime: [],

      //表单数据
      formList: {},

      //总数
      total: 0,

      // 遮罩层
      loading: true,

      rules: {
        condition: [
          {min: 0, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ]
      }
    }
  },

  created() {
    this.getList()
  },

  methods: {
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.queryParams.createTime = null
      this.queryParams.endCreateTime = null
      this.resetForm("queryForm");
      // this.queryParams.condition = ""
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
      this.$refs["queryForm"].validate(valid => {
          if (valid) {
            this.getList();
          }
        }
      );
    },

    getList() {
      if (null != this.daterangeCreateTime && '' !== this.daterangeCreateTime) {
        this.queryParams.createTime = this.daterangeCreateTime[0];
        this.queryParams.endCreateTime = this.daterangeCreateTime[1];
      }

      this.loading = true;
      listZolPhone(this.queryParams).then(res => {
        this.loading = false;
        this.formList = res.data.records
        this.total = res.data.total
      })


    },
  },


}
</script>

<style scoped>

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.content {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  height: 35px;
  -webkit-line-clamp: 2; /* 限制在一个块元素显示的文本的行数 */
  -webkit-box-orient: vertical; /* 垂直排列 */
  word-break: break-all; /* 内容自动换行 */
  font-size: 12px;
}

a:hover {
  color: red;
}

</style>
