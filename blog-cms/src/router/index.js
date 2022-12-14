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
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/article/list',
    name: 'article',
    meta: { title: '博客管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: '/article/list',
        name: 'articleList',
        component: () => import('@/views/article/ArticleList'),
        meta: { title: '文章管理', icon: 'el-icon-s-order' }
      },
      {
        path: '/article/add',
        name: 'articleAdd',
        component: () => import('@/views/article/ArticleAdd'),
        meta: { title: '写文章', icon: 'el-icon-edit' }
      },
      {
        path: '/category/add',
        name: 'categoryAdd',
        component: () => import('@/views/category/Add'),
        meta: { title: '添加分类', icon: 'el-icon-edit' }
      },
      {
        path: '/category/list',
        name: 'categoryList',
        component: () => import('@/views/category/Index'),
        meta: { title: '分类管理', icon: 'el-icon-menu' }
      },
      {
        path: '/tag/list',
        name: 'tagList',
        component: () => import('@/views/tag/Index'),
        meta: { title: '标签管理', icon: 'el-icon-price-tag' }
      },
      {
        path: '/comment/list',
        name: 'commentList',
        component: () => import('@/views/comment/Index'),
        meta: { title: '评论管理', icon: 'el-icon-chat-dot-square' }
      },
      {
        path: '/article/recycle',
        name: 'articleRecycle',
        component: () => import('@/views/article/RemoveList'),
        meta: { title: '回收站', icon: 'el-icon-delete' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
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
