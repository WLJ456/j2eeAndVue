<template>
  <div>
    <homePage />
    <div id="commentShow">
      <el-card class="box-card">
        <img :src="blogitem.imagesUrl" width="1000px" height="300px" alt />
        <div class="context">
          <h4>
            {{blogitem.blogTitle}}
            <hr />
          </h4>
          <p>{{blogitem.blogText}}</p>
          <span style="fontSize:12px; 
            position:absolute;bottom: 10px;right: 10px;">
            作者用户名：{{blogitem.name}}
            <br />
            发表时间:{{blogitem.createdDate}}
          </span>
        </div>
        <div class="comment">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <el-input v-model="CommentForm.input" clearable placeholder="请输入内容，欢迎评论！"></el-input>
              <el-button
                style="float: right; padding: 3px 5px ;marginTop:6px;backgroundColor: #409eff;color:#fff"
                type="primary"
                :plain="true"
                @click="addComment"
              >发表</el-button>
            </div>
            <!-- 评论列表 -->
            <div v-for="(item, index) in commentList" :key="index" class="text item">
              {{item.context}}
              <span
                style="float: right; fontSize:10px;marginTop:6px;"
              >评论人:{{item.name}} 评论时间：{{item.sendDate}}</span>
            </div>
          </el-card>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import homePage from './homePage';
import { request } from '../../api/request';
export default {
  name: 'comment',
  data() {
    return {
      id: this.$route.query.id,
      blogitem: './../../assets/bgc.jpg', //默认图片
      CommentForm: {
        name: this.$store.state.roleInfo.name, //评论用户
        input: '', //评论内容
        id: this.$route.query.id, //博客id
        sendDate: new Date()
      },
      commentList: this.$store.state.comments
    };
  },
  components: {
    homePage
  },
  mounted() {
    request({
      url: '/blog/findBlogById',
      method: 'post',
      params: {
        id: this.id
      },
      timeout: 3000
    }).then(res => {
      if (res.data != null) {
        this.blogitem = res.data;
      } else {
        console.log('请求失败！');
      }
    });

    request({
      url: '/comment/findCommentById',
      method: 'post',
      params: {
        id: this.id
      },
      timeout: 3000
    }).then(res => {
      if (res.data != null) {
        this.$store.state.comments = res.data;
        this.commentList = res.data;
      } else {
        console.log('请求失败！');
      }
    });
  },
  methods: {
    //发表评论
    addComment() {
      if (this.CommentForm.input == '') {
        console.log('表单内容为空');
        this.$message.error('评论内容不能为空！');
      } else {
        request({
          url: '/comment/addComment',
          method: 'post',
          params: {
            bid: this.id,
            name: this.CommentForm.name,
            context: this.CommentForm.input
          },
          timeout: 3000
        }).then(res => {
          if (res.data !== null) {
            console.log(res.data);
            this.$message({
              message: '发表成功',
              type: 'success'
            });
            console.log(this.$store.state.comments);
            this.$store.commit('addComment', res.data);
            console.log(this.$store.state.comments);
          } else {
            alert('请求失败！');
          }
        });
      }
    }
  }
};
</script>

<style scoped  lang="less">
#commentShow {
  width: 1000px;
  position: absolute;
  top: 125px;
  left: 400px;
  z-index: 1;
  .box-card {
    width: 900px;
    border-radius: 15px;
    position: relative;
    margin: 0px auto;
    margin-bottom: 40px;
    img {
      position: absolute;
      top: 0px;
      left: 0px;
    }
    .context {
      width: 100%;
      position: absolute;
      left: 0;
      top: 320px;
      height: 200px;
      text-align: center;
      h4 {
        margin-bottom: 0px;
        padding: 0;
      }
      p {
        text-align: left;
        margin-top: 6px;
        font-size: 13px;
      }
    }
    .comment {
      width: 100%;
      margin-top: 530px;
      height: 350px;
      text-align: left;
      z-index: 99;
      overflow-x: hidden;
      overflow-y: auto;
      .text {
        font-size: 14px;
      }

      .item {
        margin-bottom: 18px;
      }

      .clearfix:before,
      .clearfix:after {
        display: table;
        content: '';
      }
      .clearfix:after {
        clear: both;
      }

      .box-card {
        width: 100%;
      }
    }
  }
}
</style>
