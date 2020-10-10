<template>
  <!-- 登录页面 -->
  <div class="login_container">
    <el-alert title="登录成功" type="success" show-icon center v-show="!flagAlert1"></el-alert>
    <el-alert title="账号或密码错误！" type="error" show-icon center v-show="!flagAlert2"></el-alert>
    <div class="login_box">
      <!-- logo区域 -->
      <div class="icon_box">
        <img src="../assets/logo.png" alt />
      </div>
      <!-- 这个表单区域 -->
      <el-form
        label-width="0px"
        :model="form"
        :rules="loginRlues"
        class="login_form"
        ref="loginRef"
      >
        <el-form-item prop="user" @keyup.enter.native="login">
          <el-input
            class="login_user"
            prefix-icon="el-icon-user-solid"
            maxlength="13"
            v-model="form.user"
          ></el-input>
        </el-form-item>
        <el-form-item prop="pwd" @keyup.enter.native="login">
          <el-input
            type="password"
            class="login_pwd"
            maxlength="16"
            prefix-icon="el-icon-lock"
            v-model="form.pwd"
          ></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetForm('loginRef')">重置</el-button>
        </el-form-item>
        <router-link to="./register" class="toReg">没有账号? 前往注册</router-link>
      </el-form>
    </div>
  </div>
</template>

<script>
import { request } from '../api/request.js';
export default {
  data() {
    return {
      flagAlert1: true,
      flagAlert2: true,
      form: {
        user: '',
        pwd: ''
      },
      loginRlues: {
        user: [
          { required: true, message: '账号不能为空' },
          { max: 13, min: 3, message: '账号必须为3~13位的字符' }
        ],
        pwd: [
          { required: true, message: '密码不能为空' },
          { max: 16, min: 6, message: '请输入6~16位的密码' }
        ]
      }
    };
  },
  created() {
    //重置数据
    this.$store.commit('removeRole');
    console.log(this.$store.state.roleInfo);
  },
  methods: {
    //重置表单
    resetForm(loginRef) {
      this.$refs[loginRef].resetFields();
    },
    //登录校验和请求
    login() {
      this.$refs.loginRef.validate(valid => {
        if (!valid) {
          console.log(valid);
        } else {
          //登录请求 //后台ssm自动处理前台的get或者post请求
          request({
            url: '/user/login',
            method: 'post',
            params: {
              username: this.form.user,
              password: this.form.pwd
            }
          })
            .then(res => {
              //如果请求数据为空，提示错误信息
              if (res.data == '') {
                this.flagAlert2 = !this.flagAlert2;
                setTimeout(() => {
                  //让错误提示消失！
                  this.flagAlert2 = !this.flagAlert2;
                }, 2000);
                this.resetForm('loginRef');
              } else {
                this.$store.commit('upRoleInfo', res.data);
                this.flagAlert1 = !this.flagAlert1;
                // 跳转页面
                setTimeout(() => {
                  //管理员登录判断
                  if (res.data.name == 'admin') {
                    this.$router.push({
                      name: 'seeAllUser',
                      query: { name: res.data.name }
                    });
                  } else {
                    this.$router.push({
                      name: 'showArticle',
                      query: { name: res.data.name }
                    });
                  }
                }, 1000);
              }
            })
            .catch(err => {
              console.log(err);
            });
        }
      });
    }
  }
};
</script>

<style lang="less">
.login_container {
  height: 100%;
  width: 100%;
  .el-alert {
    width: 29%;
    left: 50%;
    top: 10px;
    transform: translateX(-50%);
    position: absolute;
  }
  .login_box {
    width: 450px;
    height: 300px;
    background: rgba(255, 254, 254, 0.945);
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    box-shadow: 0px 1px 2px 2px rgba(255, 255, 255, 0.712);
  }
  .icon_box {
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0px 0px 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
  .login_form {
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0px 20px;
    box-sizing: border-box;
  }
  .login_user,
  .login_pwd {
    font-size: 20px;
    font-weight: bolder;
  }
  .btns {
    display: flex;
    justify-content: flex-end;
  }
  .toReg {
    text-decoration: none;
    position: absolute;
    bottom: 20px;
    right: 55%;
    font-size: 14px;
    color: #2449eb;
  }
}
</style>
