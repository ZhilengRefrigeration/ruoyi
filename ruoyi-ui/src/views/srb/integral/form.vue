<template>
  <div class="app-container">
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-form-item label-width="120px" label="借款额度" prop="borrowAmount">
        <el-input-number v-model="formData.borrowAmount" placeholder="借款额度" :min="0"></el-input-number>
      </el-form-item>
      <el-form-item label-width="120px" label="积分区间开始" prop="integralStart">
        <el-input-number v-model="formData.integralStart" placeholder="积分区间开始" :min="0"></el-input-number>
      </el-form-item>
      <el-form-item label-width="120px" label="积分区间结束" prop="integralEnd">
        <el-input-number v-model="formData.integralEnd" placeholder="积分区间结束" :min="0"></el-input-number>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary"
                   v-hasPermi="['srb:integralGrade:save']"
                   @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>

import {save, update, getById} from "@/api/srb/core/integral";

export default {
  name: 'IntegralForm',
  components: {},
  props: [],
  data() {
    return {
      formData: {
        borrowAmount: undefined,
        integralStart: undefined,
        integralEnd: undefined,
      },
      rules: {
        borrowAmount: [{
          required: true,
          message: '借款额度',
          trigger: 'blur'
        }],
        integralStart: [{
          required: true,
          message: '积分区间开始',
          trigger: 'blur'
        }],
        integralEnd: [{
          required: true,
          message: '积分区间结束',
          trigger: 'blur'
        }],
      },
    }
  },
  computed: {},
  watch: {},
  created() {
    //当路由存在id属性的时候回显表单，需要调用回显接口
    let id = this.$route.query.id;
    if (id) {
      this.getById(id);
    }

  },
  mounted() {
  },
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        let id = this.$route.query.id;
        if (id) {
          this.updateData();
        } else {
          this.saveData();
        }
        this.$router.push({path: '/srb/integral/integralList'})
      })
    },

    //根据id获取
    getById(id) {
      this.$modal.loading("请稍候...");
      getById(id).then(res => {
        this.$modal.closeLoading()
        this.formData = res.data
      })
    },

    //新增
    saveData() {
      save(this.formData).then(res => {
        this.$modal.notifySuccess("新增成功");
      })
    },

    //修改
    updateData() {
      update(this.formData).then(res => {
        this.$modal.notifySuccess("修改成功");
      })
    },


    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}

</script>
<style>
</style>
