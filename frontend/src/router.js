import Vue from 'vue'
import Router from 'vue-router'
import Greeting from '@/components/Greeting'
import NotFound from "./components/NotFound";

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
      {
        path: '/',
        name: 'Greeting',
        component: Greeting
      },
      {
        path: '*',
        name: 'NotFound',
        component: NotFound
      }
    ]
})