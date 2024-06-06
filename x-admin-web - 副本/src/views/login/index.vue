<template>

  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on"
      label-position="left">

      <div class="title-container">
        <h3 class="title">欢迎使用请假系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input ref="username" v-model="loginForm.username" placeholder="用户名" name="username" type="text" tabindex="1"
          auto-complete="on" />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input :key="passwordType" ref="password" v-model="loginForm.password" :type="passwordType" placeholder="密码"
          name="password" tabindex="2" auto-complete="on" @keyup.enter.native="handleLogin" />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>
      <el-form-item label="验证码:">
        <el-input v-model="loginForm.code" name="code" prop="code"></el-input>
      </el-form-item>
      <div class="login-code" @click="refreshCode">
        <!--验证码组件-->
        <s-identify :identifyCode="identifyCode"></s-identify>
      </div>

      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleLogin">登录</el-button>
      <div class="tips">
        <el-button type="primary" @click="forgot">忘记密码</el-button>
        <el-button type="primary" @click="register">注册</el-button>
      </div>
    </el-form>
    <!--忘记密码-->
    <el-dialog :visible.sync="forgotFormVisible" @close="clearforgotForm" width="50%">
      <template #title>
        <div class="dialog-title">忘记密码</div>
        <div class="forgotlo" style=" text-align: center;color:red;">(请输入用于注册的电话号)</div>
      </template>
      <el-form ref="forgotformref" :model="forgotform" :rules="rules">
        <el-form-item label="账号:" :label-width="formLabelWidth" prop="userAccount" style="background-color: white;">
          <el-input v-model="forgotform.userAccount" autocomplete="off" class="custom-input" />
        </el-form-item>
        <el-form-item label="电话号:" :label-width="formLabelWidth" prop="phoneNumber" style="background-color: white;">
          <el-input v-model="forgotform.phoneNumber" autocomplete="off" class="custom-input" />
        </el-form-item>
        <el-form-item label="密码:" :label-width="formLabelWidth" prop="password" style="background-color: white;">
          <el-input v-model="forgotform.password" autocomplete="off" class="custom-input" type="password" />
        </el-form-item>
        <el-form-item label="再次确认:" :label-width="formLabelWidth" prop="password3" style="background-color: white;">
          <el-input v-model="forgotform.password3" autocomplete="off" class="custom-input" type="password" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="forgotFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="forgotUser">确 定</el-button>
      </div>
    </el-dialog>
    <!--注册账号-->
    <el-dialog :visible.sync="dialogFormVisible" @close="clearForm" width="50%">
      <template #title>
        <div class="dialog-title">注册账户</div>
      </template>
      <el-form ref="registrationformref" :model="registrationform" :rules="rules">
        <el-form-item label="账号:" :label-width="formLabelWidth" prop="userAccount" style="background-color: white;">
          <el-input v-model="registrationform.userAccount" autocomplete="off" class="custom-input" />
        </el-form-item>
        <el-form-item label="密码:" :label-width="formLabelWidth" prop="password" style="background-color: white;">
          <el-input type="password" v-model="registrationform.password" autocomplete="off"
            class="custom-input"></el-input>
        </el-form-item>
        <el-form-item label="确认密码:" :label-width="formLabelWidth" prop="password2" style="background-color: white;">
          <el-input type="password" v-model="registrationform.password2" autocomplete="off"
            class="custom-input"></el-input>
        </el-form-item>
        <el-form-item label="姓名:" :label-width="formLabelWidth" prop="userName" style="background-color: white;">
          <el-input v-model="registrationform.userName" autocomplete="off" class="custom-input"></el-input>
        </el-form-item>
        <el-form-item label="性别:" :label-width="formLabelWidth" prop="sex" style="background-color: white;">
          <template slot-scope="scope">
            <el-radio v-model="registrationform.sex" label="Male">男</el-radio>
            <el-radio v-model="registrationform.sex" label="Female">女</el-radio>
          </template>
        </el-form-item>
        <el-form-item label="电话号码:" :label-width="formLabelWidth" prop="phoneNumber" style="background-color: white;">
          <el-input v-model="registrationform.phoneNumber" autocomplete="off" class="custom-input"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱:" :label-width="formLabelWidth" prop="email" style="background-color: white;">
          <el-input v-model="registrationform.email" autocomplete="off" class="custom-input"></el-input>
        </el-form-item>
        <el-form-item label="地址:" :label-width="formLabelWidth" prop="address" style="background-color: white;">
          <el-input v-model="registrationform.address" autocomplete="off" class="custom-input"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveUser">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import SIdentify from '@/components/SIdentify'
import { validUsername } from '@/utils/validate'
import login from '@/api/user'
export default {
  name: 'Login',
  components: { SIdentify },
  data() {
    var checkAge = (rule, value, callback) => {
      var reg = /^[\w\.-]+@[a-zA-Z\d\.-]+\.[a-zA-Z]{2,}$/
      if (!reg.test(value)) {
        return callback(new Error('邮箱格式错误'))
      }
      callback()
    }
    var checkPass = (rule, value, callback) => {
      if (value !== this.registrationform.password) {
        return callback(new Error('密码不一致'))
      }
      callback()
    }
    var checkPass2 = (rule, value, callback) => {
      if (value !== this.forgotform.password) {
        return callback(new Error('密码不一致'))
      }
      callback()
    }
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('请输入正确用户名'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 1) {
        callback(new Error('密码不能为空'))
      } else {
        callback()
      }
    }
    return {
      rules: {
        userAccount: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        password2: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { validator: checkPass, trigger: 'blur' }
        ],
        password3: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { validator: checkPass2, trigger: 'blur' }
        ],
        userName: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        phoneNumber: [
          { required: true, message: '请输入电话号', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: checkAge, trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ],
        code: [

          { required: true, message: '请选择验证码', trigger: 'blur' }
        ]
      },
      identifyCodes: '1234567890abcdefjhijklinopqrsduvwxyz', // 随机串内容
      identifyCode: '',
      forgotFormVisible: false,
      dialogFormVisible: false,
      forgotform: {
        userAccount: '',
        phoneNumber: '',
        password: '',
        password3: ''
      },
      registrationform: {},
      formLabelWidth: '130px',
      loginForm: {
        username: 'admin',
        password: '123456',
        code: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  mounted() {
    // 初始化验证码
    this.identifyCode = ''
    this.makeCode(this.identifyCodes, 4)
  },
  methods: {
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[this.randomNum(0, this.identifyCodes.length)]
      }
    },
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    forgotUser() {
      // 提交后台，验证数据，关闭对话框，刷新数据

      login.forgotalter(this.forgotform).then(res => {
        this.$message({
          message: '修改成功',
          type: 'success'
        })
      })
      this.forgotFormVisible = false
    },
    forgot() {
      this.forgotFormVisible = true
    },
    clearForm() {
      this.registrationform = {}
      // 移除表单项的校验结果清除
      this.$refs.registrationformref.clearValidate()
    },
    clearforgotForm() {
      this.forgotform = {}
      // 移除表单项的校验结果清除
      this.$refs.forgotformref.clearValidate()
    },
    saveUser() {
      this.$refs.registrationformref.validate((valid) => {
        if (valid) {
          // 提交后台，验证数据，关闭对话框，刷新数据
          login.register(this.registrationform).then(res => {
            // 成功提示，关闭对话框，刷新数据
            this.$message({
              message: '注册成功',
              type: 'success'
            })
            this.dialogFormVisible = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    register() {
      this.dialogFormVisible = true
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      console.log(this.loginForm.code)
      if (this.loginForm.code.toLowerCase() !== this.identifyCode.toLowerCase()) {
        this.$message.error('请填写正确验证码')
        this.refreshCode()
      } else {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true
            console.log(this.loginForm)
            this.$store.dispatch('user/login', this.loginForm).then((res) => {
              console.log(res)
              console.log(this.loginForm)
              // 根据角色跳转
              login.loginuserform(this.loginForm).then(res => {
                if (res.data.roleId === 1) {
                  this.$router.push({ path: '/aa' })
                } else if (res.data.roleId === 2) {
                  this.$router.push({ path: this.redirect || '/' })
                } else {
                  this.$router.push({ path: this.redirect || '/' })
                }
              })
              this.loading = false
            }).catch(() => {
              this.loading = false
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      }
    }
  }
}
</script>
<style scoped>
.el-input.yanzhen {
  border: 2px solid black;
  margin-top: 5px;
  border-radius: 5px;
}

.el-input.custom-input {
  background-color: white;
  border: 2px solid black;
  border-radius: 5px;
  caret-color: red;
}
</style>
<style lang="scss">
.dialog-title {
  font-size: 30px;
  text-align: center;
  /* 将标题文本水平居中 */
}

/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: black;
      height: 47px;
      caret-color: black;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: red;
  overflow: hidden;
  background-image: url('../../assets/chaoshi.jpeg');
  background-size: 100%;
  display: flex;
  align-items: center;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 80px 35px 0;
    margin: 0 auto;
    overflow: hidden;
    background-color: #f1c232;
    border-radius: 10px;
    // opacity: 0.8;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
