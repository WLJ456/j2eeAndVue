<template>
  <div>
    <el-container direction="vertical">
      <el-header style=" height: 90px;">
        <span class="logo">博客网</span>
        <el-row v-show="flag">
          <el-tooltip content="点击登录" placement="top">
            <el-button type="primary" @click="toLogin">登录</el-button>
          </el-tooltip>
          <el-tooltip content="前往注册" placement="top">
            <el-button type="success" @click="toRegister">注册</el-button>
          </el-tooltip>
        </el-row>
        <el-row v-show="!flag">
          <b id="rightUser">欢迎{{username}}用户,您已登陆</b>
          <el-button type="primary" @click="toBackper" icon="el-icon-back">个人页面</el-button>
        </el-row>
      </el-header>

      <el-container>
        <el-aside width="500px">
          <el-calendar v-model="value"></el-calendar>
        </el-aside>
        <!-- 所有用户发表的博文 -->
        <el-main>
          <el-card class="box-card" v-for="(item, index) in allBlogs" :key="index">
            <img :src="item.imagesUrl" width="100px" height="100px" alt />
            <div class="context">
              <h4>{{item.blogTitle}}</h4>
              <p>{{item.blogText}}</p>
              <span style="fontSize:12px; 
            position:absolute;bottom: 10px;right: 10px;">
                作者用户名：{{item.name}}
                <br />
                发表时间:{{item.createdDate}}
              </span>
            </div>
            <div class="operate">
              <el-button
                type="primary"
                icon="el-icon-edit"
                style=" backgroundColor:#409EFF;color:#fff"
                :plain="true"
                @click="toComment(item.id)"
              >评论</el-button>
            </div>
          </el-card>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { request } from '../api/request';
export default {
  name: 'mains',
  data() {
    return {
      //用户已登陆则不显示登陆
      username: null,
      flag: true,
      value: new Date(),
      // 发布当前时间
      currentDate: new Date(),
      allBlogs: []
    };
  },
  created() {
    //判断数据是否为空，为空则显示登陆注册
    if (this.$store.state.roleInfo == null) {
      this.flag = true;
    } else {
      this.username = this.$store.state.roleInfo.name;
      this.flag = false;
    }
  },
  mounted() {
    request({
      url: '/blog/findAll',
      method: 'post',
      params: {},
      timeout: 3000
    }).then(res => {
      if (res.data != null) {
        this.$store.state.allBlogs = res.data;
        //将数据覆盖原先数据
        this.allBlogs = this.$store.state.allBlogs;
      } else {
        console.log('请求失败！');
      }
    });
  },
  methods: {
    //用户登录时执行的返回主页面方法!
    toBackper() {
      this.$router.push('/users/homePage');
    },
    //跳转登录界面
    toLogin() {
      this.$router.push('/login');
    },
    toRegister() {
      this.$router.push('/register');
    },
    //跳转到详细页面
    toComment(id) {
      if (this.username == null) {
        this.$message.error('请先登录，才能评论哦！');
      } else {
        this.$router.push({ name: 'comment', query: { id: id } });
      }
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
.el-header > .logo {
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
  #rightUser {
    font-size: 16px;
    margin-right: 26px;
    letter-spacing: 2px;
  }
  //按钮
  .el-row {
    float: right;
    font-size: 10px;
    .el-button {
      margin-right: 18px;
    }
  }
}
body > .el-container {
  margin-bottom: 40px;
}
.el-aside {
  background-color: rgba(209, 219, 229, 0);
  color: #333;
  text-align: center;
  padding-bottom: 60px;
  overflow: hidden;
  position: relative;
  .el-calendar {
    position: absolute;
    top: 20px;
    left: 20px;
    border: 6px #131414 solid;
    border-radius: 15px;
  }
}
.el-main::-webkit-scrollbar {
  display: none;
}
.el-main {
  background-color: rgba(255, 255, 255, 0);
  color: #333;
  text-align: center;
  padding-left: 50px;
  padding-top: 40px;
  .box-card {
    width: 900px;
    height: 200px;
    border: 6px #131414 solid;
    border-radius: 15px;
    box-shadow: #131414 2px 2px 2px 2px;
    background: rgba(255, 255, 255, 0.85);
    position: relative;
    margin-bottom: 40px;
    img {
      position: absolute;
      top: 50%;
      transform: translateY(-50%);
      left: 20px;
    }
    .context {
      width: 750px;
      position: absolute;
      right: 20px;
      bottom: 50px;
      height: 120px;
      overflow: hidden;
      text-align: left;
      h4 {
        margin-bottom: 0px;
        padding: 0;
      }
      p {
        margin-top: 6px;
        font-size: 13px;
      }
    }
    .operate {
      position: absolute;
      right: 20px;
      bottom: 5px;

      .el-button {
        font-size: 14px;
        height: 39px;
      }
    }
  }
}
</style>
