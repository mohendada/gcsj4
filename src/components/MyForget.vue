<!--
 * 文件描述: 用户找回密码组件
 -->
<template>
  <div id="forget">
    <el-dialog title="找回密码" width="300px" center :visible.sync="isForget">
      <el-form
        :model="ForgetUser"
        :rules="rules"
        status-icon
        ref="ruleForm"
        class="demo-ruleForm"
      >
        <el-form-item prop="name">
          <el-input
            prefix-icon="el-icon-user-solid"
            placeholder="请输入用户名"
            v-model="ForgetUser.name"
          ></el-input>
        </el-form-item>
        <el-form-item prop="pass">
          <el-input
            prefix-icon="el-icon-view"
            placeholder="请输入新密码"
            v-model="ForgetUser.pass"
          ></el-input>
        </el-form-item>
        <el-form-item prop="phone">
          <el-input
            prefix-icon="el-icon-phone"
            placeholder="请输入您的联系电话"
            v-model="ForgetUser.phone"
          ></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input
            prefix-icon="el-icon-message"
            placeholder="请输入您的邮箱"
            v-model="ForgetUser.email"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            size="medium"
            type="primary"
            @click="Forget"
            style="width: 100%"
            >找回密码</el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "MyForget",
  props: ["forget"],
  data() {
    return {
      isForget: false, // 控制注册组件是否显示
      ForgetUser: {
        name: "",
        pass: "",
        phone: "",
        email: "",
      },
      // 后端传递的用户信息
      backendInfo: {
        phone: "",
        email: "",
      },
      // 用户信息校验规则,validator(校验方法),trigger(触发方式),blur为在组件 Input 失去焦点时触发
      rules: {
        name: [{ validator: this.validateName, trigger: "blur" }],
        pass: [{ validator: this.validatePass, trigger: "blur" }],
        phone: [{ validator: this.validatePhone, trigger: "blur" }],
        email: [{ validator: this.validateEmail, trigger: "blur" }],
      },
    };
  },
  watch: {
    // 监听父组件传过来的forget变量，设置this.isForget的值
    forget: function (val) {
      if (val) {
        this.isForget = val;
      }
    },
    // 监听this.isForget变量的值，更新父组件forget变量的值
    isForget: function (val) {
      if (!val) {
        this.$refs["ruleForm"].resetFields();
        this.$emit("fromChild", val);
      }
    },
  },
  methods: {
    // 用户名的校验方法
    validateName(rule, value, callback) {
      if (!value) {
        return callback(new Error("请输入账号"));
      }
      // 用户名以字母开头,长度在5-16之间,允许字母数字下划线
      const userNameRule = /^[a-zA-Z][a-zA-Z0-9_]{4,16}$/;
      if (userNameRule.test(value)) {
        //判断数据库中是否已经存在该账号
        this.$axios
          .post("/api/users/findUserName", {
            userName: this.ForgetUser.name,
          })
          .then((res) => {
            // “001”代表用户账号，可以找回密码
            if (res.data.code == "001") {
              // 获取后端传递的用户信息
              this.getUserInfo();
              this.$refs.ruleForm.validateField("checkPass");
              return callback();
            } else {
              return callback(new Error(res.data.msg));
            }
          })
          .catch((err) => {
            return Promise.reject(err);
          });
      } else {
        return callback(new Error("该用户不存在"));
      }
    },
    // 密码的校验方法
    validatePass(rule, value, callback) {
      if (value === "") {
        return callback(new Error("请输入密码"));
      }
      // 密码以字母开头,长度在6-18之间,允许字母数字和下划线
      const passwordRule = /^[a-zA-Z]\w{5,18}$/;
      if (passwordRule.test(value)) {
        this.$refs.ruleForm.validateField("checkPass");
        return callback();
      } else {
        return callback(new Error("以字母开头,长度6-18,允许字母数字和下划线"));
      }
    },
    // 电话号码的校验方法
    validatePhone(rule, value, callback) {
      if (!value) {
        return callback(new Error("请输入联系电话"));
      }
      const phoneRule = /^[0-9]{11}$/;
      if (phoneRule.test(value)) {
        // 如果前端输入的电话号码与后端传递的电话号码一致，则通过校验
        if (value === this.backendInfo.phone) {
          return callback();
        } else {
          return callback(new Error("电话号码与系统记录的不一致"));
        }
      } else {
        return callback(new Error("请输入有效的11位电话号码"));
      }
    },
    // 邮箱的校验方法
    validateEmail(rule, value, callback) {
      if (!value) {
        return callback(new Error("请输入邮箱"));
      }
      const emailRule = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
      if (emailRule.test(value)) {
        // 如果前端输入的邮箱与后端传递的邮箱一致，则通过校验
        if (value === this.backendInfo.email) {
          return callback();
        } else {
          return callback(new Error("邮箱与系统记录的不一致"));
        }
      } else {
        return callback(new Error("请输入有效的邮箱"));
      }
    },
    // 获取用户信息方法
    getUserInfo() {
      this.$axios
        .get("/api/users/info", {
          params: {
            userName: this.ForgetUser.name,
          },
        })
        .then((res) => {
          if (res.data.code === "001") {
            this.backendInfo = res.data.data;
          } else {
            this.notifyError(res.data.msg);
          }
        })
        .catch((err) => {
          this.notifyError("获取用户信息失败");
          return Promise.reject(err);
        });
    },
    Forget() {
      // 通过element自定义表单校验规则，校验用户输入的用户信息
      this.$refs["ruleForm"].validate((valid) => {
        //如果通过校验开始找回密码
        if (valid) {
          this.$axios
            .post("/api/users/forget", {
              userName: this.ForgetUser.name,
              password: this.ForgetUser.pass,
              phone: this.ForgetUser.phone,
              email: this.ForgetUser.email,
            })
            .then((res) => {
              // “001”代表找回密码成功，其他的均为失败
              if (res.data.code === "001") {
                // 隐藏找回密码组件
                this.isForget = false;
                // 弹出通知框提示找回密码成功信息
                this.notifySucceed(res.data.msg);
              } else {
                // 弹出通知框提示找回密码失败信息
                this.notifyError(res.data.msg);
              }
            })
            .catch((err) => {
              return Promise.reject(err);
            });
        } else {
          return false;
        }
      });
    },
  },
};
</script>
<style></style>