<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
            label-width="68px">

            <el-form-item label="用户名称" prop="userName">
                <el-input v-model="queryParams.userName" placeholder="请输入用户名称" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>



            <el-form-item label="贷款方式ID " prop="wayId">
                <el-input v-model="queryParams.wayId" placeholder="请输入贷款方式ID " clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="借款用途" prop="borrowerPurpose">
                <el-input v-model="queryParams.borrowerPurpose" placeholder="请输入借款用途" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="  1： 待审核  2：贷款成功 3:贷款失败" prop="borrowerState">
                <el-input v-model="queryParams.borrowerState" placeholder="请输入  1： 待审核  2：贷款成功 3:贷款失败" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="放款人" prop="loanerId">
                <el-input v-model="queryParams.loanerId" placeholder="请输入放款人" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                    v-hasPermi="['potenza:borrower:add']">新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
                    v-hasPermi="['potenza:borrower:edit']">修改</el-button>
            </el-col>

            <el-col :span="1.5">
                <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                    v-hasPermi="['potenza:borrower:export']">导出</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="borrowerList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="${comment}" align="center" prop="borrowerId" />
            <el-table-column label="用户ID" align="center" prop="userId" />
            <el-table-column label="用户名称" align="center" prop="userName" />
            <el-table-column label="产品ID " align="center" prop="productId" />
            <el-table-column label="默认为最高可以借款金额" align="center" prop="borrowerMoney" />
            <el-table-column label="贷款周期ID" align="center" prop="periodsId" />
            <el-table-column label="当款方式" align="center" prop="wayId" />
            <el-table-column label="借款用途" align="center" prop="borrowerPurpose" />
            <el-table-column label="  1： 待审核  2：贷款成功 3:贷款失败" align="center" prop="borrowerState" />
            <el-table-column label="放款人" align="center" prop="loanerId" />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                        v-hasPermi="['potenza:borrower:edit']">修改</el-button>

                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize" @pagination="getList" />

        <!-- 添加或修改贷款对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="用户ID" prop="userId">
                    <el-input v-model="form.userId" placeholder="请输入用户ID" />
                </el-form-item>
                <el-form-item label="用户名称" prop="userName">
                    <el-input v-model="form.userName" placeholder="请输入用户名称" />
                </el-form-item>
                <el-form-item label="产品ID " prop="productId">
                    <el-input v-model="form.productId" placeholder="请输入产品ID " />
                </el-form-item>
                <el-form-item label="默认为最高可以借款金额" prop="borrowerMoney">
                    <el-input v-model="form.borrowerMoney" placeholder="请输入默认为最高可以借款金额" />
                </el-form-item>
                <el-form-item label="贷款周期ID" prop="periodsId">
                    <el-input v-model="form.periodsId" placeholder="请输入贷款周期ID" />
                </el-form-item>
                <el-form-item label="贷款方式ID " prop="wayId">
                    <el-input v-model="form.wayId" placeholder="请输入贷款方式ID" />
                </el-form-item>
                <el-form-item label="借款用途" prop="borrowerPurpose">
                    <el-input v-model="form.borrowerPurpose" placeholder="请输入借款用途" />
                </el-form-item>
                <el-form-item label="  1： 待审核  2：贷款成功 3:贷款失败" prop="borrowerState">
                    <el-input v-model="form.borrowerState" placeholder="请输入  1： 待审核  2：贷款成功 3:贷款失败" />
                </el-form-item>
                <el-form-item label="放款人" prop="loanerId">
                    <el-input v-model="form.loanerId" placeholder="请输入放款人" />
                </el-form-item>
                <el-form-item label="删除状态0：存在，2：删除" prop="delFlag">
                    <el-input v-model="form.delFlag" placeholder="请输入删除状态0：存在，2：删除" />
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
import { listBorrower, getBorrower, addBorrower, updateBorrower } from "@/api/potenza/borrower";

export default {
    name: "Borrower",
    data() {
        return {
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
            // 贷款表格数据
            borrowerList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 5,
                userId: null,
                userName: null,
                productId: null,
                borrowerMoney: null,
                periodsId: null,
                wayId: null,
                borrowerPurpose: null,
                borrowerState: null,
                loanerId: null,
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
                userId: [
                    { required: true, message: "用户ID不能为空", trigger: "blur" }
                ],
                userName: [
                    { required: true, message: "用户名称不能为空", trigger: "blur" }
                ],
                productId: [
                    { required: true, message: "产品ID 不能为空", trigger: "blur" }
                ],
                periodsId: [
                    { required: true, message: "贷款周期ID不能为空", trigger: "blur" }
                ],
                wayId: [
                    { required: true, message: "贷款方式ID 0:等额本息 6000   1:等额本金 6000 1000 第一个月60000*利率 第二个月 5000*利率不能为空", trigger: "blur" }
                ],
                borrowerPurpose: [
                    { required: true, message: "借款用途不能为空", trigger: "blur" }
                ],
                loanerId: [
                    { required: true, message: "放款人不能为空", trigger: "blur" }
                ],
            }
        };
    },
    created() {
        this.getList();
        const username = this.$store.state.user.name;
        console.log(username)
    },
    methods: {
        /** 查询贷款列表 */
        getList() {
            this.loading = true;
            listBorrower(this.queryParams).then(response => {
                this.borrowerList = response.rows;
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
                borrowerId: null,
                userId: null,
                userName: null,
                productId: null,
                borrowerMoney: null,
                periodsId: null,
                wayId: null,
                borrowerPurpose: null,
                borrowerState: null,
                loanerId: null,
                createBy: null,
                updateBy: null,
                delFlag: null,
                createTime: null,
                updateTime: null
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
            this.ids = selection.map(item => item.borrowerId)
            this.single = selection.length !== 1
            this.multiple = !selection.length
        },
        /** 新增按钮操作 */
        handleAdd() {
            this.reset();
            this.open = true;
            this.title = "添加贷款";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const borrowerId = row.borrowerId || this.ids
            getBorrower(borrowerId).then(response => {
                this.form = response.data;
                this.open = true;
                this.title = "修改贷款";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.borrowerId != null) {
                        updateBorrower(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addBorrower(this.form).then(response => {
                            this.$modal.msgSuccess("新增成功");
                            this.open = false;
                            this.getList();
                        });
                    }
                }
            });
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('potenza/borrower/export', {
                ...this.queryParams
            }, `borrower_${new Date().getTime()}.xlsx`)
        }
    }
};
</script>
