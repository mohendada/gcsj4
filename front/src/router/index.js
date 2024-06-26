import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  }
  // 404 page must be placed at the end !!!
  // { path: '*', redirect: '/404', hidden: true }
]
export const asyncRoutes = [
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/user',
    name: 'sysManage',
    meta: { title: '请假管理', icon: 'el-icon-s-help', roles: ['manager'] },
    alwaysShow: true,
    children: [
      {
        path: 'leavemenage',
        name: 'leavemenage',
        component: () => import('@/views/sys/leavemenage'),
        meta: { title: '请假管理', icon: 'userManage' }
      },
    ]
  },


  {
    path: '/ck',
    component: Layout,
    redirect: '/sys/user',
    name: 'sysManage',
    meta: { title: '仓库管理', icon: 'el-icon-s-help', roles: ['manager'] },
    alwaysShow: true,
    children: [
      {
        path: 'AdminManage',
        name: 'AdminManage',
        component: () => import('@/views/supplier/SupplierManage.vue'),
        meta: { title: '供货商管理', icon: 'Supplier' }
      },
      {
        path: 'StorageManage',
        name: 'StorageManage',
        component: () => import('@/views/storage/StorageManage.vue'),
        meta: { title: '仓库名管理', icon: 'StorageManage' }
      },
      {
        path: 'GoodstypeManage',
        name: 'GoodstypeManage',
        component: () => import('@/views/goodstype/GoodstypeManage.vue'),
        meta: { title: '物品类型管理', icon: 'GoodstypeManage' }
      },
      {
        path: 'GoodsManage',
        name: 'GoodsManage',
        component: () => import('@/views/store/GoodsManage.vue'),
        meta: { title: '物品管理', icon: 'GoodsManage' }
      },
      {
        path: 'RecordManage',
        name: 'RecordManage',
        component: () => import('@/views/record/RecordManage.vue'),
        meta: { title: '记录管理', icon: 'RecordManage' }
      },
    ]
  },
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/user',
    name: 'sysManage',
    meta: { title: '请假申请', icon: 'el-icon-s-help', roles: ['employee'] },
    alwaysShow: true,
    children: [
      {
        path: 'leave',
        name: 'leave',
        component: () => import('@/views/sys/leave'),
        meta: { title: '请假申请', icon: 'userManage' }
      }
    ]
  },
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/goods',
    name: 'goodManage',
    meta: {title: '商品管理',icon: 'el-icon-s-help',roles: ['employee']},
    alwaysShow: true,
    children: [
      {
        path: 'goodsList',
        name: 'goodsList',
        component:()=> import('@/views/sys/goods'),
        meta: {title: "商品浏览",icon: 'userManage'}
      }
    ]
  },
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/order',
    name: 'orderManage',
    meta: {title: '订单管理', icon: 'el-icon-s-help', roles: ['employee']},
    alwaysShow: true,
    children: [
      {
        path: 'orderList',
        name: 'orderList',
        component: () => import('@/views/sys/order.vue'),
        meta: {title: "订单浏览", icon: 'orderManage'}
      }
    ]
  },
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/refound',
    name: 'refoundManage',
    meta: {title: '退货管理', icon: 'el-icon-s-help', roles: ['employee']},
    alwaysShow: true,
    children: [
      {
        path: 'refoundList',
        name: 'refoundList',
        component: () => import('@/views/sys/refound.vue'),
        meta: {title: "退货申请浏览", icon: 'refoundManage'}
      }
    ]
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
