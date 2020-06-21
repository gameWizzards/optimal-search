import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false

new Vue({
    performance: process.env.NODE_ENV !== 'production',
    router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
