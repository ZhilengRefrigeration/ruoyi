<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="英语单词" prop="englishWord">
        <el-input
          v-model="queryParams.englishWord"
          placeholder="英语单词"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="中文" prop="chineseWord">
        <el-input
          v-model="queryParams.chineseWord"
          placeholder="请输入中文"
          clearable
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
      <el-form ref="form" :model="form" :rules="rulesEdit" label-width="80px">
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
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--添加英语对话框-->
    <el-dialog :title="title" :visible.sync="openAdd" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rulesAdd" label-width="80px">
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
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listWord, getWord, delWord, addWord, updateWord} from "@/api/business/english/word";

export default {
  name: "Word",
  dicts: ['english_collect', 'english_top'],
  data() {
    return {
      // 遮罩层
      loading: true,
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
      form: {},
      // 表单校验
      rulesEdit: {
        englishWord: [
          {required: true, message: "英语单词不能为空", trigger: "blur"}
        ],
        chineseWord: [
          {required: true, message: "中文不能为空", trigger: "blur"}
        ],
        isCollect: [
          {required: true, message: "是否收藏 1收藏 2不收藏不能为空", trigger: "change"}
        ],
        top: [
          {required: true, message: "置顶 1置顶 2不置顶不能为空", trigger: "change"}
        ],
        createTime: [
          {required: true, message: "创建时间不能为空", trigger: "blur"}
        ]
      },
      rulesAdd: {
        content: [
          {required: true, message: "中英文不能为空", trigger: "blur"}
        ],
        isCollect: [
          {required: true, message: "是否收藏 1收藏 2不收藏不能为空", trigger: "change"}
        ],
        top: [
          {required: true, message: "置顶 1置顶 2不置顶不能为空", trigger: "change"}
        ],
        createTime: [
          {required: true, message: "创建时间不能为空", trigger: "blur"}
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
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
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWord(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.openAdd = false;
              this.getList();
            });
          }
        }
      });
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
    }
  }
};
</script>
