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
        <el-descriptions-item label="报名起止时间">{{competitionObj.enrollBeginTime}} 至 {{competitionObj.enrollEndTime}}</el-descriptions-item>
        <el-descriptions-item label="比赛起止时间">{{competitionObj.competitionBeginTime}} 至 {{competitionObj.competitionEndTime}}</el-descriptions-item>
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
      <div style="margin-bottom: 5px;">
        <el-button size="small" type="success" @click="addOfTeam">
        新增球队
        </el-button>
      </div>
      <el-table
        max-height="800"
        v-loading="loading" :data="competitionOfTeamList" @selection-change="handleSelectionChange">
<!--        <el-table-column label="球队ID" align="center" prop="teamId" width="80"/>-->
        <el-table-column label="球队logo" align="center" prop="avatar" >
          <template slot-scope="scope">
            <el-avatar :src="scope.row.teamLogo"></el-avatar>
          </template>
        </el-table-column>
        <el-table-column label="球队名" align="center" prop="teamName" />
        <el-table-column label="球队所属的组" align="center" prop="competitionGroup" />
        <el-table-column label="隐藏球员头像" align="center" prop="isHideAvatar">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.isHideAvatar"
              @change="changeSwitch($event,scope.row)"
            ></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status" >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status===0" style="color: #0656fa;">申请中</el-tag>
            <el-tag v-if="scope.row.status===1"  style="color: #04fa08">已同意</el-tag>
            <el-tag v-if="scope.row.status===-1"  style="color: #bfc2c5">已驳回</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="队长" align="center" prop="captain" />
        <el-table-column label="领队人" align="center" prop="contacts" />
        <el-table-column label="领队人电话" align="center" prop="contactsTel" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleEditOfTeam(scope.row)" v-hasPermi="['system:competitionOfTeam:edit']">编辑</el-button>
            <el-popconfirm  v-if="scope.row.status===0" @confirm="bindConfirm(scope.row.id,1)" title="你确定同意此球队加入赛会吗？">
              <el-button slot="reference" size="mini" type="text" icon="el-icon-success" v-hasPermi="['system:competitionOfTeam:edit']">同意</el-button>
            </el-popconfirm>
            <el-popconfirm  v-if="scope.row.status===0" @confirm="bindConfirm(scope.row.id,-1)" title="你确定不同意此球队加入赛会吗？">
            <el-button slot="reference" size="mini" type="text" icon="el-icon-info" v-hasPermi="['system:competitionOfTeam:remove']">驳回</el-button>
            </el-popconfirm>
            <el-button size="mini" type="text" icon="el-icon-s-custom" @click="handleTeamUser(scope.row)" v-hasPermi="['system:competitionOfTeam:list']">球队成员</el-button>
            <el-popconfirm  v-if="scope.row.status===0" @confirm="bindDelOfTeamConfirm(scope.row.id,1)" title="你确定要删除此球队吗？">
              <el-button slot="reference" size="mini" type="text" icon="el-icon-delete" v-hasPermi="['system:competitionOfTeam:remove']">删除</el-button>
            </el-popconfirm>
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
        <el-table-column label="比赛日期" align="center" prop="competitionDate" width="180">
          <template slot-scope="scope">
            <el-tag type="danger" style="font-weight: bold;font-size: larger" >{{ scope.row.competitionDate }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="比赛时间" align="center" prop="competitionTime" width="100">
          <template slot-scope="scope">
            <el-tag type="danger" style="font-weight: bold;font-size: larger" >{{ parseTime(scope.row.competitionTime, '{h}:{i}') }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="比赛类型" align="center" prop="vsType" width="100">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.vsType == 0" style="font-weight: bold;font-size: smaller" >循环赛</el-tag>
            <el-tag type="success" v-if="scope.row.vsType == 1" style="font-weight: bold;font-size: smaller" >淘汰赛</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="比赛状态"  align="center" prop="status" width="100">
          <template slot-scope="scope">
            <el-tag>
              <dict-tag :options="dict.type.vs_status" :value="scope.row.status"/>
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="主队名" align="center" prop="mainTeamName" />
        <el-table-column label="主队得分" align="center" prop="mainTeamScore" width="100">
          <template slot-scope="scope">
            <el-tag type="success" style="font-weight: bold;font-size: larger" >{{ scope.row.mainTeamScore }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="客队得分" align="center" prop="guestTeamScore" width="100">
          <template slot-scope="scope">
            <el-tag type="success" style="font-weight: bold;font-size: larger" >{{ scope.row.guestTeamScore }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="客队名" align="center" prop="guestTeamName" />
        <el-table-column label="球场名称" align="center" prop="buildingName" width="250"/>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-edit-outline" @click="handleTeamVsTeamRecord(scope.row)" v-hasPermi="['system:competitionOfTeam:edit']">比赛记录</el-button>
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleTeamVsTeamEdit(scope.row)" v-hasPermi="['system:competitionTeamVsTeam:edit']">编辑赛程</el-button>
            <el-button size="mini" type="text" icon="el-icon-delete" @click="handleTeamVsTeamDel(scope.row)" v-hasPermi="['system:competitionOfTeam:del']">删除赛程</el-button>
<!--            <el-button v-if="new Date(scope.row.competitionDate).getTime() > new Date().getTime()" size="mini" type="text" icon="el-icon-delete" @click="handleTeamVsTeamDel(scope.row)" v-hasPermi="['system:competitionOfTeam:del']">删除赛程</el-button>-->
          </template>
        </el-table-column>
      </el-table>
    </el-tab-pane>
    <el-tab-pane label="赛会推广" name="competitionSpread"> <span slot="label"><i class="el-icon-s-promotion"></i> 赛会推广</span>
      <el-tabs type="border-card" tab-position="top" >
        <el-tab-pane label="普通海报" >
          <el-row >
            <el-col :span="8" >
              <el-card :body-style="{ padding: '0px' }" >
                <img :src="competitionObj.competitionBackImg" class="image">
                <div style="padding: 14px;">
                  <span>赛会名称：{{competitionObj.competitionName}}</span>
                  <div class="bottom clearfix">
                    <time class="time">报名时间：{{competitionObj.enrollBeginTime}} 至 {{competitionObj.enrollEndTime}}</time>
                    <el-button type="text" @click="genCompetitionCommonAqrCode(competitionObj.id)" class="button">获取赛会推广码</el-button>
                  </div>
                </div>
                <el-divider><i class="el-icon-caret-bottom">推广二维码</i></el-divider>
                <div style="width: 100%;text-align: center">
                  <el-image
                    class="s-image"
                    :src="spreadImgurl"
                    :preview-src-list="[spreadImgurl]"
                    :fit="imgfit"></el-image>
                </div>
                <el-divider content-position="center"> <span style="color: #ae192a">注：点击预览可以分享或者保存哦</span></el-divider>
              </el-card>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="自定义海报">
          <el-row >
            <el-col :span="8" >
              <el-card :body-style="{ padding: '0px' }" >
                <img :src="competitionObj.competitionBackImg"  class="image">
                <div style="padding: 14px;">
                  <span>赛会名称：{{competitionObj.competitionName}}</span>
                  <div class="bottom clearfix">
                    <time class="time">报名时间：{{competitionObj.enrollBeginTime}} 至 {{competitionObj.enrollEndTime}}</time>
                  </div>
                </div>
              </el-card>
              <el-divider><i class="el-icon-caret-bottom">推广海报</i></el-divider>
              <el-carousel :interval="0" type="card" height="400px" style="margin-top: 20px"
                           :autoplay="false"
                           trigger="click" @change="clickCarousel">
                <el-carousel-item v-for="item in autoSpreadCardImgs" :key="item.id">
                  <el-image style="width: 300px; height: 400px" :src="item.img" :fit="imgfit"></el-image>
                </el-carousel-item>
              </el-carousel>
            </el-col>
            <el-col :span="2">
              <el-divider><i class="el-icon-caret-right"></i></el-divider>
            </el-col>
            <el-col :span="8" >
              <el-card :body-style="{ padding: '0px' }" >
                <div class='invite-head'>
                  <div class='card-com' bindtap='saveImageToPhotos'>
                    <div class='inv-card'>
                      <div class='pic'>
                        <el-image  class='img' id='card-img' :src="spreadAdImg" />
                      </div>
                      <div class='info' id='card-info'>
                        <div class='u-name'><label class='n'>
                          无篮球,不兄弟
                        </label></div>
                        <div class='u-des'>邀请你和我一起做篮球兄弟！</div>
                        <div class='pp'>长按二维码，开启你的篮球之旅</div>
                        <el-image class='ewm-img' id='ewm-img' :src="spreadImgurl" />
                      </div>
                    </div>
                  </div>
                </div>
                <el-divider content-position="center"> <span style="color: #ae192a">注：请截图保存或者分享哦</span></el-divider>
              </el-card>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </el-tab-pane>
  </el-tabs>
    <!-- 添加或修改赛会中-参赛队伍对话框 -->
    <el-dialog :title="ofTeamTitle" :visible.sync="ofTeamOpen" width="700px" append-to-body>
      <el-form ref="ofTeamForm" :model="ofTeamForm" :rules="ofTeamFormRules" label-width="120px">
        <el-form-item label="球队名" prop="teamName">
          <el-input v-model="ofTeamForm.teamName" placeholder="请输入球队名" />
        </el-form-item>
        <el-form-item label="球队logo" prop="teamLogo">
          <el-upload
            class="avatar-uploader"
            action="https://mall.lzsport.cn/prod-api/system/file/uploadMore"
            :show-file-list="false"
            name="files"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="ofTeamForm.teamLogo" :src="ofTeamForm.teamLogo" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="隐藏队员头像" prop="isHideAvatar">
          <el-switch
            v-model="ofTeamForm.isHideAvatar"
            active-text="隐藏"
            inactive-text="显示">
          </el-switch>
        </el-form-item>
        <el-form-item label="队长" prop="captain">
          <el-input v-model="ofTeamForm.captain" placeholder="请输入队长姓名" />
        </el-form-item>
        <el-form-item label="领队人" prop="contacts">
          <el-input v-model="ofTeamForm.contacts" placeholder="请输入领队人" />
        </el-form-item>
        <el-form-item label="领队人电话" prop="contactsTel">
          <el-input v-model="ofTeamForm.contactsTel" placeholder="请输入领队人电话" />
        </el-form-item>
        <el-form-item label="领队人电话区号" prop="contactsAreaCode">
          <el-input v-model="ofTeamForm.contactsAreaCode" placeholder="请输入领队人电话区号" />
        </el-form-item>
        <el-form-item label="组内的序号" prop="serialNumber">
          <el-input-number v-model="ofTeamForm.serialNumber"  :min="0" :max="100" placeholder="请输入组内的序号" />
        </el-form-item>
        <el-form-item label="备注说明" prop="remark">
          <el-input v-model="ofTeamForm.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="ofTeamSubmitForm">确 定</el-button>
        <el-button @click="ofTeamCancel">取 消</el-button>
      </div>
    </el-dialog>

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
        <el-table-column label="隐藏头像" align="center" prop="isHideAvatar">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.isHideAvatar"
              @change="changeUserSwitch($event,scope.row)"
            ></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="真实姓名" align="center" prop="realName" />
        <el-table-column label="球衣号" align="center" prop="jerseyNumber" />
        <el-table-column label="证件类型" align="center" prop="idType" />
        <el-table-column label="证件号码" align="center" prop="idCardNo" width="180"/>
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
        <el-form-item label="比赛状态" prop="status">
          <el-select v-model="vsform.status" placeholder="请选择">
            <el-option
              v-for="item in vsStatus"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="比赛类型" prop="vsType">
          <el-radio-group v-model="vsform.vsType">
            <el-radio label="0">循环赛</el-radio>
            <el-radio label="1">淘汰赛</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="主队名" prop="mainTeamName">
          <el-select v-model="vsform.mainTeamName" filterable clearable @change="changeMainTeamName" placeholder="请选择主队名">
            <el-option
              v-for="item in competitionOfTeamList"
              :value="item.id"
              :key="item.id"
              :label="item.teamName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="客队名" prop="guestTeamName">
          <el-select v-model="vsform.guestTeamName" filterable clearable  @change="changeGuestTeamName" placeholder="请选择客队名">
            <el-option
              v-for="item in competitionOfTeamList"
              :value="item.id"
              :key="item.id"
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
    <!--赛程按组系统智能排赛程-->
    <el-dialog
      width="20%"
      title="智能分组赛程排班"
      :visible.sync="mindVisible" append-to-body>
      <el-select v-model="selectGroupValue" placeholder="请选择需要智能排班的分组">
        <el-option
          v-for="item in competitionTeamGroupList"
          :key="item.id"
          :label="item.competitionGroup"
          :value="item.id">
        </el-option>
      </el-select>
      <div style="text-align: right; margin: 0">
        <el-button size="mini" type="text" @click="mindVisible = false">取消</el-button>
        <el-button type="primary" size="mini" @click="mindSetOk">确定</el-button>
      </div>
      <el-button slot="reference">删除</el-button>
    </el-dialog>
    <!--赛程比赛数据记录-->
    <el-dialog :title="vsRecordTitle" :visible.sync="vsRecordOpen" width="70%"  append-to-body :close-on-click-modal="false" >
      <el-skeleton  :loading="skeletonLoading" animated :count="3">
        <el-form>
      <el-row>
        <el-col :span="16" style="font-size: large;font-weight: bold"><i class="el-icon-time">比赛时间：</i>{{ competitionRecord.teamVsTeamVo.competitionTime }} {{ competitionRecord.teamVsTeamVo.weekDayName }}</el-col>
      </el-row>
      <div class="s-div">
      <el-row>
        <el-col :span="8" style="font-size: large;font-weight: bold" >
          <el-avatar :src="competitionRecord.teamVsTeamVo.mainTeamLogo"></el-avatar>
          <span style="position: absolute;margin-left: 10px;margin-top: 8px">{{competitionRecord.teamVsTeamVo.mainTeamName}}</span>
        </el-col>
        <el-col :span="8" style="text-align: center">
          <span style="font-weight: bold;font-size: xx-large;color: #ae192a">
            {{competitionRecord.teamVsTeamVo.mainTeamScore===null?0:competitionRecord.teamVsTeamVo.mainTeamScore}}:{{competitionRecord.teamVsTeamVo.guestTeamScore===null?0:competitionRecord.teamVsTeamVo.guestTeamScore}}</span>
        </el-col>
        <el-col :span="8"  style="font-size: large;font-weight: bold">
          <el-avatar :src="competitionRecord.teamVsTeamVo.guestTeamLogo"></el-avatar>
          <span style="position: absolute;margin-left: 10px;margin-top: 8px">{{competitionRecord.teamVsTeamVo.guestTeamName}}</span>
        </el-col>
      </el-row>
      <el-container>
        <el-aside style="width: 50%;margin-top:10px">
          <el-form-item label="第一节">
            <el-input-number v-model="competitionRecord.mainTeam.oneNodeScore" @change="handleMainOneNodeChange" :min="0"></el-input-number>
          </el-form-item>
          <el-form-item label="第二节">
            <el-input-number v-model="competitionRecord.mainTeam.twoNodeScore" @change="handleMainTwoNodeChange" :min="0"></el-input-number>
          </el-form-item>
          <el-form-item label="第三节">
            <el-input-number v-model="competitionRecord.mainTeam.threeNodeScore" @change="handleMainThreeNodeChange" :min="0"></el-input-number>
          </el-form-item>
          <el-form-item label="第四节">
            <el-input-number v-model="competitionRecord.mainTeam.fourNodeScore" @change="handleMainFourNodeChange" :min="0"></el-input-number>
          </el-form-item>
          <el-form-item label="第五节">
            <el-input-number v-model="competitionRecord.mainTeam.fiveNodeScore" @change="handleMainFiveNodeChange" :min="0"></el-input-number>
          </el-form-item>
          <el-form-item label="第六节">
            <el-input-number v-model="competitionRecord.mainTeam.sixNodeScore" @change="handleMainSixNodeChange" :min="0"></el-input-number>
          </el-form-item>
        </el-aside>
        <el-main style="width: 55%; background-color: white;">
          <el-form-item  label="第一节">
            <el-input-number v-model="competitionRecord.guestTeam.oneNodeScore" @change="handleGuestOneNodeChange" :min="0"></el-input-number>
          </el-form-item>
          <el-form-item label="第二节">
            <el-input-number v-model="competitionRecord.guestTeam.twoNodeScore" @change="handleGuestTwoNodeChange" :min="0"></el-input-number>
          </el-form-item>
          <el-form-item label="第三节">
            <el-input-number v-model="competitionRecord.guestTeam.threeNodeScore" @change="handleGuestThreeNodeChange" :min="0"></el-input-number>
          </el-form-item>
          <el-form-item label="第四节">
            <el-input-number v-model="competitionRecord.guestTeam.fourNodeScore" @change="handleGuestFourNodeChange" :min="0"></el-input-number>
          </el-form-item>
          <el-form-item label="第五节">
            <el-input-number v-model="competitionRecord.guestTeam.fiveNodeScore" @change="handleGuestFiveNodeChange" :min="0"></el-input-number>
          </el-form-item>
          <el-form-item label="第六节">
            <el-input-number v-model="competitionRecord.guestTeam.sixNodeScore" @change="handleGuestSixNodeChange" :min="0" label="描述文字"></el-input-number>
          </el-form-item>
        </el-main>
      </el-container>
        <el-row style="width: 100% ;text-align: center">
          <el-button
            type="primary"
            icon="el-icon-check"
            @click="handleTeamVsTeamRecordSave"
            v-hasPermi="['system:competitionOfTeam:save']"
          >数据保存</el-button>
          <el-button
            type="primary"
            icon="el-icon-close"
            @click="vsRecordOpen=false"
          >关闭</el-button>
        </el-row>
      </div>
      </el-form>
       <div class="a-div">
         <el-tabs type="card" style="margin-top: 10px" @tab-click="handleTagTeamClick">
           <el-tab-pane>
             <span slot="label"><i class="el-icon-s-flag"></i> {{competitionRecord.mainTeam.teamName}}</span>
           </el-tab-pane>
           <el-tab-pane>
             <span slot="label"><i class="el-icon-s-flag"></i> {{competitionRecord.guestTeam.teamName}}</span>
           </el-tab-pane>
         </el-tabs>
         <el-table title="球员得分" :data="teamMembersScoreList" :span-method="objectSpanMethod" border style="width: 100%;">
           <el-table-column label="球员" align="center" prop="realName" />
           <el-table-column label="球衣号" align="center" prop="jerseyNumber" />
           <el-table-column label="总得分" align="center" prop="totalScore" />
           <el-table-column label="2分球" align="center" prop="twoPoints" />
           <el-table-column label="3分球" align="center" prop="threePoints" />
           <el-table-column label="罚球" align="center" prop="penalty" />
           <el-table-column label="篮板" align="center" prop="backboard" />
           <el-table-column label="前板" align="center" prop="frontPlate" />
           <el-table-column label="后板" align="center" prop="backPlate" />
           <el-table-column label="助攻" align="center" prop="assists" />
           <el-table-column label="抢断" align="center" prop="snatch" />
           <el-table-column label="盖帽" align="center" prop="block" />
           <el-table-column label="失误" align="center" prop="fault" />
           <el-table-column label="犯规" align="center" prop="breaks" />
           <el-table-column label="首发" align="center" prop="isFirstLaunch" >
             <template slot-scope="scope">
               <el-switch v-model="scope.row.isFirstLaunch==1" disabled></el-switch>
             </template>
           </el-table-column>
           <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
             <template slot-scope="scope">
               <el-button
                 size="mini"
                 type="text"
                 icon="el-icon-edit"
                 @click="handleUpdateMemberScore(scope.row)"
                 v-hasPermi="['system:competitionMemberScore:edit']"
               >计分</el-button>
             </template>
           </el-table-column>
         </el-table>
       </div>
      </el-skeleton>
      <el-dialog :close-on-click-modal="false" width="35%" title="球员得分记录" :visible.sync="innerMemberVisible" append-to-body>
        <el-form ref="scoreform" :model="scoreform" :rules="scoreformRules" size="mini" label-width="80px">
          <el-form-item label="球队名" prop="teamName">
            <el-input v-model="scoreform.teamName"  :disabled="true" />
          </el-form-item>
          <el-form-item label="球员名" prop="realName">
            <el-input v-model="scoreform.realName"  :disabled="true" />
          </el-form-item>
          <el-form-item label="球衣号" prop="jerseyNumber">
            <el-input v-model="scoreform.jerseyNumber"  :disabled="true" />
          </el-form-item>
          <el-form-item label="首发" prop="isFirstLaunch">
            <el-switch @change="switchFirstLaunch" v-model="isFirstLaunch"></el-switch>
          </el-form-item>
          <el-form-item label="总得分" prop="totalScore">
            <el-input-number :min="0" v-model="scoreform.totalScore"  placeholder="请输入总得分" />
          </el-form-item>
          <el-form-item label="2分球" prop="twoPoints">
            <el-input-number :min="0" v-model="scoreform.twoPoints" placeholder="请输入2分球" />
          </el-form-item>
          <el-form-item label="3分球" prop="threePoints">
            <el-input-number :min="0" v-model="scoreform.threePoints" placeholder="请输入3分球" />
          </el-form-item>
          <el-form-item label="罚球" prop="penalty">
            <el-input-number :min="0" v-model="scoreform.penalty" placeholder="请输入罚球" />
          </el-form-item>
          <el-form-item label="篮板" prop="backboard">
            <el-input-number :min="0" v-model="scoreform.backboard" placeholder="请输入篮板" />
          </el-form-item>
          <el-form-item label="前板" prop="frontPlate">
            <el-input-number :min="0" v-model="scoreform.frontPlate" placeholder="请输入前板" />
          </el-form-item>
          <el-form-item label="后板" prop="backPlate">
            <el-input-number :min="0" v-model="scoreform.backPlate" placeholder="请输入后板" />
          </el-form-item>
          <el-form-item label="助攻" prop="assists">
            <el-input-number :min="0" v-model="scoreform.assists" placeholder="请输入助攻" />
          </el-form-item>
          <el-form-item label="抢断" prop="snatch">
            <el-input-number :min="0" v-model="scoreform.snatch" placeholder="请输入抢断" />
          </el-form-item>
          <el-form-item label="盖帽" prop="block">
            <el-input-number :min="0" v-model="scoreform.block" placeholder="请输入盖帽" />
          </el-form-item>
          <el-form-item label="失误" prop="fault">
            <el-input-number :min="0" v-model="scoreform.fault" placeholder="请输入失误" />
          </el-form-item>
          <el-form-item label="犯规" prop="breaks">
            <el-input-number :min="0" v-model="scoreform.breaks" placeholder="请输入犯规" />
          </el-form-item>
          <el-form-item label="备注说明" prop="remark">
            <el-input v-model="scoreform.remark" type="textarea" placeholder="请输入内容" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitScoreForm">确 定</el-button>
          <el-button @click="innerMemberVisible=false">取 消</el-button>
        </div>
      </el-dialog>
    </el-dialog>
  </div>
</template>

<script>
import { listCompetition, getCompetition, genCompetitionCommonAqrSpread, addCompetition, updateCompetition } from "@/api/system/competition";
import {
  listCompetitionOfTeam,
  batchEditById,
  intoTeamGroup,
  removeTeamGroup,
  updateCompetitionOfTeam,
  addCompetitionOfTeam,
  getCompetitionOfTeam
} from "@/api/system/competitionOfTeam";
import { listCompetitionMembers, getCompetitionMembers, delCompetitionMembers, addCompetitionMembers, updateCompetitionMembers } from "@/api/system/competitionMembers";
import { listCompetitionTeamGroup, arrangeTeamGroupSchedule, delCompetitionTeamGroup, addCompetitionTeamGroup, updateCompetitionTeamGroup } from "@/api/system/competitionTeamGroup";
import { listCompetitionTeamVsTeam,getCompetitionVsRecordById, delCompetitionTeamVsTeam, addCompetitionTeamVsTeam, updateCompetitionTeamVsTeam } from "@/api/system/competitionTeamVsTeam";
import { listWxBuilding, getWxBuilding, delWxBuilding, addWxBuilding, updateWxBuilding } from "@/api/system/WxBuilding";
import { listCompetitionResult, getCompetitionResult, editDataCompetitionResult, batchUpdateCompetitionResult, updateCompetitionResult } from "@/api/system/competitionResult";
import { listCompetitionMemberScore, getCompetitionMemberScore, delCompetitionMemberScore, addCompetitionMemberScore, updateCompetitionMemberScore } from "@/api/system/competitionMemberScore";
import {getWxApplesAccessToken, genWxApplesAqrCode} from "@/api/system/wxApplesCode";
import {parseTime} from "@/utils/ruoyi";

export default {
  name: "CompetitionSet",
  dicts: ['competition_status','vs_status'],
  data() {
    return {
      spreadImgurl:null,
      spreadPage:"pages/competition/competitiondetail/competitiondetail",
      spreadAdImg:"https://mall.lzsport.cn/image/wxIcon/spreadTempImg/timg0.jpg",
      autoSpreadCardImgs: [
        {
          id: '0',
          img: 'https://mall.lzsport.cn/image/wxIcon/spreadTempImg/timg0.jpg'
        },
        {
          id: '1',
          img: 'https://mall.lzsport.cn/image/wxIcon/spreadTempImg/timg1.jpg'
        }, {
          id: '2',
          img: 'https://mall.lzsport.cn/image/wxIcon/spreadTempImg/timg2.jpg'
        }, {
          id: '3',
          img: 'https://mall.lzsport.cn/image/wxIcon/spreadTempImg/timg3.jpg'
        }, {
          id: '4',
          img: 'https://mall.lzsport.cn/image/wxIcon/spreadTempImg/timg4.jpg'
        }, {
          id: '5',
          img: 'https://mall.lzsport.cn/image/wxIcon/spreadTempImg/timg5.jpg'
        }, {
          id: '6',
          img: 'https://mall.lzsport.cn/image/wxIcon/spreadTempImg/timg6.png'
        }, {
          id: '7',
          img: 'https://mall.lzsport.cn/image/wxIcon/spreadTempImg/timg7.png'
        }, {
          id: '8',
          img: 'https://mall.lzsport.cn/image/wxIcon/spreadTempImg/timg8.png'
        }, {
          id: '9',
          img: 'https://mall.lzsport.cn/image/wxIcon/spreadTempImg/timg9.png'
        }
      ],
      mindVisible:false,
      selectGroupValue:null,
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
      //赛程状态：-1=已取消； 0=报名中，1=比赛中；2=已结束
      vsStatus:[
        {label:"已取消",value:-1},
        {label:"报名中",value:0},
        {label:"比赛中",value:1},
        {label:"已结束",value:2}
      ],
      addGroupCode:"",
      addGroupDialogVisible:false,
      addTeamDialogVisible:false,
      teamMultipleSelection:[],
      // 查询参数
      vsform:{},
      vsTitle:"",
      vsRules: {
        status: [
          { required: true, message: "比赛状态不能为空", trigger: "blur" }
        ],
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
      skeletonLoading:false,
      vsOpen:false,
      isFirstLaunch:false,
      buildingList: [],
      buildLoading:false,
      vsRecordTitle:"",
      vsRecordOpen:false,
      teamMembersScoreList:[],
      innerMemberVisible:false,
      competitionRecord:{
        mainTeam:{
            id:null,
            fiveNodeScore: 0,
            fourNodeScore: 0,
            oneNodeScore: 0,
            sixNodeScore: 0,
            threeNodeScore:0,
            twoNodeScore: 0,
            membersScoreList:[]
          },
        guestTeam:{
          id:null,
          fiveNodeScore: 0,
          fourNodeScore: 0,
          oneNodeScore: 0,
          sixNodeScore: 0,
          threeNodeScore:0,
          twoNodeScore: 0,
          membersScoreList:[]
        },
        teamVsTeamVo:{
          id:null,
          competitionTime:null,
          mainTeamName:null,
          guestTeamName: null,
          mainTeamScore:null,
          guestTeamScore:null,
          weekDayName:null
        },
      },
      scoreform:{},
      scoreformRules:{
        totalScore: [
          { required: true, message: "总分不能为空", trigger: "blur" }
        ]
      },
      //新增球队变量
      imageUrl:null,
      ofTeamOpen:false,
      ofTeamTitle:"",
      ofTeamForm:{},
      ofTeamFormRules:{
        teamName: [
          { required: true, message: "球队名称不能为空", trigger: "blur" }
        ],
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
      listCompetitionOfTeam({"orderByColumn":"t.id","isAsc":"desc","pageNum": 1, "pageSize": 1000,"competitionId":id}).then(response => {
        this.competitionOfTeamList = response.rows;
      });
    }
  },
  methods: {
    // methods中
    getFormatterName(row) {
      for (let i in this.vsStatus) {
        if (this.vsStatus[i].value == row.status) {
          return this.vsStatus[i].label;
        }
      }
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
    //隐藏球队队员的头像
    changeSwitch(e,row){
      updateCompetitionOfTeam({"id":row.id,"isHideAvatar":row.isHideAvatar}).then(response => {
        this.$modal.msgSuccess("操作成功");
     /*   listCompetitionOfTeam({"orderByColumn":"t.id","isAsc":"desc","pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionOfTeamList = response.rows;
        });*/
      });
    },
    changeUserSwitch(e,row){
      updateCompetitionMembers({"id":row.id,"isHideAvatar":row.isHideAvatar}).then(response => {
        this.$modal.msgSuccess("操作成功");
        /*listCompetitionMembers({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id,"competitionOfTeamId":row.competitionOfTeamId}).then(response => {
          this.competitionMembersList =  response.rows;
        });*/
      });
    },
    bindConfirm(id,tage){
      updateCompetitionOfTeam({"id":id,"status":tage}).then(response => {
        this.$modal.msgSuccess("球队审核成功");
        listCompetitionOfTeam({"orderByColumn":"t.id","isAsc":"desc","pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionOfTeamList = response.rows;
        });
      });
    },
    bindDelOfTeamConfirm(id,tage){
      updateCompetitionOfTeam({"id":id,"status":tage}).then(response => {
        this.$modal.msgSuccess("球队审核成功");
        listCompetitionOfTeam({"orderByColumn":"t.id","isAsc":"desc","pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionOfTeamList = response.rows;
        });
      });
    },
    handleTeamUser(row){
      this.drawer = true
      listCompetitionMembers({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id,"competitionOfTeamId":row.id}).then(response => {
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
        listCompetitionOfTeam({"orderByColumn":"t.id","isAsc":"desc","pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionOfTeamList = response.rows;
        });
      }else if(tab.name=='competitionTeamGroup'){
        listCompetitionTeamGroup({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionTeamGroupList = response.rows;
          this.competitionTeamGroupList.push({"competitionGroup":"未分","id":null})
        });
      }else if(tab.name=='competitionVsSet'){
        listCompetitionTeamVsTeam({"orderByColumn":"competition_time","isAsc":"desc","isDeleted":0,"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionTeamVsTeamList = response.rows;
        });
      }else if(tab.name=='competitionSpread'){
        if(this.spreadImgurl === null) {
          this.genCompetitionCommonAqrCode(this.competitionObj.id);
        }
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
    //新增球队
    addOfTeam(){
      this.ofTeamForm = { };
      this.ofTeamOpen = true;
      this.ofTeamTitle = "新增球队";
    },
    ofTeamCancel(){
      this.ofTeamForm = { };
      this.ofTeamOpen = false;
      this.ofTeamTitle = "";
    },
    handleEditOfTeam(row){
      const id = row.id;
      getCompetitionOfTeam(id).then(response => {
        this.ofTeamForm = response.data;
        this.ofTeamOpen = true;
        this.ofTeamTitle = "编辑球队";
      });
    },
    ofTeamSubmitForm(){
      this.$refs["ofTeamForm"].validate(valid => {
        if (valid) {
          this.ofTeamForm.competitionId = this.competitionObj.id;
          console.info(this.ofTeamForm)
          if (this.ofTeamForm.id != null) {
            updateCompetitionOfTeam(this.ofTeamForm).then(response => {
              this.$modal.msgSuccess("编辑球队成功");
              this.ofTeamOpen = false;
              listCompetitionOfTeam({"orderByColumn":"t.id","isAsc":"desc","pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
                this.competitionOfTeamList = response.rows;
              });
            });
          } else {
            addCompetitionOfTeam(this.ofTeamForm).then(response => {
              this.$modal.msgSuccess("新增球队成功");
              this.ofTeamOpen = false;
              listCompetitionOfTeam({"orderByColumn":"t.id","isAsc":"desc","pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
                this.competitionOfTeamList = response.rows;
              });
            });
          }
        }
      });
    },
    //上传球队logo
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      let imgUrl = res.data[0];
      this.ofTeamForm.teamLogo = "https://mall.lzsport.cn/image/"+imgUrl;
    },
    beforeAvatarUpload(file) {
      console.info(file.type)
      const isJPG = (file.type === 'image/jpeg'||file.type === 'image/png' || file.type === 'image/x-icon');
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG/PNG/ICO 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
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
      this.mindVisible = true;
      this.competitionTeamGroupList = [];
      listCompetitionTeamGroup({"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
        this.competitionTeamGroupList = response.rows;
      });

    },
    mindSetOk(){
        let param ={
              id:this.selectGroupValue,
              status:0
        }
        arrangeTeamGroupSchedule(param).then(response => {
          this.$modal.msgSuccess("赛程智能设置成功");
          this.mindVisible = false;
          listCompetitionTeamVsTeam({"orderByColumn":"competition_time","isAsc":"desc","isDeleted":0,"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
            this.competitionTeamVsTeamList = response.rows;
          });
        });
    },
    changeMainTeamName(val){
      console.info(val)
      let obj={}
      obj = this.competitionOfTeamList.find(function(i){
        return i.id ===val
      });

      this.vsform.mainTeamName = obj.teamName;
      this.vsform.mainTeamId = val;
      //在change中获取到整条对象数据
      console.info(this.vsform)
    },
    changeGuestTeamName(val){
      let obj={}
      obj = this.competitionOfTeamList.find(function(i){
        return i.id ===val
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
      this.skeletonLoading = true;
      this.vsRecordOpen=true;
      this.vsRecordTitle = "比赛数据记录";
      //获取比赛数据
      getCompetitionVsRecordById(row.id).then(response=>{
        this.competitionRecord = response.data;
        this.teamMembersScoreList = this.competitionRecord.mainTeam.membersScoreList;
        this.skeletonLoading = false
      })
    },
    handleTeamVsTeamDel(row){
      const ids = row.id || this.ids;
      this.$modal.confirm('确认删除['+ parseTime(row.competitionTime, '{y}-{m}-{d} {h}:{i}') +']赛程['+row.mainTeamName+' VS '+row.guestTeamName+']数据？').then(function() {
        return delCompetitionTeamVsTeam(ids);
      }).then(() => {
        listCompetitionTeamVsTeam({"orderByColumn":"competition_time","isAsc":"desc","isDeleted":0,"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionTeamVsTeamList = response.rows;
        });
        this.$modal.msgSuccess("删除赛程成功");
      }).catch(() => {});
    },
    handleTeamVsTeamRecordSave(){
      let mainTeamTotalScore = this.competitionRecord.teamVsTeamVo.mainTeamScore;
      let guestTeamTotalScore = this.competitionRecord.teamVsTeamVo.guestTeamScore;
      //平局
      if(Number(mainTeamTotalScore)==Number(guestTeamTotalScore)){
        this.competitionRecord.mainTeam.vsResult = 'flat';
        this.competitionRecord.mainTeam.integral = 1;
        this.competitionRecord.mainTeam.netWinPoint = 0;
        this.competitionRecord.guestTeam.vsResult = 'flat';
        this.competitionRecord.guestTeam.integral = 1;
        this.competitionRecord.guestTeam.netWinPoint = 0;
      }else {
        if (Number(mainTeamTotalScore) > Number(guestTeamTotalScore)) {
          this.competitionRecord.mainTeam.vsResult = 'win';
          this.competitionRecord.mainTeam.integral = 2;
          this.competitionRecord.guestTeam.vsResult = 'fail';
          this.competitionRecord.guestTeam.integral = 1;
        } else {
          this.competitionRecord.mainTeam.vsResult = 'fail';
          this.competitionRecord.mainTeam.integral = 1;
          this.competitionRecord.guestTeam.vsResult = 'win';
          this.competitionRecord.guestTeam.integral = 2;
        }
      }
      //净胜分
      this.competitionRecord.mainTeam.netWinPoint = Number(mainTeamTotalScore) - Number(guestTeamTotalScore);
      this.competitionRecord.guestTeam.netWinPoint = Number(guestTeamTotalScore) - Number(mainTeamTotalScore);
      editDataCompetitionResult(this.competitionRecord).then(response => {
        this.$modal.msgSuccess("比赛结果记录成功");
        this.vsRecordOpen = false;
        listCompetitionTeamVsTeam({"orderByColumn":"competition_time","isAsc":"desc","isDeleted":0,"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
          this.competitionTeamVsTeamList = response.rows;
        });
      });
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
              listCompetitionTeamVsTeam({"orderByColumn":"competition_time","isAsc":"desc","isDeleted":0,"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
                this.competitionTeamVsTeamList = response.rows;
              });
            });
          } else {
            this.vsform.competitionId = this.competitionObj.id;
            addCompetitionTeamVsTeam(this.vsform).then(response => {
              this.$modal.msgSuccess("新增赛程成功");
              this.vsOpen = false;
              listCompetitionTeamVsTeam({"orderByColumn":"competition_time","isAsc":"desc","isDeleted":0,"pageNum": 1, "pageSize": 1000,"competitionId":this.competitionObj.id}).then(response => {
                this.competitionTeamVsTeamList = response.rows;
              });
            });
          }
        }
      });
    },
    handleMainOneNodeChange(currentValue, oldValue){
     let totalScore =currentValue + parseInt(this.competitionRecord.mainTeam.twoNodeScore)
      + parseInt(this.competitionRecord.mainTeam.threeNodeScore)
      + parseInt(this.competitionRecord.mainTeam.fourNodeScore)
      + parseInt(this.competitionRecord.mainTeam.fiveNodeScore)
      + parseInt(this.competitionRecord.mainTeam.sixNodeScore);
      this.competitionRecord.teamVsTeamVo.mainTeamScore = totalScore;
    },
    handleMainTwoNodeChange(currentValue, oldValue){
      let totalScore =currentValue + parseInt(this.competitionRecord.mainTeam.oneNodeScore)
        + parseInt(this.competitionRecord.mainTeam.threeNodeScore)
        + parseInt(this.competitionRecord.mainTeam.fourNodeScore)
        + parseInt(this.competitionRecord.mainTeam.fiveNodeScore)
        + parseInt(this.competitionRecord.mainTeam.sixNodeScore);
      this.competitionRecord.teamVsTeamVo.mainTeamScore = totalScore;
    },
    handleMainThreeNodeChange(currentValue, oldValue){
      let totalScore =currentValue + parseInt(this.competitionRecord.mainTeam.oneNodeScore)
        + parseInt(this.competitionRecord.mainTeam.twoNodeScore)
        + parseInt(this.competitionRecord.mainTeam.fourNodeScore)
        + parseInt(this.competitionRecord.mainTeam.fiveNodeScore)
        + parseInt(this.competitionRecord.mainTeam.sixNodeScore);
      this.competitionRecord.teamVsTeamVo.mainTeamScore = totalScore;
    },
    handleMainFourNodeChange(currentValue, oldValue){
      let totalScore =currentValue + parseInt(this.competitionRecord.mainTeam.oneNodeScore)
        + parseInt(this.competitionRecord.mainTeam.twoNodeScore)
        + parseInt(this.competitionRecord.mainTeam.threeNodeScore)
        + parseInt(this.competitionRecord.mainTeam.fiveNodeScore)
        + parseInt(this.competitionRecord.mainTeam.sixNodeScore);
      this.competitionRecord.teamVsTeamVo.mainTeamScore = totalScore;
    },
    handleMainFiveNodeChange(currentValue, oldValue){
      let totalScore =currentValue + parseInt(this.competitionRecord.mainTeam.oneNodeScore)
        + parseInt(this.competitionRecord.mainTeam.twoNodeScore)
        + parseInt(this.competitionRecord.mainTeam.fourNodeScore)
        + parseInt(this.competitionRecord.mainTeam.threeNodeScore)
        + parseInt(this.competitionRecord.mainTeam.sixNodeScore);
      this.competitionRecord.teamVsTeamVo.mainTeamScore = totalScore;
    },
    handleMainSixNodeChange(currentValue, oldValue){
      let totalScore =currentValue + parseInt(this.competitionRecord.mainTeam.oneNodeScore)
        + parseInt(this.competitionRecord.mainTeam.twoNodeScore)
        + parseInt(this.competitionRecord.mainTeam.fourNodeScore)
        + parseInt(this.competitionRecord.mainTeam.threeNodeScore)
        + parseInt(this.competitionRecord.mainTeam.fiveNodeScore);
      this.competitionRecord.teamVsTeamVo.mainTeamScore = totalScore;
    },


    handleGuestOneNodeChange(currentValue, oldValue){
      let totalScore =currentValue + parseInt(this.competitionRecord.guestTeam.twoNodeScore)
        + parseInt(this.competitionRecord.guestTeam.threeNodeScore)
        + parseInt(this.competitionRecord.guestTeam.fourNodeScore)
        + parseInt(this.competitionRecord.guestTeam.fiveNodeScore)
        + parseInt(this.competitionRecord.guestTeam.sixNodeScore);
      this.competitionRecord.teamVsTeamVo.guestTeamScore = totalScore;
    },
    handleGuestTwoNodeChange(currentValue, oldValue){
      let totalScore =currentValue + parseInt(this.competitionRecord.guestTeam.oneNodeScore)
        + parseInt(this.competitionRecord.guestTeam.threeNodeScore)
        + parseInt(this.competitionRecord.guestTeam.fourNodeScore)
        + parseInt(this.competitionRecord.guestTeam.fiveNodeScore)
        + parseInt(this.competitionRecord.guestTeam.sixNodeScore);
      this.competitionRecord.teamVsTeamVo.guestTeamScore = totalScore;
    },
    handleGuestThreeNodeChange(currentValue, oldValue){
      let totalScore =currentValue + parseInt(this.competitionRecord.guestTeam.oneNodeScore)
        + parseInt(this.competitionRecord.guestTeam.twoNodeScore)
        + parseInt(this.competitionRecord.guestTeam.fourNodeScore)
        + parseInt(this.competitionRecord.guestTeam.fiveNodeScore)
        + parseInt(this.competitionRecord.guestTeam.sixNodeScore);
      this.competitionRecord.teamVsTeamVo.guestTeamScore = totalScore;
    },
    handleGuestFiveNodeChange(currentValue, oldValue){
      let totalScore =currentValue + parseInt(this.competitionRecord.guestTeam.oneNodeScore)
        + parseInt(this.competitionRecord.guestTeam.twoNodeScore)
        + parseInt(this.competitionRecord.guestTeam.threeNodeScore)
        + parseInt(this.competitionRecord.guestTeam.fourNodeScore)
        + parseInt(this.competitionRecord.guestTeam.sixNodeScore);
      this.competitionRecord.teamVsTeamVo.guestTeamScore = totalScore;
    },
    handleGuestSixNodeChange(currentValue, oldValue){
      let totalScore =currentValue + parseInt(this.competitionRecord.guestTeam.oneNodeScore)
        + parseInt(this.competitionRecord.guestTeam.twoNodeScore)
        + parseInt(this.competitionRecord.guestTeam.threeNodeScore)
        + parseInt(this.competitionRecord.guestTeam.fourNodeScore)
        + parseInt(this.competitionRecord.guestTeam.fiveNodeScore);
      this.competitionRecord.teamVsTeamVo.guestTeamScore = totalScore;
    },
    handleGuestFourNodeChange(currentValue, oldValue){
      let totalScore =currentValue + parseInt(this.competitionRecord.guestTeam.oneNodeScore)
        + parseInt(this.competitionRecord.guestTeam.twoNodeScore)
        + parseInt(this.competitionRecord.guestTeam.threeNodeScore)
        + parseInt(this.competitionRecord.guestTeam.fiveNodeScore)
        + parseInt(this.competitionRecord.guestTeam.sixNodeScore);
      this.competitionRecord.teamVsTeamVo.guestTeamScore = totalScore;
    },
    handleTagTeamClick(tab, event){
      console.info(tab.index)
      if(tab.index==0){
        this.teamMembersScoreList = this.competitionRecord.mainTeam.membersScoreList;
      }else {
        this.teamMembersScoreList = this.competitionRecord.guestTeam.membersScoreList;
      }
    },
    handleUpdateMemberScore(row){
      this.scoreform = row;
      this.innerMemberVisible = true;
      if (this.scoreform.isFirstLaunch===1){
        this.isFirstLaunch = true;
      }else {
        this.isFirstLaunch = false;
      }
    },
    switchFirstLaunch(val){
      if (val){
        this.scoreform.isFirstLaunch = 1;
      }else {
        this.scoreform.isFirstLaunch = 0;
      }
    },
    submitScoreForm(){
      if(this.scoreform.id == null){
        addCompetitionMemberScore(this.scoreform).then(response => {
          this.$modal.msgSuccess("球员计分成功");
          this.innerMemberVisible = false;
          getCompetitionVsRecordById(this.scoreform.competitionVsId).then(response=>{
            this.competitionRecord = response.data;
          })
        });
      }else {
        updateCompetitionMemberScore(this.scoreform).then(response => {
          this.$modal.msgSuccess("球员计分成功");
          this.innerMemberVisible = false;
          getCompetitionVsRecordById(this.scoreform.competitionVsId).then(response=>{
            this.competitionRecord = response.data;
          })
        });
      }
    },
    //获取普通赛会推广二维码
    genCompetitionCommonAqrCode(id){
      let data ={
        "page":this.spreadPage,
        "scene":id
      };
      genCompetitionCommonAqrSpread(data).then(response => {
        this.$modal.msgSuccess("生成普通推广二维码成功");
        this.spreadImgurl = response.data.codeImgUrl;
      });
    },
    clickCarousel(data){
         console.info(data)
      if(this.spreadImgurl===null){
        this.genCompetitionCommonAqrCode(this.competitionObj.id);
      }
      this.spreadAdImg = this.autoSpreadCardImgs[data].img;
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
.el-aside {
  background: white;
  border-width: 0 1px 0 0;
  border-style: solid;
  border-color:#d3dce6;
}
.s-div {
  background: white;
  border-width: 1px 1px 1px 1px;
  border-style: solid;
  border-color:#d3dce6;
  border-radius: 15px;
}
.a-div {
  margin-top: 10px;
  background: white;
  border-width: 1px 1px 1px 1px;
  border-style: solid;
  border-color:#d3dce6;
  border-radius: 15px;
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

}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 510px;
  max-height: 300px;
  display: block;
}
.image2{
  width: 500px;
  height: 710px;
  padding: 5px;
  display: block;
}
.s-image {
  width: 200px;
  height: 200px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}


.canvas{ width:400px; height:690px; background-color: #fff; position: fixed; left: -100%; top: -100%; }
.invite-head{ width: 100%; height: 725px; background-color: #f5f5f5; position: relative;}
.invite-head .share-btn{ width: 114px; height: 60px; background-color: #e94579;
  position: absolute; right: 0; bottom: 38px; border-radius: 40px 0 0  40px;  display: flex;
  justify-content: center; align-items: center;}
.invite-head .share-btn .i{ width: 48px; height: 24px; background-repeat: no-repeat; background-size: cover;
  display: inline-block; background-image:url('https://7830-x01-a0804c-1258524456.tcb.qcloud.la/icons/i-ffxx-ico.png')}
.card-com,.renew-com{ width: 400px; height: 690px; background-color: #fff;overflow: hidden;
  position: absolute; left: 50%; transform: translateX(-50%); top: 15px;}
.inv-card{ width: 100%; height: 100%; }
.inv-card .pic{ width: 100%; height: 570px; }
.inv-card .pic .img{ width: 100%; height: 100%; display: inline-block;}
.inv-card .info{ width: 100%; height: 120px; padding: 15px 0 0 15px; box-sizing: border-box;
  background-color: #fff; position: relative;}
.inv-card .info .u-name{ color: #333; font-size: 24px;}
.inv-card .info .u-des{ color: #333; font-size: 22px; margin-top: 2px; }
.inv-card .info .pp{ color: #999; font-size:16px; margin-top: 5px;}
.inv-card .info .ewm-img{ width: 80px; height: 80px; position: absolute; right: 15px; bottom: 20px;}
.inv-tip{ width: 100%; text-align: center;color: #333; font-size: 24px; position: absolute; bottom: 135px;}
.query{ position: fixed; left: 100%; bottom: -100%;}
.pd{ width: 15px; height: 40px;}
.ewm-leftTop{ width:306px; height:590px;}
.pt1{ height: 610px;}
.pt2{ height: 640px;}
.pt3{ height: 668px;}
.poster-mod{ width: 100%; height: 150px; overflow: hidden; background: #fff;  white-space: nowrap;}
.poster-item{ height: 144px; background-color: #f0f0f0; border-radius: 6px;  border:1.5px solid transparent;   display: inline-block; overflow: hidden; margin-right: 35px;}
.poster-item.cur{ border-color: #e94579;}
.poster-item .img{ width: 144px; height: 144px;}
</style>
