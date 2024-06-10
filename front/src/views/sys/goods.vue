<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-input v-model="searchModel.goodsName" placeholder="商品名称" clearable/>
      <el-button type="primary" round icon="el-icon-search" @click="getGoodsList">查询</el-button>
      <el-button type="primary" icon="el-icon-circle-plus" @click="openAdd(null)">新增</el-button>
    </el-card>
    <!-- 结果列表 -->
    <el-card>
      <el-table :data="goodsList" style="width: 100%">
        <el-table-column label="#" width="60">
          <template slot-scope="scope">
            {{ (searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="goodsName" label="商品名称" width="80"></el-table-column>
        <el-table-column prop="goodsPrice" label="商品价格" width="80"></el-table-column>
        <el-table-column label="商品图片" width="200">
          <template slot-scope="scope">
            <img :src="scope.row.goodsPhoto" alt="商品图片" style="width: 150px; height: 150px"/>
          </template>
        </el-table-column>
        <el-table-column prop="supplierId" label="供应商ID" width="120"></el-table-column>
        <el-table-column prop="goodsStatus" label="状态" width="120">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.goodsStatus === 0" type="info">未上架</el-tag>
            <el-tag v-if="scope.row.goodsStatus === 1" type="success">已上架</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="goodsType" label="商品种类" width="120">
          <template slot-scope="scope">
            <!--            {{scope.row.goodsType}}-->
            <el-tag v-if="scope.row.goodsType == 1">零食</el-tag>
            <el-tag v-if="scope.row.goodsType == 2">水果</el-tag>
            <el-tag v-if="scope.row.goodsType == 3">电子产品</el-tag>
            <el-tag v-if="scope.row.goodsType == 4">服装</el-tag>
            <el-tag v-if="scope.row.goodsType == 5">蔬菜</el-tag>
            <el-tag v-if="scope.row.goodsType == 6">日用品</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="goodsDesc" label="商品描述" width="120"></el-table-column>
        <el-table-column label="启用" width="90">
          <template slot-scope="scope">
            <el-button @click="changeGoodsStatus(scope.row.goodsId, scope.row.goodsStatus)" type="primary"
                       icon="el-icon-edit"></el-button>
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
      <el-form ref="goodsId" :model="goodsForm" v-if="false">
        <el-form-item label="商品Id" :label-width="formLabelWidth" prop="goodsId">
          <el-input v-model="goodsForm.goodsId" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <el-form ref="goodsFormRef" :model="goodsForm" :rules="rules">
        <el-form-item label="商品名称" :label-width="formLabelWidth" prop="goodsName">
<!--          <el-input v-model="goodsForm.goodsName" autocomplete="off"></el-input>-->
          <el-select v-model="goodsForm.goodsName" placeholder="请选择" autocomplete="off">
            <el-option v-for="item in goodsNameList" :key="item.id" :label="item.name" :value="item.name"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="商品价格" :label-width="formLabelWidth" prop="goodsPrice">
          <el-input v-model="goodsForm.goodsPrice" type="number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品图片" prop="goodsPhoto">
          <input type="file" @change="handleFileChange">
          <img v-if="imageUrl" :src="imageUrl" alt="商品图片预览"
               style="max-width: 200px; max-height: 200px; margin-top: 10px;">
        </el-form-item>
        <el-form-item label="供应商ID" :label-width="formLabelWidth" prop="supplierId">
          <el-input v-model="goodsForm.supplierId" type="number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品种类" :label-width="formLabelWidth" prop="goodsType">
          <el-select v-model="goodsForm.goodsType" placeholder="请选择">
            <el-option v-for="item in tableData" :key="item.value" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth" prop="goodsDesc">
          <el-input type="textarea" v-model="goodsForm.goodsDesc" autocomplete="off"></el-input>
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
import goodsApi, {changeGoodsStatus} from '@/api/goods'
import {mapGetters} from 'vuex'

export default {
  computed: {
    ...mapGetters(['name']),
    filteredGoodsList() {
      // 根据搜索条件过滤商品列表
      if (!this.searchModel.goodsName) {
        return this.allGoods
      }
      return this.allGoods.filter((item) =>
        item.goodsName.toLowerCase().includes(this.searchModel.goodsName.toLowerCase())
      )
    }
  },
  data() {
    return {
      dialogTitle: '',
      formLabelWidth: '130px',
      tableData: [],
      goodsForm: {
        goodsId: '',
        goodsName: '',
        goodsPrice: '',
        goodsPhoto: '', // 保存图片地址
        supplierId: '',
        goodsType: '',
        goodsDesc: ''
      },
      goodsNameList:'',
      goodsPhoto: '',
      imageUrl: '',
      dialogFormVisible: false,
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        goodsName: '',
        supplierId: ''
      },
      goodsList: [],
      allGoods: [], // 存储所有的商品数据
      rules: {
        goodsName: [{required: true, message: '请输入商品名称', trigger: 'blur'}],
        goodsPrice: [{required: true, message: '请输入商品价格', trigger: 'blur'}],
        goodsPhoto: [{required: true, message: '请输入商品图片', trigger: 'blur'}],
        supplierId: [{required: true, message: '请输入供应商ID', trigger: 'blur'}],
        goodsType: [{required: true, message: '请选择商品种类', trigger: 'blur'}]
      }
    }
  },
  methods: {
    changeGoodsStatus(goodsId, status) {
      this.$confirm(`是否${status === 0 ? '禁用' : '启用'}该商品？`)
        .then(() => {
          changeGoodsStatus(goodsId).then(() => {
            this.$message({
              type: 'success',
              message: '已更改'
            })
            this.getGoodsList()
            this.goodsForm = {
              goodsId: '',
              goodsName: '',
              goodsPrice: '',
              goodsPhoto: '', // 保存图片地址
              supplierId: '',
              goodsType: '',
              goodsDesc: ''
            }
            this.imageUrl = ''
            this.goodsPhoto = ''
          })
        })
        .catch(() => {
          // 取消后的操作
        })
    },
    deleteGoods(goodsId) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          goodsApi
            .deleteGoods(goodsId)
            .then(() => {
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.getGoodsList()
            })
            .catch(() => {
              this.$message({
                type: 'error',
                message: '删除失败'
              })
            })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    handleFileChange(event) {
      this.goodsPhoto = event.target.files[0]
      const file = event.target.files[0] // 获取用户选择的文件
      if (file) {
        // 使用 FileReader 来读取文件并生成预览图
        const reader = new FileReader()
        reader.onload = (e) => {
          this.imageUrl = e.target.result // 将读取到的文件数据保存到 imageUrl 变量中
        }
        reader.readAsDataURL(file)
      }
    },
    saveGoods() {
      this.goodsForm.goodsPhoto = this.goodsPhoto
      // this.goodsForm.goodsName
      if (this.goodsForm.goodsType == "零食") {
        this.goodsForm.goodsType = 1
      } else if (this.goodsForm.goodsType == "水果") {
        this.goodsForm.goodsType = 2
      } else if (this.goodsForm.goodsType == "电子产品") {
        this.goodsForm.goodsType = 3
      } else if (this.goodsForm.goodsType == "服装") {
        this.goodsForm.goodsType = 4
      } else if (this.goodsForm.goodsType == "蔬菜") {
        this.goodsForm.goodsType = 5
      } else if (this.goodsForm.goodsType == "日用品") {
        this.goodsForm.goodsType = 6
      }
      this.$refs.goodsFormRef.validate((valid) => {
        if (valid) {
          console.log(this.goodsForm)
          goodsApi
            .saveGoods(this.goodsForm, this.goodsPhoto)
            .then(() => {
              this.$message({
                message: '成功提交',
                type: 'success'
              },
              this.imageUrl='')
              this.dialogFormVisible = false
              this.getGoodsList()
            })
            .catch(() => {
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
        goodsApi.getGoodsById(goodsId).then((response) => {
          this.goodsForm = response.data
          this.imageUrl = this.goodsForm.goodsPhoto
          this.goodsPhoto = this.imageUrl
          if (this.goodsForm.goodsType == 1) {
            this.goodsForm.goodsType = "零食"
          } else if (this.goodsForm.goodsType == 2) {
            this.goodsForm.goodsType = "水果"
          } else if (this.goodsForm.goodsType == 3) {
            this.goodsForm.goodsType = "电子产品"
          } else if (this.goodsForm.goodsType == 4) {
            this.goodsForm.goodsType = "服装"
          } else if (this.goodsForm.goodsType == 5) {
            this.goodsForm.goodsType = "蔬菜"
          } else if (this.goodsForm.goodsType == 6) {
            this.goodsForm.goodsType = "日用品"
          }
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
      goodsApi.getGoodsList(this.searchModel).then((res) => {
        this.allGoods = res.data.records
        this.total = res.data.total
        this.filterGoodsList()
      })
    },
    filterGoodsList() {
      const start = (this.searchModel.pageNo - 1) * this.searchModel.pageSize
      const end = start + this.searchModel.pageSize
      this.goodsList = this.filteredGoodsList.slice(start, end)
    },
    handleSearch() {
      this.searchModel.pageNo = 1
      this.filterGoodsList()
    },
    TypeloadPost() {
      this.$axios
        .post(this.$httpUrl + '/goodstype/listPage', {
          pageSize: 10,
          pageNum: 0
        })
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            this.tableData = res.data
            this.total = res.total
          } else {
            alert('获取数据失败')
          }
        })
    },
    GoodloadPost() {
      this.$axios.post(this.$httpUrl + '/storage/listPage', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
      }).then(res => res.data).then(res => {
        console.log(res)
        if (res.code == 200) {
          this.goodsNameList = res.data
          this.total = res.total
        } else {
          alert('获取数据失败')
        }

      })
    }
  },
  created() {
    this.getGoodsList()
    this.TypeloadPost()
    this.GoodloadPost()
  },
  watch: {
    searchModel: {
      handler() {
        this.handleSearch()
      },
      deep: true
    }
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
