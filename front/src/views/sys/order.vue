<template>
  <div>

    <!-- 搜索栏 -->
    <el-card id="search">
      <el-input v-model="searchModel.orderName" placeholder="订单名称" clearable/>
      <el-button type="primary" round icon="el-icon-search" @click="getOrderList">查询</el-button>
    </el-card>

    <!-- 结果列表 -->
    <el-card>
      <el-table :data="orderList" style="width: 100%">
        <el-table-column prop="orderId" label="订单ID" width="90"></el-table-column>
        <el-table-column prop="orderName" label="订单名称" width="150"></el-table-column>
        <el-table-column prop="orderNumber" label="订单数量" width="120"></el-table-column>
        <el-table-column prop="orderStatus" label="订单状态" width="120"></el-table-column>
        <el-table-column prop="orderTime" label="订单时间" width="200"></el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button @click="openAddOrder(scope.row.orderId)" type="primary" icon="el-icon-edit" circle></el-button>
            <el-button @click="deleteOrder(scope.row.orderId)" type="danger" icon="el-icon-delete" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 分页栏 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :current-page="searchModel.pageNo" :page-sizes="[5, 8, 10, 20]" :page-size="searchModel.pageSize"
                   layout="total, sizes, prev, pager, next, jumper" :total="total"/>
  </div>
</template>

<script>
import orderApi from '@/api/order'
import {mapGetters} from 'vuex'
import goodsApi from "@/api/goods";

export default {
  data() {
    return {
      dialogFormVisible: false,
      orderForm: {
        orderName: '',
        orderNumber: ''
      },
      rules: {
        orderName: [
          {required: true, message: '请输入订单名称', trigger: 'blur'}
        ],
        orderNumber: [
          {required: true, message: '请输入订单数量', trigger: 'blur'},
          {type: 'number', message: '订单数量必须为数字值'}
        ]
      },
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        orderName: ''
      },
      orderList: [],
      total: 0
    }
  },
  methods: {
    openAddOrder(goodsId) {
      if (goodsId == null) {
        this.dialogTitle = '新增商品'
      } else {
        this.dialogTitle = '修改商品'
        goodsApi.getGoodsById(goodsId).then(response => {
          this.goodsForm = response.data
          this.imageUrl = this.goodsForm.goodsPhoto
          this.goodsPhoto = this.imageUrl
        })
      }
      this.dialogFormVisible = true
    },
    saveOrder() {
      this.$refs.orderForm.validate(valid => {
        if (valid) {
          orderApi.generateOrder(this.orderForm).then(() => {
            this.$message({
              message: '成功新建订单',
              type: 'success'
            });
            this.dialogFormVisible = false;
            this.getOrderList(); // 新建订单后刷新列表
          }).catch(() => {
            this.$message.error('新建订单失败');
          });
        } else {
          return false;
        }
      });
    },
    deleteOrder(orderId) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        orderApi.deleteOrder(orderId).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getOrderList()
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
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getOrderList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getOrderList()
    },
    getOrderList() {
      orderApi.getOrderList(this.searchModel).then(res => {
        this.orderList = res.data.records;
        this.total = res.data.total;
      });
    }
  },
  created() {
    this.getOrderList();
  },
  watch: {
    searchModel: {
      handler() {
        this.getOrderList();
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
</style>
