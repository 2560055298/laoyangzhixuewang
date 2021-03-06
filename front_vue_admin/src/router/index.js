import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/teacher',
    component: Layout,
    redirect: '/teahcer/table',
    name: '讲师管理',
    meta: { title: '讲师管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: '讲师列表',
        component: () => import('@/views/teacher/edu/list'),
        meta: { title: '讲师列表', icon: 'table' }
      },
      {
        path: 'save',
        name: '添加讲师',
        component: () => import('@/views/teacher/edu/save'),
        meta: { title: '添加讲师', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: '修改讲师',
        component: () => import('@/views/teacher/edu/save'),
        meta: { title: '修改讲师', icon: 'tree' },
        hidden:true
      }
    ]
  },

  {
    path: '/subject',
    component: Layout,
    redirect: '/subject/table',
    name: '分类管理',
    meta: { title: '分类管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: '显示课程分类',
        component: () => import('@/views/subject/edu/list'),
        meta: { title: '显示课程分类', icon: 'table' }
      },
      {
        path: 'save',
        name: '导入课程分类',
        component: () => import('@/views/subject/edu/save'),
        meta: { title: '导入课程分类', icon: 'tree' }
      },
    ]
  },

  {
    path: '/course',
    component: Layout,
    redirect: '/course/list',
    name: '课程管理',
    meta: {
      title: '课程管理',
      icon: 'example'
    },
    children: [{
        path: 'list',
        name: '课程列表',
        component: () => import('@/views/course/edu/list'),
        meta: {
          title: '课程列表',
          icon: 'table'
        }
      },
      {
        path: 'info',
        name: '添加课程',
        component: () => import('@/views/course/edu/info'),
        meta: {
          title: '添加课程',
          icon: 'tree'
        }
      },
      {
        path: 'info/:id',
        name: 'EduCourseInfoEdit',
        component: () => import('@/views/course/edu/info'),
        meta: {
          title: '编辑课程基本信息',
          noCache: true
        },
        hidden: true
      },
      {
        path: 'chapter/:id',
        name: 'EduCourseChapterEdit',
        component: () => import('@/views/course/edu/chapter'),
        meta: {
          title: '编辑课程大纲',
          noCache: true
        },
        hidden: true
      },
      {
        path: 'publish/:id',
        name: 'EduCoursePublishEdit',
        component: () => import('@/views/course/edu/publish'),
        meta: {
          title: '发布课程',
          noCache: true
        },
        hidden: true
      }
    ]
  },

  {
    path: '/statistics/daily',
    component: Layout,
    redirect: '/statistics/daily',
    name: '',
    meta: { title: '统计分析', icon: 'example' },
    children: [
      {
        path: 'create',
        name: '生成统计',
        component: () => import('@/views/statistics/daily/create'),
        meta: { title: '生成统计', icon: 'table' }
      },
      {
        path: 'chart',
        name: '统计图表',
        component: () => import('@/views/statistics/daily/chart'),
        meta: { title: '统计图表', icon: 'tree' }
      },
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  base:'/new/',
  routes: constantRouterMap
})
