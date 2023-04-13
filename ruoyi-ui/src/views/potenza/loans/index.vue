<template>
    <div class="from">

        <div>

        </div>
        <div>
            <h1>贷款中心</h1>

            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="借款金额" prop="borrowerMoney">
                    <el-input v-model="ruleForm.borrowerMoney"></el-input>
                </el-form-item>
                <el-form-item label="借款周期" prop="periodsId">
                    <el-select v-model="ruleForm.periodsId" placeholder="请选择">
                        <el-option v-for="item in periods" :key="item.periodsId" :label="item.periodsName"
                            :value="item.periodsId">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="借款方式" prop="wayId">
                    <el-select v-model="ruleForm.wayId" placeholder="请选择方式">
                        <el-option label="等额本息" value=0></el-option>
                        <el-option label="等额本金" value=1></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <h4>总还款金额:{{ borrowerMoney }}</h4>
                </el-form-item>
                <el-form-item label="借款用途" prop="borrowerPurpose">
                    <el-input v-model="ruleForm.borrowerPurpose"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm(ruleForm)">借款</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>


import { listPeriods, insertBorrower } from "@/api/potenza/periods";
import { detail } from "@/api/potenza/periods";

export default {
    data() {
        return {
            ruleForm: {
                borrowerMoney: '',
                periodsId: null,
                borrowerPurpose: '',
                wayId: null,
                userName: '',
                userId: null,
                productId: null

            },
            periods: [],
            timer: '',
            borrowerMoney: '',
            rules: {
                borrowerMoney: [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
                periodsId: [{ required: true, message: '请输入借款周期', trigger: 'blur' }],
                borrowerPurpose: [{ required: true, message: '请输入借款方式', trigger: 'blur' }],
                wayId: [{ required: true, message: '请输入借款用途', trigger: 'blur' }],
            }

        }
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    const username = this.$store.state.user.name;
                    this.ruleForm.userName = username
                    insertBorrower(this.ruleForm).then(respon => {
                        console.log(respon)
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        listPeriods() {
            listPeriods().then(response => {
                console.log(response.rows)
                this.periods = response.rows
            });
        },
        valChange() {
            if (this.ruleForm.borrowerMoney != '' && this.ruleForm.periodsId != null && this.ruleForm.wayId != null) {
                let pram = {}
                pram.borrowerMoney = this.ruleForm.borrowerMoney
                pram.periodsId = this.ruleForm.periodsId
                pram.wayId = this.ruleForm.wayId
                console.log(pram)
                detail(pram).then(respon => {

                    this.borrowerMoney = respon.data
                })


            }
        }
    },
    created() {
        this.listPeriods()
        this.timer = setInterval(this.valChange, 2000);
    },

}
</script>

<style>
.from {
    margin: auto;
    width: 40%;
}
</style>