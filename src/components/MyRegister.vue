<!--
 * 文件描述: 用户注册组件
 -->
<template>
  <div id="register">
    <el-dialog title="注册" width="300px" center :visible.sync="isRegister">
      <el-form
        :model="RegisterUser"
        :rules="rules"
        status-icon
        ref="ruleForm"
        class="demo-ruleForm"
      >
        <el-form-item prop="name">
          <el-input
            prefix-icon="el-icon-user-solid"
            placeholder="请输入账号（用户名）"
            v-model="RegisterUser.name"
          ></el-input>
        </el-form-item>
        <el-form-item prop="pass">
          <el-input
            prefix-icon="el-icon-view"
            placeholder="请输入密码"
            v-model="RegisterUser.pass"
          ></el-input>
        </el-form-item>
        <el-form-item prop="account">
          <el-input
            prefix-icon="el-icon-user"
            placeholder="请输入账号昵称"
            v-model="RegisterUser.account"
          ></el-input>
        </el-form-item>
        <el-form-item prop="phone">
          <el-input
            prefix-icon="el-icon-phone"
            placeholder="请输入联系电话"
            v-model="RegisterUser.phone"
          ></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input
            prefix-icon="el-icon-message"
            placeholder="请输入邮箱"
            v-model="RegisterUser.email"
          ></el-input>
        </el-form-item>
        <el-form-item prop="address">
          <el-input
            prefix-icon="el-icon-location"
            placeholder="请输入您的地址"
            v-model="RegisterUser.address"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button
            size="medium"
            type="primary"
            @click="Register"
            style="width: 100%"
            >注册</el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "MyRegister",
  props: ["register"],
  data() {
    // 用户名的校验方法
    let validateName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入用户名"));
      }
      // 用户名以字母开头,长度在5-16之间,允许字母数字下划线
      const userNameRule = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
      if (userNameRule.test(value)) {
        //判断数据库中是否已经存在该用户名
        this.$axios
          .post("/api/users/findUserName", {
            userName: this.RegisterUser.name,
          })
          .then((res) => {
            // “001”代表用户名不存在，可以注册
            if (res.data.code == "001") {
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
        return callback(new Error("字母开头,长度5-16之间,允许字母数字下划线"));
      }
    };
    // 密码的校验方法
    let validatePass = (rule, value, callback) => {
      if (value === "") {
        return callback(new Error("请输入密码"));
      }
      // 密码以字母开头,长度在6-18之间,允许字母数字和下划线
      const passwordRule = /^[a-zA-Z]\w{5,17}$/;
      if (passwordRule.test(value)) {
        this.$refs.ruleForm.validateField("checkPass");
        return callback();
      } else {
        return callback(
          new Error("字母开头,长度6-18之间,允许字母数字和下划线")
        );
      }
    };
    // 账号昵称的校验方法
    let validateAccount = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入账号昵称"));
      }
      // 账号昵称可以输入中文，英文，数字，字母，下划线，但长度不能超过16
      const accountRule = /^[\u4E00-\u9FA5A-Za-z0-9_]{1,16}$/;
      if (accountRule.test(value)) {
        return callback();
      } else {
        return callback(new Error("账号昵称格式不正确"));
      }
    };
    // 手机号码的校验方法（有效的11位数字）
    let validatePhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入联系电话"));
      }
      // 手机号码的正则表达式
      const phoneRule = /^[0-9]{11}$/;
      if (phoneRule.test(value)) {
        // 校验通过
        return callback();
      } else {
        return callback(new Error("请输入正确的手机号码"));
      }
    };

    // 邮箱的校验方法
    let validateEmail = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入邮箱"));
      }
      // 邮箱的正则表达式
      const emailRule = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
      if (emailRule.test(value)) {
        // 校验通过
        return callback();
      } else {
        return callback(new Error("请输入正确的邮箱地址"));
      }
    };
    //确认地址的检验方法(不为空，长度不超过100就行)
    let validateAddress = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入地址"));
      }
      // 地址长度不超过100个字符
      if (value.length <= 100) {
        return callback();
      } else {
        return callback(new Error("地址长度不能超过100个字符"));
      }
    };

    return {
      isRegister: false, // 控制注册组件是否显示
      RegisterUser: {
        name: "",
        pass: "",
        account: "",
        phone: "",
        email: "",
        address: "",
        role_ID: "1",
      },
      // 用户信息校验规则,validator(校验方法),trigger(触发方式),blur为在组件 Input 失去焦点时触发
      rules: {
        name: [{ validator: validateName, trigger: "blur" }],
        pass: [{ validator: validatePass, trigger: "blur" }],
        account: [{ validator: validateAccount, trigger: "blur" }],
        phone: [{ validator: validatePhone, trigger: "blur" }],
        email: [{ validator: validateEmail, trigger: "blur" }],
        address: [{ validator: validateAddress, trigger: "blur" }],
      },
    };
  },
  watch: {
    // 监听父组件传过来的register变量，设置this.isRegister的值
    register: function (val) {
      if (val) {
        this.isRegister = val;
      }
    },
    // 监听this.isRegister变量的值，更新父组件register变量的值
    isRegister: function (val) {
      if (!val) {
        this.$refs["ruleForm"].resetFields();
        this.$emit("fromChild", val);
      }
    },
  },
  methods: {
    Register() {
      // 通过element自定义表单校验规则，校验用户输入的用户信息
      this.$refs["ruleForm"].validate((valid) => {
        //如果通过校验开始注册
        if (valid) {
          this.$axios
            .post("/api/users/register", {
              name: this.RegisterUser.name,
              pass: this.RegisterUser.pass,
              account: this.RegisterUser.account,
              phone: this.RegisterUser.phone,
              email: this.RegisterUser.email,
              address: this.RegisterUser.address,
              role_ID: this.RegisterUser.role_ID,
            })
            .then((res) => {
              // “001”代表注册成功，其他的均为失败
              if (res.data.code === "001") {
                // 隐藏注册组件
                this.isRegister = false;
                // 弹出通知框提示注册成功信息
                this.notifySucceed(res.data.msg);
              } else {
                // 弹出通知框提示注册失败信息
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
