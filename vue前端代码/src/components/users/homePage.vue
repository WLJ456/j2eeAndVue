<template>
  <div id="homePage">
    <el-container direction="vertical">
      <el-header style=" height: 90px;">
        <span>博客网</span>
      </el-header>
      <el-row class="tac">
        <el-col :span="12">
          <!-- 侧边导航栏 -->
          <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            router
          >
            <!-- 个人信息 -->
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-user"></i>
                <span>个人信息</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/users/seeInfo">查看信息</el-menu-item>
                <el-menu-item index="/users/updataInfo">修改信息</el-menu-item>
                <el-menu-item index="/login">切换账号</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <!-- 网站首页 -->
            <el-menu-item index="/mains">
              <i class="el-icon-menu"></i>
              <span slot="title">网站首页</span>
            </el-menu-item>
            <!-- 查看博文 -->
            <el-menu-item index="/users/showArticle">
              <i class="el-icon-notebook-2"></i>
              <span slot="title">查看博文</span>
            </el-menu-item>
            <!-- 发表博文 -->
            <el-menu-item index="/users/sendArticle">
              <i class="el-icon-position"></i>
              <span slot="title">发表博文</span>
            </el-menu-item>
            <!-- 退出登录 -->
            <el-menu-item index="/mains" @click="loginOut">
              <i class="el-icon-switch-button"></i>
              <span slot="title">退出登录</span>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
    </el-container>
  </div>
</template>

<script>
export default {
  name: '',
  data() {
    return {};
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    loginOut() {
      // 推出登陆后清空state
      this.$store.commit('removeRole');
    }
  }
};
</script>

<style lang="less" scoped>
.el-container {
  padding: 0;
  margin: 0;
  height: 100vh; //自适应布局
}
.el-header span {
  font-family: cursive;
  font-size: 40px;
  float: left;
  letter-spacing: 3px;
  font-style: italic;
  margin-left: 20px;
}
.el-header {
  background-color: rgba(84, 92, 100, 0.2);
  color: rgb(255, 255, 255);
  text-align: center;
  line-height: 90px;
}

.el-row {
  width: 450px;
  margin: 35px 20px;
  .el-menu-item,
  .el-submenu__title {
    height: 70px;
    line-height: 70px;
  }
  .el-col {
    border: 10px #aaccdc solid;
    border-radius: 15px;
  }
  // .el-menu-item {
  //   border: 1px red solid;
  // }
}
body > .el-container {
  margin-bottom: 40px;
}
</style>
