<template>
  <div>
    <homePage />
    <div id="showArticle">
      <!-- 查看自己博文数据 -->
      <el-card class="box-card" v-for="(item, index) in blogbyName" :key="index">
        <img :src="item.imagesUrl" width="100px" height="100px" alt />
        <div class="context">
          <h4>{{item.blogTitle}}</h4>
          <p>{{item.blogText}}</p>
          <span
            style="fontSize:12px; 
            position:absolute;bottom: 10px;right: 10px;"
          >发表时间：{{item.createdDate}}</span>
        </div>
        <div class="operate">
          <el-button type="primary" icon="el-icon-view" @click="toComment(item.id)">查看</el-button>
          <el-button
            type="danger"
            style=" backgroundColor:#F56C6C;color:#fff"
            :plain="true"
            icon="el-icon-delete"
            @click="delBlog(item.id)"
          >删除</el-button>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import homePage from './homePage';
import { request } from '../../api/request';
export default {
  name: 'showArticle',
  data() {
    return {
      name: this.$store.state.roleInfo.name,
      blogbyName: this.$store.state.blogbyName
    };
  },
  components: {
    homePage
  },
  //处理请求！
  mounted() {
    request({
      url: '/blog/findBlogByName',
      method: 'post',
      params: {
        name: this.name
      },
      timeout: 3000
    }).then(res => {
      if (res.data != null) {
        this.$store.state.blogbyName = res.data;
        //将数据覆盖原先数据
        this.blogbyName = this.$store.state.blogbyName;
      } else {
        console.log('请求失败！');
      }
    });
  },
  methods: {
    delBlog(id) {
      request({
        url: '/blog/delBlog',
        method: 'post',
        params: {
          id: id
        },
        timeout: 3000
      }).then(res => {
        if (res.data == true) {
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          this.$store.commit('delBlogById', id);
    
        } else {
          this.$message.error('删除失败');
        }
      });
    },
    //跳转到详细页面
    toComment(id) {
      this.$router.push({ name: 'comment', query: { id: id } });
    }
  }
};
</script>

<style scoped lang="less">
#showArticle {
  width: 1000px;
  position: absolute;
  top: 125px;
  left: 400px;
  z-index: 1;
  .box-card {
    width: 900px;
    height: 200px;
    border: 6px #131414 solid;
    border-radius: 15px;
    box-shadow: #131414 2px 2px 2px 2px;
    background: rgba(255, 255, 255, 0.85);
    position: relative;
    margin: 0px auto;
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
