<template>
  <el-dialog
    width="600px"
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    @closed="dialogClose"
  >
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="90px">
      <el-form-item label="属性名" prop="attrName">
        <el-input v-model="dataForm.attrName" placeholder="属性名"></el-input>
      </el-form-item>
      <el-form-item label="属性类型" prop="attrType">
        <el-select v-model="dataForm.attrType" placeholder="请选择">
          <el-option label="规格参数" :value="1"></el-option>
          <el-option label="销售属性" :value="0"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="值类型" prop="valueType">
        <el-switch
          v-model="dataForm.valueType"
          active-text="允许多个值"
          inactive-text="只能单个值"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :inactive-value="0"
          :active-value="1"
        ></el-switch>
      </el-form-item>
      <el-form-item label="可选值" prop="valueSelect">
        <el-select
          v-model="dataForm.valueSelect"
          multiple
          filterable
          allow-create
          placeholder="请输入内容"
        ></el-select>
      </el-form-item>

      <el-form-item label="属性图标" prop="icon">
        <el-popover
          placement="bottom-start"
          width="460"
          trigger="click"
          @show="$refs['iconSelect'].reset()"
        >
          <IconSelect ref="iconSelect" @selected="selected"/>
          <el-input slot="reference" :value="dataForm.icon" placeholder="属性图标" readonly>
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
      <el-form-item label="所属分组" prop="attrGroupId" v-if="type === 1">
        <el-select ref="groupSelect" v-model="dataForm.attrGroupId" placeholder="请选择">
          <el-option
            v-for="item in attrGroups"
            :key="item.attrGroupId"
            :label="item.attrGroupName"
            :value="item.attrGroupId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="可检索" prop="searchType" v-if="type === 1">
        <el-switch
          v-model="dataForm.searchType"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="1"
          :inactive-value="0"
        ></el-switch>
      </el-form-item>
      <el-form-item label="快速展示" prop="showDesc" v-if="type === 1">
        <el-switch
          v-model="dataForm.showDesc"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="1"
          :inactive-value="0"
        ></el-switch>
      </el-form-item>
      <el-form-item label="启用状态" prop="enable">
        <el-switch
          v-model="dataForm.enable"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="1"
          :inactive-value="0"
        ></el-switch>
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
import {getAttrGroupList} from "@/api/mall/product/attr-group";
import {addAttr, editAttr, getAttr} from "@/api/mall/product/attr";
import IconSelect from "@/components/IconSelect";

export default {
  name: "attr-add-or-update",

  data() {
    return {
      visible: false,
      dataForm: {
        attrName: "",
        searchType: 0,
        valueType: 1,
        icon: "",
        valueSelect: "",
        attrType: 1,
        enable: 1,
        catelogId: "",
        attrGroupId: "",
        showDesc: 0
      },
      catelogPath: [],
      attrGroups: [],
      dataRule: {
        attrName: [
          {required: true, message: "属性名不能为空", trigger: "blur"}
        ],
        searchType: [
          {required: true, message: "是否需要检索不能为空", trigger: "blur"}
        ],
        valueType: [
          {required: true, message: "值类型不能为空", trigger: "blur"}
        ],
        icon: [
          {required: true, message: "属性图标不能为空", trigger: "blur"}
        ],
        attrType: [
          {required: true, message: "属性类型不能为空", trigger: "blur"}
        ],
        enable: [
          {required: true, message: "启用状态不能为空", trigger: "blur"}
        ],
        catelogId: [
          {required: true, message: "需要选择正确的三级分类数据", trigger: "blur"}
        ],
        showDesc: [
          {required: true, message: "快速展示不能为空", trigger: "blur"}
        ]
      }
    };
  },
  props: {
    type: {
      type: Number,
      default: 1
    }
  },
  watch: {
    catelogPath(path) {
      //监听到路径变化需要查出这个三级分类的分组信息
      this.attrGroups = [];
      this.dataForm.attrGroupId = "";
      this.dataForm.catelogId = path[path.length - 1];
      if (path && path.length === 3) {
        let params = {
          page: 1,
          limit: 99999,
          catelogId: path[path.length - 1],
        }
        getAttrGroupList(params).then(res => {
          this.attrGroups = res.page.list
        })


      } else if (path.length === 0) {
        this.dataForm.catelogId = "";
      } else {
        this.$message.error("请选择正确的分类");
        this.dataForm.catelogId = "";
      }
    }
  },
  components: {CategoryCascader,IconSelect},
  methods: {
    init(id) {
      this.dataForm.attrId = id;
      this.dataForm.attrType = this.type;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.attrId) {
          getAttr(this.dataForm.attrId).then(res => {
            this.dataForm.attrName = res.attr.attrName;
            this.dataForm.searchType = res.attr.searchType;
            this.dataForm.valueType = res.attr.valueType;
            this.dataForm.icon = res.attr.icon;
            this.dataForm.valueSelect = res.attr.valueSelect.split(";");
            this.dataForm.attrType = res.attr.attrType;
            this.dataForm.enable = res.attr.enable;
            this.dataForm.catelogId = res.attr.catelogId;
            this.dataForm.showDesc = res.attr.showDesc;
            this.catelogPath = res.attr.catelogPath;
            this.$nextTick(() => {
              this.dataForm.attrGroupId = res.attr.attrGroupId;
            });
          })

        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          let data = this.dataForm
          data.valueSelect = this.dataForm.valueSelect.join(";")
          if (!this.dataForm.attrId) {
            addAttr(data).then(res => {
              this.$modal.notifySuccess("添加成功")
              this.visible = false;
              this.$emit("refreshDataList");
            })
          } else {
            editAttr(data).then(res => {
              this.$modal.notifySuccess("修改成功")
              this.visible = false;
              this.$emit("refreshDataList");
            })
          }
        }
      });
    },


    dialogClose() {
      this.catelogPath = [];
    },

    // 选择图标
    selected(name) {
      this.dataForm.icon = name;
    },
  }
};
</script>
