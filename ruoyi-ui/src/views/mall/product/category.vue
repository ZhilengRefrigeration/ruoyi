<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label="">
        <el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽"></el-switch>
      </el-form-item>
      <el-form-item label="">
        <el-button type="danger" size="mini" @click="batchDelete" icon="el-icon-delete">批量删除</el-button>
        <el-button v-if="draggable" size="mini" @click="batchSave" icon="el-icon-check">批量保存</el-button>
      </el-form-item>
    </el-form>

    <el-input
      style="width: 400px;margin-bottom: 20px"
      clearable
      prefix-icon="el-icon-search"
      @input="rest"
      placeholder="输入关键字进行过滤"
      v-model="filterText">
    </el-input>

    <el-tree
      :filter-node-method="filterNode"
      :data="menus"
      :props="defaultProps"
      :expand-on-click-node="false"
      show-checkbox
      node-key="catId"
      :default-expanded-keys="expandedKey"
      :draggable="draggable"
      :allow-drop="allowDrop"
      @node-drop="handleDrop"
      ref="menuTree">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span style="margin-left: 40px">
              <el-button
                v-if="node.level <=2"
                type="text"
                size="mini"
                icon="el-icon-plus"
                @click="() => append(data)"
              >添加</el-button>
            <el-divider direction="vertical"></el-divider>
              <el-button type="text" icon="el-icon-edit" size="mini" @click="edit(data)">修改</el-button>
            <el-divider direction="vertical"></el-divider>
              <el-button
                v-if="node.childNodes.length===0"
                type="text"
                size="mini"
                icon="el-icon-delete"
                @click="() => remove(node, data)"
              >删除</el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="30%"
      :close-on-click-modal="false">
      <el-form :model="category" :rules="dataRule" ref="category">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="category.name" autocomplete="off" maxlength="50"></el-input>
        </el-form-item>
        <el-form-item label="菜单图标" prop="icon">
          <el-popover
            placement="bottom-start"
            width="460"
            trigger="click"
            @show="$refs['iconSelect'].reset()"
          >
            <IconSelect ref="iconSelect" @selected="selected"/>
            <el-input slot="reference" :value="category.icon" placeholder="点击选择图标" readonly>
              <svg-icon
                v-if="category.icon"
                slot="prefix"
                :icon-class="category.icon"
                class="el-input__icon"
                style="height: 32px;width: 16px;"
              />
              <i v-else slot="prefix" class="el-icon-search el-input__icon"/>
            </el-input>
          </el-popover>
        </el-form-item>
        <el-form-item label="计量单位" prop="productUnit">
          <el-input v-model="category.productUnit" autocomplete="off" maxlength="50"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
import {addCategory, batchSave, editCategory, getCategory, getMenus, removeMenus} from "@/api/mall/product/category";
import IconSelect from "@/components/IconSelect";

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {IconSelect},
  name: "Category",
  props: {},
  data() {
    return {
      pCid: [],
      draggable: false,
      updateNodes: [],
      maxLevel: 0,
      title: "",
      dialogType: "", //edit,add
      category: {
        name: "",
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        productUnit: "",
        icon: null,
        catId: null
      },
      dialogVisible: false,
      menus: [],
      expandedKey: [],
      defaultProps: {
        children: "children",
        label: "name"
      },

      dataRule: {
        name: [
          { required: true, message: "分类名称不能为空", trigger: "blur" },
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
        ],
        productUnit: [
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
        ],
      },

      filterText: '',
    };
  },

  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {
    filterText(val) {
      this.$refs.menuTree.filter(val);
    }
  },
  //方法集合
  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.name.indexOf(value) !== -1;
    },

    // 选择图标
    selected(name) {
      this.category.icon = name;
    },

    getMenus() {
      this.$modal.loading("请稍候...");
      getMenus().then(res => {
        this.$modal.closeLoading()
        this.menus = res.page;
      })
    },

    batchDelete() {
      let catIds = [];
      let checkedNodes = this.$refs.menuTree.getCheckedNodes();
      for (let i = 0; i < checkedNodes.length; i++) {
        catIds.push(checkedNodes[i].catId);
      }
      if (catIds.length===0) {
        this.$modal.notifyWarning("请选择删除内容")
        return
      }

      this.$confirm(`是否批量删除菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        removeMenus(catIds).then(res => {
          this.$modal.notifySuccess("删除成功")
          this.getMenus();
        })
      }).catch(() => {
      });
    },

    batchSave() {
      batchSave(this.updateNodes).then(res => {
        this.$modal.notifySuccess("菜单顺序等修改成功")
        this.getMenus();
        this.expandedKey = this.pCid;
        this.updateNodes = [];
        this.maxLevel = 0;
      })
    },

    handleDrop(draggingNode, dropNode, dropType, ev) {
      //1、当前节点最新的父节点id
      let pCid = 0;
      let siblings = null;
      if (dropType === "before" || dropType === "after") {
        pCid =
          dropNode.parent.data.catId === undefined
            ? 0
            : dropNode.parent.data.catId;
        siblings = dropNode.parent.childNodes;
      } else {
        pCid = dropNode.data.catId;
        siblings = dropNode.childNodes;
      }
      this.pCid.push(pCid);

      //2、当前拖拽节点的最新顺序，
      for (let i = 0; i < siblings.length; i++) {
        if (siblings[i].data.catId === draggingNode.data.catId) {
          //如果遍历的是当前正在拖拽的节点
          let catLevel = draggingNode.level;
          if (siblings[i].level !== draggingNode.level) {
            //当前节点的层级发生变化
            catLevel = siblings[i].level;
            //修改他子节点的层级
            this.updateChildNodeLevel(siblings[i]);
          }
          this.updateNodes.push({
            catId: siblings[i].data.catId,
            sort: i,
            parentCid: pCid,
            catLevel: catLevel
          });
        } else {
          this.updateNodes.push({catId: siblings[i].data.catId, sort: i});
        }
      }
    },

    updateChildNodeLevel(node) {
      if (node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          var cNode = node.childNodes[i].data;
          this.updateNodes.push({
            catId: cNode.catId,
            catLevel: node.childNodes[i].level
          });
          this.updateChildNodeLevel(node.childNodes[i]);
        }
      }
    },

    allowDrop(draggingNode, dropNode, type) {
      //1、被拖动的当前节点以及所在的父节点总层数不能大于3

      //1）、被拖动的当前节点总层数
      this.countNodeLevel(draggingNode);
      //当前正在拖动的节点+父节点所在的深度不大于3即可
      let deep = Math.abs(this.maxLevel - draggingNode.level) + 1;

      //   this.maxLevel
      if (type === "inner") {
        return deep + dropNode.level <= 3;
      } else {
        return deep + dropNode.parent.level <= 3;
      }
    },

    countNodeLevel(node) {
      //找到所有子节点，求出最大深度
      if (node.childNodes != null && node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          if (node.childNodes[i].level > this.maxLevel) {
            this.maxLevel = node.childNodes[i].level;
          }
          this.countNodeLevel(node.childNodes[i]);
        }
      }
    },

    //打开修改框数据显示
    edit(data) {
      this.dialogType = "edit";
      this.title = "修改分类";
      this.dialogVisible = true;

      //发送请求获取当前节点最新的数据
      getCategory(data).then(res => {
        this.category = res.data
      })
    },

    append(data) {
      this.dialogType = "add";
      this.title = "添加分类";
      this.dialogVisible = true;
      this.category.parentCid = data.catId;
      this.category.catLevel = data.catLevel * 1 + 1;
      this.category.catId = null;
      this.category.name = "";
      this.category.icon = "";
      this.category.productUnit = "";
      this.category.sort = 0;
      this.category.showStatus = 1;
    },

    submitData() {
      this.$refs["category"].validate(valid => {
        if (valid) {
          if (this.dialogType === "add") {
            this.addCategory();
          }
          if (this.dialogType === "edit") {
            this.editCategory();
          }
        }
      });
    },

    //修改三级分类数据
    editCategory() {
      editCategory(this.category).then(res => {
        this.$modal.notifySuccess("菜单修改成功")
        this.dialogVisible = false;
        this.getMenus();
        this.expandedKey = [this.category.parentCid];
      })
    },

    //添加三级分类
    addCategory() {
      addCategory(this.category).then(res => {
        this.$modal.notifySuccess("菜单保存成功")
        //关闭对话框
        this.dialogVisible = false;
        //刷新出新的菜单
        this.getMenus();
        //设置需要默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      })
    },

    //删除单个分类
    remove(node, data) {
      var ids = [data.catId];
      this.$confirm(`是否删除【${data.name}】菜单?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        removeMenus(ids).then(res => {
          this.$modal.notifySuccess("删除成功")
          this.getMenus();
          //设置需要默认展开的菜单
          this.expandedKey = [node.parent.data.catId];
        })
      }).catch(() => {
      });
    },

    //搜索框删除内容后重置
    rest() {
      if (!this.filterText) {
        this.getMenus()
      }
    },


  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.getMenus();
  },
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {
  },
  beforeCreate() {
  }, //生命周期 - 创建之前
  beforeMount() {
  }, //生命周期 - 挂载之前
  beforeUpdate() {
  }, //生命周期 - 更新之前
  updated() {
  }, //生命周期 - 更新之后
  beforeDestroy() {
  }, //生命周期 - 销毁之前
  destroyed() {
  }, //生命周期 - 销毁完成
  activated() {
  } //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>
