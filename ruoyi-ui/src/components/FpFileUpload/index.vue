<template>
  <div>
    <!--
      allowFileSizeValidation: true //开启文件大小检验
      allowFileTypeValidation: true //开启文件类型检验
      allowImagePreview: true //开启图片预览
      allowMultiple: true //启用多文件上传
      allowProcess: false //禁用组件自己的上传按钮
      allowRevert: false //禁用撤销按钮
      instantUpload: false //禁用自动上传
      credits: false //隐藏[Powered by PQINA]字样
     -->
    <file-pond
        ref="pond"
        @Init="handleInit"
        label-idle="拖放文件到这里..."
        :max-file-size="fileSize"
        :accepted-file-types="fileType"
        :max-files="maxFilesLimit"
        :image-preview-min-height="imgPreviewMinHeight"
        :image-preview-max-height="imgPreviewMaxHeight"
        :image-preview-height="imgPreviewFixedHeight"
        :allow-file-size-validation="true"
        :allow-file-type-validation="true"
        :allow-image-preview="true"
        :allow-multiple="true"
        :allow-process="false"
        :allow-revert="false"
        :instant-upload="false"
        credits="false"
    />
  </div>
</template>

<script setup>
// Import FilePond
import vueFilePond from 'vue-filepond';

// Import plugins
import FilePondPluginFileValidateType from 'filepond-plugin-file-validate-type/dist/filepond-plugin-file-validate-type';
import FilePondPluginFileValidateSize from 'filepond-plugin-file-validate-size/dist/filepond-plugin-file-validate-size';
import FilePondPluginImagePreview from 'filepond-plugin-image-preview/dist/filepond-plugin-image-preview';

// Import styles
import 'filepond/dist/filepond.min.css';
import 'filepond-plugin-image-preview/dist/filepond-plugin-image-preview.min.css';

// Create FilePond component
const FilePond = vueFilePond(FilePondPluginFileValidateType, FilePondPluginFileValidateSize, FilePondPluginImagePreview);
const pond = ref(null);

const {proxy} = getCurrentInstance();
const emit = defineEmits();

const props = defineProps({
  // 单个文件大小限制
  fileSize: {
    type: String,
    default: '5MB',
  },
  // 总体文件大小限制，格式同fileSize
  totalFileSize: {
    type: String,
    default: null,
  },
  // 允许上传的文件类型
  fileType: {
    type: Array,
    default: () => [
      //文档
      'text/plain', 'application/pdf', 'application/vnd.ms-excel', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
      //图片
      'image/jpeg', 'image/png', 'image/webp', 'image/gif',
      //压缩包
      'application/vnd.rar', 'application/zip', 'application/x-7z-compressed',
    ],
  },
  // 允许上传的最大文件数量
  maxFilesLimit: {
    type: Number,
    default: 1,
  },
  // 图片预览最小高度
  imgPreviewMinHeight: {
    type: Number,
    default: 44,
  },
  // 图片预览最大高度
  imgPreviewMaxHeight: {
    type: Number,
    default: 256,
  },
  // 图片预览固定高度
  imgPreviewFixedHeight: {
    type: Number,
    default: null,
  },
});

// 初始化
function handleInit() {
  // console.info('pond.value', pond.value)
  // console.info('FilePond', FilePond)
}

// 获取文件（确保一定返回JavaScript File对象的数组）
function getFiles() {
  const pondFiles = pond.value.getFiles()
  if (pondFiles) {
    const jsFiles = []
    for (let i = 0; i < pondFiles.length; i++) {
      jsFiles.push(pondFiles[i].file)
    }
    return jsFiles
  } else {
    return []
  }
}

// 获取指定索引的文件（JavaScript File对象）
function getFile(index) {
  const pondFile = pond.value.getFile(index)
  return pondFile ? pondFile.file : null;
}

// 添加文件
function addFile(source) {
  pond.value.addFile(source);
}

function addFiles(source) {
  pond.value.addFiles(source);
}

// 移除文件
function removeFile(index) {
  pond.value.removeFile(index);
}

// 移除所有文件
function removeFiles() {
  pond.value.removeFiles();
}

defineExpose({
  getFiles,
  getFile,
  addFile,
  addFiles,
  removeFile,
  removeFiles,
});

</script>

<!--
注意：如果需要网格布局，则需要在没有scoped的style标签里添加式样，示例如下：
<style>
.filepond--item {
  width: calc(20% - 0.5em);
}
</style>
-->
