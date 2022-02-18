<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="英语单词" prop="englishWord">
        <el-input
          v-model="queryParams.englishWord"
          placeholder="英语单词"
          clearable
          maxlength="20"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="中文" prop="chineseWord">
        <el-input
          v-model="queryParams.chineseWord"
          placeholder="请输入中文"
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
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['english:word:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['english:word:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['english:word:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['english:word:export']"
        >导出
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <router-link :to="'/business/english/collect/'" class="link-type">
          <el-button
            type="warning"
            plain
            icon="el-icon-star-off"
            size="mini"
            @click="handleCollect"
            v-hasPermi="['english:word:collect']"
          >
            收藏夹
          </el-button>
        </router-link>

      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="英语单词" align="center" prop="englishWord" :show-overflow-tooltip="true"/>
      <el-table-column label="中文" align="center" prop="chineseWord" :show-overflow-tooltip="true"/>
      <el-table-column label="排序" align="center" prop="sort" :show-overflow-tooltip="true"/>
      <el-table-column label="收藏" align="center" prop="isCollect">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.english_collect" :value="scope.row.isCollect"/>
        </template>
      </el-table-column>
      <el-table-column label="置顶" align="center" prop="top">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.english_top" :value="scope.row.top"/>
        </template>
      </el-table-column>
      <el-table-column label="查看次数" align="center" prop="lookCount" :show-overflow-tooltip="true"/>
      <el-table-column label="创建时间" align="center" prop="createTime" :show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="点击查看详情" placement="top-start">
            <el-button circle
                       type=""
                       icon="el-icon-view"
                       @click="handleView(scope.row,scope.index)"
                       v-hasPermi="['openapi:word:query']"
            ></el-button>
          </el-tooltip>
          <el-button circle
                     type="primary"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['english:word:edit']"
          ></el-button>
          <el-button circle
                     type="danger"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['english:word:remove']"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 修改英语单词对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="formEdit" :model="form" :rules="rulesEdit" label-width="80px" v-loading="loadingEdit">
        <el-form-item label="英语单词" prop="englishWord">
          <el-input v-model="form.englishWord" placeholder="请输入英语单词"/>
        </el-form-item>
        <el-form-item label="中文" prop="chineseWord">
          <el-input v-model="form.chineseWord" placeholder="请输入对应的中文"/>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-slider
            v-model="form.sort"
            show-input>
          </el-slider>
        </el-form-item>
        <el-form-item label="收藏" prop="isCollect">
          <el-select v-model="form.isCollect" placeholder="请选择是否收藏">
            <el-option
              v-for="dict in dict.type.english_collect"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="置顶" prop="top">
          <el-select v-model="form.top" placeholder="请选择置顶">
            <el-option
              v-for="dict in dict.type.english_top"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormEdit">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--添加英语对话框-->
    <el-dialog :title="title" :visible.sync="openAdd" width="500px" append-to-body>
      <el-form ref="formAdd" :model="form" :rules="rulesAdd" label-width="80px" v-loading="loadingEdit">
        <el-form-item label="中英文" prop="content">
          <el-input v-model="form.content" placeholder="请输入中文或英文"/>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-slider
            v-model="form.sort"
            show-input>
          </el-slider>
        </el-form-item>
        <el-form-item label="收藏" prop="isCollect">
          <el-select v-model="form.isCollect" placeholder="请选择是否收藏">
            <el-option
              v-for="dict in dict.type.english_collect"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="置顶" prop="top">
          <el-select v-model="form.top" placeholder="请选择置顶">
            <el-option
              v-for="dict in dict.type.english_top"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormAdd">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!--    抽屉  查看详情-->
    <el-drawer
      title="单词内容"
      :visible.sync="drawer"
      direction="rtl"
      :before-close="handleClose">


      <div v-loading="loadingC">
        <div class="div1">
          {{ form.englishWord }}
        </div>
        <div class="div2">
          {{ form.content }}
        </div>
        <div class="div2" style="height:320px ">
          {{ oneEnglishData.en }}
          <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
          {{ oneEnglishData.zh }}
        </div>
      </div>


    </el-drawer>
  </div>
</template>

<script>
import {listWord, getWord, delWord, addWord, updateWord, getWordRPC} from "@/api/business/english/word";
import {getOneEnglishApi} from "@/api/business/openapi/oneenglish";

export default {
  name: "Word",
  dicts: ['english_collect', 'english_top'],
  data() {
    return {
      // 抽屉遮罩层
      loadingC: true,
      //抽屉开关
      drawer: false,
      // 遮罩层
      loading: true,
      loadingEdit: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 英语单词表格数据
      wordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openAdd: false,
      // 查看次数时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        englishWord: null,
        chineseWord: null,
        createTime: null
      },
      // 表单参数
      form: {
        sort: 0
      },
      //英语一言数据
      oneEnglishData: {},
      // 表单校验
      rulesEdit: {
        englishWord: [
          {required: true, message: "英语单词不能为空", trigger: "blur"},
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        chineseWord: [
          {required: true, message: "中文不能为空", trigger: "blur"},
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        isCollect: [
          {required: true, message: "收藏不能为空", trigger: "blur"}
        ],
        top: [
          {required: true, message: "置顶不能为空", trigger: "blur"}
        ],

      },
      rulesAdd: {
        content: [
          {required: true, message: "中英文不能为空", trigger: "blur"},
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        isCollect: [
          {required: true, message: "收藏不能为空", trigger: "blur"}
        ],
        top: [
          {required: true, message: "置顶不能为空", trigger: "blur"}
        ],

      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //获取英语一言api数据
    getOneEnglishApi() {
      getOneEnglishApi().then(res => {
        this.oneEnglishData = res.data
      })
    },

    //根据id查询放入抽屉
    findById(id) {
      this.loadingC = true;
      getWordRPC(id).then(res => {
        this.form = res.data
        this.loadingC = false
      })
    },


    //关闭抽屉
    handleClose(done) {
      done();
      this.getList();
    },

    /** 详细按钮操作 */
    handleView(row) {
      this.drawer = true
      this.form = row;
      this.findById(row.id)
      this.getOneEnglishApi()
    },

    /** 查询英语单词列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.createTime = this.daterangeCreateTime[0];
        this.queryParams.endCreateTime = this.daterangeCreateTime[1];
      }
      listWord(this.queryParams).then(response => {
        this.wordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.openAdd = false
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        englishWord: null,
        chineseWord: null,
        sort: null,
        isCollect: null,
        top: null,
        lookCount: null,
        createTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.queryParams.createTime=null
      this.queryParams.endCreateTime=null
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.openAdd = true;
      this.title = "添加英语单词";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改英语单词";
      });
    },
    /** 提交按钮 */
    submitFormEdit() {
      this.$refs["formEdit"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              this.loadingEdit = true
              updateWord(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.loadingEdit = false
                this.open = false;
                this.getList();
              }).catch(err => {
                this.loadingEdit = false
              });

            }
          }
        }
      );
    },

    submitFormAdd() {
      this.$refs["formAdd"].validate(valid => {
          if (valid) {
            if (this.form.id == null) {
              this.loadingEdit = true
              addWord(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.openAdd = false;
                this.loadingEdit = false
                this.getList();
              }).catch(err => {
                this.loadingEdit = false
              });

            }
          }
        }
      );
    },


    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除英语单词编号为"' + ids + '"的数据项？').then(function () {
        return delWord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('english/word/export', {
        ...this.queryParams
      }, `word_${new Date().getTime()}.xlsx`)
    },

    //收藏夹操作
    handleCollect() {

    }
  }
};
</script>
<style scoped>
.div1 {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  height: 180px;
  margin: 10px;
  padding: 30px;
  padding-top: 60px;
  text-align: center;
  text-shadow: 2px 2px 2px grey;
  font-size: 30px;
}

.div2 {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  margin: 10px;
  padding: 30px;
  height: 280px;
  padding-top: 100px;
  text-align: center;
  text-shadow: 2px 2px 2px grey;
  font-size: 20px;
}
</style>
