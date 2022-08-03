import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { Quasar } from 'quasar'
// import quasarUserOptions from 
const quasarUserOptions = require('./quasar-user-options.js')

createApp(App).use(Quasar, quasarUserOptions).use(router).mount("#app");
