import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
let roleInfo = JSON.parse(sessionStorage.getItem('roleInfo') || null);
export default new Vuex.Store({
  state: {
    roleInfo: roleInfo,
    allBlogs: [],
    blogbyName: [],
    comments: []
  },
  mutations: {
    //返回登陆页面就清空用户信息
    removeRole(state) {
      state.roleInfo = null;
      sessionStorage.removeItem('roleInfo')
    },
    //修改roleInfo数据
    upRoleInfo(state, form) {
      state.roleInfo = form;
      //将信息存入session中
      sessionStorage.setItem('roleInfo', JSON.stringify(form));
    },
    //通过id删除博文
    delBlogById(state, id) {
      //删除网站首页的blog
      state.allBlogs.some((item, i) => {
        if (item.id == id) {
          state.allBlogs.splice(i, 1);
          return true;
        }
      })
      //删除个人微博
      state.blogbyName.some((item, i) => {
        if (item.id == id) {
          state.blogbyName.splice(i, 1);
          return true;
        }
      })
    },

    //发表评论
    addComment(state, from) {
      
      state.comments.push(from)
    }
  },
  getters: {

  },
  actions: {
  },
  modules: {
  }
})
