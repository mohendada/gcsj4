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
        <el-table-column label="商品图片" width="120">
          <template slot-scope="scope">
            <img :src="scope.row.goodsPhoto" alt="商品图片" style="width: 50px; height: 50px"/>
          </template>
        </el-table-column>
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
        <el-form-item label="商品图片" prop="goodsPhoto">
          <input type="file" @change="handleFileChange">
          <img v-if="imageUrl" :src="imageUrl" alt="商品图片预览"
               style="max-width: 200px; max-height: 200px; margin-top: 10px;">
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
        goodsPhoto: '', // 保存图片地址
        supplierId: ''
      },
      goodsPhoto: '',
      // file : '',
      imageUrl:'',
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
    // 处理文件上传成功事件
    // handleUploadSuccess(response) {
    //   this.goodsForm.goodsPhoto = response.data.url; // 将返回的图片地址赋值给商品图片字段
    // },
    // // 处理文件上传失败事件
    // handleUploadError(err) {
    //   console.log('文件上传失败', err);
    // },
    // 其他方法保持不变...
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
    handleFileChange(event) {
      this.goodsPhoto = event.target.files[0];
      alert(this.goodsPhoto);
      const file = event.target.files[0]; // 获取用户选择的文件
      if (file) {
        // 使用 FileReader 来读取文件并生成预览图
        const reader = new FileReader();
        reader.onload = (e) => {
          this.imageUrl = e.target.result; // 将读取到的文件数据保存到 imageUrl 变量中
        };
        reader.readAsDataURL(file);
      }
    },
    saveGoods() {

      this.goodsForm.goodsPhoto = this.goodsPhoto;
      this.$refs.goodsFormRef.validate(valid => {
        // this.goodsForm.goodsPhoto = this.goodsPhoto;

        // alert(JSON.stringify(this.goodsForm));
        if (valid) {
          goodsApi.saveGoods(this.goodsForm,this.goodsPhoto).then(() => {
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
