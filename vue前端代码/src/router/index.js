import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)
//解决路由重复点击报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
const routes = [
  { path: '/login', component: () => import('../components/login.vue') },
  { path: '/register', component: () => import('../components/register.vue') },
  { path: '/', redirect: '/mains' },
  {
    path: '/users/homePage', name: 'homePage',
    component: () => import('../components/users/homePage.vue'),
    //配置元数据
    meta: {
      requireAuth: true
    },
  },
  {
    path: '/users/seeInfo', name: 'seeInfo',
    component: () => import('../components/users/seeInfo.vue'),
    meta: {
      requireAuth: true
    },
  },
  {
    path: '/users/updataInfo',
    name: 'updataInfo',
    component: () => import('../components/users/updataInfo.vue'),
    meta: {
      requireAuth: true
    },
  },
  {
    path: '/users/showArticle', name: 'showArticle',
    component: () => import('../components/users/showArticle.vue'),
    meta: {
      requireAuth: true
    },
  },
  {
    path: '/users/sendArticle', name: 'sendArticle',
    component: () => import('../components/users/sendArticle.vue'),
    meta: {
      requireAuth: true
    },
  },
  {
    path: '/mains', name: 'mains',
    component: () => import('../components/mains.vue')
  },
  {
    path: '/NotFound/404', name: 'page404', component: () => import('../components/NotFound/404.vue')
  },
  {
    path: '/users/comment', name: 'comment',
    component: () => import('../components/users/comment.vue'),
    meta: {
      requireAuth: true
    },
  },
  {
    path: '/admin/seeAllBlog', name: 'seeAllBlog',
    component: () => import('../components/admin/seeAllBlog.vue'),
    meta: {
      requireAuth: true
    },
  },
  {
    path: '/admin/seeAllUser', name: 'seeAllUser',
    component: () => import('../components/admin/seeAllUser.vue'),
    meta: {
      requireAuth: true
    },
  },

]
const router = new VueRouter({
  mode: 'history',
  routes
})
// 解决Loading chunk (\d)+ failed问题
router.onError((error) => {
  const pattern = /Loading chunk (\d)+ failed/g;
  const isChunkLoadFailed = error.message.match(pattern);
  if (isChunkLoadFailed) {
    window.location.reload();
    // router.replace(router.history.pending.fullPath);
  } else {
    console.log(error)
  }
});
//前端拦截器,即所有操作需要用户登录
router.beforeEach((to, from, next) => {
  if (to.matched.length === 0) {
    next('/NotFound/404') // 判断此跳转路由的来源路由是否存在，存在的情况跳转到来源路由，否则跳转到404页面
  }
  if (to.meta.requireAuth) {// 判断该路由是否需要登录权限
    if (sessionStorage.getItem('roleInfo')) {
      console.log("用户已登录")
      next()
    } else {
      alert("请先登录哟！")
      next('/Login')
    }
  }
  else {
    next()
  }

})
export default router
