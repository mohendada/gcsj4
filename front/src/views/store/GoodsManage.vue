<template>
  <div>
    <div style="margin-bottom: 5px;">
      <el-input v-model="goodsName" placeholder="请输入物品名" suffix-icon="el-icon-search" style="width: 200px;"
                @keyup.enter.native="loadPost"></el-input>
      <el-select v-model="goodsStoreId" placeholder="请选择仓库" style="margin-left: 5px;">
        <el-option
            v-for="item in storageData"
            :key="item.id"
            :label="item.id"
            :value="item.id">
        </el-option>
      </el-select>
      <el-select v-model="goodsKind" placeholder="请选择分类" style="margin-left: 5px;">
        <el-option
            v-for="item in goodstypeData"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>

      <el-button type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
      <el-button type="success" @click="resetParam">重置</el-button>

      <el-button type="primary" style="margin-left: 5px;" @click="add">新增</el-button>
      <el-button type="primary" style="margin-left: 5px;" @click="inGoods">入库</el-button>
      <el-button type="primary" style="margin-left: 5px;" @click="outGoods">出库</el-button>
    </div>

    <el-table :data="tableData"
              :header-cell-style="{ background: '#f2f5fc', color: '#555555' }"
              border
              highlight-current-row
              @current-change="selectCurrentChange"
    >
      <!--          id-->
      <el-table-column prop="goodsId" label="商品ID" width="200">
      </el-table-column>
      <!--           name-->
      <el-table-column prop="goodsName" label="物品名" width="180">
      </el-table-column>
      <el-table-column prop="goodsShelfId" label="货架编号" width="180">
      </el-table-column>
      <el-table-column prop="goodsShelfFloor" label="层号" width="180">
      </el-table-column>
      <!--          storage-->
      <!--      <el-table-column prop="goodsStoreId" label="仓库" width="180" :formatter="formatStorage">-->
      <!--      </el-table-column>-->
      <el-table-column prop="goodsStoreId" label="仓库" width="180">
      </el-table-column>
      <!--          goodtype-->
      <!--      <el-table-column prop="goodsKind" label="分类" width="180" :formatter="formatGoodstype">-->
      <!--      </el-table-column>-->
      <el-table-column prop="goodsKind" label="分类" width="180">
      </el-table-column>
      <!--          count-->
      <el-table-column prop="goodsNumber" label="数量" width="180">
      </el-table-column>
      <!--      &lt;!&ndash;          remark&ndash;&gt;-->
      <!--&lt;!&ndash;      <el-table-column prop="remark" label="备注">&ndash;&gt;-->
      <!--&lt;!&ndash;      </el-table-column>&ndash;&gt;-->

      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
              @onConfirm="del(scope.row.goodsId)"
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
        title="物品维护"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>

      <el-form ref="form" :rules="rules" :model="form" label-width="100px">
        <el-form-item label="物品名" prop="goodsName">
          <el-col :span="20">
            <el-input v-model="form.goodsName"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="货架编号" prop="goodsShelfId">
          <el-col :span="20">
            <el-input v-model="form.goodsShelfId"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="层号" prop="goodsShelfFloor">
          <el-col :span="20">
            <el-input v-model="form.goodsShelfFloor"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="仓库" prop="goodsStoreId">
          <el-col :span="20">
            <el-select v-model="form.goodsStoreId" placeholder="请选择仓库" style="margin-left: 5px;">
              <el-option
                  v-for="item in storageData"
                  :key="item.id"
                  :label="item.id"
                  :value="item.id">
              </el-option>
            </el-select>

          </el-col>
        </el-form-item>
        <el-form-item label="分类" prop="goodsKind">
          <el-col :span="20">
            <el-select v-model="form.goodsKind" placeholder="请选择分类" style="margin-left: 5px;">
              <el-option
                  v-for="item in goodstypeData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>

        <el-form-item label="数量" prop="goodsNumber">
          <el-col :span="20">
            <el-input v-model="form.goodsNumber"></el-input>
          </el-col>
        </el-form-item>
        <!--        <el-form-item label="备注" prop="remark">-->
        <!--          <el-col :span="20">-->
        <!--            <el-input type="textarea" v-model="form.remark"></el-input>-->
        <!--          </el-col>-->
        <!--        </el-form-item>-->
      </el-form>


      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
    </el-dialog>

    <el-dialog
        title="出入库"
        :visible.sync="inDialogVisible"
        width="30%"
        center>

      <el-dialog
          width="75%"
          title="供应商选择"
          :visible.sync="innerVisible"
          append-to-body>
        <SelectUser @doSelectUser="doSelectUser"></SelectUser>
        <span slot="footer" class="dialog-footer">
                    <el-button @click="innerVisible = false">取 消</el-button>
                    <el-button type="primary" @click="confirmUser">确 定</el-button>
                  </span>
      </el-dialog>

      <el-form ref="form1" :rules="rules1" :model="form1" label-width="80px">
        <el-form-item label="物品名">
          <el-col :span="20">
            <el-input v-model="form1.goodsName" readonly></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="申请人">
          <el-col :span="20">
            <el-input v-model="form1.supplierName" readonly @click.native="selectUser"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="数量" prop="goodsNumber">
          <el-col :span="20">
            <el-input v-model="form1.goodsNumber"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input type="textarea" v-model="form1.remark"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="inDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doInGoods">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>


<script>
import SelectUser from "@/views/store/SelectUser.vue";

export default {
  name:"GoodsManage",
  components: {SelectUser},
  data() {
    let checkCount = (rule, value, callback) => {
      if (value > 9999) {
        callback(new Error('数量输入过大'));
      } else {
        callback();
      }
    };

    let checkName  = (rule, value, callback) => {
      for (let i = 0; i < this.tableData.length; i++) {
        if (this.tableData[i].goodsName == value){
          callback(new Error('名字重复'));
          break
        }
      }
      callback()
    };

    return {
      // user: JSON.parse(sessionStorage.getItem('CurUser')),
      storageData: [],
      goodstypeData: [],
      tableData: [],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      goodsId: '',
      goodsName: '',
      goodsStoreId: '',
      goodsShelfId: '',
      goodsKind: '',
      centerDialogVisible: false,
      inDialogVisible: false,
      innerVisible: false,
      currentRow: {},
      tempUser: {},
      val: '',
      form: {
        goodsId: '',
        goodsName: '',
        goodsShelfId: '',
        goodsShelfFloor: '',
        goodsStoreId: '',
        goodsKind: '',
        goodsNumber: '',
        // count:''
      },
      form1: {
        // goods: '',
        goodsId: '',
        goodsName: '',
        goodsNumber: '',
        supplierName: '',
        goodsStoreId: '',
        goodsKind: '',
        // userid: '',
        // adminId: '',
        remark: '',
        action: '1'
      },
      rules1: {},
      rules: {
        goodsName: [
          {required: true, message: '请输入物品名', trigger: 'blur'},
          {validator: checkName, trigger: 'blur'}
        ],
        goodsStoreId: [
          {required: true, message: '请选择仓库', trigger: 'blur'}
        ],
        goodsKind: [
          {required: true, message: '请选择分类', trigger: 'blur'}
        ],
        goodsNumber: [
          {required: true, message: '请输入数量', trigger: 'blur'},
          {pattern: /^([1-9][0-9]*){1,4}$/, message: '数量必须为正整数字', trigger: "blur"},
          {validator: checkCount, trigger: 'blur'}
        ],
        goodsShelfId: [
          {required: true, message: "请输入货架号", trigger: 'blur' },
          {pattern: /^[0-9]*[1-9][0-9]*$/, message:"必须为整数", trigger: 'blur' },
          {validator: checkCount, trigger: 'blur'}
        ],
        goodsShelfFloor: [
          { required: true, message: "请输入层号", trigger: 'blur'},
          {pattern: /^[0-9]*[1-9][0-9]*$/, message:"必须为整数", trigger: 'blur' },
          {validator: checkCount, trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    doSelectUser(val) {
      console.log(val)
      this.tempUser = val
    },
    confirmUser() {
      this.form1.supplierName = this.tempUser.supplierName
      this.form1.supplierId = this.tempUser.supplierId

      this.innerVisible = false
    },
    selectCurrentChange(val) {
      this.currentRow = val;
    },
    formatStorage(row) {
      let temp = this.storageData.find(item => {
        return item.id == row.storage
      })

      return temp && temp.name
    },
    formatGoodstype(row) {
      let temp = this.goodstypeData.find(item => {
        return item.id == row.goodstype
      })

      return temp && temp.name
    },
    resetForm() {
      this.$refs.form.resetFields();
    },
    resetInForm() {
      this.$refs.form1.resetFields();
    },
    del(id) {
      this.$axios.get(this.$httpUrl + '/store/del?id=' + id).then(res => res.data).then(res => {
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
      this.val = 2
      this.centerDialogVisible = true
      this.$nextTick(() => {
        //赋值到表单
        this.form.goodsId = row.goodsId
        this.form.name = row.name
        this.form.storage = row.storage
        this.form.goodstype = row.goodstype
        this.form.count = row.count
        this.form.remark = row.remark
      })
    },
    add() {
      this.val = 1
      this.centerDialogVisible = true
      this.$nextTick(() => {
        this.resetForm()

        this.form.goodsStoreId = ''
      })

    },
    inGoods() {
      if (!this.currentRow.goodsId) {
        alert('请选择记录');
        return;
      }

      this.inDialogVisible = true
      this.$nextTick(() => {
        this.resetInForm()
      })
      this.form1.goodsKind = this.currentRow.goodsKind
      this.form1.goodsStoreId = this.currentRow.goodsStoreId
      this.form1.goodsName = this.currentRow.goodsName
      this.form1.goodsId = this.currentRow.goodsId
      // this.form1.adminId = this.user.id
      this.form1.action = '1'
    },

    outGoods() {
      if (!this.currentRow.goodsId) {
        alert('请选择记录');
        return;
      }

      this.inDialogVisible = true
      this.$nextTick(() => {
        this.resetInForm()
      })
      this.form1.goodsKind = this.currentRow.goodsKind
      this.form1.goodsStoreId = this.currentRow.goodsStoreId
      this.form1.goodsName = this.currentRow.goodsName
      this.form1.goodsId = this.currentRow.goodsId
      // this.form1.adminId = this.user.id
      this.form1.action = '2'

    },
    selectUser() {
      this.innerVisible = true
    },
    doSave() {
      this.$axios.post(this.$httpUrl + '/store/save', this.form).then(res => res.data).then(res => {
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
      this.$axios.post(this.$httpUrl + '/store/update', this.form).then(res => res.data).then(res => {
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
          if (this.val == 2) {
            this.doMod();
          } else if (this.val == 1) {
            this.doSave();
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    doInGoods() {

      this.$axios.post(this.$httpUrl + '/record/save', this.form1).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {

          this.$message({
            message: '操作成功！',
            type: 'success'
          });
          this.inDialogVisible = false
          this.loadPost()
          this.resetInForm()
        } else {
          this.$message({
            message: '操作失败！',
            type: 'error'
          });
        }

      })
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
    resetParam() {
      this.goodsName = ''
      this.goodsStoreId = ''
      this.goodsKind = ''
    },
    loadPost() {
      this.$axios.post(this.$httpUrl + '/store/listPage', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param: {
          goodsName: this.goodsName + '',
          goodsKind: this.goodsKind + '',
          goodsStoreId: this.goodsStoreId + ''
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
    },
    loadStorage() {
      this.$axios.get(this.$httpUrl + '/storage/list').then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {
          this.storageData = res.data
        } else {
          alert('获取数据失败')
        }

      })
    },
    loadGoodstype() {
      this.$axios.get(this.$httpUrl + '/goodstype/list').then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {
          this.goodstypeData = res.data
        } else {
          alert('获取数据失败')
        }

      })
    }
  },
  beforeMount() {
    this.loadStorage()
    this.loadGoodstype()
    this.loadPost()

  }
}
</script>

<style scoped>

</style>
