<template>
  <div>
    <el-divider content-position="left" style="font-weight: bold">赛会设置
    </el-divider>
    <el-button type="primary" @click="close()" style="position: absolute;margin-left: 750px;margin-top: -40px" size="small">返回</el-button>
  <el-tabs type="border-card" tab-position="left" v-model="activeName" @tab-click="handleTagClick" style="height: 900px">
    <el-tab-pane name="competitionInfo"  label="赛会信息">
      <span slot="label"><i class="el-icon-document"></i>赛会信息</span>
      <el-descriptions style="margin-left: 20px" :column="2">
        <el-descriptions-item label="赛会名称">{{competitionObj.competitionName}}</el-descriptions-item>
        <el-descriptions-item label="赛会审核状态">
          <el-tag size="small" v-if="competitionObj.auditStatus==1" type='success' >通过</el-tag>
          <el-tag size="small" v-if="competitionObj.auditStatus==0" type='info' >待审核</el-tag>
          <el-tag size="small" v-if="competitionObj.auditStatus==-1" type='danger' >未通过</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="赛会状态">
            <el-tag size="small">
              <dict-tag  :options="dict.type.competition_status" :value="competitionObj.status"/>
            </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="比赛赛制">
          <el-tag size="small" v-if="competitionObj.competitionType==1">一人制</el-tag>
          <el-tag size="small" v-if="competitionObj.competitionType==2">二人制</el-tag>
          <el-tag size="small" v-if="competitionObj.competitionType==3">三人制</el-tag>
          <el-tag size="small" v-if="competitionObj.competitionType==4">四人制</el-tag>
          <el-tag size="small" v-if="competitionObj.competitionType==5">五人制</el-tag>
          <el-tag size="small" v-if="competitionObj.competitionType==6">六人制</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="身高隐藏">
          <el-tag size="small" v-if="competitionObj.heightHide==1" type='info' >隐藏</el-tag>
          <el-tag size="small" v-else type='success' >显示</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="报名起止时间">{{competitionObj.enrollBeginTime}}-{{competitionObj.enrollEndTime}}</el-descriptions-item>
        <el-descriptions-item label="比赛起止时间">{{competitionObj.competitionBeginTime}}-{{competitionObj.competitionEndTime}}</el-descriptions-item>
        <el-descriptions-item label="比赛主办方">{{competitionObj.organizer}}</el-descriptions-item>
        <el-descriptions-item label="比赛承办商">{{competitionObj.undertake}}</el-descriptions-item>
        <el-descriptions-item label="赛事联系人">{{competitionObj.contacts}}</el-descriptions-item>
        <el-descriptions-item label="联系人电话">{{competitionObj.contactsTel}}</el-descriptions-item>
        <el-descriptions-item label="赛会地址" >
          {{competitionObj.competitionAddress}}
        </el-descriptions-item>
        <el-descriptions-item label="赛会图片">
          <el-image fit="contain"
            style="width: 400px; height: 400px"
            :src="competitionObj.competitionBackImg"
            :preview-src-list="[competitionObj.competitionBackImg]">
          </el-image>
        </el-descriptions-item>
        <el-descriptions-item label="比赛说明" style="width: 150px;overflow:hidden;">
          <span > {{competitionObj.remark}}</span>
        </el-descriptions-item>
      </el-descriptions>

    </el-tab-pane>
    <el-tab-pane label="球队审核" name="competitionTeamApprove"><span slot="label"><i class="el-icon-s-check"></i> 球队审核</span>
      <el-table
        max-height="800"
        v-loading="loading" :data="competitionOfTeamList" @selection-change="handleSelectionChange">
        <el-table-column label="球队ID" align="center" prop="teamId" />
        <el-table-column label="球队logo" align="center" prop="avatar" >
          <template slot-scope="scope">
            <el-avatar :src="scope.row.teamLogo"></el-avatar>
          </template>
        </el-table-column>
        <el-table-column label="球队名" align="center" prop="teamName" />
        <el-table-column label="球队所属的组" align="center" prop="competitionGroup" />
        <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status" >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status==0">申请</el-tag>
            <el-tag v-if="scope.row.status==1">同意</el-tag>
            <el-tag v-if="scope.row.status==-1">驳回</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="联系人" align="center" prop="contacts" />
        <el-table-column label="联系人电话" align="center" prop="contactsTel" />
        <el-table-column label="组内的序号" align="center" prop="serialNumber" />
        <el-table-column label="备注说明" align="center" prop="remark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-popconfirm  v-if="competitionObj.status==0" @confirm="bindConfirm(scope.row.id,1)"
              title="你确定同意此球队加入赛会吗？"
            >
              <el-button
                slot="reference"
                size="mini"
                type="text"
                icon="el-icon-success"
                v-hasPermi="['system:competitionOfTeam:edit']"
              >同意</el-button>
            </el-popconfirm>
            <el-popconfirm  v-if="competitionObj.status==0" @confirm="bindConfirm(scope.row.id,-1)"
              title="你确定不同意此球队加入赛会吗？"
            >
            <el-button
              slot="reference"
              size="mini"
              type="text"
              icon="el-icon-info"
              v-hasPermi="['system:competitionOfTeam:remove']"
            >驳回</el-button>
            </el-popconfirm>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-s-custom"
              @click="handleTeamUser(scope.row)"
              v-hasPermi="['system:competitionOfTeam:list']"
            >球队成员</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-tab-pane>
    <el-tab-pane label="球队分组" name="competitionTeamGroup"> <span slot="label"><i class="el-icon-film"></i> 球队分组</span>
      <el-container style="height: 800px; border: 1px solid #eee">
        <el-aside width="300px" style="background-color: rgb(238, 241, 246)">
          <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
              <el-button
                type="primary"
                plain
                icon="el-icon-plus"
                @click="handleAddGroup"
                v-hasPermi="['system:competition:add']"
              >新增分组</el-button>
            </el-col>
          </el-row>
          <el-table ref="singleTable" :data="competitionTeamGroupList"
            highlight-current-row
            @current-change="handleCurrentChange" style="width: 100%">
            <el-table-column property="competitionGroup" label="分组名称">
              <template slot-scope="scope">
                <el-tag>{{scope.row.competitionGroup}}组</el-tag>
              </template>
            </el-table-column>
            <el-table-column property="remark" label="操作">
              <template slot-scope="scope">
                <el-button type="primary" v-if="scope.row.id" @click="delGroup(scope.row)"  plain icon="el-icon-delete">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-aside>
        <el-container>
          <el-header style="text-align: left; font-size: 25px;font-weight: bold;color: #ae192a">
            <span>分组球队-{{currentGroupRow.competitionGroup}}组</span>
            <el-button v-if="currentGroupRow.competitionGroup&&currentGroupRow.id" type="primary" @click="addTeamDialog(currentGroupRow)" style="margin-left: 150px">新增球队</el-button>
          </el-header>
          <el-main>
            <el-table :data="alreadyGroupTeamList">
              <el-table-column label="球队ID" align="center" prop="teamId" />
              <el-table-column label="球队logo" align="center" prop="avatar" >
                <template slot-scope="scope">
                  <el-avatar :src="scope.row.teamLogo"></el-avatar>
                </template>
              </el-table-column>
              <el-table-column label="球队名" align="center" prop="teamName" />
              <el-table-column label="球队所属的组" align="center" prop="competitionGroup" />
              <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="联系人" align="center" prop="contacts" />
              <el-table-column label="联系人电话" align="center" prop="contactsTel" />
              <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                  <el-button v-if="scope.row.competitionGroup" @click="clickDelTeamGroup(scope.row)" type="primary" icon="el-icon-delete" circle></el-button>
                </template>
            </el-table-column>
            </el-table>
          </el-main>
        </el-container>
      </el-container>
    </el-tab-pane>
    <el-tab-pane label="赛程设置" name="competitionVsSet"> <span slot="label"><i class="el-icon-c-scale-to-original"></i> 赛程设置</span>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-time"
            @click="handleTeamVsTeamAdd"
            v-hasPermi="['system:competitionTeamVsTeam:add']"
          >手动设置赛程</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-bangzhu"
            @click="handleMindTeamVsTeam"
            v-hasPermi="['system:competitionTeamVsTeam:add']"
          >系统智能设置小组循环赛赛程</el-button>
        </el-col>
      </el-row>
      <el-table
        title="赛程列表"
        :data="competitionTeamVsTeamList"
        :span-method="(...arg)=>objectSpanMethod(...arg,competitionTeamVsTeamList)"
        border
        max-height="800"
        style=" margin-top: 20px">
        <el-table-column label="比赛日期" align="center" prop="competitionDate" width="180"/>
        <el-table-column label="比赛时间" align="center" prop="competitionTime" width="180">
          <template slot-scope="scope">
            <el-tag type="danger" style="font-weight: bold;font-size: larger" >{{ parseTime(scope.row.competitionTime, '{h}:{i}') }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="主队名" align="center" prop="mainTeamName" />
        <el-table-column label="主队得分" align="center" prop="mainTeamScore" >
          <template slot-scope="scope">
            <el-tag type="success" style="font-weight: bold;font-size: larger" >{{ scope.row.mainTeamScore }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="客队得分" align="center" prop="guestTeamScore" >
          <template slot-scope="scope">
            <el-tag type="success" style="font-weight: bold;font-size: larger" >{{ scope.row.guestTeamScore }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="客队名" align="center" prop="guestTeamName" />
        <el-table-column label="球场名称" align="center" prop="buildingName" />
        <el-table-column label="比赛类型" align="center" prop="vsType" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit-outline"
              @click="handleTeamVsTeamRecord(scope.row)"
              v-hasPermi="['system:competitionOfTeam:edit']"
            >比赛记录</el-button>
            <el-button
              v-if="new Date(scope.row.competitionDate).getTime() > new Date().getTime()"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleTeamVsTeamEdit(scope.row)"
              v-hasPermi="['system:competitionTeamVsTeam:edit']"
            >编辑赛程</el-button>
            <el-button
              v-if="new Date(scope.row.competitionDate).getTime() > new Date().getTime()"
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleTeamVsTeamDel(scope.row)"
              v-hasPermi="['system:competitionOfTeam:del']"
            >删除赛程</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-tab-pane>
    <el-tab-pane label="赛会推广" name="competitionSpread"> <span slot="label"><i class="el-icon-s-promotion"></i> 赛会推广</span> 赛会推广</el-tab-pane>
  </el-tabs>

    <el-drawer
      title="球队成员"
      :visible.sync="drawer"
      direction="ltr"
      size="60%">
      <el-table :data="competitionMembersList" @selection-change="handleSelectionChange">
        <el-table-column label="个人照片" align="center" prop="personalPhoto" >
          <template slot-scope="scope">
            <el-image
              style=" height: 100px"
              :src="scope.row.personalPhoto"
              :preview-src-list="[scope.row.personalPhoto]"
              fit="contain"></el-image>
          </template>
        </el-table-column>
        <el-table-column label="真实姓名" align="center" prop="realName" />
        <el-table-column label="球衣号" align="center" prop="jerseyNumber" />
        <el-table-column label="证件类型" align="center" prop="idType" />
        <el-table-column label="证件号码" align="center" prop="idCardNo" />
        <el-table-column label="联系电话" align="center" prop="contactsTel" />
<!--        <el-table-column label="申请时间" align="center" prop="createdTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>-->
<!--        <el-table-column label="比赛得分" align="center" prop="score" />
        <el-table-column label="总罚球" align="center" prop="penalty" />
        <el-table-column label="2分球" align="center" prop="twoPoints" />
        <el-table-column label="3分球" align="center" prop="threePoints" />
        <el-table-column label="总犯规" align="center" prop="breaks" />
        <el-table-column label="总篮板球" align="center" prop="rebound" />
        <el-table-column label="总盖帽" align="center" prop="block" />-->
        <el-table-column label="状态" align="center" prop="status" >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status==0">报名加入中</el-tag>
            <el-tag v-if="scope.row.status==1">已加入</el-tag>
            <el-tag v-if="scope.row.status==-1">不同意加入</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="人员类型" align="center" prop="userType" >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.userType==0">球员</el-tag>
            <el-tag v-if="scope.row.userType==1">队长</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>

    <el-dialog
      title="新增分组"
      :visible.sync="addGroupDialogVisible"
      width="30%"
      center>
      <el-select v-model="addGroupCode" placeholder="请选择">
        <el-option
          v-for="item in groupNumbers"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>组
      <span slot="footer" class="dialog-footer">
        <el-button @click="addGroupDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addGroupIsOk">确 定</el-button>
      </span>
    </el-dialog>


    <el-dialog
      title="选择球队添加到分组"
      :visible.sync="addTeamDialogVisible"
      width="30%"
      center>
      <el-table
        ref="multipleTable"
        :data="selectTeamList"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionTeamChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column label="球队名" align="center" prop="teamName" />
        <el-table-column label="球队所属的组" align="center" prop="competitionGroup" />
        <el-table-column label="联系人" align="center" prop="contacts" />
        <el-table-column label="联系人电话" align="center" prop="contactsTel" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addTeamDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="selectTeamIsOk">确 定</el-button>
      </span>
    </el-dialog>
    <!--赛程新增或者编辑-->
    <el-dialog :title="vsTitle" :visible.sync="vsOpen" width="650px" append-to-body>
      <el-form ref="vsform" :model="vsform" :rules="vsRules" label-width="80px">
        <el-form-item  prop="id" hidden>
          <el-input  v-model="vsform.id" />
        </el-form-item>
        <el-form-item label="比赛时间" prop="competitionTime">
          <el-date-picker clearable
                          v-model="vsform.competitionTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择比赛时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="比赛类型" prop="vsType">
          <el-radio-group v-model="vsform.vsType">
            <el-radio label="循环赛"></el-radio>
            <el-radio label="淘汰赛"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="主队名" prop="mainTeamName">
          <el-select v-model="vsform.mainTeamName" filterable clearable @change="changeMainTeamName" placeholder="请选择主队名">
            <el-option
              v-for="item in competitionOfTeamList"
              :value="item.teamId"
              :key="item.teamId"
              :label="item.teamName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="客队名" prop="guestTeamName">
          <el-select v-model="vsform.guestTeamName" filterable clearable  @change="changeGuestTeamName" placeholder="请选择客队名">
            <el-option
              v-for="item in competitionOfTeamList"
              :value="item.teamId"
              :key="item.teamId"
              :label="item.teamName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="球场名称" prop="buildingName">
          <el-select
            v-model="vsform.buildingName"
            filterable
            @change="changeBuildName"
            remote
            reserve-keyword
            placeholder="请输入关键词"
            :remote-method="remoteMethod"
            :loading="buildLoading">
            <el-option
              v-for="item in buildingList"
              :key="item.id"
              :label="item.buildingName"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="比赛地址" prop="competitionAddress">
          <el-input  v-model="vsform.competitionAddress" disabled />
        </el-form-item>
        <el-form-item label="备注说明" prop="remark">
          <el-input v-model="vsform.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitTeamVsTeamForm">确 定</el-button>
        <el-button @click="vsOpen=false">取 消</el-button>
      </div>
    </el-dialog>

    <!--赛程比赛数据记录-->
    <el-dialog :title="vsRecordTitle" :visible.sync="vsRecordOpen" width="750px" append-to-body>
      <el-row>
        <el-col :span="24"><div class="grid-content bg-purple-dark">{{ competitionUnifiedRecord.teamVsTeamVo.competitionTime }}</div></el-col>
      </el-row>
      <el-row>
        <el-col :span="8"><div class="grid-content bg-purple">{{competitionUnifiedRecord.teamVsTeamVo.mainTeamName}}</div></el-col>
        <el-col :span="8"><div class="grid-content bg-purple-light">{{competitionUnifiedRecord.teamVsTeamVo.mainTeamScore}} :
          {{competitionUnifiedRecord.teamVsTeamVo.guestTeamScore}}
        </div></el-col>
        <el-col :span="8"><div class="grid-content bg-purple">{{competitionUnifiedRecord.teamVsTeamVo.guestTeamName}}</div></el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { listCompetition, getCompetition, delCompetition, addCompetition, updateCompetition } from "@/api/system/competition";
import { listCompetitionOfTeam, batchEditById, intoTeamGroup, removeTeamGroup, updateCompetitionOfTeam } from "@/api/system/competitionOfTeam";
import { listCompetitionMembers, getCompetitionMembers, delCompetitionMembers, addCompetitionMembers, updateCompetitionMembers } from "@/api/system/competitionMembers";
import { listCompetitionTeamGroup, arrangeTeamGroupSchedule, delCompetitionTeamGroup, addCompetitionTeamGroup, updateCompetitionTeamGroup } from "@/api/system/competitionTeamGroup";
import { listCompetitionTeamVsTeam, getCompetitionUnifiedRecord, delCompetitionTeamVsTeam, addCompetitionTeamVsTeam, updateCompetitionTeamVsTeam } from "@/api/system/competitionTeamVsTeam";
import { listWxBuilding, getWxBuilding, delWxBuilding, addWxBuilding, updateWxBuilding } from "@/api/system/WxBuilding";

export default {
  name: "CompetitionSet",
  dicts: ['competition_status'],
  data() {
    return {

      imgfit:"fill",
      drawer:false,
      activeName:"competitionInfo",
      // 遮罩层
      loading: true,
      // 比赛信息表格数据
      currentGroupRow:{},
      competitionObj: {},
      competitionOfTeamList:[],
      competitionMembersList:[],
      competitionTeamGroupList:[],
      //已经分组的球队数据
      alreadyGroupTeamList: [],
      selectTeamList:[],
      competitionTeamVsTeamList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      groupNumbers:[],
      addGroupCode:"",
      addGroupDialogVisible:false,
      addTeamDialogVisible:false,
      teamMultipleSelection:[],
      // 查询参数
      vsform:{},
      vsTitle:"",
      vsRules: {
        competitionTime: [
          { required: true, message: "比赛时间不能为空", trigger: "blur" }
        ],
        mainTeamName: [
          { required: true, message: "主队名不能为空", trigger: "blur" }
        ],
        guestTeamName: [
          { required: true, message: "客队名不能为空", trigger: "blur" }
        ],
        buildingName: [
          { required: true, message: "比赛球场名称不能为空", trigger: "blur" }
        ],
        vsType:[
          { required: true, message: "比赛类型不能为空", trigger: "blur" }
        ]
      },
      vsOpen:false,
      buildingList: [],
      buildLoading:false,
      vsRecordTitle:"",
      vsRecordOpen:false,
      competitionUnifiedRecord:{
        teamVsTeamVo:{
          competitionTime:null,
          mainTeamName:null,
          guestTeamName: null,
          mainTeamScore:null,
          guestTeamScore:null
        },
      }
    };
  },
  created() {
    const id = this.$route.params && this.$route.params.id;
    if (id) {
      this.loading = true;
      getCompetition(id).then((response) => {
        this.competitionObj = response.data;
        this.loading = false;
      });
      listCompetitionOfTeam({"pageNum": 1, "pageSize": 1000,"competitionId":id}).then(response => {
        this.competitionOfTeamList = response.rows;
      });
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    },
    //点击新增分组按钮
    handleAddGroup(){
      //循环获取0-25的组的数据值
      for (let i = 0; i < 26; i++) {
        let groupList = {"value":String.fromCharCode(65+i),"label":String.fromCharCode(65+i)}
        this.groupNumbers[i] = groupList;
      }
      this.addGroupDialogVisible=true;
    },
    //新增分组保存
    addGroupIsOk(){
      if(this.addGroupCode) {
        addCompetitionTeamGroup({
          "status": 1,
          "competitionId": this.competitionObj.id,
          "competitionGroup": this.addGroupCode
        }).then(response => {
          this.$message({
            message: '恭喜你，新增分组成功',
            type: 'success'
          });
          this.addGroupDialogVisible=false;
          listCompetitionTeamGroup({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
            this.competitionTeamGroupList = response.rows;
            this.competitionTeamGroupList.push({"competitionGroup":"未分","id":null})
          });
        });
      }else {
        this.$message({
          showClose: true,
          message: '请选择分组值',
          type: 'warning'
        });
      }
    },
    //球队移除分组
    clickDelTeamGroup(row){
      let competitionObj = this.competitionObj;
      let that = this;
      that.$modal.confirm('是否确认从分组中移除球队为"' + row.teamName + '"的数据？').then(function() {
        removeTeamGroup(row.id,{}).then(response => {
          listCompetitionOfTeam({"pageNum": 1, "pageSize": 1000,"competitionId":competitionObj.id,"competitionGroup":row.competitionGroup})
            .then(response => {
              that.alreadyGroupTeamList = response.rows;
            });
          that.$message({
            message: '恭喜你，球队移除成功',
            type: 'success'
          });
        });
      })
    },
    //删除分组
    delGroup(row){
      console.info(row)
      listCompetitionOfTeam({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id,"competitionGroup":row.competitionGroup}).then(response => {
        if(response.rows.length>0){
          this.$message({
            showClose: true,
            message: '当前分组下已有球队数据，请删除分组下的球队数据后再做删除分组操作',
            type: 'warning'
          });
        }else {
          const id = row.id;
          this.$modal.confirm('是否确认删除赛会中分组数据为"' + row.competitionGroup + '"的数据？').then(function() {
            return delCompetitionTeamGroup(id);
          }).then(() => {
            listCompetitionTeamGroup({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
              this.competitionTeamGroupList = response.rows;
              this.competitionTeamGroupList.push({"competitionGroup":"未分","id":null})
            });
            this.$modal.msgSuccess("删除成功");
          }).catch(() => {});
        }
      });
    },
    addTeamDialog(row){
      console.info(row)
      this.addTeamDialogVisible = true
      listCompetitionOfTeam({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
        let newArr = response.rows.filter(item => !item.competitionGroup);
        this.selectTeamList = newArr;
      });
    },
    handleSelectionTeamChange(val){
      //console.info(this.currentGroupRow)
      let list = [];
      for (let i = 0;i<val.length;i++){
        let team = val[i];
        let selectedTeam = {
          "id": team.id,
          "competitionGroup": this.currentGroupRow.competitionGroup
        };
        list.push(selectedTeam);
      }
      this.teamMultipleSelection=list;
      //console.info(this.teamMultipleSelection)
      //this.multipleSelection = val;
    },
    //保存选择的分组数据
    selectTeamIsOk(){
      if(this.teamMultipleSelection.length==0){
        this.$message({
          showClose: true,
          message: '请选择球队',
          type: 'warning'
        });
      }else {
        this.addTeamDialogVisible = false;
        batchEditById(this.teamMultipleSelection).then(response => {
          this.$message({
            message: '恭喜你，成功新增到分组',
            type: 'success'
          });
          listCompetitionOfTeam({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id,"competitionGroup":this.currentGroupRow.competitionGroup}).then(response => {
            this.alreadyGroupTeamList = response.rows;
          });
        });
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    bindConfirm(id,tage){
      console.info(id)
      console.info(tage)
      updateCompetitionOfTeam({"id":id,"status":tage}).then(response => {

      });
    },
    handleTeamUser(row){
      this.drawer = true
      listCompetitionMembers({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id,"competitionTeamId":row.teamId}).then(response => {
         this.competitionMembersList =  response.rows;
      });
    },
    // 表单重置
    reset() {

    },
    objectSpanMethod({ row, column, rowIndex, columnIndex },data) {
      if (columnIndex === 0) {
        if(column.property==='competitionDate') {
          var spanArr = this.getSpanArr(data, column.property)
          const _row = spanArr[rowIndex]
          const _col = _row > 0 ? 1 : 0
          return {
            rowspan: _row,
            colspan: _col
          }
        }
    }
    },
    // 处理合并行的数据
    getSpanArr: function(data, spanKey) {
      var that = this
      var spanArr = []
      var pos = ''
      for (var i = 0; i < data.length; i++) {
        if (i === 0) {
          spanArr.push(1)
          pos = 0
        } else {
          // 判断当前元素与上一个元素是否相同
          if (data[i][spanKey] === data[i - 1][spanKey]) {
            spanArr[pos] += 1
            spanArr.push(0)
          } else {
            spanArr.push(1)
            pos = i
          }
        }
      }
      return spanArr
    },
    handleCurrentChange(val) {
      this.currentGroupRow = val;
      console.info(val)
      let competitionGroupVal = null;
      let isGroup = false;
      if(val.id!=null){
        competitionGroupVal = val.competitionGroup;
        isGroup = true;
      }
      listCompetitionOfTeam({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id,"competitionGroup":competitionGroupVal}).then(response => {
        if(competitionGroupVal){
          this.alreadyGroupTeamList = response.rows;
        }else {
          let newArr = response.rows.filter(item => !item.competitionGroup);
          this.alreadyGroupTeamList = newArr;
        }
      });
    },
    handleTagClick(tab, event){
      console.info(tab.name)
      if(tab.name=='competitionTeamApprove'){
        listCompetitionOfTeam({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionOfTeamList = response.rows;
        });
      }else if(tab.name=='competitionTeamGroup'){
        listCompetitionTeamGroup({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionTeamGroupList = response.rows;
          this.competitionTeamGroupList.push({"competitionGroup":"未分","id":null})
        });
      }else if(tab.name=='competitionVsSet'){
        listCompetitionTeamVsTeam({"orderByColumn":"competition_time","pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionTeamVsTeamList = response.rows;
        });
      }else if(tab.name=='competitionSpread'){

      }
    },
    /** 关闭按钮 */
    close() {
      const obj = { path: "/wechat/competition" };
      this.$tab.closeOpenPage(obj);
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
      this.title = "添加比赛信息";
    },
    handleTeamVsTeamAdd(){
      this.vsform = { };
      this.vsOpen=true;
      this.vsTitle = "新增赛程"
    },
    handleMindTeamVsTeam(){
      /*arrangeTeamGroupSchedule({})*/
    },
    changeMainTeamName(val){
      let obj={}
      obj = this.competitionOfTeamList.find(function(i){
        return i.teamId ===val
      });
      this.vsform.mainTeamName = obj.teamName;
      this.vsform.mainTeamId = val;
      //在change中获取到整条对象数据
      console.info(this.vsform)
    },
    changeGuestTeamName(val){
      let obj={}
      obj = this.competitionOfTeamList.find(function(i){
        return i.teamId ===val
      });
      this.vsform.guestTeamName = obj.teamName;
      this.vsform.guestTeamId = val;
      console.info(this.vsform)
    },
    changeBuildName(val){
      let obj={}
      obj = this.buildingList.find(function(i){
        return i.id ===val
      });
      this.vsform.buildingName = obj.buildingName;
      this.vsform.buildingId = val;
      this.vsform.competitionAddress=obj.address ;
      console.info(this.vsform)
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
    handleTeamVsTeamEdit(row){
      this.vsform = row;
      this.vsOpen=true;
      this.vsTitle = "编辑赛程"
    },
    handleTeamVsTeamRecord(row){
      this.vsRecordOpen=true;
      this.vsRecordTitle = "比赛数据记录";
      //获取比赛数据
      getCompetitionUnifiedRecord(row.id).then(response=>{
        this.competitionUnifiedRecord = response.data
      })
    },
    handleTeamVsTeamDel(row){
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除赛会中的赛程数据？').then(function() {
        return delCompetitionTeamVsTeam(ids);
      }).then(() => {
        listCompetitionTeamVsTeam({"orderByColumn":"competition_time","pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionTeamVsTeamList = response.rows;
        });
        this.$modal.msgSuccess("删除赛程成功");
      }).catch(() => {});
    },
    /** 提交按钮 */
    submitTeamVsTeamForm() {
      this.$refs["vsform"].validate(valid => {
        if (valid) {
          console.info(this.vsform)
          if (this.vsform.id != null) {
            updateCompetitionTeamVsTeam(this.vsform).then(response => {
              this.$modal.msgSuccess("编辑赛程成功");
              this.vsOpen = false;
              listCompetitionTeamVsTeam({"orderByColumn":"competition_time","pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
                this.competitionTeamVsTeamList = response.rows;
              });
            });
          } else {
            this.vsform.competitionId = this.competitionObj.id;
            addCompetitionTeamVsTeam(this.vsform).then(response => {
              this.$modal.msgSuccess("新增赛程成功");
              this.vsOpen = false;
              listCompetitionTeamVsTeam({"orderByColumn":"competition_time","pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
                this.competitionTeamVsTeamList = response.rows;
              });
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
.el-header {
  background-color: #bdc8c6;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 275px;
}
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
