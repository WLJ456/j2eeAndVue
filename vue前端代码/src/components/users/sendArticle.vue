<template>
  <div>
    <homePage />

    <div id="sendArticle">
      <!-- 发表博文 -->

      <el-form
        ref="form"
        :rules="Rulesform"
        :model="form"
        label-width="85px"
        @submit.native.prevent
      >
        <el-form-item label="用户账号:">
          <el-input v-model="form.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="标题:" prop="blogTitle">
          <el-input v-model="form.blogTitle"></el-input>
        </el-form-item>

        <el-form-item label="文章头像:">
          <!-- 上传文章头像 -->

          <el-upload
            class="upload-demo"
            action="/api/user/upload"
            :on-remove="handleRemove"
            :file-list="fileList"
            :on-success="handleSuccess"
            :auto-upload="flag"
            list-type="picture"
            :limit="limNum"
            ref="upload"
            name="upload"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传一张jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>

        <el-form-item label="内容：" prop="blogText">
          <el-input type="textarea" v-model="form.blogText"></el-input>
        </el-form-item>

        <!--发表按钮 -->
        <el-form-item>
          <el-button
            type="primary"
            style="backgroundColor: #409eff;color:#fff"
            :plain="true"
            @click="toSubmit"
          >发表</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import homePage from './homePage';
import { request } from '../../api/request';
export default {
  name: 'sendAtricle',
  data() {
    return {
      Rulesform: {
        blogTitle: [
          { required: true, message: '标题不能为空' },
          { max: 50, min: 1, message: '标题最多50个字' }
        ],
        blogText: [{ required: true, message: '内容不能为空' }]
      },
      msg:null,
      flag: true, //是否自动上传flag
      limNum: 1,
      fileList: [],
      form: {
        blogTitle: '',
        name: this.$store.state.roleInfo.name,
        blogText: '',
        imagesUrl: null
      }
    };
  },
  components: {
    homePage
  },
  methods: {
   
    handleSuccess(response, file, fileList) {
      //将路径存入form表单
      //自行拼接以解决乱码问题
      this.msg=response + file.name;
      this.form.imagesUrl = 'http://49.233.69.229:8080/' + response + file.name;
    },
    //上传发表
    toSubmit() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          console.log(valid);
        } else {
          if (this.form.imagesUrl != null) {
            //执行上传函数
            this.sendFrom();
          } else {
            this.$message.error('发表失败，请上传图片！');
          }
        }
      });
    },
    //上传表单
    sendFrom() {
      request({
        url: '/blog/addBlog',
        method: 'post',
        params: {
          blogTitle: this.form.blogTitle,
          name: this.form.name,
          blogText: this.form.blogText,
          imagesUrl: this.form.imagesUrl
        },
      }).then(res => {
        if (res.data == true) {
          this.$message({
            message: '发表成功',
            type: 'success'
          });
          this.$router.push('/users/showArticle');
        } else {
          this.$message.error('上传失败！');
        }
      });
    },
    //上传时删除时的函数
    handleRemove(file, fileList) {
      console.log('上传删除时');
      console.log(file)
      console.log(this.form.imagesUrl);
      if (this.form.imagesUrl !== null) {
        request({
          url: '/user/delImg',
          method: 'post',
          params: {
            imgUrl: this.msg
          },
        }).then(res => {
          console.log(res.data);
        });
      } else {
        alert('取消上传失败！');
      }
    }
  }
};
</script>

<style scoped  lang="less">
#sendArticle {
  width: 1000px;
  position: absolute;
  background-color: #e9e9e9;
  border: 10px #aaccdc solid;
  border-radius: 15px;
  top: 125px;
  left: 400px;
  z-index: 1;
  .el-form {
    width: 600px;
    margin: 70px auto;
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
