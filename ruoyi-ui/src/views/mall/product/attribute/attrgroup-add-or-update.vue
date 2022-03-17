<template>
  <el-dialog
    width="600px"
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    @closed="dialogClose"
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="90px"
    >
      <el-form-item label="组名" prop="attrGroupName">
        <el-input v-model="dataForm.attrGroupName" placeholder="组名" ></el-input>
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input-number v-model.number="dataForm.sort" :min="1" :max="9999" label="排序"></el-input-number>
      </el-form-item>
      <el-form-item label="描述" prop="descript">
        <el-input type="textarea" :rows="2" v-model="dataForm.descript" placeholder="描述"></el-input>
      </el-form-item>

      <el-form-item label="组图标" prop="icon">
        <el-popover
          placement="bottom-start"
          width="460"
          trigger="click"
          @show="$refs['iconSelect'].reset()"
        >
          <IconSelect ref="iconSelect" @selected="selected"/>
          <el-input slot="reference" :value="dataForm.icon" placeholder="点击选择图标" readonly>
            <svg-icon
              v-if="dataForm.icon"
              slot="prefix"
              :icon-class="dataForm.icon"
              class="el-input__icon"
              style="height: 32px;width: 16px;"
            />
            <i v-else slot="prefix" class="el-icon-search el-input__icon"/>
          </el-input>
        </el-popover>
      </el-form-item>

      <el-form-item label="所属分类" prop="catelogId">
        <category-cascader :catelogPath.sync="catelogPath"></category-cascader>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import CategoryCascader from '../../../components/mall/category-cascader'

import {addAttrGroup, getAttrGroup,editAttrGroup} from "@/api/mall/product/attr-group";
import IconSelect from "@/components/IconSelect";

export default {
  name: "attrgroup-add-or-update",

  components: {CategoryCascader,IconSelect},

  data() {
    return {
      visible: false,
      catelogPath: [],

      dataForm: {
        attrGroupId: 0,
        attrGroupName: "",
        sort: "",
        descript: "",
        icon: "",
        catelogId: 0,
      },
      dataRule: {
        attrGroupName: [
          {required: true, message: "组名不能为空", trigger: "blur"},
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        sort: [{required: true, message: "排序不能为空", trigger: "blur"}],
        descript: [
          {required: true, message: "描述不能为空", trigger: "blur"},
          { min: 1, max: 255, message: '长度在 1 到 255 个字符', trigger: 'blur' }
        ],
        icon: [
          {required: true, message: "组图标不能为空", trigger: "blur"},
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
        ],
        catelogId: [
          {required: true, message: "所属分类id不能为空", trigger: "blur"}
        ]
      }
    };
  },

  methods: {
    dialogClose() {
      this.catelogPath = [];
    },

    // 选择图标
    selected(name) {
      this.dataForm.icon = name;
    },

    init(id) {
      this.dataForm.attrGroupId = id;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.attrGroupId) {
          getAttrGroup(this.dataForm.attrGroupId).then(res => {
            this.dataForm = res.attrGroup
            //查出catelogId的完整路径
            this.catelogPath = res.attrGroup.catelogPath;
          })
        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          let data = {
            attrGroupId: this.dataForm.attrGroupId || undefined,
            attrGroupName: this.dataForm.attrGroupName,
            sort: this.dataForm.sort,
            descript: this.dataForm.descript,
            icon: this.dataForm.icon,
            catelogId: this.catelogPath[this.catelogPath.length - 1]
          }
          if (!this.dataForm.attrGroupId) {
            addAttrGroup(data).then(res => {
              this.$modal.notifySuccess("添加成功");
              this.visible = false;
              this.$emit("refreshDataList");
            })
          } else {
            editAttrGroup(data).then(res => {
              this.$modal.notifySuccess("修改成功");
              this.visible = false;
              this.$emit("refreshDataList");
            })
          }
        }
      });
    }
  },

};
</script>
