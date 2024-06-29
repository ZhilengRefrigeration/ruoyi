<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="比赛名称" prop="competitionName">
        <el-input
          v-model="queryParams.competitionName"
          placeholder="请输入比赛名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="球场名称" prop="buildingName">
        <el-input
          v-model="queryParams.buildingName"
          placeholder="请输入球场名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择比赛状态" clearable>
          <el-option
            v-for="dict in dict.type.competition_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="比赛地址" prop="competitionAddress">
        <el-input
          v-model="queryParams.competitionAddress"
          placeholder="请输入比赛地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人" prop="contacts">
        <el-input
          v-model="queryParams.contacts"
          placeholder="请输入赛事联系人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主办方" prop="organizer">
        <el-input
          v-model="queryParams.organizer"
          placeholder="请输入主办方"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="赞助商" prop="sponsor">
        <el-input
          v-model="queryParams.sponsor"
          placeholder="请输入赞助商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:competition:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:competition:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:competition:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:competition:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="competitionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="35" align="center" />
      <el-table-column label="id" align="center" prop="id" width="55" />
      <el-table-column label="赛会背景图" align="center" prop="competitionBackImg" width="150" >
        <template slot-scope="scope">
          <el-image
            style="width: 200px; height: 100px"
            :src="scope.row.competitionBackImg"
            :preview-src-list="[scope.row.competitionBackImg]"
            :fit="imgfit"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="比赛名称" align="center" prop="competitionName" width="200" show-overflow-tooltip/>
      <el-table-column label="比赛状态" align="center" prop="status" width="100">
        <template slot-scope="scope">
          <el-tag>
            <dict-tag :options="dict.type.competition_status" :value="scope.row.status"/>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="auditStatus" width="100" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.auditStatus==1" type='success' >通过</el-tag>
          <el-tag v-if="scope.row.auditStatus==0" type='info' >待审核</el-tag>
          <el-tag v-if="scope.row.auditStatus==-1" type='danger' >未通过</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="比赛赛制" align="center" prop="competitionType" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.competitionType==1">一人制</el-tag>
          <el-tag v-if="scope.row.competitionType==2">二人制</el-tag>
          <el-tag v-if="scope.row.competitionType==3">三人制</el-tag>
          <el-tag v-if="scope.row.competitionType==4">四人制</el-tag>
          <el-tag v-if="scope.row.competitionType==5">五人制</el-tag>
          <el-tag v-if="scope.row.competitionType==6">六人制</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="报名开始时间" align="center" prop="enrollBeginTime" width="110">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.enrollBeginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="报名结束时间" align="center" prop="enrollEndTime" width="110">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.enrollEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="赛事联系人" align="center" prop="contacts" width="100"/>
      <el-table-column label="联系人电话" align="center" prop="contactsTel" width="120"/>
      <el-table-column label="比赛开始时间" align="center" prop="competitionBeginTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.competitionBeginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="比赛结束时间" align="center" prop="competitionEndTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.competitionEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="比赛地址" align="center" prop="competitionAddress"  show-overflow-tooltip width="180"/>
      <el-table-column label="备注说明" align="center" prop="remark" show-overflow-tooltip  width="210"/>
      <el-table-column label="身高隐藏" align="center" prop="heightHide" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.heightHide==1" type='info' >隐藏</el-tag>
          <el-tag v-else type='success' >显示</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="主办方" align="center" prop="organizer" width="180" show-overflow-tooltip/>
      <el-table-column label="承办方" align="center" prop="undertake" width="180" show-overflow-tooltip/>
      <el-table-column label="赞助商" align="center" prop="sponsor" width="100" show-overflow-tooltip/>
      <el-table-column label="创建时间" align="center" prop="createdTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" fixed="right" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:competition:edit']"
          >修改</el-button>
<!--          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:competition:remove']"
          >删除</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-setting"
            @click="handleCompetitionSet(scope.row)">设置</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改比赛信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="650px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="比赛名称" prop="competitionName" >
          <el-input v-model="form.competitionName" />
        </el-form-item>
        <el-form-item label="比赛球场" prop="buildingName">
          <el-select v-model="form.buildingName" filterable @change="changeBuildName" remote reserve-keyword
            placeholder="请输入球场名称" :remote-method="remoteMethod" :loading="buildLoading">
            <el-option
              v-for="item in buildingList"
              :key="item.id"
              :label="item.buildingName"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="比赛地址" prop="competitionAddress">
          <el-input v-model="form.competitionAddress" disabled />
        </el-form-item>
        <el-form-item label="报名开始时间" prop="enrollBeginTime">
          <el-date-picker clearable
                          v-model="form.enrollBeginTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择报名开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="报名结束时间" prop="enrollEndTime">
          <el-date-picker clearable
                          v-model="form.enrollEndTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择报名结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="比赛开始时间" prop="competitionBeginTime">
          <el-date-picker clearable
                          v-model="form.competitionBeginTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择比赛开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="比赛结束时间" prop="competitionEndTime">
          <el-date-picker clearable
                          v-model="form.competitionEndTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择比赛结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="赛事联系人" prop="contacts">
          <el-input v-model="form.contacts" placeholder="请输入赛事联系人" />
        </el-form-item>
        <el-form-item label="赛事联系人电话" prop="contactsTel">
          <el-input v-model="form.contactsTel" placeholder="请输入赛事联系人电话" />
        </el-form-item>
        <el-form-item label="主办方" prop="organizer">
          <el-input v-model="form.organizer" placeholder="请输入主办方" />
        </el-form-item>
        <el-form-item label="承办方" prop="undertake">
          <el-input v-model="form.undertake" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="赞助商" prop="sponsor">
          <el-input v-model="form.sponsor" type="textarea" placeholder="请输入赞助商" />
        </el-form-item>
        <el-form-item label="赛会背景图" prop="competitionBackImg">
          <el-upload
            multiple
            class="avatar-uploader"
            action="https://mall.lzsport.cn/prod-api/system/file/uploadMore"
            :show-file-list="false"
            name="files"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="身高" prop="heightHide">
          <el-select v-model="form.heightHide" >
            <el-option label="隐藏" :value="1"/>
            <el-option label="显示" :value="0" />
          </el-select>
        </el-form-item>
        <el-form-item label="备注说明" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCompetition, getCompetition, delCompetition, addCompetition, updateCompetition } from "@/api/system/competition";
import {listWxBuilding} from "@/api/system/WxBuilding";

export default {
  name: "Competition",
  dicts: ['competition_status'],
  data() {
    return {
      imgfit:"fill",
      imageUrl:null,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],

      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 比赛信息表格数据
      competitionList: [],
      buildingList:[],
      // 弹出层标题
      title: "",
      buildLoading:false,
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mainTeamId: null,
        mainTeamName: null,
        guestTeamId: null,
        guestTeamName: null,
        competitionCode: null,
        competitionName: null,
        designated: null,
        competitionType: null,
        competitionTime: null,
        buildingId: null,
        buildingName: null,
        competitionAddress: null,
        founder: null,
        status: null,
        cityCode: null,
        cityName: null,
        maxPlayer: null,
        createdBy: null,
        modifiedBy: null,
        competitionNature: 1,
        enrollBeginTime: null,
        enrollEndTime: null,
        contacts: null,
        contactsAreaCode: null,
        contactsTel: null,
        competitionBeginTime: null,
        competitionEndTime: null,
        organizer: null,
        undertake: null,
        competitionBackImg: null,
        createdId: null,
        auditStatus: null,
        heightHide: null,
        sponsor: null,
        orderByColumn:"id",
        isAsc:"desc",
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询比赛信息列表 */
    getList() {
      this.loading = true;
      listCompetition(this.queryParams).then(response => {
        this.competitionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      let imgUrl = res.rows[0];
      this.form.competitionBackImg = "https://mall.lzsport.cn/image/"+imgUrl;
    },
    beforeAvatarUpload(file) {
      console.info(file.type)
      const isJPG = (file.type === 'image/jpeg'||file.type === 'image/png');
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG/PNG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    changeBuildName(val){
      let obj={}
      obj = this.buildingList.find(function(i){
        return i.id ===val
      });
      this.form.buildingName = obj.buildingName;
      this.form.buildingId = val;
      this.form.competitionAddress=obj.address ;
      console.info(this.form)
    },
    //远程查询球场信息
    remoteMethod(query) {
      this.buildLoading = true;
      let queryParam = {
        "pageNum": 1, "pageSize": 30,"status":2,"isDeleted":0,"buildingName":query
      }
      listWxBuilding(queryParam).then(response => {
        this.buildingList = response.rows;
        this.buildLoading = false;
      });
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        mainTeamId: null,
        mainTeamName: null,
        guestTeamId: null,
        guestTeamName: null,
        competitionCode: null,
        competitionName: null,
        designated: null,
        competitionType: null,
        competitionTime: null,
        buildingId: null,
        buildingName: null,
        competitionAddress: null,
        founder: null,
        status: 0,
        cityCode: null,
        cityName: null,
        maxPlayer: null,
        createdTime: null,
        lastUpdatedTime: null,
        createdBy: null,
        modifiedBy: null,
        isDeleted: null,
        longitude: null,
        latitude: null,
        remark: null,
        competitionNature: null,
        enrollBeginTime: null,
        enrollEndTime: null,
        contacts: null,
        contactsAreaCode: null,
        contactsTel: null,
        competitionBeginTime: null,
        competitionEndTime: null,
        organizer: null,
        undertake: null,
        competitionBackImg: null,
        createdId: null,
        auditStatus: 0,
        heightHide: null,
        sponsor: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加赛会信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCompetition(id).then(response => {
        this.form = response.data;
        this.imageUrl = this.form.competitionBackImg;
        this.open = true;
        this.title = "修改赛会信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCompetition(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompetition(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除比赛信息编号为"' + ids + '"的数据项？').then(function() {
        return delCompetition(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 分配角色操作 */
    handleCompetitionSet: function(row) {
      const id = row.id;
      this.$router.push("/system/competition/set/" + id);
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/competition/export', {
        ...this.queryParams
      }, `competition_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
