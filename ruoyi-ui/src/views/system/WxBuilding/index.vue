<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="buildingName">
        <el-input
          v-model="queryParams.buildingName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="城市" prop="cityName">
        <el-input
          v-model="queryParams.cityName"
          placeholder="请输入城市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否开放" prop="isOpen">
        <el-select v-model="queryParams.isOpen">
          <el-option
            v-for="item in booleanOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="支持在线" prop="isSupportlive">
        <el-select v-model="queryParams.isSupportlive">
          <el-option
            v-for="item in booleanOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
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
          v-hasPermi="['system:WxBuilding:add']"
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
          v-hasPermi="['system:WxBuilding:edit']"
        >修改</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-s-check"
          size="mini"
          :disabled="single"
          @click="handleApprovalUpdate"
          v-hasPermi="['system:WxBuilding:approval']"
        >审批</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:WxBuilding:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:WxBuilding:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="WxBuildingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="ID" align="center" prop="id" />-->

      <el-table-column label="球场图片" align="center" prop="contactTel" >
        <template slot-scope="scope">
          <el-image
            style="width: 200px; height: 100px"
            :src="scope.row.codeImgUrl"
            :preview-src-list="[scope.row.codeImgUrl]"
            :fit="imgfit"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center" prop="buildingName" show-overflow-tooltip/>
<!--      <el-table-column label="经度" align="center" prop="longitude" show-overflow-tooltip/>
      <el-table-column label="纬度" align="center" prop="latitude" show-overflow-tooltip/>-->
<!--      <el-table-column label="省" align="center" prop="cityName" show-overflow-tooltip/>
      <el-table-column label="市" align="center" prop="cityName" />
      <el-table-column label="区县编码" align="center" prop="countyCode" />-->
      <el-table-column label="城市" align="center" prop="cityName" />
      <el-table-column label="支持在线" align="center" prop="isSupportlive" >
        <template slot-scope="scope">
          <el-switch
            disabled
            v-model="scope.row.isSupportlive"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="球馆状态" align="center" prop="status" >
        <template slot-scope="scope">
          <el-tag  v-if="scope.row.status==1" style="color: #5a5e66">待审核</el-tag >
          <el-tag  v-if="scope.row.status==2" style="color:green">审核通过</el-tag >
          <el-tag  v-if="scope.row.status==3" style="color:red">审核拒绝</el-tag >
        </template>
      </el-table-column>
      <el-table-column label="地址" align="center" prop="address" show-overflow-tooltip/>
      <el-table-column label="备注" align="center" prop="remark" show-overflow-tooltip/>
      <el-table-column label="是否开放" align="center" prop="isOpen" >
        <template slot-scope="scope">
          <el-switch
            disabled
            v-model="scope.row.isOpen"
          ></el-switch>
        </template>
      </el-table-column>
<!--      <el-table-column label="人均价格" align="center" prop="mittelkurs" show-overflow-tooltip/>-->
      <el-table-column label="管理员二维码" align="center" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-image
          style="width: 100px; height: 100px"
          :src="scope.row.chatGroupUrl"
          :preview-src-list="[scope.row.chatGroupUrl]"
          :fit="imgfit"></el-image>
      </template>
      </el-table-column>
      <el-table-column label="创建人ID" align="center" prop="createdId" />
<!--      <el-table-column label="描述" align="center" prop="desc" show-overflow-tooltip/>-->
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="创建人" align="center" prop="createdBy" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:WxBuilding:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:WxBuilding:remove']"
          >删除</el-button>
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

    <!-- 添加或修改球场管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="55%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="名称" prop="buildingName">
          <el-input v-model="form.buildingName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="省" prop="provinceCode">
          <el-input v-model="form.provinceCode" placeholder="请输入省" />
        </el-form-item>
        <el-form-item label="市" prop="cityCode">
          <el-input v-model="form.cityCode" placeholder="请输入市" />
        </el-form-item>
        <el-form-item label="区县编码" prop="countyCode">
          <el-input v-model="form.countyCode" placeholder="请输入区县编码" />
        </el-form-item>

        <el-form-item label="在线地图" prop="onlineMap">
          <el-amap
            ref="map"
            :vid="'amapDemo'"
            :center="center"
            :zoom="zoom"
            :events="events"
            :plugin="plugin"
            class="amap-demo"
            style="height: 500px;width: 800px"
          >
            <el-amap-marker v-for="(u,i) in markers" :position="u.position" :key="i">
            </el-amap-marker>
          </el-amap>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="城市" prop="cityName">
          <el-input v-model="form.cityName" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="球场图片" prop="defaultPicture">
          <el-upload
            class="upload-demo"
            ref="upload"
            :action="uploadUrl"
            multiple
            :limit="5"
            :on-exceed = "handleExceed"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            :file-list="fileList"
            list-type="picture-card"
            :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;margin-top: 105px" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过2M</div>
          </el-upload>
        </el-form-item>

        <el-form-item label="支持在线" prop="isSupportlive">
          <el-switch v-model="form.isSupportlive" ></el-switch>
        </el-form-item>
        <el-form-item label="是否开放" prop="isOpen">
          <el-switch v-model="form.isOpen" ></el-switch>
        </el-form-item>
        <el-form-item label="人均价格" prop="mittelkurs">
          <el-input v-model="form.mittelkurs" placeholder="请输入人均价格" />
        </el-form-item>
        <el-form-item label="管理员二维码" prop="chatGroupUrl">
          <el-upload
            class="avatar-uploader"
            action="https://mall.lzsport.cn/lqwx/file/uploadMore"
            :show-file-list="false"
            name="files"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="描述" prop="desc">
          <el-input v-model="form.desc" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" disabled  >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="拒绝原因" prop="rejectReason">
          <el-input v-model="form.rejectReason" disabled />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 球场审批的对话框 -->
    <el-dialog :title="approveTitle" :visible.sync="approveOpen" width="55%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="名称" prop="buildingName">
          <el-input v-model="form.buildingName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="省" prop="provinceCode">
          <el-input v-model="form.provinceCode" placeholder="请输入省" />
        </el-form-item>
        <el-form-item label="市" prop="cityCode">
          <el-input v-model="form.cityCode" placeholder="请输入市" />
        </el-form-item>
        <el-form-item label="区县编码" prop="countyCode">
          <el-input v-model="form.countyCode" placeholder="请输入区县编码" />
        </el-form-item>

        <el-form-item label="在线地图" prop="onlineMap">
          <el-amap
            ref="map"
            :vid="'amapDemo'"
            :center="center"
            :zoom="zoom"
            :events="events"
            :plugin="plugin"
            class="amap-demo"
            style="height: 500px;width: 800px"
          >
            <el-amap-marker v-for="(u,i) in markers" :position="u.position" :key="i">
            </el-amap-marker>
          </el-amap>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="城市" prop="cityName">
          <el-input v-model="form.cityName" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="球场图片" prop="defaultPicture">
          <el-upload
            class="upload-demo"
            ref="upload"
            :action="uploadUrl"
            multiple
            :limit="5"
            :on-exceed = "handleExceed"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            :file-list="fileList"
            list-type="picture-card"
            :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;margin-top: 105px" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过2M</div>
          </el-upload>
        </el-form-item>

        <el-form-item label="支持在线" prop="isSupportlive">
          <el-switch v-model="form.isSupportlive" ></el-switch>
        </el-form-item>
        <el-form-item label="是否开放" prop="isOpen">
          <el-switch v-model="form.isOpen" ></el-switch>
        </el-form-item>
        <el-form-item label="人均价格" prop="mittelkurs">
          <el-input v-model="form.mittelkurs" placeholder="请输入人均价格" />
        </el-form-item>
        <el-form-item label="管理员二维码" prop="chatGroupUrl">
          <el-upload
            class="avatar-uploader"
            action="https://mall.lzsport.cn/lqwx/file/uploadMore"
            :show-file-list="false"
            name="files"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="描述" prop="desc">
          <el-input v-model="form.desc" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status"  >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="拒绝原因" prop="rejectReason">
          <el-input v-model="form.rejectReason" placeholder="请输入拒绝原因" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="图片预览" :visible.sync="imgPreviewDialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>
import { AMapManager } from "vue-amap";
import { listWxBuilding, getWxBuilding,batchUploadFiles, delWxBuilding, addWxBuilding, updateWxBuilding } from "@/api/system/WxBuilding";
let amapManager = new AMapManager();
export default {
  amapManager,
  name: "WxBuilding",
  data() {
    var me = this;
    me.city = '全国';
    return {
      geoCoder: null,
      // 搜索提示
      AutoComplete: null,
      center: [104.065861, 30.657401],
      zoom: 12,
      markers: [
        {
          position: [104.032651, 30.612437]
        },
      ],
      events: {
        init(o){
          console.log(o.getCenter());
          // o 是高德地图定位插件实例
        },
        zoomchange: (e) => {
          console.log(e);
        },
        zoomend: (e) => {
          //获取当前缩放zoom值
          console.log(this.$refs.map.$$getInstance().getZoom());
          console.log(e);
        },
        click: e => {
          this.markers[0].position = [e.lnglat.lng, e.lnglat.lat]
          this.form.longitude = e.lnglat.lng;
          this.form.latitude = e.lnglat.lat;
          let lnglat = [this.form.longitude, this.form.latitude];
          this.geoCoder.getAddress(lnglat, (status, result) => {
            if (status === "complete" && result.regeocode) {
              this.form.address = result.regeocode.formattedAddress;
            }
          });
        }
      },
      //使用其他组件
      plugin: [
        {
          pName: 'Scale',
          events: {
            init(instance) {
              console.log(instance)
            }
          }
        },
        {
          pName: 'ToolBar',
          events: {
            init(instance) {
              console.log(instance)
            }
          }
        },
        {
          pName: "Geocoder",
          events: {
            init(instance){
              me.geoCoder = new AMap.Geocoder({
                city: "010", //城市设为北京，默认：“全国”
                radius: 1000, //范围，默认：500
              });
              //地址逆解析插件
            }
          }
        }
      ],
      imageUrl:null,
      uploadUrl : "https://mall.lzsport.cn/lqwx/file/uploadMoreFiles",
      fileList:[],
      booleanOptions:[{
        value: true,
        label: '是'
      },{
        value: false,
        label: '否'
      }],
      options: [{
        value: 1,
        label: '待审核'
      }, {
        value: 2,
        label: '审核通过'
      }, {
        value: 3,
        label: '审核拒绝'
      }],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      imgfit:"fill",
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 球场管理表格数据
      WxBuildingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        createdTime: null,
        createdBy: null,
        buildingName: null,
        address: null,
        longitude: null,
        latitude: null,
        provinceCode: null,
        cityCode: null,
        countyCode: null,
        remark: null,
        cityName: null,
        status: null,
        isOpen: null,
        createdId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      imgPreviewDialogVisible:false,
      dialogImageUrl:null,
      approveTitle:null,
      approveOpen:false,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询球场管理列表 */
    getList() {
      this.loading = true;
      listWxBuilding(this.queryParams).then(response => {
        response.rows.forEach((item) => {
          let obj = item;
          obj.codeImgUrl = item.defaultPicture;
          if (item.defaultPicture){
            let arr = item.defaultPicture.split(",");
            obj.codeImgUrl = arr[0];
          }
          this.WxBuildingList.push(obj);
        });
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        isDeleted: null,
        createdTime: null,
        createdBy: null,
        modifiedBy: null,
        lastUpdatedTime: null,
        buildingName: null,
        address: null,
        longitude: null,
        latitude: null,
        provinceCode: null,
        cityCode: null,
        countyCode: null,
        remark: null,
        cityName: null,
        defaultPicture: null,
        isSupportlive: null,
        status: 0,
        rejectReason: null,
        isOpen: null,
        mittelkurs: null,
        chatGroupUrl: null,
        createdId: null,
        desc: null
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
      this.title = "添加球场管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      this.fileList = [];
      getWxBuilding(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.imageUrl = this.form.chatGroupUrl;
        var defaultPictureKeys = Object.keys(response.data.defaultPicture);
       // alert(defaultPictureKeys.length == 0);//true 即为空对象
        if(defaultPictureKeys.length > 0){
          var array = response.data.defaultPicture.split(",");//逗号是分隔符
          array.forEach((item) => {
            let imgItem = {url: item} //单个图片
            this.fileList.push(imgItem);
          });
        }

        this.title = "修改球场管理";
      });
    },
    //限制上传文件的个数提示
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 5 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`);
    },
    //删除之前执行的方法
    beforeRemove(file, fileList) {
      //移除文件
      //return this.$confirm(`确定移除 ${file.name}？`);
    },

    //文件删除的方法
    handleRemove(file, fileList) {
      //此处elementui会自动去掉删除的图片，fileList数组会自动清楚删除的图片的地址
      //所以fileList就是删除成功之后的数组，不需要在进行任何处理
      console.log(file, fileList);
      this.fileList = fileList;
    },
    //文件上传
    submitUpload() {
      let {uploadFiles, action, data} = this.$refs.upload
      let checkFileType = false
      let checkFileSize = false
      uploadFiles.forEach((item) => {
        if(item.raw){
          const isJPG = (item.raw.type === 'image/jpeg'||item.raw.type === 'image/png');
          const isLt2M = item.raw.size / 1024 / 1024 < 2;
          if (!isJPG) {
            checkFileType = true;
          }
          if (!isLt2M) {
            checkFileSize = true;
          }
        }
      });
      if(checkFileType){
        this.$message.error('上传头像图片只能是 JPG/PNG 格式!');
        return
      }
      if(checkFileSize){
        this.$message.error('上传头像图片大小不能超过 2MB!');
        return;
      }
      const formData = new FormData()
      uploadFiles.map(file => {
        formData.append("files",file.raw)
      })
      batchUploadFiles(formData,action).then(response => {
        console.info(response)
        response.data.forEach((item) => {
          let imgItem = {url: "https://mall.lzsport.cn/image/"+item} //单个图片
          this.fileList.push(imgItem);
        });
        this.$modal.msgSuccess("文件上传到服务器成功");
      }).catch(err=>{
       // this.$message.error(err.message);
        console.log(err);
      });
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.imgPreviewDialogVisible = true;
    },
    //审批
    handleApprovalUpdate(row){
      this.reset();
      const id = row.id || this.ids;
      this.fileList = [];
      getWxBuilding(id).then(response => {
        this.form = response.data;
        this.approveOpen = true;
        this.imageUrl = this.form.chatGroupUrl;
        var defaultPictureKeys = Object.keys(response.data.defaultPicture);
        // alert(defaultPictureKeys.length == 0);//true 即为空对象
        if(defaultPictureKeys.length > 0){
          var array = response.data.defaultPicture.split(",");//逗号是分隔符
          array.forEach((item) => {
            let imgItem = {url: item} //单个图片
            this.fileList.push(imgItem);
          });
        }
        this.approveTitle = "球场审批";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          let imgs = [];
          //重新设置图片格式
          this.fileList.forEach((item) => {
            imgs.push(item.url);
          });
          this.form.defaultPicture = imgs.join(',')
          if (this.form.id != null) {
            updateWxBuilding(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWxBuilding(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除球场管理编号为"' + ids + '"的数据项？').then(function() {
        return delWxBuilding(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      let imgUrl = res.data[0];
      this.form.chatGroupUrl = "https://mall.lzsport.cn/image/"+imgUrl;
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
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/WxBuilding/export', {
        ...this.queryParams
      }, `WxBuilding_${new Date().getTime()}.xlsx`)
    },
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
