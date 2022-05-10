<template>
  <div class="app-container">
    <el-descriptions title="什么是正则表达式？">
      <el-descriptions-item label="描述" contentStyle="color: #999999">
        在编写处理字符串的程序或网页时，经常有查找符合某些复杂规则的字符串的需要。正则表达式就是用于描述这些规则的工具。换句话说，正则表达式就是记录文本规则的代码。
      </el-descriptions-item>
    </el-descriptions>

    <el-row :gutter="50" style="margin-top: 20px">
      <div>
        <el-col :span="24">常用的正则表达式</el-col>
      </div>
    </el-row>

    <el-row :gutter="50" style="margin-top: 20px">
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('[1-9]\\d*.\\d*|0\\.\\d*[1-9]\\d*')">中文字符</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('[^\x00-\xff]')">双字节字符</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('\\s')">空白行</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}')">
            Email地址
          </at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+')">网址URL</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('0?(13|14|15|18|17)[0-9]{9}')">手机(国内)</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('[0-9-()（）]{7,18}')">电话号码(国内)</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('-([1-9]\\d*.\\d*|0\\.\\d*[1-9]\\d*)')">负浮点数</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('-?[1-9]\\d*')">匹配整数</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('[1-9]\\d*.\\d*|0\\.\\d*[1-9]\\d*')">正浮点数</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('[1-9]([0-9]{4,10})')">腾讯QQ号</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('\\d{6}')">邮政编码</at-button>
        </div>
      </el-col>

    </el-row>

    <el-row :gutter="50" style="margin-top: 20px">
      <el-col :span="2">
        <div>
          <at-button type="error" hollow
                     @click="print('(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)')">
            IP地址
          </at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('\\d{17}[\\d|x]|\\d{15}')">身份证号</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}')">格式日期</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('[1-9]\\d*')">正整数</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('-[1-9]\\d*')">负整数</at-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div>
          <at-button type="error" hollow @click="print('^<([a-z]+)([^<]+)*(?:>(.*)<\\/\\1>|\\s+\\/>)$')">HTML标签</at-button>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="50" style="margin-top: 20px">
      <el-col :span="24">
        <el-input
          v-model="input"
        >
        </el-input>
      </el-col>
    </el-row>

    <el-row :gutter="50" style="margin-top: 20px">
      <el-col :span="8">
        <template>
          <el-table
            :data="one"
            style="width: 100%">
            <el-table-column label="常用元字符" align="center">
              <el-table-column align="center"
                prop="code"
                label="代码"
                width="180">
              </el-table-column>
              <el-table-column align="center"
                prop="explanation"
                label="说明"
              >
              </el-table-column>
            </el-table-column>
          </el-table>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-table
            :data="two"
            style="width: 100%">
            <el-table-column label="常用限定符" align="center">
              <el-table-column align="center"
                               prop="code"
                               label="代码/语法"
                               width="180">
              </el-table-column>
              <el-table-column align="center"
                               prop="explanation"
                               label="说明"
              >
              </el-table-column>
            </el-table-column>
          </el-table>
        </template>
      </el-col>
      <el-col :span="8">
        <template>
          <el-table
            :data="three"
            style="width: 100%">
            <el-table-column label="常用反义词" align="center">
              <el-table-column align="center"
                               prop="code"
                               label="代码/语法"
                               width="180">
              </el-table-column>
              <el-table-column align="center"
                               prop="explanation"
                               label="说明"
              >
              </el-table-column>
            </el-table-column>
          </el-table>
        </template>
      </el-col>
    </el-row>


  </div>
</template>

<script>
export default {
  name: "Regex",

  data() {
    return {
      input: '',

      one: [{
        code: '.',
        explanation: '匹配除换行符以外的任意字符',
      }, {
        code: '\\w',
        explanation: '匹配字母或数字或下划线',
      }, {
        code: '\\s',
        explanation: '匹配任意的空白符',
      }, {
        code: '\\d',
        explanation: '匹配数字',
      },
        {
          code: '\\b',
          explanation: '匹配单词的开始或结束',
        },
        {
          code: '^',
          explanation: '匹配字符串的开始',
        },
        {
          code: '$',
          explanation: '匹配字符串的结束',
        },
      ],

      two :[{
        code: '*',
        explanation: '重复零次或更多次',
      }, {
        code: '+',
        explanation: '重复一次或更多次',
      }, {
        code: '?',
        explanation: '重复零次或一次',
      }, {
        code: '{n}',
        explanation: '重复n次',
      },
        {
          code: '{n,}',
          explanation: '重复n次或更多次',
        },
        {
          code: '{n,m}',
          explanation: '重复n到m次',
        },
      ],

      three :[{
        code: '\\W',
        explanation: '匹配任意不是字母，数字，下划线，汉字的字符',
      }, {
        code: '\\S',
        explanation: '匹配任意不是空白符的字符',
      }, {
        code: '\\D',
        explanation: '匹配任意非数字的字符',
      }, {
        code: '\\B',
        explanation: '匹配不是单词开头或结束的位置',
      },
        {
          code: '[^x]',
          explanation: '匹配除了x以外的任意字符',
        },
        {
          code: '[^aeiou]',
          explanation: '匹配除了aeiou这几个字母以外的任意字符',
        },
      ],
    }
  },

  methods: {
    print(data) {
      this.input = data
    },
  }
}
</script>

<style scoped>

</style>
