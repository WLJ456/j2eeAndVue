import Vue from 'vue'
import App from './App.vue'
import './assets/css/global.css'

// import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// Vue.use(ElementUI)
import {
  Button, Switch, Form, FormItem, Menu, Submenu, MenuItem, MenuItemGroup, Row, Col,
  Input, Icon, Alert, Container, Header, Main, Aside, Footer, Tooltip, Calendar, Card,
  Option, Radio, Select, RadioGroup, Checkbox, CheckboxGroup, Upload, Dialog, Message, Table, TableColumn
} from 'element-ui'
import 'element-ui/lib/theme-chalk/base.css';
import CollapseTransition from 'element-ui/lib/transitions/collapse-transition';
Vue.component(CollapseTransition.name, CollapseTransition)
Vue.use(TableColumn)
Vue.use(Table)
Vue.component(Message.name, Message)
Vue.prototype.$message = Message;//需要单独挂载
Vue.use(Dialog)
Vue.use(Checkbox)
Vue.use(CheckboxGroup)
Vue.use(Upload)
Vue.use(RadioGroup)
Vue.use(Select)
Vue.use(Radio)
Vue.use(Option)
Vue.use(Card)
Vue.use(Calendar)
Vue.use(Row);
Vue.use(Col);
Vue.use(Button)
Vue.use(Menu)
Vue.use(Aside)
Vue.use(Footer)
Vue.use(Main)
Vue.use(Switch)
Vue.use(Header)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Icon)
Vue.use(Alert)
Vue.use(Container)
Vue.use(Tooltip)
Vue.use(Submenu);
Vue.use(MenuItem);
Vue.use(MenuItemGroup);
Vue.config.productionTip = false
import router from './router'
import store from './store'
// 图形验证工具
import SlideVerify from 'vue-monoplasty-slide-verify';
Vue.use(SlideVerify);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
