<template>
  <div>
    <el-select
        v-model="selectedValue"
        :placeholder="placeholder"
        :clearable="clearable"
        @change="handleChange"
    >
      <el-option
          v-for="dict in dataOptions"
          :key="dict.value"
          :label="dict.label"
          :value="dict.value"
      />
    </el-select>
  </div>
</template>

<script setup>
import { getDicts } from '@/api/system/dict/data'

const { proxy } = getCurrentInstance();
const emit = defineEmits();

const props = defineProps({
  // v-model
  modelValue: {
    type: [String, Number],
    required: false,
  },
  // 直接自定义选项
  options: {
    type: Array,
    default: () => [],
  },
  // 获取数据的方法
  fetchData: {
    type: Function,
    required: false,
  },
  // 字典名称
  dictName: {
    type: String,
    required: false
  },
  // 提示文本
  placeholder: {
    type: String,
    default: '',
  },
  // 是否能够清空选项
  clearable: {
    type: Boolean,
    default: true,
  },
});

const dataOptions = ref([]);
const selectedValue = ref(null);

// v-model双向绑定：更新父组件的值
function handleChange(value) {
  emit('update:modelValue', value);
}

// v-model双向绑定：更新子组件的值
watch(() => props.modelValue, (value) => {
  selectedValue.value = value;
});

// 获取数据
async function doFetchData() {
  if (props.options instanceof Array && props.options.length > 0) {
    // 如果是直接自定义选项，则直接使用
    dataOptions.value = props.options;
  } else if (props.fetchData) {
    // 如果是通过方法获取数据，则调用方法获取数据
    const dataList = await props.fetchData();
    dataList.map((item) => {
      dataOptions.value.push({
        label: item.label,
        value: item.value,
      });
    });
  } else if (props.dictName) {
    // 如果是字典类型，则从字典中获取数据
    const dicts = proxy.useDict(props.dictName);
    let list = dicts[props.dictName]._object[props.dictName];
    if (list.length > 0) {
      //已经缓存过字典数据
      list.map((item) => {
        dataOptions.value.push({
          label: item.label,
          value: item.value,
        });
      });
    } else {
      //没有缓存过字典数据
      const response = await getDicts(props.dictName);
      response.data.map((item) => {
        dataOptions.value.push({
          label: item.dictLabel,
          value: item.dictValue,
        });
      });
    }
  }
}
doFetchData()

</script>