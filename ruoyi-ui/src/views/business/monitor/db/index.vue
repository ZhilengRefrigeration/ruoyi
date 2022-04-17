<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini"
                   @click="handleExportHtml"
                   v-hasPermi="['monitor:db:list']">导出 HTML</el-button>
        <el-button type="primary" icon="el-icon-plus" size="mini"
                   v-hasPermi="['monitor:db:word']"
                   @click="handleExportWord">导出 Word</el-button>
        <el-button type="primary" icon="el-icon-plus" size="mini"
                   v-hasPermi="['monitor:db:markdown']"
                   @click="handleExportMarkdown">导出 Markdown</el-button>
        <el-select @change="getHtml"
                   v-model="selectValue"
                   placeholder="请选择数据源"
                   size="mini"
                   style="margin-left: 30px;width: 150px;margin-right: 15px">
          <el-option
            v-for="item in dataSourceKey"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-tooltip content="选择需要展示的数据库源" placement="top">
          <i class="el-icon-question"></i>
        </el-tooltip>
      </el-col>
    </el-row>

    <!-- 展示文档 -->
    <div v-loading="loading" :style="'height:'+ height">
      <i-frame :src="src"/>
    </div>
  </div>
</template>
<script>
import {exportHtml, exportWord, exportMarkdown, getDataSource} from "@/api/business/monitor/db/dbDoc";
import iFrame from "@/components/iFrame/index";

export default {
  name: "DBDoc",
  components: {iFrame},
  data() {
    return {
      height: document.documentElement.clientHeight - 94.5 + "px",
      loading: true,
      src: '',

      dataSourceKey: [
        {
          value: '',
          label: ''
        }
      ],

      selectValue: ''

    };
  },


  mounted: function () {
    setTimeout(() => {
      this.loading = false;
    }, 100);
    const that = this;
    window.onresize = function temp() {
      that.height = document.documentElement.clientHeight - 94.5 + "px";
    };
  },
  created() {

    this.getDataSource()


  },
  methods: {
    // 加载所有数据源key
    getDataSource() {
      getDataSource().then(res => {
        this.dataSourceKey = res.data

        //下拉框默认选中
        this.selectValue=this.dataSourceKey[0].value

        //根据数据源名去加载数据源
        this.getHtml()
      })
    },

    getHtml() {
      // 加载 Html，进行预览
      let param={
        dataSourceKey:this.selectValue
      }
      exportHtml(param).then(response => {
        let blob = new Blob([response], {type: 'text/html'});
        this.src = window.URL.createObjectURL(blob);
      })
    },


    /** 处理导出 HTML */
    handleExportHtml() {
      let param={
        dataSourceKey:this.selectValue
      }
      exportHtml(param).then(response => {
        this.$download.html(response, '数据库文档.html');
      })
    },
    /** 处理导出 Word */
    handleExportWord() {
      let param={
        dataSourceKey:this.selectValue
      }
      exportWord(param).then(response => {
        this.$download.word(response, '数据库文档.doc');
      })
    },
    /** 处理导出 Markdown */
    handleExportMarkdown() {
      let param={
        dataSourceKey:this.selectValue
      }
      exportMarkdown(param).then(response => {
        this.$download.markdown(response, '数据库文档.md');
      })
    }
  }
};
</script>
