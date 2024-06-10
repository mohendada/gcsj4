<template>
  <div>
    <el-table :data="tableData"
              :header-cell-style="{ background: '#f2f5fc', color: '#555555' }"
              border>
      <el-table-column prop="returnGoodsId" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="returnGoodsName" label="退货商品" width="180">
      </el-table-column>
      <el-table-column prop="returnGoodsTime" label="申请时间" width="180">
      </el-table-column>
      <el-table-column prop="returnGoodsNumber" label="退货数量" width="180">
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <el-popconfirm
            title="确定同意吗？"
            @onConfirm="confirmReq(1,scope.row.returnGoodsId)"
            style="margin-left: 5px;"
          >
            <el-button size="small" slot="reference" type="success">同意</el-button>
          </el-popconfirm>
          <el-popconfirm
            title="确定拒绝吗？"
            @onConfirm="confirmReq(0,scope.row.returnGoodsId)"
            style="margin-left: 5px;"
          >
            <el-button size="small" slot="reference" type="danger">拒绝</el-button>
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
  </div>
</template>

<script>
import ta from "element-ui/src/locale/lang/ta";

export default {
  name: "StorageManage",
  data() {
    return {
      tableData: [],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      rules: {
        name: [
          {required: true, message: '请输入仓库名', trigger: 'blur'},
          // Assuming checkName is a method for name validation
          {validator: this.checkName, trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    // Assuming checkName is a method for name validation
    checkName(rule, value, callback) {
      // Validation logic here
    },
    confirmReq(status, id) {
      this.$axios.post(this.$httpUrl + 'sys/refound/confirm', {
        orderid: id,
        status: status,
      }).then(res => {
        if (res.data.code === 20000) {
          alert("操作成功")
          this.loadPost()
        } else {
          alert("操作失败")
        }
      }).catch(error => {
        console.error("请求失败:", error);
        alert("请求失败，请稍后重试");
      });
    },
    handleSizeChange(val) {
      this.pageNum = 1;
      this.pageSize = val;
      this.loadPost();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.loadPost();
    },
    loadPost() {
      this.$axios.get(this.$httpUrl + '/sys/refound/GetAllRefound', {
        params: {
          pageSize: this.pageSize,
          pageNum: this.pageNum,
        }
      }).then(res => {
        if (res.data.code === 20000) {
          this.tableData = res.data.data.records;
          this.total = res.data.total;
          console.log(this.tableData)
        } else {
          alert('获取数据失败');
        }
      }).catch(error => {
        console.error("请求失败:", error);
        alert("请求失败，请稍后重试");
      });
    }
  },
  created() {
    this.loadPost();
  }
}
</script>

<style scoped>

</style>
