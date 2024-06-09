<template>
  <div>
    <!---搜索栏-->
    <el-card id="search">
      <el-input v-model="searchModel.applicant" placeholder="申请人" clearable />
      <el-input v-model="searchModel.approval" placeholder="审批人" clearable />
      <el-button type="primary" round icon="el-icon-search" @click="getLeaveAppList">查询</el-button>
    </el-card>
    <!--结果列表-->
    <el-card>
      <el-table :data="applicantlist" style="width: 100%">
        <el-table-column label="#" width="180">
          <template slot-scope="scope">
            <!---（pageNo-1）*pageSize+index+1--->
            {{ (searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="id" label="id" v-if="false">
        </el-table-column>
        <el-table-column prop="applicant" label="申请人" width="180">
        </el-table-column>
        <el-table-column prop="btime" label="开始时间" width="180">
        </el-table-column>
        <el-table-column prop="btime" label="结束时间" width="180">
        </el-table-column>
        <el-table-column prop="reason" label="原因" width="180">
        </el-table-column>
        <el-table-column prop="yn" label="批准状态" width="180">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.yn === '通过'" type="success">通过</el-tag>
            <el-tag v-if="scope.row.yn === '未批改'" type="info">未批</el-tag>
            <el-tag v-if="scope.row.yn === '未通过'" type="danger">未通过</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="审批" width="180">
          <template slot-scope="scope">
            <el-button @click="check(scope.row.id)" type="primary" icon="el-icon-edit" circle></el-button>
            <el-button @click="delet(scope.row.id)" type="danger" icon="el-icon-delete" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!--分页栏-->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage4"
      :page-sizes="[5, 8, 10, 20]" :page-size="searchModel.pageSize" layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
    <!--审批对话框-->
    <el-dialog title="请假申请" :visible.sync="dialogFormVisible" @close="clearForm">
      <el-form ref="checkformref" :model="applicantform" :rules="rules">
        <el-form-item label="申请人" :label-width="formLabelWidth" prop="applicant" :editable="false">
          <el-input v-model="applicantform.applicant" autocomplete="off" />
        </el-form-item>
        <el-form-item label="开始时间" :label-width="formLabelWidth" prop="btime" :editable="false">
          <el-date-picker v-model="applicantform.btime" type="datetime" placeholder="选择日期时间" />
        </el-form-item>
        <el-form-item label="结束时间" :label-width="formLabelWidth" prop="etime" :editable="false">
          <el-date-picker v-model="applicantform.etime" type="datetime" placeholder="选择日期时间" />
        </el-form-item>
        <el-form-item label="原因" :label-width="formLabelWidth" prop="reason" :editable="false">
          <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="applicantform.reason" />
        </el-form-item>
        <el-form-item label="审批:" :label-width="formLabelWidth" prop="yn">
          <template slot-scope="scope">
            <el-radio v-model="applicantform.yn" label="通过">通过</el-radio>
            <el-radio v-model="applicantform.yn" label="未通过">未通过</el-radio>
          </template>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveApplication">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import leaveappApi from '@/api/leapp'
export default {
  data() {
    return {
      dialogFormVisible: false,
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      applicantlist: [],
      applicantform: {}
    }
  },
  methods: {
    delet(id) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        leaveappApi.deleteLeaveapplication(id)
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
        this.getLeaveAppList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    saveApplication() {
      leaveappApi.saveApplication(this.applicantform).then(res => {
        // 成功提示，关闭对话框，刷新数据
        this.$message({
          message: '成功审批',
          type: 'success'
        })
        this.dialogFormVisible = false
        // 刷新
        this.getLeaveAppList()
      })
    },
    check(id) {
      leaveappApi.getLeaveapplicationByid(id).then(response => {
        this.applicantform = response.data
      })
      this.dialogFormVisible = true
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getLeaveAppList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getLeaveAppList()
    },
    getLeaveAppList() {
      /** 调用api/leapp.js的方法 */
      leaveappApi.getLeaveAppList(this.searchModel).then(res => {
        this.applicantlist = res.data.rows
        this.total = res.data.total
      })
    }
  },
  created() {
    /** 调用上面method的方法 */
    this.getLeaveAppList()
  }
}
</script>
<style>
#search .el-input {
  width: 200px;
  margin-right: 100px;
}

.el-card {
  margin: 10px;
}
</style>
