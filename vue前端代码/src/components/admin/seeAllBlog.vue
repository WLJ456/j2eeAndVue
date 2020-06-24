<template>
  <div>
    <div id="seeAllBlog">
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
              <!-- 查看所有博主 -->
              <el-menu-item index="/admin/seeAllUser">
                <i class="el-icon-notebook-2"></i>
                <span slot="title">查看所有博主</span>
              </el-menu-item>
              <!-- 博文系统日志 -->
              <el-menu-item index="/admin/seeAllBlog">
                <i class="el-icon-position"></i>
                <span slot="title">博文系统日志</span>
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
    <div id="BlogContext">
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column prop="name" label="用户名" width="180"></el-table-column>
        <el-table-column prop="blogTitle" label="博文标题" width="180"></el-table-column>
        <el-table-column prop="blogText" label="博文内容"></el-table-column>
        <el-table-column prop="imagesUrl" label="图片地址"></el-table-column>
        <el-table-column prop="createdDate" label="发表日期"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { request } from '../../api/request.js';
export default {
  name: 'seeAllBlog',
  data() {
    return {
      name: this.$store.state.roleInfo.name,
      tableData: []
    };
  },
  mounted() {
    request({
      url: '/blog/findAll',
      method: 'post',
      params: {
        username: this.name
      },
      timeout: 3000
    }).then(res => {
      if (res.data != null) {
        this.tableData = res.data;
      } else {
        console.log('请求错误！');
      }
    });
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

<style scoped lang="less">
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
}
body > .el-container {
  margin-bottom: 40px;
}

#BlogContext {
  background-color: #e9e9e9;
  width: 1000px;
  border: 10px #aaccdc solid;
  border-radius: 15px;
  position: absolute;
  top: 125px;
  left: 400px;
  z-index: 1;
}
</style>
