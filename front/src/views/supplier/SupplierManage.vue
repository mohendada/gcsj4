<template>
  <div>
    <div style="margin-bottom: 5px;">
      <el-input v-model="supplierName" placeholder="请输入公司名" suffix-icon="el-icon-search" style="width: 200px;"
                @keyup.enter.native="loadPost"></el-input>
      <!--            <el-select v-model="sex" filterable placeholder="请选择性别" style="margin-left: 5px;">-->
      <!--                <el-option-->
      <!--                        v-for="item in sexs"-->
      <!--                        :key="item.value"-->
      <!--                        :label="item.label"-->
      <!--                        :value="item.value">-->
      <!--                </el-option>-->
      <!--            </el-select>-->
      <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
      <el-button type="success" @click="resetParam">重置</el-button>

      <el-button type="primary" style="margin-left: 5px;" @click="add">新增</el-button>
    </div>
    <el-table :data="tableData"
              :header-cell-style="{ background: '#f2f5fc', color: '#555555' }"
              border
    >
      <el-table-column prop="supplierId" label="供应商编号" width="300">
      </el-table-column>
      <el-table-column prop="supplierAddress" label="供货商地址" width="300">
      </el-table-column>
      <el-table-column prop="supplierName" label="公司名称" width="300">
      </el-table-column>
      <!--            <el-table-column prop="age" label="年龄" width="80">-->
      <!--            </el-table-column>-->
      <!--            <el-table-column prop="sex" label="性别" width="80">-->
      <!--                <template slot-scope="scope">-->
      <!--                    <el-tag-->
      <!--                            :type="scope.row.sex === 1 ? 'primary' : 'success'"-->
      <!--                            disable-transitions>{{scope.row.sex === 1 ? '男' : '女'}}</el-tag>-->
      <!--                </template>-->
      <!--            </el-table-column>-->
      <!--            <el-table-column prop="roleId" label="角色" width="120">-->
      <!--                <template slot-scope="scope">-->
      <!--                    <el-tag-->
      <!--                            :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"-->
      <!--                            disable-transitions>{{scope.row.roleId === 0 ? '超级管理员' :-->
      <!--                        (scope.row.roleId === 1 ? '管理员' : '用户')}}</el-tag>-->
      <!--                </template>-->
      <!--            </el-table-column>-->
      <el-table-column prop="supplierPhoneNumber" label="电话" width="300">
      </el-table-column>
      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @onConfirm="del(scope.row.supplierId)"
              style="margin-left: 5px;"
          >
            <el-button slot="reference" size="small" type="danger">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 20,30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

    <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>

      <el-form ref="form" :rules="rules" :model="form" label-width="100px">

<!--        <el-form-item label="供应商编号" prop="supplierId">-->
<!--          <el-col :span="30">-->
<!--            <el-input v-model="form.supplierId"></el-input>-->
<!--          </el-col>-->
<!--        </el-form-item>-->

        <el-form-item label="供货商地址" prop="supplierAddress">
          <el-col :span="30">
            <el-input v-model="form.supplierAddress"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="公司名称" prop="supplierName">
          <el-col :span="30">
            <el-input v-model="form.supplierName"></el-input>
          </el-col>
        </el-form-item>

        <!--                <el-form-item label="密码" prop="password">-->
        <!--                    <el-col :span="20">-->
        <!--                        <el-input v-model="form.password"></el-input>-->
        <!--                    </el-col>-->
        <!--                </el-form-item>-->
        <!--                <el-form-item label="年龄" prop="age">-->
        <!--                    <el-col :span="20">-->
        <!--                        <el-input v-model="form.age"></el-input>-->
        <!--                    </el-col>-->
        <!--                </el-form-item>-->
        <!--                <el-form-item label="性别">-->
        <!--                    <el-radio-group v-model="form.sex">-->
        <!--                        <el-radio label="1">男</el-radio>-->
        <!--                        <el-radio label="0">女</el-radio>-->
        <!--                    </el-radio-group>-->
        <!--                </el-form-item>-->
        <el-form-item label="电话" prop="supplierPhoneNumber">
          <el-col :span="30">
            <el-input v-model="form.supplierPhoneNumber"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
export default {

  data() {
    // let checkAge = (rule, value, callback) => {
    //   if (value > 150) {
    //     callback(new Error('年龄输入过大'));
    //   } else {
    //     callback();
    //   }
    // };
    let checkName  = (rule, value, callback) => {
      for (let i = 0; i < this.tableData.length; i++) {
        if (this.tableData[i].supplierName == value){
          callback(new Error('公司名重复'));
          break
        }
      }
      callback()
    };

    return {
      tableData: [],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      supplierName: '',
      // sex: '',
      // sexs: [
      //   {
      //     value: '1',
      //     label: '男'
      //   }, {
      //     value: '0',
      //     label: '女'
      //   }
      // ],
      centerDialogVisible: false,
      val1:'',

      form: {
        supplierId: '',
        supplierAddress:'',
        // no:'',
        supplierName: '',
        // password:'',
        // age:'',
        supplierPhoneNumber: '',
        // sex:'0',
        // roleId:'1'
        val:''
      },
      rules: {
        supplierAddress: [
          {required: true, message: '请输入公司地址', trigger: 'blur'},

        ],
        supplierName: [
          {required: true, message: '请输入公司名称', trigger: 'blur'},
          {validator: checkName , trigger: 'blur'}
        ],
        // password: [
        //     {required: true, message: '请输入密码', trigger: 'blur'},
        //     {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        // ],
        // age: [
        //     {required: true, message: '请输入年龄', trigger: 'blur'},
        //     {min: 1, max: 3, message: '长度在 1 到 3 个位', trigger: 'blur'},
        //     {pattern: /^([1-9][0-9]*){1,3}$/,message: '年龄必须为正整数字',trigger: "blur"},
        //     {validator:checkAge,trigger: 'blur'}
        // ],

        //
        // supplierPhoneNumber: [
        //   {required: true, message: "手机号不能为空", trigger: "blur"},
        //   {pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur"}
        // ]
        //

      }
    }
  },
  methods: {
    resetForm() {
      this.$refs.form.resetFields();
    },
    del(supplierId) {
      console.log(supplierId)
      console.log(this.$httpUrl + '/supplier/del?supplierId=' + supplierId)
      this.$axios.get(this.$httpUrl + '/supplier/del?supplierId=' + supplierId).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.loadPost()
        } else {
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    mod(row) {
      console.log(row)
      this.val1=2
      this.centerDialogVisible = true
      this.$nextTick(() => {
        //赋值到表单
        this.form.supplierId = row.supplierId
        this.form.supplierAddress = row.supplierAddress
        this.form.supplierName = row.supplierName
        // this.form.password = ''
        // this.form.age = row.age + ''
        // this.form.sex = row.sex + ''
        this.form.supplierPhoneNumber = row.supplierPhoneNumber
        // this.form.roleId = row.roleId
      })
    },
    add() {
      this.val1=1
      this.form.supplierId = ''
      this.centerDialogVisible = true
      this.$nextTick(() => {
        this.resetForm()
      })

    },
    doSave() {
      console.log(this.$httpUrl + '/supplier/save')
      this.$axios.post(this.$httpUrl + '/supplier/save', this.form).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.centerDialogVisible = false
          this.loadPost()
          this.resetForm()
        } else {
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    doMod() {
      this.$axios.post(this.$httpUrl + '/supplier/update', this.form).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.centerDialogVisible = false
          this.loadPost()
          this.resetForm()
        } else {
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
    },
    save() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.val1 == 2) {
            // this.doSave();
            console.log("do mod")
            this.doMod();
          }
          else if(this.val1 == 1){
            console.log("do save")
            this.doSave();
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1
      this.pageSize = val
      this.loadPost()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val
      this.loadPost()
    },
    loadGet() {
      this.$axios.get(this.$httpUrl + '/supplier/list').then(res => res.data).then(res => {
        console.log(res)
      })
    },
    resetParam() {
      // this.supplierId = ''
      this.supplierName = ''
      // this.supplierPhoneNumber = ''
      // this.supplierAddress = ''
    },
    loadPost() {
      console.log(this.$httpUrl + '/supplier/listPageC1')
      this.$axios.post(this.$httpUrl + '/supplier/listPageC1', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param: {
          supplierId: this.supplierId,
          supplierName: this.supplierName,
          supplierAddress : this.supplierAddress,
          supplierPhoneNumber : this.supplierPhoneNumber
          // sex:this.sex,
          // roleId: '1'
        }
      }).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {
          this.tableData = res.data
          this.total = res.total
        } else {
          alert('获取数据失败')
        }

      })
    }
  },
  beforeMount() {
    //this.loadGet();
    this.loadPost()
  }
}
</script>

<style scoped>

</style>
