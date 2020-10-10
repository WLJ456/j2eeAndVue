<template>
  <div class="reg_container">
      <div class="reg_box">
        <!-- 顶部返回按钮 -->
        <div class="backLogin" @click="backLogin">
          <i class="el-icon-arrow-left"></i> 返回
        </div>
        <!-- 顶部图标 -->
        <div class="icon_box">
          <img src="../assets/logo.png" alt />
        </div>
        <!-- 注册区域 -->
        <el-form
          label-width="0px"
          :rules="regRlues"
          :model="form"
          class="reg_form"
          ref="regRef"
          @keyup.enter.native="register"
        >
          <el-form-item prop="user">
            <el-input
              class="reg_user"
              prefix-icon="el-icon-user"
              v-model="form.user"
              maxlength="13"
              placeholder="用户名"
            ></el-input>
          </el-form-item>
          <el-form-item prop="pwd">
            <el-input
              type="password"
              class="pwd"
              prefix-icon="el-icon-lock"
              maxlength="16"
              v-model="form.pwd"
              placeholder="输入密码"
              :show-password="flag"
            ></el-input>
          </el-form-item>
          <el-form-item prop="ckpwd">
            <el-input
              type="password"
              class="check_pwd"
              prefix-icon="el-icon-lock"
              maxlength="16"
              v-model="form.ckpwd"
              placeholder="再次输入密码"
              :show-password="flag"
            ></el-input>
          </el-form-item>
          <el-form-item prop="tel">
            <el-input
              type="number"
              prefix-icon="el-icon-phone"
              class="tel"
              maxlength="11"
              v-model="form.tel"
              placeholder="手机号"
            ></el-input>
          </el-form-item>
          <el-form-item prop="code">
            <el-input
              type="number"
              prefix-icon="el-icon-success"
              class="code"
              v-model="form.code"
              placeholder="验证码"
            ></el-input>
            <el-button type="info" class="get_code" @click="verifyFlag = true">立即获取</el-button>
          </el-form-item>
          <el-form-item class="btns">
            <el-button
              type="primary"
              style="backgroundColor: #409eff;color:#fff"
              :plain="true"
              @click="register"
            >注册</el-button>
            <el-button type="danger" @click="resetForm">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    <!-- 滑块验证 -->
    <el-collapse-transition>
      <div class="Verify" v-show="verifyFlag">
        <slideVerify @toslide="cheackverify" />
      </div>
    </el-collapse-transition>
  </div>
</template>

<script>
import { request } from '../api/request.js';
import axios from 'axios';
import slideVerify from './unit/slideVerify';
export default {
  data() {
    //验证不能输入中文
    var checkData = (rule, value, callback) => {
      if (value) {
        if (/[\u4E00-\u9FA5]/g.test(value)) {
          callback(new Error('账号不能汉字!'));
        } else {
          callback();
        }
      }
      callback();
    };
    //验证两次密码是否一致
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.form.ckpwd !== '') {
          this.$refs.regRef.validateField('ckpwd');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.form.pwd) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      //滑块验证码控制
      verifyFlag: false,
      flag: true,
      form: {
        user: '',
        pwd: '',
        ckpwd: '',
        tel: '',
        code: ''
      },
      regRlues: {
        user: [
          { validator: checkData, trigger: 'blur' },
          { required: true, message: '账号不能为空' },
          { max: 13, min: 3, message: '账号必须为3~13位的字符' }
        ],
        pwd: [
          { required: true, message: '密码不能为空' },
          { validator: validatePass, trigger: 'blur' },
          {
            max: 16,
            min: 3,
            message: '密码必须为3~16位的字符'
          }
        ],
        ckpwd: [
          { required: true, message: '密码不能为空' },
          { validator: validatePass2, trigger: 'blur' },
          {
            max: 16,
            min: 3,
            message: '密码必须为3~16位的字符'
          }
        ],
        tel: [
          { required: true, message: '电话不能为空' },
          { max: 11, min: 11, message: '请输入11位手机号' }
        ],
        code: [
          { required: true, message: '验证码不能为空' },
          { max: 4, min: 4, message: '请输入4位验证码' }
        ]
      }
    };
  },
  methods: {
    resetForm() {
      this.$refs.regRef.resetFields();
    },
    register() {
      this.$refs.regRef.validate(valid => {
        if (!valid) {
          console.log(valid);
        } else if (this.form.code == 8520) {
          request({
            url: '/user/addUser',
            method: 'post',
            params: {
              name: this.form.user,
              password: this.form.pwd,
              phonenumber: this.form.tel
            },
            timeout: 10000
          }).then(
            res => {
              if (res.data == true) {
                this.$message({
                  message: '注册成功，正在前往登录页面',
                  type: 'success'
                });
                setTimeout(() => {
                  this.$router.push('/login');
                }, 1000);
              } else {
                this.$message.error('注册失败，用户已存在！');
              }
            },
            err => {
              console.log('这是err方法！');
            }
          );
        } else {
          this.$message.error('验证码错误！(调用的第三方接口)');
        }
      });
    },
    backLogin() {
      this.$router.push('login');
    },
    //核对滑块验证是否成功
    cheackverify(flag) {
      if (flag == true && this.form.tel != null && this.form.tel != '') {
        this.verifyFlag = !this.verifyFlag;
        //验证成功后，获取验证码
        this.getCode();
      } else {
        this.verifyFlag = !this.verifyFlag;
        this.register();
      }
    },
    //获取验证码
    getCode() {
      this.$message.error(
        '(100条免费的接口用完了!!)短信费有点贵,直接告诉你吧，测试验证码为：8520'
      );
      axios({
        url: '/code/efficient/voice',
        method: 'get',
        params: {
          key: 'e5e7651607054ce49d7cb04e09324ef6',
          mobile: this.form.tel,
          valicode: 8520,
          playtimes: 2
        },
        timeout: 2000
      }).then(res => {
        console.log(res);
      });
    }
  },
  components: { slideVerify }
};
</script>

<style lang="less">
.reg_container {
  height: 100%;
  width: 100%;
  .reg_box {
    width: 450px;
    height: 600px;
    background: rgba(255, 254, 254, 0.945);
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    box-shadow: 0px 1px 2px 2px rgb(231, 230, 230);
    .backLogin {
      height: 30px;
      position: absolute;
      left: 25px;
      top: 25px;
      color: #669a9c;
      font-size: 18px;
    }
    .backLogin:hover {
      font-size: 20px;
      font-weight: bold;
      cursor: pointer;
    }
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
  .reg_form {
    position: absolute;
    bottom: 10%;
    width: 100%;
    padding: 0 15px;
    box-sizing: border-box;
    font-size: 20px;
    font-weight: bolder;
    .code {
      width: 40%;
      float: left;
    }
    .get_code {
      float: right;
    }
  }
  .Verify {
    background-color: #fff;
    position: absolute;
    left: 50%;
    top: 40%;
    transform: translate(-50%, -50%);
  }
}
</style>
