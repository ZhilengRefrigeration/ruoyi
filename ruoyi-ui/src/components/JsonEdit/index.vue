<template>
  <div class="json-editor">
    <textarea ref="textarea"/>
  </div>
</template>

<script>
import CodeMirror from 'codemirror'
import 'codemirror/addon/lint/lint.css'
import 'codemirror/lib/codemirror.css'
import 'codemirror/theme/rubyblue.css'

require('script-loader!jsonlint')
import 'codemirror/mode/javascript/javascript'
import 'codemirror/addon/lint/lint'
import 'codemirror/addon/lint/json-lint'

export default {
  props: ['value'],
  data() {
    return {
      jsonEditor: false,
      oldValue:undefined,
    }
  },
  watch: {
    value(value) {
      const editorValue = this.jsonEditor.getValue()
      if (value !== editorValue) {
        this.jsonEditor.setValue(JSON.stringify(this.value, null, 2))
      }
    }
  },
  mounted() {
    // CodeMirror.fromTextArea()中第一个参数是DOM元素，而且必须是textarea元素；第二个参数是可选配置项
    this.jsonEditor = CodeMirror.fromTextArea(this.$refs.textarea, {
      lineNumbers: true,
      mode: 'application/json',
      gutters: ['CodeMirror-lint-markers'],
      theme: 'default',
      lint: true,

    })

    this.jsonEditor.setValue(JSON.stringify(this.value, null, 2))
    this.jsonEditor.on('change', cm => {
      this.$emit('changed', cm.getValue())
      this.$emit('input', cm.getValue())
    })

    this.$nextTick(function() {
      this.$on('autoFormat', function(value) {
        this.autoFormat(value)
      });

    });
  },

  created() {
    this.oldValue=this.value
  },

  methods: {
    getValue() {
      return this.jsonEditor.getValue()
    },

    //代码格式化
    autoFormat(newValue) {
      try {
        //判断值是否变化，只有变化才需要格式化
        if (newValue !== this.oldValue) {
          this.jsonEditor.setValue(JSON.stringify(JSON.parse(this.value), null, 2));
        }
      } catch (e) {
        this.$modal.notifyWarning("格式错误")
      }
    }
  }
}
</script>

<style scoped>
.json-editor {
  height: 100%;
  position: relative;
}

.json-editor >>> .CodeMirror {
  height: auto;
  min-height: 750px;
}

.json-editor >>> .CodeMirror-scroll {
  min-height: 750px;
}

.json-editor >>> .cm-s-rubyblue span.cm-string {
  color: #F08047;
}

.addbtn {
  margin-bottom: 15px;
  margin-left: 30px;
}
</style>
