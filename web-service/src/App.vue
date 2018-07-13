<template>
  <div id="app">
    <router-link to="/">[HOME]</router-link>
    <router-link to="/cors">[CORS test client]</router-link>
    <router-link to="/chat">[WebSocket test client]</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

#app a {
  color: darkgray;
  text-decoration: none;
}

#app div {
  margin-top: 30px;
}
</style>