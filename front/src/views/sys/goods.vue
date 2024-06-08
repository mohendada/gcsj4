<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-input v-model="searchModel.goodsName" placeholder="商品名称" clearable/>
      <el-input v-model="searchModel.supplierId" placeholder="供应商ID" clearable/>
      <el-button type="primary" round icon="el-icon-search" @click="getGoodsList">查询</el-button>
      <el-button type="primary" icon="el-icon-circle-plus" @click="openAdd(null)">新增</el-button>
    </el-card>
    <!-- 结果列表 -->
    <el-card>
      <el-table :data="goodsList" style="width: 100%">
        <el-table-column label="#" width="180">
          <template slot-scope="scope">
            {{ (searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="goodsId" label="商品ID" width="180"></el-table-column>
        <el-table-column prop="goodsName" label="商品名称" width="180"></el-table-column>
        <el-table-column prop="goodsPrice" label="商品价格" width="180"></el-table-column>
        <el-table-column prop="goodsPhoto" label="商品图片" width="180"></el-table-column>
        <el-table-column prop="supplierId" label="供应商ID" width="180"></el-table-column>
        <el-table-column prop="goodsStatus" label="状态" width="180">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.goodsStatus === 0" type="info">未上架</el-tag>
            <el-tag v-if="scope.row.goodsStatus === 1" type="success">已上架</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button @click="openAdd(scope.row.goodsId)" type="primary" icon="el-icon-edit" circle></el-button>
            <el-button @click="deleteGoods(scope.row.goodsId)" type="danger" icon="el-icon-delete" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 分页栏 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :current-page="searchModel.pageNo" :page-sizes="[5, 8, 10, 20]" :page-size="searchModel.pageSize"
                   layout="total, sizes, prev, pager, next, jumper" :total="total"/>
    <!-- 新增或修改商品 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" @close="clearForm">
      <el-form ref="goodsFormRef" :model="goodsForm" :rules="rules">
        <el-form-item label="商品名称" :label-width="formLabelWidth" prop="goodsName">
          <el-input v-model="goodsForm.goodsName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品价格" :label-width="formLabelWidth" prop="goodsPrice">
          <el-input v-model="goodsForm.goodsPrice" type="number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品图片" :label-width="formLabelWidth" prop="goodsPhoto">
          <el-input v-model="goodsForm.goodsPhoto" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="供应商ID" :label-width="formLabelWidth" prop="supplierId">
          <el-input v-model="goodsForm.supplierId" type="number" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="saveGoods">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import goodsApi from '@/api/goods'
import {mapGetters} from 'vuex'

export default {
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      dialogTitle: '',
      formLabelWidth: '130px',
      goodsForm: {
        goodsName: '',
        goodsPrice: '',
        goodsPhoto: '',
        supplierId: ''
      },
      dialogFormVisible: false,
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        goodsName: '',
        supplierId: ''
      },
      goodsList: [],
      rules: {
        goodsName: [
          {required: true, message: '请输入商品名称', trigger: 'blur'}
        ],
        goodsPrice: [
          {required: true, message: '请输入商品价格', trigger: 'blur'}
        ],
        goodsPhoto: [
          {required: true, message: '请输入商品图片', trigger: 'blur'}
        ],
        supplierId: [
          {required: true, message: '请输入供应商ID', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    deleteGoods(goodsId) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        goodsApi.deleteGoods(goodsId).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getGoodsList()
        }).catch(() => {
          this.$message({
            type: 'error',
            message: '删除失败'
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    saveGoods() {
      this.$refs.goodsFormRef.validate(valid => {
        if (valid) {
          goodsApi.saveGoods(this.goodsForm).then(() => {
            this.$message({
              message: '成功提交',
              type: 'success'
            })
            this.dialogFormVisible = false
            this.getGoodsList()
          }).catch(() => {
            this.$message({
              type: 'error',
              message: '提交失败'
            })
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    clearForm() {
      this.goodsForm = {}
      this.$refs.goodsFormRef.clearValidate()
    },
    openAdd(goodsId) {
      if (goodsId == null) {
        this.dialogTitle = '新增商品'
      } else {
        this.dialogTitle = '修改商品'
        goodsApi.getGoodsById(goodsId).then(response => {
          this.goodsForm = response.data
        })
      }
      this.dialogFormVisible = true
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getGoodsList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getGoodsList()
    },
    getGoodsList() {
      goodsApi.getGoodsList(this.searchModel).then(res => {
        this.goodsList = res.data.records
        this.total = res.data.total
      })
    }
  },
  created() {
    this.getGoodsList()
  }
}
</script>

<style>
#search .el-input {
  width: 200px;
  margin-right: 20px;
}

.el-card {
  margin: 10px;
}

.el-dialog .el-input {
  width: 100%;
}
</style>
