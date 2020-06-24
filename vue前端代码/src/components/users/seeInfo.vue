<template>
  <div>
    <homePage />
    <div id="mainShow">
      <el-form ref="form" :model="form" label-width="80px" @submit.native.prevent>
        <el-form-item label="用户ID:">
          <el-input v-model="form.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户账号:">
          <el-input v-model="form.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户密码:">
          <el-input v-model="form.password" disabled :type="type"></el-input>
        </el-form-item>
        <el-form-item label="显示密码:">
          <el-switch v-model="form.flag" @change="showpwd"></el-switch>
        </el-form-item>
        <el-form-item label="电话号码:">
          <el-input v-model="form.phonenumber" disabled></el-input>
        </el-form-item>
        <el-form-item label="邮箱地址:">
          <el-input v-model="form.emailaddress" disabled></el-input>
        </el-form-item>
        <el-form-item label="个人简介：">
          <el-input type="textarea" v-model="form.userdesc" style="width:500px" disabled></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="toUpdata">前往修改</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import homePage from './homePage';
export default {
  name: 'seeInfo',
  data() {
    return {
      type: 'password',
      form: {
        id: this.$store.state.roleInfo.id,
        name: this.$store.state.roleInfo.name,
        password: this.$store.state.roleInfo.password,
        flag: false,
        phonenumber: this.$store.state.roleInfo.phonenumber,
        emailaddress: this.$store.state.roleInfo.emailaddress,
        userdesc: this.$store.state.roleInfo.userdesc//个人介绍
      }
    };
  },
  components: {
    homePage
  },
  ///调试！
  created() {
    console.log(this.$store.state.roleInfo);
  },
  methods: {
    //显示密码和隐藏密码
    showpwd() {
      console.log(123);
      if (this.form.flag == true) {
        this.type = 'text';
      } else {
        this.type = 'password';
      }
    },
    //跳转页面
    toUpdata() {
      this.$router.push('/users/updataInfo');
    }
  }
};
</script>

<style scoped  lang="less">
#mainShow {
  background-color: #e9e9e9;
  width: 1000px;

  border: 10px #aaccdc solid;
  border-radius: 15px;
  position: absolute;
  top: 125px;
  left: 400px;
  z-index: 1;
  .el-form {
    width: 800px;
    height: 550px;
    margin: 30px auto;
    .el-form-item {
      .el-input {
        background-color: pink;
        width: 500px;
        height: 20px;
      }
    }
  }
}
</style>
