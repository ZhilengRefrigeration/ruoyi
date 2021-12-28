<template>
  <div class="app-container">
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple" style="height: 180px">
<!--          文案内容区域-->
<!--          内容-->
          <div class="content_div">
            {{responseCopyWriting.content}}
          </div>
<!--          来源-->
          <div class="source_div">
            {{responseCopyWriting.source}}

          </div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12">
        <!--        翻译区域-->
        <div class="grid-content bg-purple">
          <el-row :gutter="15">
            <el-form ref="translation" :model="translationData" :rules="translationRules" size="medium"
                     label-width="100px" label-position="top">
              <el-col :span="12">
                <el-form-item label="翻译平台" prop="translationType">
                  <el-select
                    v-model="translationData.translationType"
                    placeholder="翻译平台"
                    clearable
                    size="small"
                    style="width: 150px">
                    <el-option
                      v-for="dict in dict.type.translation_type"
                      :key="dict.value"
                      :label="dict.label"
                      :value="dict.value"/>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="20">
                <el-form-item label="翻译区域" prop="q">
                  <el-input v-model="translationData.q" type="textarea" placeholder="请输入翻译内容" show-word-limit
                            :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item size="large">
                  <el-button type="primary" @click="submitForm">提交</el-button>
                  <el-button @click="resetForm">重置</el-button>
                </el-form-item>
              </el-col>
            </el-form>
          </el-row>
        </div>
      </el-col>
      <el-col :span="12">
        <!--        翻译结果显示区域-->
        <div class="grid-content bg-purple ">
          <div class="spans">
            {{ responseTranslation }}
          </div>
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import {translation,getCopyWriting} from "@/api/business/english/translation";

export default {
  dicts: ['translation_type'],
  name: "Log",
  data() {
    return {
      //翻译响应数据
      responseTranslation: '',

      translationData: {
        translationType: '',
        q: '',
      },

      //文案参数
      copyWriting:[],

      //文案响应数据
      responseCopyWriting:{},

      translationRules: {
        translationType: [{
          required: true,
          message: '翻译平台不能为空',
          trigger: 'change'
        }],
        q: [
          {
          required: true,
          message: '请输入翻译内容',
          trigger: 'blur'
        },
          {
            min: 1,
            max: 120,
            message: '长度在 1 到 120 个字符',
            trigger: 'blur'
          }
        ],
      },
    }
  },
  created() {
    this.getCopyWriting()
  },
  methods: {
    //获取文案
    getCopyWriting() {
      getCopyWriting(this.copyWriting).then(res =>{
        this.responseCopyWriting=res.data
      })
    },

    submitForm() {
      this.$refs['translation'].validate(valid => {
        if (valid) {
          translation(this.translationData).then(res => {
            let result = res.data.transResult
            let results = ''
            result.forEach(r => {
              results = results + '  ' + r.dst;
            })
            this.responseTranslation = results

          })
        }

      })
    },
    resetForm() {
      this.$refs['translation'].resetFields()
    },
  },
};
</script>

<style>
.bg-purple {
  box-shadow: 0 0 9px 3px #999;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  height: 500px;
  margin-top: 20px;
  padding: 50px;
}

.spans {
  margin: 50px;
  margin-top: 40px;
  padding: 50px;
  font-family: Georgia;
  font-size: 20px;
  height: 300px;
  box-shadow: 0 0 9px 3px #999;
  font-style: italic;
}

.content_div{
  float: left;
  width: 88%;
  text-shadow: 2px 2px 2px grey;
}
.source_div{
  float: left;
  padding-top: 70px;
  width: 12%;
  text-shadow: 2px 2px 2px grey;
}

</style>
