<template>
  <div>
    <homePage />
    <div id="UpdataShow">
      <el-form ref="form" :model="form" :rules="upRules" label-width="85px" @submit.native.prevent>
        <el-form-item label="用户ID:" prop="id">
          <el-input v-model="form.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户账号:" prop="name">
          <el-input v-model="form.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户密码:" prop="password">
          <el-input v-model="form.password" type="type"></el-input>
        </el-form-item>
        <el-form-item label="电话号码:" prop="phonenumber">
          <el-input v-model="form.phonenumber" type="number"></el-input>
        </el-form-item>
        <el-form-item label="邮箱地址:" prop="emailaddress">
          <el-input v-model="form.emailaddress" ></el-input>
        </el-form-item>
        <el-form-item label="个人简介：" prop="userdesc">
          <el-input type="textarea" v-model="form.userdesc" style="width:500px"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button :plain="true" @click="toSubmit">保存</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import homePage from './homePage';
import { request } from '../../api/request';
export default {
  name: 'updataInfo',
  data() {
    return {
      form: {
        id: this.$store.state.roleInfo.id,
        name: this.$store.state.roleInfo.name,
        password: this.$store.state.roleInfo.password,
        phonenumber: this.$store.state.roleInfo.phonenumber,
        emailaddress: this.$store.state.roleInfo.emailaddress,
        userdesc: this.$store.state.roleInfo.userdesc
      },
      // 表单校验规则
      upRules: {
        password: [
          { required: true, message: '密码不能为空' },
          { max: 16, min: 6, message: '请输入6~16位的密码' }
        ],
        phonenumber: [
          { required: true, message: '手机号不能为空' },
          { max: 11, min: 11, message: '请输入11位的手机号' }
        ],
        emailaddress: [{ required: true, message: '邮箱不能为空' }]
      }
    };
  },
  components: {
    homePage
  },

  methods: {
    toSubmit() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          console.log(valid);
        } else {
          this.$store.commit('upRoleInfo', this.form);
          console.log(this.form);
          //请求修改个人信息
          request({
            url: '/user/updata',
            method: 'post',
            params: {
              id: this.form.id,
              name: this.form.name,
              password: this.form.password,
              phonenumber: this.form.phonenumber,
              emailaddress: this.form.emailaddress,
              userdesc: this.form.userdesc
            },
            timeout: 10000
          }).then(
            res => {
              console.log('这是请求的数据');
              if (res.data == true) {
                this.$message({
                  message: '保存成功',
                  type: 'success'
                });
                this.$router.push('/users/seeInfo');
              } else {
                this.$message.error('保存失败');
              }
            },
            err => {
              console.log('这是err方法！');
            }
          );
        }
      });
    }
  }
};
</script>



<style scoped  lang="less">
#UpdataShow {
  background-color: #e9e9e9;
  width: 1000px;
  border: 10px #aaccdc solid;
  border-radius: 15px;
  position: absolute;
  top: 125px;
  left: 400px;
  z-index: 1;
  .el-form {
    width: 600px;
    margin: 70px auto;
    .el-button {
      background-color: #409eff;
      color: #fff;
    }
    .el-form-item {
      .el-input {
        width: 500px;
        height: 20px;
        margin-bottom: 20px;
      }
    }
  }
}
</style>
