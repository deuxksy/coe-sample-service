import Vue from 'vue'
import VueRouter from 'vue-router'
import cors from '../cors/cors.vue'
import chat from '../chat/chat.vue'
import room from '../chat/room.vue'

Vue.use(VueRouter)

const Home = { template: '<div>Home</div>'}
const NotFound = { template: '<div>Not Found</div>'}

const router = new VueRouter({
    mode: 'history',
    routes: [
        {path: '/', component: Home},
        {path: '/cors', component: cors},
        {
            path: '/chat', 
            component: chat,
            children: [
                {path: ':id', component: room}
            ]
        },
        {path: '/*', component: NotFound},
    ]
})

export default router